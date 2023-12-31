package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MysqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//http://localhost:8080/web-app-23544/api/orador/nuevo
@WebServlet("/api/orador")
public class NuevoOradorController extends HttpServlet{
	
	private OradorRepository repo = new MysqlOradorRepository();
	
	//Crear?? GET O POST?
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//Obtengo el json desde el frontend
		String jsonOrador = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));//spring
		
		//Convertimos de json String a Objeto java usando libreria jackson2
		//Hacemos un ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		
		//Estas 2 lineas nos permite convertir las respuestas de LocalDate
		//Y permitir la escritura de TIMESTAMPS
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		//Esto finalmente nos convierte el Json a Objeto
		OradorRequest oR = mapper.readValue(jsonOrador, OradorRequest.class);
		
		
		//Creamos el orador con los parametros nuevos
		Orador orador = new Orador(
				oR.getNombre(), 
				oR.getApellido(), 
				oR.getMail(), 
				oR.getTema(), 
				LocalDate.now());
				
		//Ahora por medio del repository guardamos en la db
		OradorRepository repo = new MysqlOradorRepository();
		this.repo.save(orador);
		
		
		//Convierto ahora Objeto java a String/Json
		//Enviar por medio de response al front
		String jsonParaEnviarAlFrontend = mapper.writeValueAsString(orador);
		response.getWriter().print(jsonParaEnviarAlFrontend);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Orador> listado = this.repo.findAll();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		String jsonParaEnviarAlFrontend = mapper.writeValueAsString(listado);
		response.getWriter().print(jsonParaEnviarAlFrontend);
		
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		this.repo.delete(Long.parseLong(id));
		
		response.setStatus(HttpServletResponse.SC_OK); //STATUS 200
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		String jsonOrador = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));//spring
		
		//Convertimos de json String a Objeto java usando libreria jackson2
		//Hacemos un ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		
		//Estas 2 lineas nos permite convertir las respuestas de LocalDate
		//Y permitir la escritura de TIMESTAMPS
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		//Esto finalmente nos convierte el Json a Objeto
		OradorRequest oR = mapper.readValue(jsonOrador, OradorRequest.class);
		
		//busco el orador en la db
		Orador orador = this.repo.getById(Long.parseLong(id));
		
		//ahora actualizo los datos
		orador.setNombre(oR.getNombre());
		orador.setApellido(oR.getApellido());
		orador.setMail(oR.getMail());
		orador.setTema(oR.getTema());
		
		//ahora actualizo en la db
		this.repo.update(orador);
		
		//informo al front el ok
		response.setStatus(HttpServletResponse.SC_OK); // STATUS 200
	}

}

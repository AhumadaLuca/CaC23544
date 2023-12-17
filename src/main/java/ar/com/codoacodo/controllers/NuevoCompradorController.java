package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.codoacodo.entity.Comprador;
import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MysqlCompradorRepository;
import ar.com.codoacodo.repository.CompradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//http://localhost:8080/web-app-23544/api/orador/nuevo
@WebServlet("/api/comprador")
public class NuevoCompradorController extends AppBaseServlet{
	
	private CompradorRepository repo = new MysqlCompradorRepository();
	
	//Crear?? GET O POST?
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//Obtengo el json desde el frontend
		String jsonComprador = super.toJson(request);
		
		//Esto finalmente nos convierte el Json a Objeto
		CompradorRequest oR = mapper.readValue(jsonComprador, CompradorRequest.class);
		
		
		//Creamos el orador con los parametros nuevos
		Comprador comprador = new Comprador(
				oR.getNombre(),
				oR.getApellido(),
				oR.getEmail(),
				oR.getCantidad(),
				oR.getCategoria(),
				oR.getPrecioTotal(),
				LocalDate.now());
				
				
		//Ahora por medio del repository guardamos en la db
		this.repo.save(comprador);
		
		
		//Convierto ahora Objeto java a String/Json
		//Enviar por medio de response al front
		String jsonParaEnviarAlFrontend = mapper.writeValueAsString(comprador);
		response.getWriter().print(jsonParaEnviarAlFrontend);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Comprador> listado = this.repo.findAll();
		
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
		
		String jsonComprador = super.toJson(request);//spring
		
		
		//Esto finalmente nos convierte el Json a Objeto
		CompradorRequest oR = mapper.readValue(jsonComprador, CompradorRequest.class);
		
		//busco el orador en la db
		Comprador comprador = this.repo.getById(Long.parseLong(id));
		
		//ahora actualizo los datos
		comprador.setNombre(oR.getNombre());
		comprador.setApellido(oR.getApellido());
		comprador.setEmail(oR.getEmail());
		comprador.setCantidad(oR.getCantidad());
		comprador.setCategoria(oR.getCategoria());
		comprador.setPrecioTotal(oR.getPrecioTotal());
		
		//ahora actualizo en la db
		this.repo.update(comprador);
		
		//informo al front el ok
		response.setStatus(HttpServletResponse.SC_OK); // STATUS 200
	}

}


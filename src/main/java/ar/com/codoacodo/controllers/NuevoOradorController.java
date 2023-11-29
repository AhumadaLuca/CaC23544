package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MysqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/orador/nuevo")
public class NuevoOradorController extends HttpServlet{
	
	//Crear?? GET O POST?
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//Capturo los parametros enviados por el front
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String mail = request.getParameter("mail");
		String tema = request.getParameter("tema");
		
		//Creo mi orador con los parametros recibidos
		Orador nuevo = new Orador(nombre, apellido, mail, tema, LocalDate.now());
		
		//Ahora por medio del repository guardamos en la db
		OradorRepository repo = new MysqlOradorRepository();
		
		repo.save(nuevo);
		
		//Ahora respondo al front
		response.getWriter().print("OK"); //JSON		
	}

}

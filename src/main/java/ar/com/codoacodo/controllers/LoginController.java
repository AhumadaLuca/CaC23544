package ar.com.codoacodo.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ar.com.codoacodo.repository.LoginRepository;
import ar.com.codoacodo.repository.MysqlLoginRepository;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//Validaciones
		LoginRepository repo = new MysqlLoginRepository();
		repo.login(username, password);
		
		//Crear el token para enviar al front
		resp.setStatus(HttpServletResponse.SC_CREATED); //201
		
		resp.getWriter().print("");
	}

}

package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public class AppBaseServlet extends HttpServlet {
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	public AppBaseServlet() {
		super(); //En este caso como es el mismo, no hace falta llamar al padre, pero es buena practica
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	public String toJson(HttpServletRequest request) throws IOException {
		String json = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));
		return json;
	}

}

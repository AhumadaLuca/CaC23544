package ar.com.codoacodo;

import java.time.LocalDate;

public class Auto {
	
	/*Atributos*/
	String marca;
	String modelo;
	String color;
	Integer anio;
	Float velocidad;
	Float velocidadMaxima;
	Boolean encendido;
	String dominio;
	LocalDate fechaCreacion;
	
	/*Constructor/es*/
	Auto(String marca, String modelo, String color, Integer anio, String dominio, Float velocidadMaxima){
		velocidad = 0f;
		encendido = Boolean.FALSE;
		fechaCreacion = LocalDate.now();
		
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.anio = anio;
		this.dominio = dominio;
		this.velocidadMaxima = velocidadMaxima;
	}
	
	/*Metodos*/
	void encender() {
		if(!this.encendido) {
			this.encendido = Boolean.TRUE;
			System.out.println("Auto encendido");
		}else {
			System.out.println("Ya está encendido!");
		}
	}
	
	void apagar() {
		if(this.encendido) {
			this.encendido = Boolean.FALSE;
			velocidad = 0f;
			System.out.println("Auto apagado");
		}else {
			System.out.println("Ya está apagado!");
		}
	}
	
	//Asumimos que acelera de a 1
	void acelerar() {
		if(encendido) {
			if(velocidad < velocidadMaxima) {
				velocidad++;
			}else {
				System.out.println("Velocidad máxima: "+ velocidad +" alcanzada");
			}
		}else {
			System.out.println("Primero debe encender el auto para acelerar!");
		}
	}
	
	void frenar() {
		if(encendido) {
			if(velocidad > 0) {
				velocidad --;
			}else {
				System.out.println("La velocidad llego a 0");
			}
		}else {
			System.out.println("Frenando con el auto apagado...");
		}
	}

}

package com.salesianostriana.edu.proyecto;

import com.salesianostriana.edu.proyecto.servicio.AsignaturaServicio;
import com.salesianostriana.edu.proyecto.servicio.CursoServicio;
import com.salesianostriana.edu.proyecto.servicio.HorarioServicio;
import com.salesianostriana.edu.proyecto.servicio.TituloServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Bean
	public CommandLineRunner app(TituloServicio tituloServicio, CursoServicio cursoServicio, AsignaturaServicio asignaturaServicio,
			HorarioServicio horarioServicio){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

			tituloServicio.cargarListado();
			cursoServicio.cargarListado(tituloServicio);
			asignaturaServicio.cargarListado(cursoServicio);
			horarioServicio.cargarListado(asignaturaServicio);



			}
		};
	}



}

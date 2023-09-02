package com.utn.ejer02;

import com.utn.ejer02.Entidades.Domicilio;
import com.utn.ejer02.Entidades.Persona;
import com.utn.ejer02.Repositorios.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ejer02Application {

	@Autowired
	RepositorioPersona personaRepository;
	public static void main(String[] args) {
		SpringApplication.run(Ejer02Application.class, args);
	}

	@Bean
	CommandLineRunner init(RepositorioPersona personaRepo) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");
			Persona persona = Persona.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.edad(30)
					.build();

			Domicilio domicilio = Domicilio.builder()
					.calle("Suipacha")
					.numero(30)
					.build();

			persona.setDomicilio(domicilio);
			personaRepository.save(persona);

			Persona personaRecuperada = personaRepository.findById(persona.getId()).orElse(null);
			if (personaRecuperada != null) {
				System.out.println("Nombre: " + personaRecuperada.getNombre());
				System.out.println("Apellido: " + personaRecuperada.getApellido());
				System.out.println("Edad: " + personaRecuperada.getEdad());
				System.out.println("Calle : " + personaRecuperada.getDomicilio().getCalle());
				System.out.println("Número :" + personaRecuperada.getDomicilio().getNumero());
			}
		};
	}
}






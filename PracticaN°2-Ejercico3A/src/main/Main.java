package main;

import java.time.LocalDate;

import excepcions.FueraTerminoException;
import model.Concurso;
import model.Participante;
import persistence.BaseDatosRegistrarInscripto;
import persistence.ConnectionManager;
import persistence.EnDiscoRegistrarInscripto;
import persistence.PorMailNotificarRegistro;

public class Main {

	public static void main(String[] args) {

		Concurso concursoDeSistemas = new Concurso(1, LocalDate.now().minusDays(2), LocalDate.now().plusDays(8),
				new BaseDatosRegistrarInscripto(ConnectionManager.getProperties()), new PorMailNotificarRegistro());

		Concurso concursoDeMatematicas = new Concurso(2, LocalDate.now(), LocalDate.now().plusDays(8),
				new EnDiscoRegistrarInscripto("C:\\Users\\franm\\OneDrive\\Escritorio\\planilla.txt"),
				new PorMailNotificarRegistro());

		Participante miParticipante = new Participante(42699344, "franmartin765@gmial.com");

		try {
			concursoDeSistemas.inscribirParticipante(miParticipante);
			concursoDeMatematicas.inscribirParticipante(miParticipante);
		} catch (FueraTerminoException e) {

			e.printStackTrace();
		}
	}

}

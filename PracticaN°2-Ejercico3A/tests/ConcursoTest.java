
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import excepcions.FueraTerminoException;
import model.Concurso;
import model.Participante;
import persistence.PorMailNotificarRegistro;

class ConcursoTest {

	@Test
	void testInscribirParticipanteAConcurso() {

		FakeRegistrarInscripto fakeObject = new FakeRegistrarInscripto();

		Concurso primerConcurso = new Concurso(1, LocalDate.now().minusDays(2), LocalDate.now().plusDays(8), fakeObject,
				new PorMailNotificarRegistro());

		Participante miParticipante = new Participante(42699344, "franmartin765@gmial.com");

		try {

			primerConcurso.inscribirParticipante(miParticipante);
			int valorEsparado = 1;

			assertEquals(valorEsparado, primerConcurso.cantidadInscriptos());

		} catch (FueraTerminoException e) {

			System.err.println("Excepcion inscripcion: " + e.getMessage());
		}
	}

	@Test
	void testInscribirParticipantePrimerDia() {

		FakeRegistrarInscripto fakeObject = new FakeRegistrarInscripto();

		Concurso segundoConcurso = new Concurso(2, LocalDate.now(), LocalDate.now().plusDays(8), fakeObject,
				new PorMailNotificarRegistro());

		Participante miParticipante = new Participante(39584452, "franmartin765@gmial.com");

		try {

			segundoConcurso.inscribirParticipante(miParticipante);

			int resultado2 = miParticipante.devolverCantidadDePuntos();
			int puntosEsperados = 10;

			assertEquals(puntosEsperados, resultado2);

		} catch (FueraTerminoException e) {

			System.err.println("Excepcion inscripcion: " + e.getMessage());
		}

	}

	@Test
	void testInscribirParticipanteFueraDeRango() {

		FakeRegistrarInscripto fakeObject = new FakeRegistrarInscripto();

		Concurso tercerConcurso = new Concurso(3, LocalDate.now().minusDays(16), LocalDate.now().minusDays(2),
				fakeObject, new PorMailNotificarRegistro());

		Participante miParticipante = new Participante(42699344, "franmartin765@gmial.com");

		try {

			tercerConcurso.inscribirParticipante(miParticipante);
			int cantidadEsperada = 0;

			assertEquals(cantidadEsperada, tercerConcurso.cantidadInscriptos());

		} catch (FueraTerminoException e) {

			assertEquals(e.getMessage(), "La inscripcion a finalizado.");

			System.err.println("Excepcion inscripcion: " + e.getMessage());
		}
	}

}

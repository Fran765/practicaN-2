
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import excepcions.FueraTerminoException;
import model.Concurso;
import model.Participante;

class ConcursoTest {

	@Test
	void testInscribirParticipanteAConcurso() {

		FakeRegistrarInscripto registroFake = new FakeRegistrarInscripto();

		FakeNotificarRegistro notificacionFake = new FakeNotificarRegistro();

		Concurso primerConcurso = new Concurso(1, LocalDate.now().minusDays(2), LocalDate.now().plusDays(8),
				registroFake, notificacionFake);

		Participante miParticipante = new Participante(42699344, "franmartin765@gmial.com");

		try {

			primerConcurso.inscribirParticipante(miParticipante);
			Integer valorEsparado = 1;

			assertEquals(valorEsparado, primerConcurso.cantidadInscriptos());

			assertEquals(this.textoAValidar(miParticipante, primerConcurso), registroFake.devolverInformacion());

			assertEquals(primerConcurso.devolverTextoDeMensajeExitoso(), notificacionFake.devolverMensajeGuardado());

		} catch (FueraTerminoException e) {

			System.err.println("Excepcion inscripcion: " + e.getMessage());
		}
	}

	@Test
	void testInscribirParticipantePrimerDia() {

		FakeRegistrarInscripto registroFake = new FakeRegistrarInscripto();

		FakeNotificarRegistro notificacionFake = new FakeNotificarRegistro();

		Concurso segundoConcurso = new Concurso(2, LocalDate.now(), LocalDate.now().plusDays(8), registroFake,
				notificacionFake);

		Participante miParticipante = new Participante(39584452, "franmartin765@gmial.com");

		try {

			segundoConcurso.inscribirParticipante(miParticipante);

			Integer resultado2 = miParticipante.devolverCantidadDePuntos();
			Integer puntosEsperados = 10;

			assertEquals(puntosEsperados, resultado2);

			assertEquals(this.textoAValidar(miParticipante, segundoConcurso), registroFake.devolverInformacion());

			assertEquals(segundoConcurso.devolverTextoDeMensajeExitoso(), notificacionFake.devolverMensajeGuardado());

		} catch (FueraTerminoException e) {

			System.err.println("Excepcion inscripcion: " + e.getMessage());
		}

	}

	@Test
	void testInscribirParticipanteFueraDeRango() {

		FakeRegistrarInscripto registroFake = new FakeRegistrarInscripto();

		FakeNotificarRegistro notificacionFake = new FakeNotificarRegistro();

		Concurso tercerConcurso = new Concurso(3, LocalDate.now().minusDays(16), LocalDate.now().minusDays(2),
				registroFake, notificacionFake);

		Participante miParticipante = new Participante(42699344, "franmartin765@gmial.com");

		try {

			tercerConcurso.inscribirParticipante(miParticipante);
			Integer cantidadEsperada = 0;

			assertEquals(cantidadEsperada, tercerConcurso.cantidadInscriptos());

			assertEquals(this.textoAValidar(miParticipante, tercerConcurso), registroFake.devolverInformacion());

			assertEquals(tercerConcurso.devolverTextoDeMensajeExitoso(), notificacionFake.devolverMensajeGuardado());

		} catch (FueraTerminoException e) {

			assertEquals(e.getMessage(), "La inscripcion a finalizado.");

			System.err.println("Excepcion inscripcion: " + e.getMessage());
		}
	}

	private String textoAValidar(Participante inscripto, Concurso elConcurso) {
		return DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now()) + " || "
				+ (elConcurso.devolverIdentificador() + "") + " || " + (inscripto.devolverDni() + "\n");
	}

}

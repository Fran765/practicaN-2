package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import excepcions.FueraTerminoException;

public class Concurso {

	private static String mensajeNotificacionRegistroExitoso = "Se pudo inscribir con exito al concurso.";

	private Integer idConcurso;
	private Set<Participante> participantes;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private RegistrarInscripto registro;
	private NotificarRegistro medioNotificacion;

	public Concurso(Integer idConcurso, LocalDate InicioIncripcion, LocalDate FinInscripcion,
			RegistrarInscripto unRegistro, NotificarRegistro medioNotificacion) {

		if (idConcurso.equals(null) || InicioIncripcion.equals(null) || FinInscripcion.equals(null)
				|| unRegistro.equals(null) || medioNotificacion.equals(null)) {
			throw new RuntimeException("Error: faltan datos al momento de crear el concurso\n");
		}
		if (idConcurso.toString().isEmpty() || (InicioIncripcion.toString().isEmpty())
				|| FinInscripcion.toString().isEmpty()) {
			throw new RuntimeException("Error: hay campos vacios al momento de crear el concurso\n");
		}

		this.idConcurso = idConcurso;
		this.participantes = new HashSet<Participante>();
		this.fechaInicio = InicioIncripcion;
		this.fechaFin = FinInscripcion;
		this.registro = unRegistro;
		this.medioNotificacion = medioNotificacion;
	}

	public void inscribirParticipante(Participante nuevo) throws FueraTerminoException {

		if ((!participantes.contains(nuevo)) && (this.puedeIncribirse())) {

			if (fechaInicio.isEqual(LocalDate.now())) {
				nuevo.sumarPuntos(10);
			}
			this.participantes.add(nuevo);

			this.dejarRegistroDeInscripcion(nuevo);

			this.notificarInscripcion(nuevo);

		} else {
			throw new FueraTerminoException("La inscripcion a finalizado.");
		}
	}

	private void dejarRegistroDeInscripcion(Participante nuevo) {

		String fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());

		String fechaParticipanteCurso = fecha + " || " + (this.idConcurso + "") + " || " + (nuevo.devolverDni() + "\n");

		this.registro.registrar(fechaParticipanteCurso);

	}

	private boolean puedeIncribirse() {
		LocalDate hoy = LocalDate.now();
		return (hoy.isBefore(fechaFin) && hoy.isAfter(fechaInicio.minusDays(1)));

	}

	public Integer cantidadInscriptos() {
		return (participantes.size());
	}

	private void notificarInscripcion(Participante nuevo) {

		this.medioNotificacion.notificar(nuevo.consultarDireccionEmail(), mensajeNotificacionRegistroExitoso);

	}

	public Integer devolverIdentificador() {
		return this.idConcurso;
	}

	public String devolverTextoDeMensajeExitoso() {
		return this.mensajeNotificacionRegistroExitoso;
	}

}

package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import excepcions.FueraTerminoException;
import persistence.BaseDatosRegistrarInscripto;
import persistence.EnDiscoRegistrarInscripto;

public class Concurso {

	private int idConcurso;
	private Set<Participante> participantes;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private RegistrarInscripto registro;
	private NotificarRegistro medioNotificacion;

	public Concurso(int idConcurso, LocalDate InicioIncripcion, LocalDate FinInscripcion, RegistrarInscripto unRegistro,
			NotificarRegistro medioNotificacion) {

		this.idConcurso = idConcurso;
		this.participantes = new HashSet();
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

	private boolean puedeIncribirse() {
		LocalDate hoy = LocalDate.now();
		return (hoy.isBefore(fechaFin) && hoy.isAfter(fechaInicio.minusDays(1)));

	}

	public int cantidadInscriptos() {
		return (participantes.size());
	}

	private void dejarRegistroDeInscripcion(Participante nuevo) {

		String fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());

		String fechaParticipanteCurso = "Fecha: " + fecha + " || " + " id_concurso: " + (this.idConcurso + "") + " || "
				+ " Dni participante: " + (nuevo.devolverDni() + "\n");

		this.registro.registrar(fechaParticipanteCurso);

	}

	private void notificarInscripcion(Participante nuevo) {

		this.medioNotificacion.notificar(nuevo.consultarDireccionEmail(),
				"Se pudo inscribir con exito al concurso: " + this.idConcurso);

	}

}

package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import exceptions.FueraTerminoException;
import persistence.RegistrarParticipanteEnDisco;

public class Concurso {

	private int idConcurso;
	private Set<Participante> participantes;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private RegistrarParticipanteEnDisco planilla;

	public Concurso(int idConcurso, LocalDate InicioIncripcion, LocalDate FinInscripcion,
			RegistrarParticipanteEnDisco planilla) {
		this.idConcurso = idConcurso;
		this.participantes = new HashSet();
		this.fechaInicio = InicioIncripcion;
		this.fechaFin = FinInscripcion;
		this.planilla = planilla;
	}

	public void inscribirParticipante(Participante nuevo) throws FueraTerminoException {

		if ((!participantes.contains(nuevo)) && (this.puedeIncribirse())) {

			if (fechaInicio.isEqual(LocalDate.now())) {
				nuevo.sumarPuntos(10);
			}
			this.participantes.add(nuevo);

			this.inscribirEnPlanilla(nuevo);

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

	private void inscribirEnPlanilla(Participante nuevo) {

		String fecha = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());

		String fechaParticipanteCurso = "Fecha: " + fecha + ", id_concurso: "
				+ (this.idConcurso + "") + ", Dni participante: " + (nuevo.devolverDni() + "\n");

		this.planilla.registrar(fechaParticipanteCurso);

	}

}

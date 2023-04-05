package model;

public class Participante {

	private Integer dni;
	private String correoElectronico;
	private Integer puntos;

	public Participante(Integer dni, String correoElectronico) {

		if (dni.equals(null) || correoElectronico.equals(null)) {
			throw new RuntimeException("Error: faltan datos al momento de crear el participante\n");
		}
		if (dni.toString().isEmpty() || correoElectronico.isEmpty()) {
			throw new RuntimeException("Error: hay campos vacios al momento de crear el participante\n");
		}

		this.dni = dni;
		this.correoElectronico = correoElectronico;
		this.puntos = 0;
	}

	public void sumarPuntos(Integer puntos) {
		this.puntos = this.puntos + puntos;
	}

	public Integer devolverCantidadDePuntos() {
		return puntos;
	}

	public Integer devolverDni() {
		return this.dni;
	}

	public String consultarDireccionEmail() {
		return this.correoElectronico;
	}

}

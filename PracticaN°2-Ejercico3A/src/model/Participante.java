package model;

public class Participante {

	private int dni;
	private String correoElectronico;
	private int puntos;

	public Participante(int dni, String correoElectronico) {
		this.dni = dni;
		this.correoElectronico = correoElectronico;
		this.puntos = 0;
	}

	public void sumarPuntos(int puntos) {
		this.puntos = this.puntos + puntos;
	}

	public int devolverCantidadDePuntos() {
		return puntos;
	}

	public int devolverDni() {
		return this.dni;
	}

	public String consultarDireccionEmail() {
		return this.correoElectronico;
	}

}

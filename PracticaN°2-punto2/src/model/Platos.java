package model;

public class Platos {

	private String nombre;
	private float precio;

	public Platos(String nombre, float precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public float devolverPrecio() {
		return precio;
	}

}

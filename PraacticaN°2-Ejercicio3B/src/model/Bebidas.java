package model;

public class Bebidas {

	private String nombre;
	private float precio;

	public Bebidas(String nombre, float precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public float devolverPrecio() {
		return precio;
	}
}

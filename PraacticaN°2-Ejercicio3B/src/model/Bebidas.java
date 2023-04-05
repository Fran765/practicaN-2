package model;

public class Bebidas {

	private String nombre;
	private float precio;

	public Bebidas(String nombre, float precio) {
		
		if(nombre.equals(null) || nombre.isEmpty()) {
			throw new RuntimeException("Error: faltan datos o hay campos vacios al momento de crear la bebidas\n");
		}
		this.nombre = nombre;
		this.precio = precio;
	}

	public float devolverPrecio() {
		return precio;
	}
}

package model;

public class Platos {

	private String nombre;
	private float precio;

	public Platos(String nombre, float precio) {
		
		if(nombre.equals(null) || nombre.isEmpty()) {
			throw new RuntimeException("Error: faltan datos o hay campos vacios al momento de crear el plato.\n");
		}
		this.nombre = nombre;
		this.precio = precio;
	}

	public float devolverPrecio() {
		return precio;
	}

}

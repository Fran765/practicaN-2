package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private List<Bebidas> bebida;
	private List<Platos> plato;

	public Pedido() {
		this.bebida = new ArrayList<Bebidas>();
		this.plato = new ArrayList<Platos>();
	}

	public void agregarPlato(Platos unPlato) {
		this.plato.add(unPlato);
	}

	public void agregarBebida(Bebidas unaBebida) {
		this.bebida.add(unaBebida);
	}

	public Double precioTotalBebidas() {
		Double total = 0.0;
		for (Bebidas unaBebida : bebida) {
			total = total + unaBebida.devolverPrecio();
		}
		return total;
	}

	public Double precioTotalPlatos() {
		Double total = 0.0;
		for (Platos unPlato : plato) {
			total = total + unPlato.devolverPrecio();
		}
		return total;
	}

	public Double precioTotal() {
		return (this.precioTotalPlatos() + this.precioTotalBebidas());
	}

}

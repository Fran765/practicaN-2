package model;

public class Viedma extends Tarjeta {

	private int descuento;

	public Viedma(int saldoMin, Double saldo, int nroTarjeta, int descuento) {
		super(saldoMin, saldo, nroTarjeta);
		this.descuento = descuento;
	}

	@Override
	public Double realizarDescuentos(Pedido unPedido) {

		return (unPedido.precioTotal());

	}

}

package model;

public class ComarcaPlus extends Tarjeta {

	private int descuento;

	public ComarcaPlus(int saldoMin, Double saldo, int nroTarjeta, int descuento) {
		super(saldoMin, saldo, nroTarjeta);
		this.descuento = descuento;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double realizarDescuentos(Pedido unPedido) {

		return (descuento(unPedido.precioTotal(), descuento));

	}

}

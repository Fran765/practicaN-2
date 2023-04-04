package model;

import excepcions.SaldoException;

public abstract class Tarjeta {

	protected Double saldo;
	protected int saldoMin;
	protected int nroTarjeta;

	public Tarjeta(int saldoMin, Double saldo, int nroTarjeta) {

		this.saldo = saldo;
		this.saldoMin = saldoMin;
		this.nroTarjeta = nroTarjeta;
	}

	public abstract Double realizarDescuentos(Pedido unPedido);

	protected void efectuarPago(Double precioFinal) throws SaldoException {

		if (this.saldo - precioFinal < this.saldoMin) {
			this.saldo = this.saldo - precioFinal;

		} else {

			throw new SaldoException("Fondos negativos alcanzados.");
		}
	}

	protected Double descuento(Double precio, int descuento) {
		return (precio - ((descuento * precio) / 100));
	}

}

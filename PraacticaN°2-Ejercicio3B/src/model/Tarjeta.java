package model;

import excepcions.SaldoException;

public abstract class Tarjeta {

	protected Double saldo;
	protected Integer saldoMinimo;
	protected Integer nroTarjeta;

	public Tarjeta(Integer saldoMinimo, Double saldo, Integer nroTarjeta) {

		if (saldoMinimo.equals(null) || saldo.equals(null) || nroTarjeta.equals(null)) {
			throw new RuntimeException("Error: faltan datos al momento de crear la tarjeta.\n");
		}
		if (saldoMinimo.toString().isEmpty() || saldo.toString().isEmpty() || nroTarjeta.toString().isEmpty()) {
			throw new RuntimeException("Error: hay campos vacios al momento de crear la tarjeta.\n");
		}

		this.saldo = saldo;
		this.saldoMinimo = saldoMinimo;
		this.nroTarjeta = nroTarjeta;
	}

	public abstract Double realizarDescuentos(Pedido unPedido);

	protected void efectuarPago(Double precioFinal) throws SaldoException {

		if (this.saldo - precioFinal < this.saldoMinimo) {
			this.saldo = this.saldo - precioFinal;

		} else {

			throw new SaldoException("Fondos negativos alcanzados.");
		}
	}

	protected Double descuento(Double precio, Integer descuento) {
		return (precio - ((descuento * precio) / 100));
	}

}

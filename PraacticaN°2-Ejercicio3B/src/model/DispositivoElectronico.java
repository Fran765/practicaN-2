package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import excepcions.SaldoException;
import persistence.EnDiscoRegistroComida;

public class DispositivoElectronico {

	private Pedido miPedido;
	private Double importeAbonar;
	private RegistroComida registro;

	public DispositivoElectronico(RegistroComida registro) {

		if (registro.equals(null) || registro.toString().isEmpty()) {
			throw new RuntimeException("Error: faltan datos o hay campos vacios al momento de crear el Dispositivo\n");
		}
		this.registro = registro;
		this.importeAbonar = 0.0;
	}

	public void tomarPedido(Pedido nuevoPedido) {
		this.miPedido = nuevoPedido;
	}

	public void realizarPago(Tarjeta tarjetaDada, int propina) throws SaldoException {

		this.importeAbonar = sumarPropina(tarjetaDada.realizarDescuentos(miPedido), propina);
		tarjetaDada.efectuarPago(importeAbonar);

		this.dejarRegistro(importeAbonar);
	}

	private Double sumarPropina(Double totalPedido, int propina) {
		return (((propina * totalPedido) / 100) + (totalPedido));
	}

	private void dejarRegistro(Double monto) {

		String fechaHoraImporte = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now()) + " || " + monto;

		this.registro.registrarAlmuerzoCena(fechaHoraImporte);

	}

	public Double devolverImporteAPagar() {
		return (this.importeAbonar);
	}

}

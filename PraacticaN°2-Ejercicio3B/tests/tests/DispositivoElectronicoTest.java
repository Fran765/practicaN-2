package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import excepcions.SaldoException;
import model.Bebidas;
import model.ComarcaPlus;
import model.DispositivoElectronico;
import model.MasterCard;
import model.Pedido;
import model.Platos;
import model.Tarjeta;
import model.Viedma;
import model.Visa;
import persistence.EnDiscoRegistroComida;

class DispositivoElectronicoTest {

	@Test
	void testPagoTarjetaVisa() {

		Tarjeta miVisa = new Visa(20000, 10000.0, 5547, 3);

		FakeRegistroComida soloParaTest = new FakeRegistroComida();

		DispositivoElectronico miDispositivo = new DispositivoElectronico(soloParaTest);

		miDispositivo.tomarPedido(this.instanciarPedido());

		try {
			miDispositivo.realizarPago(miVisa, 5);
		} catch (SaldoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Double resultado = 9265.725;

		assertEquals(resultado, miDispositivo.devolverImporteAPagar());
	}

	@Test
	void testPagoTarjetaMastercard() {

		Tarjeta miMasterCard = new MasterCard(20000, 10000.0, 3090, 2);

		FakeRegistroComida soloParaTest = new FakeRegistroComida();

		DispositivoElectronico miDispositivo = new DispositivoElectronico(soloParaTest);

		miDispositivo.tomarPedido(this.instanciarPedido());

		try {
			miDispositivo.realizarPago(miMasterCard, 3);
		} catch (SaldoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Double resultado = 8950.7;

		assertEquals(resultado, miDispositivo.devolverImporteAPagar());
	}

	@Test
	void testPagoTarjetaComarcaPlus() {

		Tarjeta miComarca = new ComarcaPlus(20000, 10000.0, 7448, 2);

		FakeRegistroComida soloParaTest = new FakeRegistroComida();

		DispositivoElectronico miDispositivo = new DispositivoElectronico(soloParaTest);

		miDispositivo.tomarPedido(this.instanciarPedido());

		try {
			miDispositivo.realizarPago(miComarca, 2);
		} catch (SaldoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Double resultado = 8846.46;

		assertEquals(resultado, miDispositivo.devolverImporteAPagar());
	}

	@Test
	void testPagoTarjetaViedma() {

		Tarjeta miViedma = new Viedma(20000, 10000.0, 5222, 0);

		FakeRegistroComida soloParaTest = new FakeRegistroComida();

		DispositivoElectronico miDispositivo = new DispositivoElectronico(soloParaTest);

		miDispositivo.tomarPedido(this.instanciarPedido());

		try {
			miDispositivo.realizarPago(miViedma, 5);
		} catch (SaldoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Double resultado = 9292.5;

		assertEquals(resultado, miDispositivo.devolverImporteAPagar());
	}

	private Pedido instanciarPedido() {

		Platos plato1 = new Platos("Bondiola", 2500);
		Platos plato2 = new Platos("Ravioles", 2000);
		Platos plato3 = new Platos("Pescado", 3500);

		Bebidas bebida1 = new Bebidas("Gaseosa", 200);
		Bebidas bebida2 = new Bebidas("Agua", 150);
		Bebidas bebida3 = new Bebidas("Vino", 500);

		Pedido miPedido = new Pedido();

		miPedido.agregarPlato(plato1);
		miPedido.agregarPlato(plato2);
		miPedido.agregarPlato(plato3);

		miPedido.agregarBebida(bebida1);
		miPedido.agregarBebida(bebida2);
		miPedido.agregarBebida(bebida3);

		return miPedido;
	}
}

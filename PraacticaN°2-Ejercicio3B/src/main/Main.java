package main;

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
import persistence.BaseDatosRegistroComida;
import persistence.ConnectionManager;
import persistence.EnDiscoRegistroComida;

public class Main {

	public static void main(String[] args) {

		DispositivoElectronico miDispositivo = new DispositivoElectronico(
				new EnDiscoRegistroComida("C:\\Users\\franm\\OneDrive\\Escritorio\\registroResto.txt"));
		
		DispositivoElectronico miDispositivo2 = new DispositivoElectronico(
				new BaseDatosRegistroComida(ConnectionManager.getProperties()));

		Tarjeta miVisa = new Visa(20000, 10000.0, 5547, 3);
		Tarjeta miMasterCard = new MasterCard(20000, 10000.0, 3090, 2);
		Tarjeta miComarca = new ComarcaPlus(20000, 10000.0, 7448, 2);
		Tarjeta miViedma = new Viedma(20000, 10000.0, 5222, 0);

		Pedido miPedido = new Pedido();
		Pedido miPedido1 = new Pedido();

		miPedido1.agregarPlato(new Platos("Bondiola", 2500));
		miPedido1.agregarPlato(new Platos("Ravioles", 2000));
		miPedido.agregarPlato(new Platos("Pescado", 3500));
		miPedido.agregarPlato(new Platos("Piza", 1500));

		miPedido1.agregarBebida(new Bebidas("Gaseosa", 200));
		miPedido1.agregarBebida(new Bebidas("Agua", 150));
		miPedido.agregarBebida(new Bebidas("Vino", 500));
		miPedido.agregarBebida(new Bebidas("Cerveza", 350));

		miDispositivo.tomarPedido(miPedido1);
		
		miDispositivo2.tomarPedido(miPedido);

		try {
			miDispositivo.realizarPago(miVisa, 5);
			miDispositivo.realizarPago(miMasterCard, 3);
			miDispositivo2.realizarPago(miComarca, 2);
			miDispositivo2.realizarPago(miViedma, 5);
		} catch (SaldoException e) {
			e.printStackTrace();
		}

	}

}

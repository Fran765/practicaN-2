package tests;

import model.RegistroComida;

public class FakeRegistroComida implements RegistroComida{
	
	private String datosAlamacenados;

	@Override
	public void registrarAlmuerzoCena(String datosAlmuerzoCena) {

		this.datosAlamacenados = datosAlmuerzoCena;
	}

}

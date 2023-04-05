package tests;

import model.RegistroComida;

public class FakeRegistroComida implements RegistroComida{

	
	private String datosAlmuerzoCena;
	@Override
	public void registrarAlmuerzoCena(String datosAlmuerzoCena) {
		
		this.datosAlmuerzoCena = datosAlmuerzoCena;
		
	}
	
	String devolverInformacion() {
		return this.datosAlmuerzoCena;
	}

}

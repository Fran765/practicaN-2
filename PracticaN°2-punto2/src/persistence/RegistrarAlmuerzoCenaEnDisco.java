package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import model.RegistroComida;

public class RegistrarAlmuerzoCenaEnDisco implements RegistroComida {
	
	private String direccion;

	public RegistrarAlmuerzoCenaEnDisco(String direccion) {
		this.direccion = direccion;
	}

	
	@Override
	public void registrarAlmuerzoCena(String datosAlmuerzoCena) {
		try {
			Files.write(Paths.get(direccion), datosAlmuerzoCena.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}

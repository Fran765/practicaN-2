package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import model.RegistrarInscripto;

public class EnDiscoRegistrarInscripto implements RegistrarInscripto {

	private String ruta;

	public EnDiscoRegistrarInscripto(String ruta) {
		this.ruta =  Objects.requireNonNull(ruta);
	}

	@Override
	public void registrar(String infoParticipante) {
		try {
			Files.write(Paths.get(ruta), infoParticipante.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo persistir...", e);
		}
 
	}

}

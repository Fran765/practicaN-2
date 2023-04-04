package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import model.ResgitrarInscripto;

public class RegistrarParticipanteEnDisco implements ResgitrarInscripto {

	private String ruta;

	public RegistrarParticipanteEnDisco(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public void registrar(String infoParticipante) {
		try {
			Files.write(Paths.get(ruta), infoParticipante.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}

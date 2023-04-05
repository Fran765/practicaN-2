
import model.RegistrarInscripto;

public class FakeRegistrarInscripto implements RegistrarInscripto {

	private String infoParticipante;

	@Override
	public void registrar(String infoParticipante) {

		this.infoParticipante = infoParticipante;
	}

	String devolverInformacion() {
		return this.infoParticipante;
	}

}

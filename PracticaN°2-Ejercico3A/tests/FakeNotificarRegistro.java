import model.NotificarRegistro;

public class FakeNotificarRegistro implements NotificarRegistro{
	
	private String mensaje;

	@Override
	public void notificar(String contactoDestino, String mensaje) {
		
		this.mensaje = mensaje;	
		
	}
	
	String devolverMensajeGuardado() {
		return this.mensaje;
	}

}

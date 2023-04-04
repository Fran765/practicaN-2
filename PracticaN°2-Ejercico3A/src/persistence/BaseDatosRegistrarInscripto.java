package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import model.RegistrarInscripto;

public class BaseDatosRegistrarInscripto implements RegistrarInscripto {

	private String create = "INSERT INTO concurso_participante(id_concurso, id_participante, fecha_inscripcion)"
			+ "INSERT INTO(?, ?, ?)";

	private Properties prop;
	
	public BaseDatosRegistrarInscripto(Properties p) {
		
		this.prop = Objects.requireNonNull(p);
	}

	@Override
	public void registrar(String infoParticipante) {

		try (Connection conn = DriverManager.getConnection(prop.getProperty("connection"), prop.getProperty("username"),
				prop.getProperty("password"));

				PreparedStatement statement = conn.prepareStatement(create);) {

			String[] parts = infoParticipante.split("||");

			statement.setDate(1, java.sql.Date.valueOf(parts[0]));
			statement.setInt(2, Integer.parseInt(parts[1]));
			statement.setInt(3, Integer.parseInt(parts[2]));

		} catch (SQLException e) {
			System.out.println("Error al procesar consulta" + e);

		} catch (Exception e) {
			System.out.println("Error al insertar un punto: " + e);
		}
	}
}
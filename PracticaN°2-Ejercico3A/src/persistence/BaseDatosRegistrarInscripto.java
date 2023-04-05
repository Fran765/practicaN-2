package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import model.RegistrarInscripto;

public class BaseDatosRegistrarInscripto implements RegistrarInscripto {

	private String create = "INSERT INTO concurso_participante(info) VALUES(?)";

	private Properties prop;

	public BaseDatosRegistrarInscripto(Properties p) {

		this.prop = Objects.requireNonNull(p);
	}

	@Override
	public void registrar(String infoParticipante) {

		try (Connection conn = DriverManager.getConnection(prop.getProperty("connection"), prop.getProperty("username"),
				prop.getProperty("password"));

				PreparedStatement statement = conn.prepareStatement(create);) {
			
			statement.setString(1, infoParticipante);

		} catch (SQLException e) {
			throw new RuntimeException("Error al procesar consulta en base de datos " + e.getMessage());

		} catch (Exception e) {
			throw new RuntimeException("Error al insertar en base de datos " + e.getMessage());
		}
	}
}
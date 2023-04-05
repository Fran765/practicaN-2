package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import model.RegistroComida;

public class BaseDatosRegistroComida implements RegistroComida {

	private String create = "INSERT INTO fecha_importe(informacion) VALUES(?)";

	private Properties prop;

	public BaseDatosRegistroComida(Properties p) {

		this.prop = Objects.requireNonNull(p);
	}

	@Override
	public void registrarAlmuerzoCena(String datosAlmuerzoCena) {

		try (Connection conn = DriverManager.getConnection(prop.getProperty("connection"), prop.getProperty("username"),
				prop.getProperty("password"));

				PreparedStatement statement = conn.prepareStatement(create);) {

			statement.setString(1, datosAlmuerzoCena);

		} catch (SQLException e) {
			throw new RuntimeException("Error al procesar consulta en base de datos", e);

		} catch (Exception e) {
			throw new RuntimeException("Error al insertar en base de datos", e);
		}
	}

}

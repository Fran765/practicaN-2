package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import model.RegistroComida;

public class BaseDatosRegistroComida implements RegistroComida {

	private String create = "INSERT INTO fecha_importe(fecha, importe)" + "INSERT INTO(?, ?)";

	private Properties prop;

	public BaseDatosRegistroComida(Properties p) {

		this.prop = Objects.requireNonNull(p);
	}

	@Override
	public void registrarAlmuerzoCena(String datosAlmuerzoCena) {

		try (Connection conn = DriverManager.getConnection(prop.getProperty("connection"), prop.getProperty("username"),
				prop.getProperty("password"));

				PreparedStatement statement = conn.prepareStatement(create);) {

			String[] parts = datosAlmuerzoCena.split("||");

			statement.setDate(1, java.sql.Date.valueOf(parts[0]));
			statement.setInt(2, Integer.parseInt(parts[1]));

		} catch (SQLException e) {
			System.out.println("Error al procesar consulta" + e);

		} catch (Exception e) {
			System.out.println("Error al insertar un punto: " + e);
		}
	}

}

package ul.info.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.prefs.Preferences;
@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "pepsi", "cola");
			statement = connection.createStatement();
			try {
				ResultSet resultSet = statement.executeQuery("select 1 from pg_database where datname = 'bank'");
				if(!resultSet.next()){
					statement.executeUpdate("CREATE DATABASE \"bank\"");
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		SpringApplication.run(BankApplication.class, args);
		Preferences p=Preferences.userNodeForPackage(BankApplication.class);
		if(p!=null) {
			if (p.get("Ini", null) == null) {
				try {
					connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank", "pepsi", "cola");
					statement = connection.createStatement();
					try {
						statement.executeUpdate("INSERT INTO card( amount, created_at, currency, cvv, ex_date, pan, updated_at) VALUES (100,'2020-01-22','USD', '123', '2024-01-22', '1234123412341234' ,'2020-01-22')");
						statement.executeUpdate("INSERT INTO card( amount, created_at, currency, cvv, ex_date, pan, updated_at) VALUES (100,'2020-01-22','USD', '123', '2024-01-22', '1234123412341233' ,'2020-01-22')");
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					try {
						if (statement != null) {
							statement.close();
						}
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
				p.put("Ini", "");
			}
		}
	}

}
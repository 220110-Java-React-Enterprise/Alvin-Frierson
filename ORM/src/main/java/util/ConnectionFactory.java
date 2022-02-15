package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection factory used to establish the MariaDB database connectivity.
 */
public class ConnectionFactory
{

	private static ConnectionFactory connectionFactory;
	private static Connection connection;

	/**
	 * Constructor that established a dynamic string used to initialize the connection at object creation.
	 * @param host         host name
	 * @param port         port number
	 * @param dbname       Database name
	 * @param username     username
	 * @param password     password
	 * @throws SQLException
	 */
	private ConnectionFactory(String host, int port, String dbname, String username, String password) throws SQLException, ClassNotFoundException
	{

		String connectionStr = "jdbc:mariadb://" +
				host + ":" + port + "/" +
				dbname + "?user=" + username +
				"&password=" + password;

		Class.forName("org.mariadb.jdbc.Driver");
		connection = DriverManager.getConnection(connectionStr);
	}

	/**
	 * A method that takes the stated parameters and uses them to verifies if an established connection is present.
	 * if no connection has been established it creates a new connection factory and returns a connection.
	 * @param host        host name
	 * @param port        port number
	 * @param dbname      Database name
	 * @param username    username
	 * @param password    password
	 * @return 			  return a connection
	 * @throws SQLException
	 */
	public static Connection getConnection(String host, int port, String dbname, String username, String password) throws SQLException, ClassNotFoundException
	{
		if(connectionFactory == null)
		{
			connectionFactory = new ConnectionFactory(host, port, dbname, username, password);
		}
		return connection;
	}

	public static void closeConnection() throws SQLException
	{
		connection.close();
		connectionFactory = null;
	}

}

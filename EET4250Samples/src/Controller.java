import java.sql.Connection;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connection.DatabaseManager;

import tables.Tables;

public class Controller {

	private static Logger logger = LoggerFactory.getLogger(Controller.class);
	
	public static void main(String[] args) {
		
		/* Make a database connections with properties files */
		DatabaseManager databaseManager = new DatabaseManager("database.properties");
		Connection connection = databaseManager.establishConnection();
		if (null == connection) {
			System.exit(1);
		}		
		Scanner scanner = new Scanner(System.in);
		Tables.tableBuilds(connection);
		
		/* Close the database connection */
		databaseManager.closeConnection(connection);
		scanner.close();
		logger.debug("Program completed");
	}

}

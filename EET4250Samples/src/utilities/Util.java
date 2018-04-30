package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

	public static Logger logger = LoggerFactory.getLogger(Util.class);
	
	public static boolean tableExists(Connection conn, String tableName) {
		boolean found = false;		
		try {
			String sql = "SELECT * FROM " + tableName;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeQuery();
			found = true;
			logger.debug("Table: " + tableName +" already exists.");
		}
		catch(SQLException e) {
			found = false;
			logger.debug("SQLException: "+e.toString());
		}
		return found;
	}
	
	
}

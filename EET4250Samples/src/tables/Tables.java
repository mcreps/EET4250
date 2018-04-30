package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utilities.Util;

public class Tables {

	public static Logger logger = LoggerFactory.getLogger(Tables.class);

	public static void tableBuilds(Connection conn) {
		logger.debug("Entering tableBuilds.....");
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);		
		System.out.print("Run the table builds (Y/N). ");
		
		if (!"Y".equals(scanner.nextLine())) {
			logger.debug("Table builds aborted by user.");
			return;
		}
		
		/* Creates a map of tables and sql statements */
		Map<String, String> map = new HashMap<>();
		
		String sql = "CREATE TABLE `FinalExam_Students` ( " + 
				"  `StudentId` int(11) NOT NULL AUTO_INCREMENT, " + 
				"  `FName` varchar(45) COLLATE latin1_general_ci DEFAULT NULL, " + 
				"  `LName` varchar(45) COLLATE latin1_general_ci DEFAULT NULL, " + 
				"  `Email` varchar(200) COLLATE latin1_general_ci DEFAULT NULL, " + 
				"  PRIMARY KEY (`StudentId`)\n" + 
				")";
		map.put("FinalExam_Students", sql);
		
		
		/*
		 * Attempt to build the tables
		 */
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			String tableName = entry.getKey();
			sql = entry.getValue();
			
			if (!Util.tableExists(conn, tableName)) {				
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					int rowsAffected = ps.executeUpdate();
					if (0 != rowsAffected) {
						logger.debug("SQLException: table not built.");
					}else {
						logger.debug("Table " + tableName + " built.");
					}
				}
				catch(SQLException e) {
					logger.debug("SQLException: Table not build " + tableName + ".  " + e.getLocalizedMessage());
				}
			}
		}

	}
}

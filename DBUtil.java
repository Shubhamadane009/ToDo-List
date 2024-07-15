package TODO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String url="jdbc:mysql://localhost:3306/task";
	private static final String user="root";
	private static final String Password="Pass@123";
	
	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,Password);
	}
	
}
	




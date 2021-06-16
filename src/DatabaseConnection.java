import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	Connection connection;
	
	public DatabaseConnection(String driver, String url, String user, String pwd) {
		try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de trouver le driver");
            e.printStackTrace();
        } catch (SQLException exp) {
        	System.out.println("SQLException: " + exp.getMessage());
            System.out.println("SQLState: " + exp.getSQLState());
            System.out.println("VendorError: " + exp.getErrorCode());
        }
	}
	
}

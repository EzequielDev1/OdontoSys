package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDataBase {
	private static final String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String url = "jdbc:sqlserver://192.168.60.164:1433;encrypt=false;databaseName=OdontoSys";
	private static final String user = "sa";
	private static final String password = "S3nailab03";
	
	public static Connection getConnection() {
		try {
			Class.forName(Driver);
			System.out.println("Conexão estabelecida!");
			return DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro de conexão! ", e);
		}
	}
	
	public static void closeConnection(Connection con) {
		try {
			if(con != null) {
				con.close();
				System.out.println("Conexão fechada!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar a conexão!");
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		try {
			if(stmt != null) {
				stmt.close();
				System.out.println("Conexão fechada!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar a conexão!");
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		closeConnection(con, stmt);
		try {
			if(rs != null) {
				rs.close();
				System.out.println("Conexão fechada!");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar a conexão!");
		}
	}
	
}


package stanfordChatBot.testbot;

import java.sql.*;

public class SQLConnector {
	
	public static void dataRequest(String qry) {
	
		System.out.println("Requesting Data");
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			PreparedStatement pstmt = con.prepareStatement(qry);
			
			int rs = pstmt.executeUpdate();
			System.out.println(rs);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Select {
	public static ResultSet getData(String query) {

		Connection con = null;
		ResultSet rs = null;
		Statement st = null;

		try {
			con = ConnectionProvider.getCon();
			st = con.createStatement();
			rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}

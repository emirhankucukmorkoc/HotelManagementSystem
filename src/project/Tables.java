package project;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Tables {

	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;
		try {

			con = ConnectionProvider.getCon();
			st = con.createStatement();
			st.executeUpdate(
					"create table users(name varchar(200),email varchar(200),password varchar(200),securityQuestion varchar(500),answer varchar(200),address varchar(200),status varchar(20))");
			st.executeUpdate(
					"CREATE TABLE rooms (roomNumber varchar(200), roomType varchar(200), bed varchar(200), price varchar(500), status varchar(200))");
			st.executeUpdate(
					"CREATE TABLE customer (id int, name varchar(200), mobileNumber varchar(200), nationality varchar(200), gender varchar(50), email varchar(200), idProof varchar(200), address varchar(200), checkIn varchar(200), roomNumber varchar(200), bed varchar(200), roomType varchar(200), pricePerDay int, numberOfDaysStay int, checkOut varchar(200), totalAmount varchar(200))");

			st.executeUpdate(
					"CREATE TABLE bill (id int, name varchar(200), mobileNumber varchar(200), nationality varchar(200), gender varchar(50), email varchar(200), idProof varchar(200), address varchar(200), checkIn varchar(200), roomNumber varchar(200), bed varchar(200), roomType varchar(200), pricePerDay int, numberOfDaysStay int, checkOut varchar(200), totalAmount varchar(200))");

			JOptionPane.showMessageDialog(null, "Table Created Successfully");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);

		} finally {
			try {
				con.close();
				st.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

}

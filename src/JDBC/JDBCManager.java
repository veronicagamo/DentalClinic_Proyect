package JDBC;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCManager {

	private Connection c;

	public JDBCManager() {
		
		try {
					
			//Open Database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/dental_clinic.db");
			System.out.println("Database connection opened");
			
			this.createTables();
			
		}
		catch (SQLException ex) {
		
			ex.printStackTrace();
		}
		catch (ClassNotFoundException ex){
		
			System.out.print("Error: Libraries not loaded");
		}
	}
	
	public Connection getConnection() {
		
		return c;
	}
	
	public void disconnect(){
			
		try {
			
			c.close();
		}
		catch(SQLException ex){
		
			ex.printStackTrace();			
		}
		
	}
	
	private void createTables() {

		try {
			
			Statement stmt = c.createStatement();
			
			String sql = "CREATE TABLE dentist ("
						+ " id            INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ " name          TEXT NOT NULL,"
						+ " bank_account  TEXT NOT NULL,"
						+ " email         TEXT NOT NULL,"
						+ " mobile        TEXT NOT NULL,"
					+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE supplies ("
					+ " id	  INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	name	  TEXT NOT NULL,"
					+ "	amount 	  INT NOT NULL,"
				+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE suppliers ("
				+ "	id	    INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "	name	TEXT NOT NULL,"
				+ "	address	TEXT"
			+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE order_supplies ("
				+ "	id          INTEGER PRIMARY KEY AUTOINCREMENT,"	
				+ "	supply_id   INTEGER NOT NULL,"
				+ "	dentist_id  INTEGER NOT NULL,"
				
				+ "	FOREIGN KEY(supply_id) REFERENCES supplies(id) ON DELETE CASCADE,"
				+ "	FOREIGN KEY(dentist_id) REFERENCES dentist(id) ON DELETE CASCADE,"
			+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE appointment ("
					+ "	id               INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	date	         DATETIME NOT NULL,"
					+ "	duration         INTEGER NOT NULL,"
					+ "	room             INTEGER NOT NULL,"	
					+ "	price	         REAL NOT NULL,"
					+ "	treatment        TEXT NOT NULL,"
					+ "	dentist_id       INTEGER NOT NULL,"	
					+ "	recepcionist_id	 INTEGER NOT NULL,"
					+ "	client_id       INTEGER NOT NULL,"
					
					+ "	FOREIGN KEY(dentist_id) REFERENCES dentist(id) ON DELETE CASCADE,"
					+ "	FOREIGN KEY(recepcionist_id) REFERENCES receptionist(id) ON DELETE CASCADE,"
					+ "	FOREIGN KEY(client_id) REFERENCES client(id) ON DELETE CASCADE,"
				+ ");";
				
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE provide_supplies ("
					+ "	id           INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	date	     DATETIME NOT NULL,"
					+ "	amount       INTEGER NOT NULL,"
					+ "	supply_id    INTEGER NOT NULL,"	
					+ "	supplier_id	 INTEGER NOT NULL,"
					
					+ "	FOREIGN KEY(supply_id) REFERENCES supplies(id) ON DELETE CASCADE,"
					+ "	FOREIGN KEY(supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE,"
				+ ");";
				
			stmt.executeUpdate(sql);	
			
			sql = "CREATE TABLE receptionist ("
					+ "	id      	  INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	name	 	  TEXT NOT NULL,"
					+ "	bank_account  TEXT NOT NULL,"
					+ "	email         TEXT NOT NULL,"	
					+ "	mobile	      INTEGER NOT NULL,"
				+ ");";
				
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE client ("
					+ "	id             INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	name	       TEXT NOT NULL,"
					+ "	health_number  INTEGER NOT NULL,"
					+ "	address        TEXT NOT NULL,"	
					+ "	email	       TEXT NOT NULL,"
				+ ");";
				
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE used_supplies ("
					+ "	id              INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	supply_id	    INTEGER NOT NULL,"
					+ "	appointment_id  INTEGER NOT NULL,"
					
					+ "	FOREIGN KEY(supply_id) REFERENCES supplies(id) ON DELETE CASCADE,"
					+ "	FOREIGN KEY(appointment_id) REFERENCES appointment(id) ON DELETE CASCADE,"		
				+ ");";
				
			stmt.executeUpdate(sql);
		
		} catch (SQLException ex) {
			// Do not show a message if tables already exist
			if (!ex.getMessage().contains("already exists")) {
				
				ex.printStackTrace();
			}
		}
	}
	
}

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
			c = DriverManager.getConnection("jdbc:sqlite:./db/DentalClinic.db");
			System.out.println("Database connection opened");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			
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
						+ " doc_id            INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ " name          TEXT NOT NULL,"
						+ " bank_account  INTEGER NOT NULL,"
						+ " doc_email         TEXT NOT NULL,"
						+ " doc_mobile        TEXT NOT NULL"
					+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE supplies ("
					+ " supplies_id	  INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	name	  TEXT NOT NULL,"
					+ "	amount 	  INT NOT NULL"
				+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE suppliers ("
				+ "	id	    INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "	name	TEXT NOT NULL,"
				+ "	address	TEXT"
			+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE receptionist ("
					+ "	recep_id      	  INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	name	 	  TEXT NOT NULL,"
					+ "	bank_account  INTEGER NOT NULL,"
					+ "	email         TEXT NOT NULL,"	
					+ "	mobile	      INTEGER NOT NULL"
				+ ");";
				
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE client ("
					+ "	pat_id             INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	pat_name	       TEXT NOT NULL,"
					+ "	hum  INTEGER NOT NULL,"
					+ "	pat_address        TEXT NOT NULL,"	
					+ "	pat_email	       TEXT NOT NULL"
				+ ");";
				
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE order_supplies ("
				+ "	id          INTEGER PRIMARY KEY AUTOINCREMENT,"	
				+ "	supply_id   INTEGER NOT NULL,"
				+ "	dentist_id  INTEGER NOT NULL,"+"amount INTEGER NOT NULL," +"amount INTEGER NOT NULL,"
				
				+ "	FOREIGN KEY(supply_id) REFERENCES supplies(supplies_id) ON UPDATE CASCADE ON DELETE SET NULL,"
				+ "	FOREIGN KEY(dentist_id) REFERENCES dentist(doc_id) ON UPDATE CASCADE ON DELETE SET NULL"
			+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE appointment ("
					+ "	app_id               INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	app_date	         DATETIME NOT NULL,"
					+ "	app_duration         INTEGER NOT NULL,"
					+ "	app_room             INTEGER NOT NULL,"	
					+ "	app_price	         REAL NOT NULL,"
					+ "	app_treatment        TEXT NOT NULL CHECK (app_treatment IN ('Implants','Cleaning','Braces','Tooth extractions','Whitening')),"
					+ "	dentist_id       INTEGER NOT NULL,"	
					+ "	recepcionist_id	 INTEGER NOT NULL,"
					+ "	client_id       INTEGER NOT NULL,"
					
					+ "	FOREIGN KEY(dentist_id) REFERENCES dentist(doc_id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "	FOREIGN KEY(recepcionist_id) REFERENCES receptionist(id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "	FOREIGN KEY(client_id) REFERENCES client(pat_id) ON UPDATE CASCADE ON DELETE SET NULL"
				+ ");";
				
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE provide_supplies ("
					+ "	id           INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	date	     DATETIME NOT NULL,"
					+ "	amount       INTEGER NOT NULL,"
					+ "	supply_id    INTEGER NOT NULL,"	
					+ "	supplier_id	 INTEGER NOT NULL,"
					
					+ "	FOREIGN KEY(supply_id) REFERENCES supplies(supplies_id) ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "	FOREIGN KEY(supplier_id) REFERENCES suppliers(id) ON UPDATE CASCADE ON DELETE SET NULL"
				+ ");";
				
			stmt.executeUpdate(sql);	
			
			
			sql = "CREATE TABLE used_supplies ("
					+ "	id              INTEGER PRIMARY KEY AUTOINCREMENT,"	
					+ "	supply_id	    INTEGER NOT NULL,"
					+ "	appointment_id  INTEGER NOT NULL,"
					
					+ "	FOREIGN KEY(supply_id) REFERENCES supplies(supplies_id)  ON UPDATE CASCADE ON DELETE SET NULL,"
					+ "	FOREIGN KEY(appointment_id) REFERENCES appointment(app_id)  ON UPDATE CASCADE ON DELETE SET NULL"		
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

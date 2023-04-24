package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import JDBC.*;
import POJO.*;

public class Menu {
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static JDBCAppointmentManager appMan;
	private static JDBCDentistManager docMan;
	private static JDBCClientManager patMan;
	private static JDBCRecepcionistManager recMan;
	
	public static void main(String[] args) {
		 JDBCManager connection= new JDBCManager();
		 appMan= new JDBCAppointmentManager(connection.getConnection());
         docMan= new JDBCDentistManager(connection);
         patMan=new JDBCClientManager (connection);
         recMan= new JDBCRecepcionistManager(connection);
		while (true) {
			try {
				System.out.println("Welcome to the DentalClinic Database");
				System.out.println("Choose an option, please:");
				System.out.println("1. Create an appointment ");
				System.out.println("2. Update an appointment");
				System.out.println("3. Delete an appointment");
				System.out.println("4. View an specific appointment");
				System.out.println("5. View all appointments");
				System.out.println("6. Create a patient");
				System.out.println("0. Exit");

				int choice = Integer.parseInt(r.readLine());

				switch (choice) {
				case 1: {
					createApp();
					break;
				}
				case 2: {
					
					break;
				}
				case 3: {
					
					break;
				}
               case 4: {
					
					break;
				}
               case 5: {
					
					break;
				}
               case 6: {
					createPat();
					break;
				}
				case 0: {
					connection.disconnect();
					return;
				}
				}

			} catch (NumberFormatException e) {
				System.out.println("Wrong number introduced!");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("I/O Exception.");
				e.printStackTrace();
			}
		}

	}
		public static void createApp() throws IOException {
			Appointment app= null;
			
			System.out.println("Please, input the appointmnet's data:");
			System.out.println("Date (yyyy-MM-dd):");
			String date = r.readLine();
			LocalDate dLocalDate = LocalDate.parse(date, formatter);		
			Date dobDate = Date.valueOf(dLocalDate);
			System.out.println("Duration");
			Integer dur = Integer.parseInt(r.readLine());
			System.out.println("Room:");
			Integer room = Integer.parseInt(r.readLine());
			System.out.println("Price:");
			Double price= Double.parseDouble(r.readLine());
			String treat=null;
			do {
			System.out.println("Treatment(Implants/Cleaning/Braces/Tooth extractions/Whitening):");
			treat= r.readLine();
			}
			while (treat.equals("Implants") ||treat.equals("Cleaning")||treat.equals("Braces")|| treat.equals("Tooth extractions")||treat.equals("Whitening"));
			System.out.println("Name dentist:");
			String docname= r.readLine();
			Integer docId=null;;
			try {
				Dentists dentist= docMan.getDentistByName(docname);
				docId= dentist.getDoc_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Name patient:");
			String patname= r.readLine();
			Integer patId=null;;
			try {
				Client c= patMan.getClientByName(patname);
				 patId= c.getPat_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Name receptionist:");
			String recname= r.readLine();
			Integer recId=null;
			try {
				Receptionist re=recMan.geRecepByName(recname);
				recId= re.getRep_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
			app=new Appointment(dobDate,dur,room,price,treat,docId,recId,patId);
			try {
				appMan.createAppointment(app);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public static void createPat() throws IOException {
		Client pat=null;
		
		System.out.println("Please, input the client's data:");
		System.out.println("Patient´s name:");
		String name=r.readLine();
		System.out.println("Bank account:");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email=r.readLine();
		System.out.println("Address:");
		String add=r.readLine();
		pat=new Client(name,bank,add,email);
		try {
			patMan.createClient(pat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}

package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import JDBC.*;
import POJO.*;

public class Menu {
	
	private static final String Implants = "Implants";
	private static final String Cleaning = "Cleaning";
	private static final String Braces = "Braces";
	private static final String Tooth = "Tooth extractions";
	private static final String White = "Whitening";
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static JDBCAppointmentManager appMan;
	private static JDBCDentistManager docMan;
	private static JDBCClientManager patMan;
	private static JDBCRecepcionistManager recMan;
	private static JDBCUsed_suppliesManager usedMan;
	private static JDBCSuppliesManager supplyMan;
	
	public static void main(String[] args) {
		 JDBCManager connection= new JDBCManager();
		 appMan= new JDBCAppointmentManager(connection);
         docMan= new JDBCDentistManager(connection);
         patMan=new JDBCClientManager (connection);
         recMan= new JDBCRecepcionistManager(connection);
         usedMan= new JDBCUsed_suppliesManager(connection);
         supplyMan= new JDBCSuppliesManager (connection);
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
				System.out.println("7. View a patient by id");
				System.out.println("8. View a patient by name");
				System.out.println("9. Update a patient");
				System.out.println("10. Delete a patient");
				System.out.println("11. View all patients");
				System.out.println("12. View all appointments from a patient");
				System.out.println("13. Create a dentist");
				System.out.println("14. View a dentist by id");
				System.out.println("15. View a dentist by name");
				System.out.println("16. Update a dentist");
				System.out.println("17. Delete a dentist");
				System.out.println("18. View all dentists");
				System.out.println("19. View all appointments from a dentist");
				System.out.println("20. Create a receptionist");
				System.out.println("21. View a receptionist by id");
				System.out.println("22. View a receptionist by name");
				System.out.println("23. Update a receptionist");
				System.out.println("24. Delete a receptionist");
				System.out.println("25. View all receptionist");
				System.out.println("26. Create supply");
				System.out.println("0. Exit");

				int choice = Integer.parseInt(r.readLine());

				switch (choice) {
				case 1: {
					createApp();
					break;
				}
				case 2: {
					updateApp();
					break;
				}
				case 3: {
					deleteApp();
					break;
				}
               case 4: {
					viewApp();
					break;
				}
               case 5: {
					//viewAllApp();
					break;
				}
               case 6: {
					createPat();
					break;
				}
               case 7: {
					viewPatId();
					break;
				}
               case 8: {
					viewPatName();
					break;
				}
               case 9: {
					updatePat();
					break;
				}
               case 10: {
					deletePat();
					break;
				}
               case 11: {
					viewListPat();
					break;
				}
               case 12: {
					
					break;
				}
               case 13: {
            	   createDoc();
					break;
				}
               case 14: {
					viewDocId();
					break;
				}
               case 15: {
					viewDocName();
					break;
				}
               case 16: {
					updateDoc();
					break;
				}
               case 17: {
					deleteDoc();
					break;
				}
               case 18: {
            	   viewListDoc();
					break;
				}
               case 19: {
					
					break;
				}
               case 20: {
					createRec();
					break;
				}
               case 21: {
					viewRecId();
					break;
				}
               case 22: {
					viewRecName();
					break;
				}
               case 23: {
					updateRec();
					break;
				}
               case 24: {
					deleteRec();
					break;
				}
               case 25: {
					ListRec();
					break;
				}
               case 26: {
					createSupply();
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
			
			System.out.println("Please, input the appointment's data:");
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
			while (treat.equals("Implants") && treat.equals("Cleaning")&& treat.equals("Braces")&& treat.equals("Tooth extractions")&& treat.equals("Whitening"));

			System.out.println("Name dentist:");
			String docname= r.readLine();
			Integer docId=null;
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
			Integer id=null;
			try {
				id=appMan.createAppointment(app); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(treat) { 
			case Implants:
				
				try {
					usedMan.createUsed(id,1,2);
					supplyMan.decreaseAmount(2, 1);
					usedMan.createUsed(id,2,10);
					supplyMan.decreaseAmount(10, 2);
					usedMan.createUsed(id,3,1);
					supplyMan.decreaseAmount(1, 3);
					usedMan.createUsed(id,7,1);
					supplyMan.decreaseAmount(1, 7);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		case Cleaning:
			
			try {
				usedMan.createUsed(id,2,5);
				supplyMan.decreaseAmount(5, 2);
				usedMan.createUsed(id,4,2);
				supplyMan.decreaseAmount(2, 4);
				usedMan.createUsed(id,5,6);
				supplyMan.decreaseAmount(6, 5);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
       case Braces:
			
			try {
				usedMan.createUsed(id,6,2);
				supplyMan.decreaseAmount(2, 6);
				usedMan.createUsed(id,8,1);
				supplyMan.decreaseAmount(1, 8);
				usedMan.createUsed(id,2,10);
				supplyMan.decreaseAmount(10, 2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
       case Tooth:
			
			try {
				usedMan.createUsed(id,1,2);
				supplyMan.decreaseAmount(2, 1);
				usedMan.createUsed(id,2,20);
				supplyMan.decreaseAmount(20, 2);
				usedMan.createUsed(id,9,1);
				supplyMan.decreaseAmount(1, 9);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
       case White:
			
			try {
				usedMan.createUsed(id,2,5);
				supplyMan.decreaseAmount(5, 2);
				usedMan.createUsed(id,10,1);
				supplyMan.decreaseAmount(1, 10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
				
		}
		
		public static void updateApp() throws IOException {
			Appointment app=null;
			System.out.println("Please, input the appointment's data you want to update:");
			System.out.println("Id");
			Integer id = Integer.parseInt(r.readLine());
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
			while (treat.equals("Implants") && treat.equals("Cleaning")&& treat.equals("Braces")&& treat.equals("Tooth extractions")&& treat.equals("Whitening"));
			
			System.out.println("Name dentist:");
			String docname= r.readLine();
			Integer docId=null;
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
			app=new Appointment(id,dobDate,dur,room,price,treat,docId,recId,patId);
			try {
				appMan.updateAppointment(app);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		public static void deleteApp() throws IOException {
			System.out.println("Please, input the appointmnet's id you want to delete:");
			Integer id = Integer.parseInt(r.readLine());
	       try {
			appMan.deleteAppointment(id);
			System.out.println("It has been deleted correctly");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Incorrect delete");
			e1.printStackTrace();
		}
		}
		
		public static void viewApp() throws IOException {
			Appointment app=null;
			System.out.println("Please, input the appointment's id you want to see:");
			Integer id = Integer.parseInt(r.readLine());
	       try {
			app=appMan.viewAppointment(id);
			System.out.println(app);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		}
	public static void createPat() throws IOException {
Client pat=null;
		
		System.out.println("Please, input the client's data:");
		System.out.println("Name:");
		String name=r.readLine();
		System.out.println("Health Number:");
		Integer hum = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email=r.readLine();
		System.out.println("Address:");
		String add=r.readLine();
		pat=new Client(name,hum,add,email);
		try {
			patMan.createClient(pat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void viewPatId() throws IOException {
		Client pat=null;
		System.out.println("Please, input the client's id :");
		Integer id = Integer.parseInt(r.readLine());
       try {
		pat=patMan.getClientById(id);
		System.out.println(pat);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
	}
	
	public static void viewPatName() throws IOException {
		Client pat=null;
		System.out.println("Please, input the client's name:");
		String name=r.readLine();
       try {
		pat=patMan.getClientByName(name);
		System.out.println(pat);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
	}
	
	public static void deletePat() throws IOException {
		System.out.println("Please, input the client's id you want to delete:");
		Integer id = Integer.parseInt(r.readLine());
       try {
		patMan.deleteClient(id);
		System.out.println("It has been deleted correctly");
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		System.out.println("Incorrect delete");
		e1.printStackTrace();
	}
		
	}
	
	public static void viewListPat() throws IOException {
		System.out.println("List of patients:");
		ArrayList<Client> all = new ArrayList<Client>();
       try {
		all=patMan.getListAllClients();
		System.out.println(all);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
	}
	public static void updatePat() throws IOException {
		Client pat=null;
		System.out.println("Please, input the client's data you want to update:");
		System.out.println("Id:");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("Name:");
		String name=r.readLine();
		System.out.println("Health Number:");
		Integer hum = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email=r.readLine();
		System.out.println("Address:");
		String add=r.readLine();
		pat=new Client(id,name,hum,add,email);
       try {
		patMan.updateClient(pat);
		System.out.println("It has been updated correctly");
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		System.out.println("Incorrect update");
	}
		
	}
	public static void createDoc() throws IOException {
		Dentists den=null;
				
				System.out.println("Please, input the dentist's data:");
				System.out.println("Name:");
				String name=r.readLine();
				System.out.println("Bank Account");
				Integer bank = Integer.parseInt(r.readLine());
				System.out.println("Email:");
				String email=r.readLine();
				System.out.println("Mobile:");
				String mob=r.readLine();
				den= new Dentists(name,bank,mob,email);
				try {
					docMan.addDentist(den);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	public static void viewDocId() throws IOException {
		Dentists den=null;
		System.out.println("Please, input the dentist's id :");
		Integer id = Integer.parseInt(r.readLine());
       try {
		den=docMan.getDentistById(id);
		System.out.println(den);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		
	}
	
	public static void viewDocName() throws IOException {
		Dentists den=null;
		System.out.println("Please, input the patients's name:");
		String name=r.readLine();
       try {
		den=docMan.getDentistByName(name);
		System.out.println(den);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
	}
	
	public static void updateDoc() throws IOException {
		Dentists den=null;
		System.out.println("Please, input the dentist's data you want to update:");
		System.out.println("Id:");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("Name:");
		String name=r.readLine();
		System.out.println("Bank Account:");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email=r.readLine();
		System.out.println("Mobile:");
		String mob=r.readLine();
		den=new Dentists(id,name,bank,mob,email);
       try {
		docMan.updateDentist(den);
		System.out.println("It has been updated correctly");
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		System.out.println("Incorrect update");
	}
		
	}
	public static void deleteDoc() throws IOException {
		System.out.println("Please, input the dentist's id you want to delete:");
		Integer id = Integer.parseInt(r.readLine());
       try {
		docMan.deleteDentist(id);
		System.out.println("It has been deleted correctly");
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		System.out.println("Incorrect delete");
		e1.printStackTrace();
	}
	}
	
	public static void viewListDoc() throws IOException {
		System.out.println("List of dentists:");
		ArrayList<Dentists> all = new ArrayList<Dentists>();
       try {
		all=docMan.listAllDentists();
		System.out.println(all);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
	}
	
	public static void createRec() throws IOException {
         Receptionist rec=null;
		
		System.out.println("Please, input the receptionist's data:");
		System.out.println("Name:");
		String name=r.readLine();
		System.out.println("Bank Account:");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email=r.readLine();
		System.out.println("Mobile:");
		Integer mob = Integer.parseInt(r.readLine());
		rec=new Receptionist(name,bank,email,mob);
		try {
			recMan.addReceptionist(rec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void viewRecId() throws IOException {
		Receptionist rec=null;
		System.out.println("Please, input the receptionist's id :");
		Integer id = Integer.parseInt(r.readLine());
       try {
		rec=recMan.getreceptionistById(id);
		System.out.println(rec);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	public static void viewRecName() throws IOException {
		Receptionist rec=null;
		System.out.println("Please, input the receptionist's name:");
		String name=r.readLine();
       try {
		rec=recMan.geRecepByName(name);
		System.out.println(rec);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
	}
	public static void updateRec() throws IOException {
		Receptionist rec=null;
		System.out.println("Please, input the receptionist's data you want to update:");
		System.out.println("Id:");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("Name:");
		String name=r.readLine();
		System.out.println("Bank Account:");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email=r.readLine();
		System.out.println("Mobile:");
		Integer mob= Integer.parseInt(r.readLine());
		rec=new Receptionist (id,name,bank,email,mob);
       try {
		recMan.updateReceptionist(rec);
		System.out.println("It has been updated correctly");
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		System.out.println("Incorrect update");
	}}
	
	public static void deleteRec() throws IOException {
		System.out.println("Please, input the receptionist's id you want to delete:");
		Integer id = Integer.parseInt(r.readLine());
       try {
		recMan.deleteReceptionist(id);
		System.out.println("It has been deleted correctly");
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		System.out.println("Incorrect delete");
		e1.printStackTrace();
	}
	}
	
	public static void ListRec() throws IOException {
		System.out.println("List of receptionists:");
		ArrayList<Receptionist> all = new ArrayList<Receptionist>();
       try {
		all=recMan.listAllReceptionists();
		System.out.println(all);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
	}
	public static void createSupply() throws IOException {
       Supply s=null;
		
		System.out.println("Please, input the supply's data:");
		System.out.println("Name:");
		String name=r.readLine();
		System.out.println("Amount:");
		Integer amount = Integer.parseInt(r.readLine());

		s=new Supply(name,amount);
		try {
			supplyMan.addSupply(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}

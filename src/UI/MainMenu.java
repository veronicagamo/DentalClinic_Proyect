package UI;

import java.io.*;

import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import JDBC.*;
import JPA.JPAUserManager;
import POJO.*;
import XML.XMLManagerImpl;

public class MainMenu {
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static JDBCAppointmentManager appMan;
	private static JDBCDentistManager docMan;
	private static JDBCClientManager patMan;  
	private static JDBCRecepcionistManager recMan;
	private static JDBCUsed_suppliesManager usedMan;
	private static JDBCSuppliersManager supplierMan;
	private static JPAUserManager userMan;
	private static JDBCSuppliesManager supplyMan;
	private static JDBCOrder_suppliesManager orderMan;
	private static XMLManagerImpl xmlMan;
	private static JDBCManager connection;

	public static void main(String[] args) throws Exception {
	    connection = new JDBCManager();
		appMan = new JDBCAppointmentManager(connection);
		docMan = new JDBCDentistManager(connection);
		patMan = new JDBCClientManager(connection);
		recMan = new JDBCRecepcionistManager(connection);
		usedMan = new JDBCUsed_suppliesManager(connection);
		supplierMan = new JDBCSuppliersManager(connection);
		userMan = new JPAUserManager();
		supplyMan = new JDBCSuppliesManager(connection);
		orderMan = new JDBCOrder_suppliesManager(connection);
		xmlMan=new XMLManagerImpl();
		while (true) {
			try {
				System.out.println("\nWelcome to the DentalClinic Database!");
				System.out.println("Choose an option, please:");
				System.out.println("1. Register as a receptionist");
				System.out.println("2. Register as a dentist");
				System.out.println("3. Register as a supplier");
				System.out.println("4. Register as a patient");
				System.out.println("5. Login");
				System.out.println("0. Exit");

				int choice = Integer.parseInt(r.readLine());

				switch (choice) {
				case 1: {
					registerRec();
					break;
				}
				case 2: {
					registerDent();
					break;
				}
				case 3: {
					registerSupplier();
					break;
				}
				case 4: {
					registerPat();
					break;
				}
				case 5: {
					login();
					break;
				}
				case 0: {
					connection.disconnect();
					userMan.close();
					System.exit(0);
				}
				}

			} catch (NumberFormatException e) {
				System.out.println("You didn't type a number");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("I/O Exception.");
				e.printStackTrace();
			}
		}

	}

	public static void registerRec() throws IOException {
		Receptionist rec = null;
		System.out.println("\nPlease, input the receptionist's data:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Bank Account:");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email = r.readLine();
		System.out.println("Mobile:");
		Integer mob = Integer.parseInt(r.readLine());
		System.out.println("Username:");
		String username = r.readLine();
		System.out.println("Password:");
		String password = r.readLine();
		rec = new Receptionist(name, bank, email, mob);
		try {
			recMan.addReceptionist(rec);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User u = new User(username, password, email);
		userMan.register(u);
		Role r = userMan.getRole("receptionist");
		userMan.assignRole(u, r);
	}

	public static void registerDent() throws IOException {
		Dentists den = null;
		System.out.println("Please, input the dentist's data:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Bank Account");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email = r.readLine();
		System.out.println("Mobile:");
		String mob = r.readLine();
		System.out.println("Username:");
		String username = r.readLine();
		System.out.println("Password:");
		String password = r.readLine();
		den = new Dentists(name, bank, mob, email);
		try {
			docMan.addDentist(den);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = new User(username, password, email);
		userMan.register(u);
		Role r = userMan.getRole("dentist");
		userMan.assignRole(u, r);
	}

	public static void registerSupplier() throws IOException {
		Supplier sup = null;
		System.out.println("Please, input the supplier's data:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Address");
		String address = r.readLine();
		System.out.println("Email:");
		String email = r.readLine();
		System.out.println("Username:");
		String username = r.readLine();
		System.out.println("Password:");
		String password = r.readLine();
		sup = new Supplier(name, address, email);
		try {
			supplierMan.createSupplier(sup);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = new User(username, password, email);
		userMan.register(u);
		Role r = userMan.getRole("suppliers");
		userMan.assignRole(u, r);
	}

	public static void registerPat() throws IOException {
		Client pat = null;
		System.out.println("Please, input the patient´s data:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Health number:");
		Integer hum = Integer.parseInt(r.readLine());
		System.out.println("Address");
		String address = r.readLine();
		System.out.println("Email:");
		String email = r.readLine();
		System.out.println("Username:");
		String username = r.readLine();
		System.out.println("Password:");
		String password = r.readLine();
		pat = new Client(name, hum, address, email);
		try {
			patMan.createClient(pat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = new User(username, password, email);
		userMan.register(u);
		Role r = userMan.getRole("client");
		userMan.assignRole(u, r);

	}

	public static void login() throws Exception {
		User user = null;
		while (true) {
			System.out.println("Username:");
			String username = r.readLine();
			System.out.println("Password:");
			String password = r.readLine();
			user = userMan.login(username, password);
			if (user != null) {
				if (user.getRole().getName().equals("dentist")) {
					Dentists den = docMan.getDentistByEmail(user.getEmail());
					dentistMenu(den);
				} else if (user.getRole().getName().equals("receptionist")) {
					Receptionist rec = recMan.getRecepByEmail(user.getEmail());
					recMenu(rec);
				} else if (user.getRole().getName().equals("suppliers")) {
					Supplier supplier = supplierMan.getSupplierByEmail(user.getEmail());
					supMenu(supplier);
				} else {
					Client patient = patMan.getClientByEmail(user.getEmail());
					clientMenu(patient);
				}
			} else {
				System.out.println("Wrong username/password combination.");
			}
		}
	}

	public static void dentistMenu(Dentists den) throws Exception {
		while (true) {
			try {
				System.out.println("\n1. View all my appointments");
				System.out.println("2. View a patient by id");
				System.out.println("3. View a patient by name");
				System.out.println("4. Create supply");
				System.out.println("5. View a supply");
				System.out.println("6. Delete a supply");
				System.out.println("7. View all supplies ordered by amount");
				System.out.println("8. Order supplies");
				System.out.println("9. View an order");
				System.out.println("10. Delete an order");
				System.out.println("11. View all orders I have placed");
				System.out.println("12. Update my data");
				System.out.println("13. Delete my account");
				System.out.println("14. Change supplies used in one of my appointments");
				System.out.println("15. Exit");
				System.out.println("0. Back to main menu");

				int choice = Integer.parseInt(r.readLine());
				switch (choice) {
				case 1: {
					viewAppFromDoc(den);
					break;
				}
				case 2: {
					viewPatId();
					break;
				}
				case 3: {
					viewPatName();
					break;
				}
				case 4: {
					createSupply();
					break;
				}
				case 5: {
					viewSupply();
					break;
				}
				case 6: {
					deleteSupply();
					break;
				}
				case 7: {
					orderedSupply();
					break;
				}
				case 8: {
					order(den.getDoc_id());
					break;
				}
				case 9: {
					viewOrder();
					break;
				}
				case 10: {
					deleteOrder();
					break;
				}
				case 11: {
					viewMyOrders(den);
					break;
				}
				case 12: {
					updateDoc(den.getDoc_id());
					break;
				}
				case 13: {
					deleteDoc(den.getDoc_id());
					return;
				}
				case 14: {
					updateUsedSupply(den);
					break;
				}
				case 15: {
					connection.disconnect();
					userMan.close();
					System.exit(0);
				}
				case 0: {
					return;
				}
				}
			} catch (NumberFormatException e) {
				System.out.println("You didn't type a number");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("I/O Exception.");
				e.printStackTrace();
			}
		}
	}

	public static void recMenu(Receptionist rec) throws Exception {
		while (true) {
			try {
				System.out.println("\n1. Create an appointment ");
				System.out.println("2. Update an appointment");
				System.out.println("3. Delete an appointment");
				System.out.println("4. View a specific appointment");
				System.out.println("5. View all appointments");
				System.out.println("6. Create a patient");
				System.out.println("7. View a patient by id");
				System.out.println("8. View a patient by name");
				System.out.println("9. Update a patient");
				System.out.println("10. Delete a patient");
				System.out.println("11. View all patients");
				System.out.println("12. View all appointments from a patient");
				System.out.println("13. View a receptionist by id");
				System.out.println("14. View a receptionist by name");
				System.out.println("15. Update my data");
				System.out.println("16. Delete my data");
				System.out.println("17. View all receptionist");
				System.out.println("18. View all dentists");
				System.out.println("19. Update date of arrival of supplies");
				System.out.println("20. View all orders ordered by date of arrival");
				System.out.println("21. View a specific order");
				System.out.println("22. View all appointments from a dentist");
				System.out.println("23. Print data of patient in a XML file");
				System.out.println("24. Print data of supplier in a XML file");
				System.out.println("25. Load patient from XML file");
				System.out.println("26. Load supplier from XML file");
				System.out.println("27. Exit");
				System.out.println("0. Back to main menu");

				int choice = Integer.parseInt(r.readLine());
				switch (choice) {
				case 1: {
					createApp(rec.getRep_id());
					break;
				}
				case 2: {
					updateApp(rec.getRep_id());
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
					viewAllApp();
					break;
				}
				case 6: {
					registerPat();
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
					viewAppFromPat();
					break;
				}
				case 13: {
					viewRecId();
					break;
				}
				case 14: {
					viewRecName();
					break;
				}
				case 15: {
					updateRec(rec.getRep_id());
					break;
				}
				case 16: {
					deleteRec(rec.getRep_id());
					return;
				}
				case 17: {
					listRec();
					break;
				}
				case 18: {
					viewListDoc();
					break;
				}
				case 19: {
					updateDateOrder();
					break;
				}
				case 20: {
					viewAllOrders();
					break;
				}
				case 21: {
					viewOneOrder();
					break;
				}
				case 22: {
					viewAppFromDoc();
					break;
				}
				case 23: {
				printMeClient();
					break;
				}
				case 24: {
					printMeSupplier();
					break;
				}
				case 25: {
				loadClient();
					break;
				}
				case 26: {
					loadSupplier();
					break;
				}
				case 27:{
					connection.disconnect();
					userMan.close();
					System.exit(0);
				}
				case 0:
					return;
				}

			} catch (NumberFormatException e) {
				System.out.println("You didn't type a number");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("I/O Exception.");
				e.printStackTrace();
			}
		}

	}

	public static void supMenu(Supplier supplier){
		while (true) {
		try {
		System.out.println("\n1. View all my delivery notes");
		System.out.println("2. View one of my delivery notes");
		System.out.println("3. Update delivery date");
		System.out.println("4. Update my data");
		System.out.println("5. Delete my account");
		System.out.println("6. Exit");
		System.out.println("0. Back to main menu");

		int choice = Integer.parseInt(r.readLine());
		switch (choice) {
		case 1: {
			viewMyOrders(supplier);
			break;
		}
		case 2: {
			viewOneOrder(supplier.getSup_id());
			break;
		}
		case 3: {
			updateDateOrder(supplier.getSup_id());
			break;
		}
		case 4: {
			updateSupplier(supplier.getSup_id());
			break;
		}
		case 5: {
			deleteSupplier(supplier.getSup_id());
			break;
		}
		case 6:{
			connection.disconnect();
			userMan.close();
			System.exit(0);
		}
		case 0:
			return;
		}}catch (NumberFormatException e) {
			System.out.println("You didn't type a number");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O Exception.");
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}
	}

	public static void clientMenu(Client pat) throws IOException {
		while (true) {
			try {
				System.out.println("\n1. Update my data");
				System.out.println("2. Delete my account");
				System.out.println("3. View all my appointments");
				System.out.println("4. View my data");
				System.out.println("5. Exit");
				System.out.println("0. Back to main menu");

				int choice = Integer.parseInt(r.readLine());
				switch (choice) {
				case 1: {
					updatePat(pat.getPat_id());
					break;
				}
				case 2: {
					deletePat(pat.getPat_id());
					return;
				}
				case 3: {
					viewAppFromPat(pat);
					break;
				}
				case 4: {
					viewPatId(pat.getPat_id());
					break;
				}
				case 5:{
					connection.disconnect();
					userMan.close();
					System.exit(0);
				}
				case 0:
					return;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void updatePat() throws IOException {
		Client pat = null;
		System.out.println("\nPlease, input the client's data you want to update:");
		System.out.println("Id:");
		Integer id = Integer.parseInt(r.readLine());
		String emailOld = patMan.getClientById(id).getPat_email();
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Health Number:");
		Integer hum = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String emailNew = r.readLine();
		System.out.println("Address:");
		String add = r.readLine();
		pat = new Client(id, name, hum, add, emailNew);
			patMan.updateClient(pat);
			userMan.changeEmail(emailNew, emailOld);

	}

	public static void updatePat(Integer patId) throws IOException {
		Client pat = null;
		String emailOld = patMan.getClientById(patId).getPat_email();
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Health Number:");
		Integer hum = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String emailNew = r.readLine();
		System.out.println("Address:");
		String add = r.readLine();
		pat = new Client(patId, name, hum, add, emailNew);
			patMan.updateClient(pat);
			userMan.changeEmail(emailNew, emailOld);
	

	}

	public static void createApp(Integer id) throws IOException {
		Appointment app = null;

		System.out.println("\nPlease, input the appointment's data:");
		String date = null;
		LocalDate dLocalDate = null;
		Date dobDate = null;
		try {
			System.out.println("Date (yyyy-MM-dd):");
			date = r.readLine();
			dLocalDate = LocalDate.parse(date, formatter);
			dobDate = Date.valueOf(dLocalDate);
		} catch (Exception e) {
			System.out.println(" ERROR. Date (yyyy-MM-dd):");
			date = r.readLine();
			dLocalDate = LocalDate.parse(date, formatter);
			dobDate = Date.valueOf(dLocalDate);
		}
		System.out.println("Duration in minutes");
		Integer dur = Integer.parseInt(r.readLine());
		System.out.println("Room:");
		Integer room = Integer.parseInt(r.readLine());
		System.out.println("Price:");
		Double price = Double.parseDouble(r.readLine());
		String treat = null;
		do {
			System.out.println("Treatment(Implants/Cleaning/Braces/Tooth extractions/Whitening):");
			treat = r.readLine();
		} while (!treat.equals("Implants") && !treat.equals("Cleaning") && !treat.equals("Braces")
				&& !treat.equals("Tooth extractions") && !treat.equals("Whitening"));

		System.out.println("Name dentist:");
		String docname = r.readLine();
		Integer docId = null;
			Dentists dentist = docMan.getDentistByName(docname);
			docId = dentist.getDoc_id();
		System.out.println("Name patient:");
		String patname = r.readLine();
		Integer patId = null;
			Client c = patMan.getClientByName(patname);
			patId = c.getPat_id();

		app = new Appointment(dobDate, dur, room, price, treat, docId, id, patId);
		Integer appId = null;
			appId = appMan.createAppointment(app);
			System.out.println(appId);
		System.out.println("\nThese are the supplies available ordered by amount:");
		System.out.println(supplyMan.listAllSuppliesByAmount());
		Integer itemId = null;
		Integer amount = null;
		Integer confirm = null;
		do {
			System.out.println("\nPlease, input the supply´s id and amount you want for this appointment.");
			itemId = Integer.parseInt(r.readLine());
			amount = Integer.parseInt(r.readLine());
			usedMan.createUsed(appId, itemId, amount);
			supplyMan.decreaseAmount(amount, itemId);
			System.out.println("Do you want to add more supplies? YES:1; NO=0");
			confirm = Integer.parseInt(r.readLine());
		} while (confirm != 0);

	}

	public static void updateApp(Integer recId) throws IOException {
		Appointment app = null;
		System.out.println("\nPlease, input the appointment's data you want to update:");
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
		Double price = Double.parseDouble(r.readLine());
		String treat = null;
		do {
			System.out.println("Treatment(Implants/Cleaning/Braces/Tooth extractions/Whitening):");
			treat = r.readLine();
		} while (treat.equals("Implants") && treat.equals("Cleaning") && treat.equals("Braces")
				&& treat.equals("Tooth extractions") && treat.equals("Whitening"));

		System.out.println("Name dentist:");
		String docname = r.readLine();
		Integer docId = null;
			Dentists dentist = docMan.getDentistByName(docname);
			docId = dentist.getDoc_id();
		System.out.println("Name patient:");
		String patname = r.readLine();
		Integer patId = null;
			Client c = patMan.getClientByName(patname);
			patId = c.getPat_id();
		app = new Appointment(id, dobDate, dur, room, price, treat, docId, recId, patId);
			appMan.updateAppointment(app);

	}

	public static void deleteApp() throws IOException{
			System.out.println("\nPlease, input the appointmnet's id you want to delete:");
			Integer id = Integer.parseInt(r.readLine());
			appMan.deleteAppointment(id);
	}

	public static void viewApp() throws IOException {
		Appointment app = null;
		System.out.println("\nPlease, input the appointment's id you want to see:");
		Integer id = Integer.parseInt(r.readLine());
			app = appMan.viewAppointment(id);
			System.out.println(app);
	}

	public static void viewAllApp() throws IOException {
		ArrayList<Appointment> all = new ArrayList<Appointment>();
			all = appMan.getListAllAppointments();
			System.out.println(all);
	}

	

	public static void viewPatId() throws IOException {
		Client pat = null;
		System.out.println("\nPlease, input the client's id :");
		Integer id = Integer.parseInt(r.readLine());
			pat = patMan.getClientById(id);
			System.out.println(pat);
	}

	public static void viewPatId(Integer patId) throws IOException {
		Client pat = null;
			pat = patMan.getClientById(patId);
			System.out.println(pat);
	}

	public static void viewPatName() throws IOException {
		Client pat = null;
		System.out.println("\nPlease, input the client's name:");
		String name = r.readLine();
			pat = patMan.getClientByName(name);
			System.out.println(pat);
	}

	public static void deletePat() throws IOException {
		System.out.println("\nPlease, input the client's id you want to delete:");
		Integer id = Integer.parseInt(r.readLine());
		Client pat = patMan.getClientById(id);
		String email = pat.getPat_email();
			patMan.deleteClient(id);
			userMan.deleteUser(email);

	}

	public static void deletePat(Integer patId) throws IOException {

		Client pat = patMan.getClientById(patId);
		String email = pat.getPat_email();
			patMan.deleteClient(patId);
			userMan.deleteUser(email);

	}

	public static void viewListPat() throws IOException {
		System.out.println("\nList of patients:");
		ArrayList<Client> all = new ArrayList<Client>();
			all = patMan.getListAllClients();
			System.out.println(all);

	}

	public static void viewAppFromPat() throws IOException {
		System.out.println("\nPlease, input the client's id:");
		Integer id = Integer.parseInt(r.readLine());
		Client pat = patMan.getClientById(id);
		pat.setApp(patMan.getAllAppFromClient(id));
		System.out.println(pat.getApp());

	}

	public static void viewAppFromPat(Client pat) throws IOException {

		pat.setApp(patMan.getAllAppFromClient(pat.getPat_id()));
		System.out.println(pat.getApp());

	}

	public static void createDoc() throws IOException {
		Dentists den = null;

		System.out.println("\nPlease, input the dentist's data:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Bank Account");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email = r.readLine();
		System.out.println("Mobile:");
		String mob = r.readLine();
		den = new Dentists(name, bank, mob, email);
			docMan.addDentist(den);

	}

	public static void viewDocId() throws IOException {
		Dentists den = null;
		System.out.println("\nPlease, input the dentist's id :");
		Integer id = Integer.parseInt(r.readLine());
			den = docMan.getDentistById(id);
			System.out.println(den);

	}

	public static void viewDocName() throws IOException {
		Dentists den = null;
		System.out.println("\nPlease, input the patients's name:");
		String name = r.readLine();
			den = docMan.getDentistByName(name);
			System.out.println(den);
	}

	public static void updateDoc(Integer id) throws IOException {
		Dentists den = null;
		System.out.println("\nPlease, input your data you want to update:");
		Dentists docOld = docMan.getDentistById(id);
		String emailOld = docOld.getDoc_email();
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Bank Account:");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String emailNew = r.readLine();
		System.out.println("Mobile:");
		String mob = r.readLine();
		den = new Dentists(id, name, bank, mob, emailNew);
			docMan.updateDentist(den);
			userMan.changeEmail(emailNew, emailOld);
			System.out.println("It has been updated correctly");

	}

	public static void deleteDoc(Integer id) throws IOException {
		Dentists den = docMan.getDentistById(id);
		String email = den.getDoc_email();
			docMan.deleteDentist(id);
			userMan.deleteUser(email);
	}

	public static void viewListDoc() throws IOException {
		System.out.println("\nList of dentists:");
		ArrayList<Dentists> all = new ArrayList<Dentists>();
			all = docMan.listAllDentists();
			System.out.println(all);

	}

	public static void viewAppFromDoc(Dentists den) throws IOException {
		den.setApp(docMan.getAllAppFromDentist(den.getDoc_id()));
		System.out.println(den.getApp());
	}

	public static void viewAppFromDoc() throws IOException {
		System.out.println("\nPlease, select the dentist´s id to see the appointments");
		Integer id = Integer.parseInt(r.readLine());
		Dentists den = docMan.getDentistById(id);
		den.setApp(docMan.getAllAppFromDentist(den.getDoc_id()));
		System.out.println(den.getApp());

	}

	public static void createRec() throws IOException {
		Receptionist rec = null;

		System.out.println("\nPlease, input the receptionist's data:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Bank Account:");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String email = r.readLine();
		System.out.println("Mobile:");
		Integer mob = Integer.parseInt(r.readLine());
		rec = new Receptionist(name, bank, email, mob);
			recMan.addReceptionist(rec);

	}

	public static void viewRecId() throws IOException {
		Receptionist rec = null;
		System.out.println("\nPlease, input the receptionist's id :");
		Integer id = Integer.parseInt(r.readLine());
			rec = recMan.getreceptionistById(id);
			System.out.println(rec);
	}

	public static void viewRecName() throws IOException {
		Receptionist rec = null;
		System.out.println("\nPlease, input the receptionist's name:");
		String name = r.readLine();
			rec = recMan.geRecepByName(name);
			System.out.println(rec);
	}

	public static void updateRec(Integer id) throws IOException {
		Receptionist rec1 = null;
		System.out.println("\nPlease, input the receptionist's data you want to update:");
		Receptionist recOld = recMan.getreceptionistById(id);
		String emailOld = recOld.getRep_email();
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Bank Account:");
		Integer bank = Integer.parseInt(r.readLine());
		System.out.println("Email:");
		String emailNew = r.readLine();
		System.out.println("Mobile:");
		Integer mob = Integer.parseInt(r.readLine());
		rec1 = new Receptionist(id, name, bank, emailNew, mob);
			recMan.updateReceptionist(rec1);
			userMan.changeEmail(emailNew, emailOld);
	}

	public static void deleteRec(Integer id) throws IOException {
		Receptionist rec = recMan.getreceptionistById(id);
		String email = rec.getRep_email();
			recMan.deleteReceptionist(id);
			userMan.deleteUser(email);
	}

	public static void listRec() throws IOException {
		System.out.println("\nList of receptionists:");
		ArrayList<Receptionist> all = new ArrayList<Receptionist>();
			all = recMan.listAllReceptionists();
			System.out.println(all);

	}

	public static void createSupply() throws IOException {
		Supply s = null;

		System.out.println("\nPlease, input the supply's data:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Amount:");
		Integer amount = Integer.parseInt(r.readLine());
		s = new Supply(name, amount);
			supplyMan.addSupply(s);

	}

	public static void viewSupply() throws Exception {
		Supply s = null;
		System.out.println("\nPlease, input the supply's id you want to see:");
		Integer id = Integer.parseInt(r.readLine());
			s = supplyMan.viewSupply(id);
			System.out.println(s);

	}

	public static void deleteSupply() throws IOException {
		System.out.println("\nPlease, input the supply's id you want to delete:");
		Integer id = Integer.parseInt(r.readLine());
			supplyMan.deleteSupply(id);

	}

	public static void orderedSupply() {
		ArrayList<Supply> supplies = new ArrayList<Supply>();
			supplies.addAll(supplyMan.listAllSuppliesByAmount());
			System.out.println(supplies);

	}

	public static void order(Integer docId) throws Exception {
		Order_supplies o = null;
		System.out.println("\nPlease, input the following data in order to place an order:");
		System.out.println("Please, input the supply's id you want to order:");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("Please, input the amount:");
		Integer amount = Integer.parseInt(r.readLine());
		System.out.println("Date (yyyy-MM-dd):");
		String date = r.readLine();
		LocalDate dLocalDate = LocalDate.parse(date, formatter);
		Date dobDate = Date.valueOf(dLocalDate);
		System.out.println(supplierMan.getListAllSuppliers());
		System.out.println("Please, input the supplier´s id you want to provide this item.");
		Integer supplierId = Integer.parseInt(r.readLine());
		o = new Order_supplies(id, docId, amount, dobDate, supplierId);
			orderMan.placeOrder(o);
	}

	public static void viewOrder() throws IOException {
		Order_supplies o = null;
		System.out.println("\nPlease, input the order´s id you want to see:");
		Integer id = Integer.parseInt(r.readLine());
			o = orderMan.getOrder(id);
			System.out.println(o);
	}

	public static void viewAllOrders() {
		ArrayList<Order_supplies> all = new ArrayList<Order_supplies>();
			all.addAll(orderMan.getAllOrdersOrder());
			System.out.println(all);
	}

	public static void deleteOrder() throws IOException{
			System.out.println("\nPlease, input the order´s id you want to delete:");
			Integer id = Integer.parseInt(r.readLine());
			orderMan.deleteOrder(id);
	}

	public static void viewMyOrders(Dentists d) {
		ArrayList<Order_supplies> orders = new ArrayList<Order_supplies>();
		Integer id = d.getDoc_id();
			orders.addAll(orderMan.getAllOrdersFromDentist(id));
			System.out.println(orders);

	}

	public static void viewMyOrders(Supplier s) {
		ArrayList<Order_supplies> orders = new ArrayList<Order_supplies>();
		Integer id = s.getSup_id();
			orders.addAll(orderMan.getAllOrdersFromSupplier(id));
			System.out.println(orders);

	}

	public static void deleteSupplier(Integer id) {
		Supplier sup = supplierMan.getSupplierById(id);
		String email = sup.getEmail();
			supplierMan.deleteSupplier(id);
			userMan.deleteUser(email);
	}

	public static void updateSupplier(Integer id) throws Exception {
		Supplier supplierNew = null;
		System.out.println("\nPlease, input your data you want to update:");
		Supplier supplierOld = supplierMan.getSupplierById(id);
		String emailOld = supplierOld.getEmail();
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Address:");
		String ad = r.readLine();
		System.out.println("Email:");
		String emailNew = r.readLine();
		supplierNew = new Supplier(id, name, ad, emailNew);
			supplierMan.updateSupplier(supplierNew);
			userMan.changeEmail(emailNew, emailOld);

	}

	public static void viewOneOrder(Integer supId) throws IOException {
		System.out.println("\nPlease, input the order´s id you want to see:");
		Integer id = Integer.parseInt(r.readLine());
		ArrayList<Order_supplies> orders = new ArrayList<Order_supplies>();
		Order_supplies specific = orderMan.getOrder(id);
		orders = orderMan.getAllOrdersFromSupplier(supId);

		if (orders.contains(specific)) {
			System.out.println(specific);
		} else
			System.out.println("This order is not related with this supplier");
	}

	public static void viewOneOrder() throws IOException {
		System.out.println("\nPlease, input the order´s id you want to see:");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println(orderMan.getOrder(id));

	}

	public static void updateDateOrder(Integer supId) throws IOException {
		System.out.println("\nPlease, input the order´s id you want to change its date:");
		Integer id = Integer.parseInt(r.readLine());
		ArrayList<Order_supplies> orders = new ArrayList<Order_supplies>();
		Order_supplies specific = orderMan.getOrder(id);
		orders = orderMan.getAllOrdersFromSupplier(supId);
		if (orders.contains(specific)) {
			System.out.println("Please, input the new date :");
			System.out.println("Date (yyyy-MM-dd):");
			String date = r.readLine();
			LocalDate dLocalDate = LocalDate.parse(date, formatter);
			Date dobDate = Date.valueOf(dLocalDate);
			orderMan.updateOrderDate(id, dobDate);
		} else
			System.out.println("This order is not related with this supplier");
	}

	public static void updateDateOrder() throws IOException {
		System.out.println("Please, input the order´s id you want to change its date:");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("Please, input the new date :");
		System.out.println("Date (yyyy-MM-dd):");
		String date = r.readLine();
		LocalDate dLocalDate = LocalDate.parse(date, formatter);
		Date dobDate = Date.valueOf(dLocalDate);
		orderMan.updateOrderDate(id, dobDate);
		if (dLocalDate.equals(LocalDate.now())) {
			supplyMan.increaseAmount(orderMan.getOrder(id).getAmount(), orderMan.getOrder(id).getItem_id());
		}

	}

	public static void updateUsedSupply(Dentists den) throws Exception {
		viewAppFromDoc(den);
		System.out.println(
				"\nThese are your appointments. Select the appointment´s id for which you want to update the supplies used");
		Integer idApp = Integer.parseInt(r.readLine());
		Appointment app = appMan.viewAppointment(idApp);
		System.out.println(usedMan.listAllUsedByAppointment(app));
		System.out.println(
				"These are all the used supplies for this appointment. Which one do you want to update? Select the id");
		Integer idUsed = Integer.parseInt(r.readLine());
		Used_supplies oldUsed = usedMan.viewUsedsupplies(idUsed);
		System.out.println("Write the real amount that was used");
		Integer amountNew = Integer.parseInt(r.readLine());
		Used_supplies newUsed = new Used_supplies(oldUsed.getItem_id(), idApp, idUsed, amountNew);
		Integer difference = null;
		if (oldUsed.getAmount() > amountNew) {
			difference = oldUsed.getAmount() - amountNew;
			supplyMan.increaseAmount(difference, oldUsed.getItem_id());
		} else {
			difference = amountNew - oldUsed.getAmount();
			supplyMan.decreaseAmount(difference, oldUsed.getItem_id());
		}
		usedMan.updateUsedsupplies(newUsed);

	}
	
	public static void printMeClient() throws  IOException {
		System.out.println("\nPlease, input the client´s id you want to print in the XML file:");
		Integer id = Integer.parseInt(r.readLine());
		xmlMan.client2xml(id);
		xmlMan.simpleTransform("./xmls/Client.xml", "./xmls/client-style.xslt", "./xmls/Client-Report.html");
	}
	
public static void printMeSupplier() throws IOException {
	System.out.println("\nPlease, input the supplier´s id you want to print in the XML file:");
	Integer id = Integer.parseInt(r.readLine());
	Supplier sup= supplierMan.getSupplierById(id);
		xmlMan.supplier2xml(sup);
		xmlMan.simpleTransform("./xmls/Supplier.xml", "./xmls/supplier-style.xslt", "./xmls/Supplier-Report.html");
	}
	
public static void loadSupplier() {
	File file = new File ("./xmls/External-Supplier.xml");
	xmlMan.xml2Supplier(file);
}

public static void loadClient() {
	File file = new File ("./xmls/External-Client.xml");
	xmlMan.xml2Client(file);
}

}

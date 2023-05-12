package XML;

import java.io.File;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Interfaces.XMLManager;
import JDBC.JDBCClientManager;
import JDBC.JDBCManager;
import JDBC.JDBCSuppliersManager;
import POJO.*;

public class XMLManagerImpl implements XMLManager {
	JDBCManager manager = new JDBCManager();
	JDBCSuppliersManager supManager = null;
	JDBCClientManager clientManager= null;
	@Override
	public void client2xml(Integer id) {
		// TODO Auto-generated method stub
		Client pat = null;
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM client WHERE pat_id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			String name = rs.getString("pat_name");
			Integer healthNumber = rs.getInt("hum");
			String address = rs.getString("pat_address");
			String email = rs.getString("pat_email");

			pat = new Client(id, name, healthNumber, address, email);

			rs.close();
			stmt.close();

			JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file = new File("./xmls/Client.xml");
			marshaller.marshal(pat, file);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Override
	public void supplier2xml(Supplier sup) {
		// TODO Auto-generated method stub
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Supplier.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file = new File("./xmls/Supplier.xml");
			marshaller.marshal(sup, file);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public Supplier xml2Supplier(File xml) {
		// TODO Auto-generated method stub
		Supplier s= null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Supplier.class);
			Unmarshaller unmarsahller= jaxbContext.createUnmarshaller();
			s=(Supplier) unmarsahller.unmarshal(xml);
		  supManager= new JDBCSuppliersManager(manager);
		  supManager.createSupplier(s);
		  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public Client xml2Client(File xml) {
		Client c=null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
			Unmarshaller unmarsahller= jaxbContext.createUnmarshaller();
			c=(Client) unmarsahller.unmarshal(xml);
			 System.out.println(c);
		  clientManager= new JDBCClientManager(manager);
		  clientManager.createClient(c);
		 
		  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}

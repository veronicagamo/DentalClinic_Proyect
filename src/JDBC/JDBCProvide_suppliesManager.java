package JDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Interfaces.Provide_suppliesManager;
import POJO.Provide_suplies;


public class JDBCProvide_suppliesManager implements Provide_suppliesManager{
	
	private JDBCManager manager;
	
	public JDBCProvide_suppliesManager(JDBCManager m){
		
		this.manager=m;
	}

	@Override
	public void createProvide(Provide_suplies prov) throws Exception {
		// TODO Auto-generated method stub
	String sql= "INSERT INTO provide_supplies (date, amount, supply_id, supplier_id) VALUES (?,?,?,?)";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		prep.setDate(1,prov.getDate());
		prep.setInt(2,prov.getAmount());		
		prep.setInt(3, prov.getSupply());
		prep.setInt(4, prov.getSupplier());
		prep.executeUpdate();
	}

	@Override
	public void deleteProvide(Integer provideId) throws Exception {
		// TODO Auto-generated method stub
		String sql= "DELETE FROM provide_supplies "
				+ " WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, provideId);

		prep.executeUpdate();
		
	}

	@Override
	public void updateProvideDate(Integer provideId, Date date) throws Exception {
		// TODO Auto-generated method stub
		  String sql= "UPDATE provide_supplies "
					+ "SET date= ? WHERE id= ?";
	PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setDate(1, date);
			prep.setInt(2,provideId);


			prep.executeUpdate();
	}

	@Override
	public Provide_suplies getProvideById(Integer provideId) throws Exception {
		// TODO Auto-generated method stub
       Provide_suplies prov= null;
		
		String sql= "SELECT * FROM provide_supplies WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, provideId);
				
		ResultSet rs= prep.executeQuery();
		
		while(rs.next()) {
			
			Integer id= rs.getInt("id");
			Date date= rs.getDate("date");
			Integer amount= rs.getInt("amount");
			Integer supplyId= rs.getInt("supply_id");
			Integer supplierId= rs.getInt("supplier_id");
			prov= new Provide_suplies (id, supplierId, supplyId, amount, date);
			
		}

		rs.close();
		prep.close();
		
		return prov;
		
	}

	@Override
	public ArrayList<Provide_suplies> getAllSuppliesFromSupplier(Integer supplierId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList <Provide_suplies> prov= new  ArrayList<Provide_suplies>();
		JDBCProvide_suppliesManager p= new JDBCProvide_suppliesManager(manager);
		try {
			String sql = "SELECT provide_supplies.id FROM suppliers LEFT JOIN provide_supplies ON supplier_id=suppliers.id WHERE supplier_id= ?";
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			prep.setInt(1, supplierId);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next())
			{
				Integer id= rs.getInt("id");
				Provide_suplies provide=p.getProvideById(id);
			    prov.add(provide);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return prov;
	}

	@Override
	public ArrayList<Provide_suplies> getAll() throws Exception {
		// TODO Auto-generated method stub
		ArrayList <Provide_suplies> prov= new  ArrayList<Provide_suplies>();
       String sql= "SELECT * FROM provide_supplies ORDER BY date";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		

		ResultSet rs = prep.executeQuery();
		
		while (rs.next()){
			
			Integer id= rs.getInt("id");
			Date date= rs.getDate("date");
			Integer amount= rs.getInt("amount");
			Integer supplyId= rs.getInt("supply_id");
			Integer supplierId= rs.getInt("supplier_id");
			Provide_suplies p= new Provide_suplies (id, supplierId, supplyId, amount, date);
			
			prov.add(p);
		}
		
		rs.close();
		prep.close();

		
		return prov;
	}

	

	
}

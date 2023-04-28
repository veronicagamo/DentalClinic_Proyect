package JDBC;

import Interfaces.SuppliersManager;
import POJO.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCSuppliersManager implements SuppliersManager{
	
	private JDBCManager manager;
	
	public JDBCSuppliersManager(JDBCManager m) {
		
		this.manager=m;
	}


	@Override
	public Supplier getSupplierById(Integer supplierId) throws Exception {
		// TODO Auto-generated method stub
        Supplier s= null;
		
		String sql= "SELECT * FROM suppliers WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, supplierId);
				
		ResultSet rs= prep.executeQuery();
		
		if(rs.next()) {
			
			String name= rs.getString("name");
			String address= rs.getString("address");
			
			s= new Supplier(supplierId, name, address);
			
		}

		rs.close();
		prep.close();
		
		return s;
		
	}

	@Override
	public void updateSupplier(Supplier sup) throws Exception {
		// TODO Auto-generated method stub
		String sql="UPDATE suppliers "
				+ "SET name= ?, address= ? WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1, sup.getSup_name());
		prep.setString(2,sup.getSup_address());
		prep.setInt(3,sup.getSup_id());
		prep.executeUpdate();
	}

	@Override
	public void createSupplier(Supplier s) throws Exception {
		// TODO Auto-generated method stub
		String sql= "INSERT INTO suppliers (name,address) VALUES(?,?)";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setString(1, s.getSup_name());
		prep.setString(2, s.getSup_address());
		prep.executeUpdate();
		
	}

	@Override
	public void deleteSupplier(Integer supplierId) throws Exception {
		// TODO Auto-generated method stub
		String sql= "DELETE FROM suppliers WHERE id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, supplierId);

		prep.executeUpdate();
	}

	@Override
	public ArrayList<Supplier> getListAllSuppliers() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Supplier> all = new ArrayList<Supplier>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM suppliers";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				   Integer id= rs.getInt("id");  
				   String name=  rs.getString("name");
				   String  add= rs.getString("address");
				   
				   
				 Supplier s = new Supplier(id,name,add);
				  all.add(s);
			}
			
			rs.close();
			stmt.close();	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return all;
	}


	@Override
	public Supplier getSupplierByName(String supplierName) throws Exception {
		// TODO Auto-generated method stub
		Supplier sup=null;
		 String sql= "SELECT * FROM suppliers WHERE name= ?";
			
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,supplierName);
					
			ResultSet rs= prep.executeQuery();
			
			if(rs.next()) {
				Integer Id = rs.getInt("id");
				String name= rs.getString("name");
				String address= rs.getString("address");
				
				sup= new Supplier(Id, name, address);
				
			}

			rs.close();
			prep.close();
			
			return sup;
			
			
	}

}

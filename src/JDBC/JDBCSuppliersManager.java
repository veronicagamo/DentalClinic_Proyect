package JDBC;

import Interfaces.SuppliersManager;
import POJO.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exceptions.IdNotFoundException;
import Exceptions.NameNotFoundException;

public class JDBCSuppliersManager implements SuppliersManager{
	
	private JDBCManager manager;
	
	public JDBCSuppliersManager(JDBCManager m) {
		
		this.manager=m;
	}


	@Override
	public Supplier getSupplierById(Integer supplierId){
		// TODO Auto-generated method stub
		
        Supplier s= null;
        PreparedStatement prep=null;
		ResultSet rs=null;
		
		try {
			
			String sql= "SELECT * FROM suppliers WHERE id= ?";
		
			prep= manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, supplierId);
					
			rs= prep.executeQuery();
			
			if(rs.next()) {
				
				String name= rs.getString("name");
				String address= rs.getString("address");
				
				s= new Supplier(supplierId, name, address);
				
			}
			if(s==null) {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the suppliers");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The requested supplier can not be returned "+e);
			e.printStackTrace();
			
		}catch(IdNotFoundException e) {
			
			System.out.println(e);

		}
		
		finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				if(prep!=null) {
					prep.close();
				}    	
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return s;
		
	}

	@Override
	public void updateSupplier(Supplier sup){
		// TODO Auto-generated method stub
		
		try {
			
			String sql="UPDATE suppliers "
				+ "SET name= ?, address= ? WHERE id= ?";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, sup.getSup_name());
			prep.setString(2,sup.getSup_address());
			prep.setInt(3,sup.getSup_id());

			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the suppliers");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The requested supplier has not been updated successfully "+e);
			e.printStackTrace();

		}
		catch(IdNotFoundException e) {
		
			System.out.println(e);
		}
		
		
	}

	@Override
	public void createSupplier(Supplier s) {
		// TODO Auto-generated method stub
		
		try {
			
			String sql= "INSERT INTO suppliers (name,address) VALUES(?,?)";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setString(1, s.getSup_name());
			prep.setString(2, s.getSup_address());
			
			prep.executeUpdate();
		}
		catch(SQLException e) {
			
			System.out.println("The supplier can not be created "+e);
			e.printStackTrace();

		}
		
		
	}

	@Override
	public void deleteSupplier(Integer supplierId){
		// TODO Auto-generated method stub
		
		try {
			
			String sql= "DELETE FROM suppliers WHERE id= ?";
		
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
			prep.setInt(1, supplierId);
	
			if(prep.executeUpdate()==0) {
				
				throw new IdNotFoundException("The specified id does not correspond to any of the ids"
						+ "of the suppliers");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The requested supplier has not been deleted successfully "+e);
			e.printStackTrace();
		}
		catch(IdNotFoundException e) {
			
			System.out.println(e);
	
		}
		
		
	}

	@Override
	public ArrayList<Supplier> getListAllSuppliers(){
		// TODO Auto-generated method stub
		ArrayList<Supplier> all = new ArrayList<Supplier>();
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM suppliers";
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				   Integer id= rs.getInt("id");  
				   String name=  rs.getString("name");
				   String  add= rs.getString("address");
				   
				   
				 Supplier s = new Supplier(id,name,add);
				  all.add(s);
			}
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null) {
					stmt.close();
				}    	
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
		
		}
		
		
		return all;
	}


	@Override
	public Supplier getSupplierByName(String supplierName){
		// TODO Auto-generated method stub
		Supplier sup=null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		 
		try {
			String sql= "SELECT * FROM suppliers WHERE name= ?";
			
			prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,supplierName);
					
			rs= prep.executeQuery();
			
			if(rs.next()) {
				Integer Id = rs.getInt("id");
				String name= rs.getString("name");
				String address= rs.getString("address");
				
				sup= new Supplier(Id, name, address);
				
			}
			if(sup==null) {
				
				throw new NameNotFoundException("The specified name does not correspond to any of the names"
						+ "of the suppliers");
			}
		}
		catch(SQLException e) {
			
			System.out.println("The requested supplier can not be returned "+e);
			e.printStackTrace();
			
		}catch(NameNotFoundException e) {
			
			System.out.println(e);

		}
		
		finally {
			
			try {
				
				if(rs!=null) {
					rs.close();
				}
				if(prep!=null) {
					prep.close();
				}    	
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
	
		return sup;
		
	}

}

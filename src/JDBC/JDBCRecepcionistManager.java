package JDBC;


import Interfaces.RecepcionistManager;
import POJO.Client;
import POJO.Dentists;
import POJO.Receptionist;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCRecepcionistManager implements RecepcionistManager {

	private JDBCManager manager;
	
	public JDBCRecepcionistManager(JDBCManager m) {
		
		this.manager=m;
	}
	
	//The id variable is not included as a parameter in  INSERT INTO because it is
	//generated by the database with an AUTOINCREMENT
	@Override
	public void addReceptionist(Receptionist rec) throws Exception {
		
	
		String sql= "INSERT INTO receptionist (name,bank_account,email,mobile) "
			+ " VALUES(?,?,?,?)";
	
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setString(1, rec.getRep_name());
		prep.setInt(2, rec.getBank_account());
		prep.setString(3, rec.getRep_email());
		prep.setInt(4, rec.getRep_mobile());

		prep.executeUpdate();
		
	}
	
	@Override
	public void deleteReceptionist(Integer ReceptionistId) throws Exception{
		
		String sql= "DELETE FROM receptionist WHERE recep_id= ?";
		
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
	
		prep.setInt(1, ReceptionistId);

		prep.executeUpdate();
	
	}
	
	@Override
	public void updateReceptionist(Receptionist r) throws Exception{
		
		String sql="UPDATE receptionist SET name= ?, bank_account= ?, email= ?, mobile= ? WHERE recep_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1, r.getRep_name());
		prep.setInt(2, r.getBank_account());
		prep.setString(3, r.getRep_email());
		prep.setInt(4,r.getRep_mobile());
		prep.setInt(5,r.getRep_id());

		prep.executeUpdate();
		
	}
	
	@Override
	public ArrayList<Receptionist> listAllReceptionists() throws Exception{
		
		ArrayList <Receptionist> receptionists= new ArrayList<>();
		
		Statement stmt= manager.getConnection().createStatement();	
		
		String sql="SELECT * FROM receptionist";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()){
			
			Integer id = rs.getInt("recep_id");
			String name = rs.getString("name");
			Integer bank = rs.getInt("bank_account");
			String email = rs.getString("email");
			Integer mobile = rs.getInt("mobile");
			
			Receptionist r = new Receptionist(id, name, bank,email,mobile);
			
			receptionists.add(r);
		}
		
		rs.close();
		stmt.close();

		return receptionists;
		
	}

	@Override
	public Receptionist geRecepByName(String recepName) throws Exception {
		// TODO Auto-generated method stub
       Receptionist rec= null;
		
		String sql= "SELECT * FROM receptionist WHERE name= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setString(1,recepName);
				
		ResultSet rs= prep.executeQuery();
		
		if(rs.next()) {
			
			Integer id = rs.getInt("recep_id");
			Integer bank = rs.getInt("bank_account");
			String email = rs.getString("email");
			Integer mobile = rs.getInt("mobile");
			
			rec= new Receptionist(id, recepName,bank,email, mobile);
			
		}

		rs.close();
		prep.close();
		
		return rec;
	}

	@Override
	public Receptionist getreceptionistById(Integer receptionistId) throws Exception {
		// TODO Auto-generated method stub

		 Receptionist rec= null;
		
		String sql= "SELECT * FROM receptionist WHERE recep_id= ?";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1,  receptionistId);
				
		ResultSet rs= prep.executeQuery();
		
		if(rs.next()) {
			
			String name = rs.getString("name");
			Integer bank = rs.getInt("bank_account");
			String email = rs.getString("email");
			Integer mobile = rs.getInt("mobile");
			
			rec= new Receptionist(receptionistId, name,bank,email, mobile);
			
		}

		rs.close();
		prep.close();
		
		return rec;
	}

	@Override
	public Receptionist getRecepByEmail(String recepEmail) throws Exception {
		// TODO Auto-generated method stub
		 Receptionist rec= null;
			
			String sql= "SELECT * FROM receptionist WHERE email= ?";
			
			PreparedStatement prep= manager.getConnection().prepareStatement(sql);
			
			prep.setString(1,  recepEmail);
					
			ResultSet rs= prep.executeQuery();
			
			if(rs.next()) {
				Integer id = rs.getInt("recep_id");
				String name = rs.getString("name");
				Integer bank = rs.getInt("bank_account");
				Integer mobile = rs.getInt("mobile");
				
				rec= new Receptionist(id, name,bank,recepEmail, mobile);
				
			}

			rs.close();
			prep.close();
			
			return rec;
	}

	
}

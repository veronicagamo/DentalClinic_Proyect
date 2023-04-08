package JDBC;

import Interfaces.SuppliesManager;
import POJO.Supplies;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class JDBCSuppliesManager implements SuppliesManager{

	private JDBCManager manager;
	
	public JDBCSuppliesManager(JDBCManager m){
		
		this.manager = m;
	}
	
	public void addSupply (Supplies s) throws Exception{
		
		String sql= "INSERT INTO supplies (id, name, amount) "
				+ " VALUES(?,?,?)";
		
		PreparedStatement prep= this.manager.getConnection().prepareStatement(sql);
		
		prep.setString(1, s.getId());
		prep.setString(2, s.getName());
		prep.setString(3, s.getAmount());
		
		prep.executeUpdate();
		
	}
	
	//Returns a list with the supplies. It can be used to print the supplies in the menu
	public List<Supplies> listAllSupplies() throws Exception{
		
		
		List <Supplies> supplies= new LinkedList<>();
	
		Statement stmt= this.manager.getConnection().createStatement();	
		
		String sql="SELECT * FROM supplies";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()){
			
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int amount = rs.getInt("amount");
			
			Supplies supply = new Supplies(id, name, amount);
			
			supplies.add(supply);
		}
		
		rs.close();
		stmt.close();

		return supplies;
									
	}
	
}

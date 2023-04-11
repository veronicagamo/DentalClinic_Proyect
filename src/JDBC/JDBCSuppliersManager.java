package JDBC;

import Interfaces.SuppliersManager;
import POJO.Suppliers;

import java.sql.PreparedStatement;

public class JDBCSuppliersManager implements SuppliersManager{
	
	private JDBCManager manager;
	
	public JDBCSuppliersManager(JDBCManager m) {
		
		this.manager=m;
	}

	@Override
	public void orderSupplies(int supplyId, int dentistId) throws Exception {
		
		String sql="INSERT INTO order_supplies (supply_id, dentist_id)"
				+ "VALUES (?,?) ";
		
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		
		prep.setInt(1, supplyId);
		prep.setInt(2, dentistId);
		
		prep.executeUpdate();

	}

}

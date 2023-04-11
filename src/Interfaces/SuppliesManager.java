package Interfaces;

import java.util.ArrayList;

import POJO.Supply;

public interface SuppliesManager {

	public void addSupply(Supply s) throws Exception;
	public ArrayList<Supply> listAllSupplies() throws Exception;

}

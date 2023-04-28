package Interfaces;

import java.util.ArrayList;

import POJO.Supply;

public interface SuppliesManager {

	public void addSupply(Supply s) throws Exception;
	public void deleteSupply(Integer supId) throws Exception;
	public Supply viewSupply(Integer supId) throws Exception;
	public ArrayList<Supply> listAllSuppliesByAmount() throws Exception;
	public void increaseAmount (Integer a, Integer itemId) throws Exception;
	public void decreaseAmount (Integer a, Integer itemId) throws Exception;
}

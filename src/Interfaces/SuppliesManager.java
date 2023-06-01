package Interfaces;

import java.util.ArrayList;

import POJO.Supply;

public interface SuppliesManager {

	public void addSupply(Supply s);

	public void deleteSupply(Integer supId);

	public Supply viewSupply(Integer supId) throws Exception;

	public ArrayList<Supply> listAllSuppliesByAmount();

	public void increaseAmount(Integer a, Integer itemId);

	public void decreaseAmount(Integer a, Integer itemId);
}

package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Supply implements Serializable{

	private static final long serialVersionUID = -2196147451131341836L;

	private Integer item_id;
	private String item_name;
	private Integer item_amount;
	private ArrayList<Order_supplies> ordered;
	private ArrayList<Used_supplies> used;
	private ArrayList<Provide_suplies> provided;
	
	public Supply(Integer item_id, String item_name, Integer item_amount, ArrayList<Order_supplies> ordered,
			ArrayList<Used_supplies> used, ArrayList<Provide_suplies> provided) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_amount = item_amount;
		this.ordered = ordered;
		this.used = used;
		this.provided = provided;
	}

	public Supply() {
		super();
		this.ordered= new ArrayList<Order_supplies>();
		this.used= new ArrayList<Used_supplies> ();
		this.provided= new ArrayList<Provide_suplies>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(item_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supply other = (Supply) obj;
		return Objects.equals(item_id, other.item_id);
	}

	@Override
	public String toString() {
		return "Supply [item_id=" + item_id + ", item_name=" + item_name + ", item_amount=" + item_amount + "]";
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public Integer getItem_amount() {
		return item_amount;
	}

	public void setItem_amount(Integer item_amount) {
		this.item_amount = item_amount;
	}

	public ArrayList<Order_supplies> getOrdered() {
		return ordered;
	}

	public void setOrdered(ArrayList<Order_supplies> ordered) {
		this.ordered = ordered;
	}

	public ArrayList<Used_supplies> getUsed() {
		return used;
	}

	public void setUsed(ArrayList<Used_supplies> used) {
		this.used = used;
	}

	public ArrayList<Provide_suplies> getProvided() {
		return provided;
	}

	public void setProvided(ArrayList<Provide_suplies> provided) {
		this.provided = provided;
	}
	
	
	
	
	
}

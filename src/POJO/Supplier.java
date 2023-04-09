package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Supplier implements Serializable {

	
	private static final long serialVersionUID = 8373601696022464169L;
 
	private Integer sup_id;
	private String sup_name;
	private String sup_address;
	private ArrayList<Provide_suplies> provide;
	
	public Supplier(Integer sup_id, String sup_name, String sup_address, ArrayList<Provide_suplies> provide) {
		super();
		this.sup_id = sup_id;
		this.sup_name = sup_name;
		this.sup_address = sup_address;
		this.provide = provide;
	}

	public Supplier() {
		super();
		this.provide= new ArrayList <Provide_suplies>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(sup_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		return Objects.equals(sup_id, other.sup_id);
	}

	@Override
	public String toString() {
		return "Supplier [sup_id=" + sup_id + ", sup_name=" + sup_name + ", sup_address=" + sup_address + "]";
	}

	public Integer getSup_id() {
		return sup_id;
	}

	public void setSup_id(Integer sup_id) {
		this.sup_id = sup_id;
	}

	public String getSup_name() {
		return sup_name;
	}

	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}

	public String getSup_address() {
		return sup_address;
	}

	public void setSup_address(String sup_address) {
		this.sup_address = sup_address;
	}

	public ArrayList<Provide_suplies> getProvide() {
		return provide;
	}

	public void setProvide(ArrayList<Provide_suplies> provide) {
		this.provide = provide;
	}
	
	
	
	
	
}

package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class Dentists implements Serializable{


	private static final long serialVersionUID = -4597260876619998054L;
	
	private Integer doc_id;
	private String name;
	private String bank_account;
	private Integer doc_mobile;
	private String doc_email;
	private ArrayList<Appointment> appointments;
	private ArrayList<Order_supplies> ordered;
	
	public Dentists() {
		super();
		appointments = new ArrayList<Appointment>();
		ordered= new ArrayList<Order_supplies>();
	}

	public Dentists(Integer doc_id, String name, String bank_account, Integer doc_mobile, String doc_email,
			ArrayList<Appointment> appointments, ArrayList<Order_supplies> ordered) {
		super();
		this.doc_id = doc_id;
		this.name = name;
		this.bank_account = bank_account;
		this.doc_mobile = doc_mobile;
		this.doc_email = doc_email;
		this.appointments = appointments;
		this.ordered = ordered;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doc_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dentists other = (Dentists) obj;
		return Objects.equals(doc_id, other.doc_id);
	}

	@Override
	public String toString() {
		return "Dentist [doc_id=" + doc_id + ", name=" + name + ", bank_account=" + bank_account + ", doc_mobile="
				+ doc_mobile + ", doc_email=" + doc_email + "]";
	}

	public Integer getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Integer doc_id) {
		this.doc_id = doc_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public Integer getDoc_mobile() {
		return doc_mobile;
	}

	public void setDoc_mobile(Integer doc_mobile) {
		this.doc_mobile = doc_mobile;
	}

	public String getDoc_email() {
		return doc_email;
	}

	public void setDoc_email(String doc_email) {
		this.doc_email = doc_email;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public ArrayList<Order_supplies> getOrdered() {
		return ordered;
	}

	public void setOrdered(ArrayList<Order_supplies> ordered) {
		this.ordered = ordered;
	} 
	
	
	
	
	
	
}

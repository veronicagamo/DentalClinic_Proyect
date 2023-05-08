package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class Dentists implements Serializable{


	private static final long serialVersionUID = -4597260876619998054L;
	
	private Integer doc_id;
	private String name;
	private Integer bank_account;
	private String doc_mobile;
	private String doc_email;
	private ArrayList <Appointment> app;
	
	public Dentists() {
		super();
		this.setApp(new  ArrayList <Appointment>());
	}

	public Dentists (Integer doc_id, String name, Integer bank_account, String doc_mobile, String doc_email) {
		super();
		this.doc_id = doc_id;
		this.name = name;
		this.bank_account = bank_account;
		this.doc_mobile = doc_mobile;
		this.doc_email = doc_email;
		this.setApp(new  ArrayList <Appointment>());
	}
	


	public Dentists(String name, Integer bank_account, String doc_mobile, String doc_email) {
		super();
		this.name = name;
		this.bank_account = bank_account;
		this.doc_mobile = doc_mobile;
		this.doc_email = doc_email;
		this.setApp(new  ArrayList <Appointment>());
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
				+ doc_mobile + ", doc_email=" + doc_email + "]\n";
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

	public Integer getBank_account() {
		return bank_account;
	}

	public void setBank_account(Integer bank_account) {
		this.bank_account = bank_account;
	}

	public String getDoc_mobile() {
		return doc_mobile;
	}

	public void setDoc_mobile(String doc_mobile) {
		this.doc_mobile = doc_mobile;
	}

	public String getDoc_email() {
		return doc_email;
	}

	public void setDoc_email(String doc_email) {
		this.doc_email = doc_email;
	}

	public ArrayList <Appointment> getApp() {
		return app;
	}

	public void setApp(ArrayList <Appointment> app) {
		this.app = app;
	}


	
	 
	
	
	
	
}

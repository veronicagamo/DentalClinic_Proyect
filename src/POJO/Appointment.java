package POJO;

//Ya domino bases de datos
import java.io.Serializable;

import java.sql.Date;
import java.util.Objects;



public class Appointment implements Serializable{

	private static final long serialVersionUID = -2549829229281834329L;
	
	
	private Integer app_id;
	private Date app_date;
	private Integer app_duration;
	private Integer app_room;
	private Double app_price;
	private String app_treatment;
	private Integer dentist_id;
	private Integer receptionist_id;
	private Integer patient_id;
	
	
	public Appointment() {
		super();
	}


	public Appointment(Integer app_id, Date app_date, Integer app_duration, Integer app_room, Double app_price,
			String app_treatment, Integer dentist_id, Integer receptionist, Integer patient_id) {
		super();
		this.app_id = app_id;
		this.app_date = app_date;
		this.app_duration = app_duration;
		this.app_room = app_room;
		this.app_price = app_price;
		this.app_treatment = app_treatment;
		this.dentist_id = dentist_id;
		this.receptionist_id = receptionist;
		this.patient_id = patient_id;
	}

	
	public Appointment(Date app_date, Integer app_duration, Integer app_room, Double app_price, String app_treatment,
			Integer dentist_id, Integer receptionist_id, Integer patient_id) {
		super();
		this.app_date = app_date;
		this.app_duration = app_duration;
		this.app_room = app_room;
		this.app_price = app_price;
		this.app_treatment = app_treatment;
		this.dentist_id = dentist_id;
		this.receptionist_id = receptionist_id;
		this.patient_id = patient_id;
	}


	@Override
	public int hashCode() {
		return Objects.hash(app_id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(app_id, other.app_id);
	}


	@Override
	public String toString() {
		return "Appointment [app_id=" + app_id + ", app_date=" + app_date + ", app_duration=" + app_duration
				+ ", app_room=" + app_room + ", app_price=" + app_price + ", app_treatment=" + app_treatment + "]";
	}


	public Integer getApp_id() {
		return app_id;
	}


	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}


	public Date getApp_date() {
		return app_date;
	}


	public void setApp_date(Date app_date) {
		this.app_date = app_date;
	}


	public Integer getApp_duration() {
		return app_duration;
	}


	public void setApp_duration(Integer app_duration) {
		this.app_duration = app_duration;
	}


	public Integer getApp_room() {
		return app_room;
	}


	public void setApp_room(Integer app_room) {
		this.app_room = app_room;
	}


	public Double getApp_price() {
		return app_price;
	}


	public void setApp_price(Double app_price) {
		this.app_price = app_price;
	}


	public String getApp_treatment() {
		return app_treatment;
	}


	public void setApp_treatment(String app_treatment) {
		this.app_treatment = app_treatment;
	}


	public Integer getDentist() {
		return dentist_id;
	}


	public void setDentist(Integer dentist) {
		this.dentist_id = dentist;
	}


	public Integer getReceptionist() {
		return receptionist_id;
	}


	public void setReceptionist(Integer receptionist) {
		this.receptionist_id = receptionist;
	}


	public Integer getPatient() {
		return patient_id;
	}


	public void setPatient(Integer patient) {
		this.patient_id = patient;
	}

	
	
	
	
	
}

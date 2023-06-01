package POJO;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Objects;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Client")
@XmlType(propOrder = { "pat_name", "pat_email", "hum", "pat_address" })
public class Client implements Serializable {

	private static final long serialVersionUID = -2430812566256576791L;
	@XmlTransient
	private Integer pat_id;
	@XmlElement
	private String pat_name;
	@XmlElement
	private Integer hum;
	@XmlElement
	private String pat_address;
	@XmlElement
	private String pat_email;
	@XmlTransient
	private ArrayList<Appointment> app;
	// @XmlTransient
	// private byte[] photo;

	public Client() {
		super();
		this.app = new ArrayList<Appointment>();
	}

	public Client(Integer pat_id, String pat_name, Integer hum, String pat_address, String pat_email) {
		super();
		this.pat_id = pat_id;
		this.pat_name = pat_name;
		this.hum = hum;
		this.pat_address = pat_address;
		this.pat_email = pat_email;
		this.app = new ArrayList<Appointment>();
	}

	public Client(String pat_name, Integer hum, String pat_address, String pat_email) {
		super();
		this.pat_name = pat_name;
		this.hum = hum;
		this.pat_address = pat_address;
		this.pat_email = pat_email;
		this.app = new ArrayList<Appointment>();
	}

//	public Client(Integer pat_id, String pat_name, Integer hum, String pat_address, String pat_email,
//			 byte[] photo) {
//		super();
//		this.pat_id = pat_id;
//		this.pat_name = pat_name;
//		this.hum = hum;
//		this.pat_address = pat_address;
//		this.pat_email = pat_email;
//		this.app = new ArrayList <Appointment> ();
//		this.photo = photo;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(pat_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(pat_id, other.pat_id);
	}

	@Override
	public String toString() {
		return "\n[Id=" + pat_id + ",  Name=" + pat_name + ",  Health number=" + hum + ",  Address=" + pat_address
				+ ",  Email=" + pat_email + "]";
	}

	public Integer getPat_id() {
		return pat_id;
	}

	public void setPat_id(Integer pat_id) {
		this.pat_id = pat_id;
	}

	public String getPat_name() {
		return pat_name;
	}

	public void setPat_name(String pat_name) {
		this.pat_name = pat_name;
	}

	public Integer getHum() {
		return hum;
	}

	public void setHum(Integer hum) {
		this.hum = hum;
	}

	public String getPat_address() {
		return pat_address;
	}

	public void setPat_address(String pat_address) {
		this.pat_address = pat_address;
	}

	public String getPat_email() {
		return pat_email;
	}

	public void setPat_email(String pat_email) {
		this.pat_email = pat_email;
	}

	public ArrayList<Appointment> getApp() {
		return app;
	}

	public void setApp(ArrayList<Appointment> app) {
		this.app = app;
	}

//	public byte[] getPhoto() {
//		return photo;
//	}
//
//	public void setPhoto(byte[] photo) {
//		this.photo = photo;
//	}

}

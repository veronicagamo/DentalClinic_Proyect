package POJO;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {

	private static final long serialVersionUID = -2430812566256576791L;
	
	private Integer pat_id;
	private String pat_name;
	private Integer hum;
	private String pat_address;
	private String pat_email;

	
	
	public Client() {
		super();
	}


	public Client (Integer pat_id, String pat_name, Integer hum, String pat_address, String pat_email) {
		super();
		this.pat_id = pat_id;
		this.pat_name = pat_name;
		this.hum = hum;
		this.pat_address = pat_address;
		this.pat_email = pat_email;
	}




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
		return "Client [pat_id=" + pat_id + ", pat_name=" + pat_name + ", hum=" + hum + ", pat_address=" + pat_address
				+ ", pat_email=" + pat_email + "]";
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


	
	
	
	
	
	
	
	
	
    
}

package POJO;

import java.io.Serializable;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Supplier")
@XmlType(propOrder = { "sup_name", "email", "sup_address" })
public class Supplier implements Serializable {

	private static final long serialVersionUID = 8373601696022464169L;
	@XmlTransient
	private Integer sup_id;
	@XmlElement
	private String sup_name;
	@XmlElement
	private String sup_address;
	@XmlElement
	private String email;

	public Supplier(Integer sup_id, String sup_name, String sup_address, String email) {
		super();
		this.sup_id = sup_id;
		this.sup_name = sup_name;
		this.sup_address = sup_address;
		this.email = email;
	}

	public Supplier(String sup_name, String sup_address, String email) {
		super();
		this.sup_name = sup_name;
		this.sup_address = sup_address;
		this.email = email;
	}

	public Supplier() {
		super();
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
		return "\n[Id=" + sup_id + ",  Name=" + sup_name + ",  Address=" + sup_address + "]";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

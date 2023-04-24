package POJO;


import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;



public class Provide_suplies implements Serializable {

	private static final long serialVersionUID = -1935718576864133405L;
	private Integer id;
	private Integer supplier;
	private Integer supply;
	private Integer amount;
	private Date date;
	
	
	public Provide_suplies() {
		super();
	}

	public Provide_suplies(Integer id, Integer supplier, Integer supply, Integer amount, Date date) {
		super();
		this.id = id;
		this.supplier = supplier;
		this.supply = supply;
		this.amount = amount;
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provide_suplies other = (Provide_suplies) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Provide_suplies [id=" + id + ", amount=" + amount + ", date=" + date + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSupplier() {
		return supplier;
	}

	public void setSupplier(Integer supplier) {
		this.supplier = supplier;
	}

	public Integer getSupply() {
		return supply;
	}

	public void setSupply(Integer supply) {
		this.supply = supply;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}

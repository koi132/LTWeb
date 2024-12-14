package vn.iotstar.utescore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "thongtinsan")
public class Thongtinsan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FieldID")
    private int fieldID;

    @Column(name = "FieldName", nullable = false, length = 50)
    private String fieldName;

    @Column(name = "Type", nullable = false, length = 20)
    private String type;

    @Column(name = "Price", nullable = false, columnDefinition = "float default 0")
    private double price;

    @Column(name = "Detail", length = 255)  
    private String detail; 
    
    @Column(name = "Status", nullable = false, length = 50)
    private String status = "available";

    // Constructors
    public Thongtinsan() {
    }

    public Thongtinsan(String fieldName, String type, double price, String detail) {
        this.fieldName = fieldName;
        this.type = type;
        this.price = price;
        this.detail = detail;
    }

    // Getters and Setters
    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Thongtinsan [fieldID=" + fieldID + ", fieldName=" + fieldName + ", type=" + type + ", price=" + price
				+ ", detail=" + detail + ", status=" + status + "]";
	}


}

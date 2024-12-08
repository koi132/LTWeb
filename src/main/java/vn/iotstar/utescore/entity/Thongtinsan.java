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

	    // Constructors
	    public Thongtinsan() {
	    }

	    public Thongtinsan(String fieldName, String type) {
	        this.fieldName = fieldName;
	        this.type = type;
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

		@Override
		public String toString() {
			return "Thongtinsan [fieldID=" + fieldID + ", fieldName=" + fieldName + ", type=" + type + "]";
		}
	    
}

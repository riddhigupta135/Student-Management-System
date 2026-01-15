package studentmanagement.model;

//This class contains getters and setters of all private variables of Student

public class Student {

	private int studentId;
	private String name;
	private String email;
	private String phone;
	private String address;
	private boolean isFeeDue;
	private int feeDueAfter;
	private int batchNumber;
	private boolean isDeleted;
	
	public Student(int studentId, String name, String email, String phone, 
			String address, int batchNumber, boolean isFeeDue, int feeDueAfter, boolean isDeleted) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.batchNumber = batchNumber;
		this.feeDueAfter = feeDueAfter;
		this.isFeeDue =isFeeDue;
		this.isDeleted = isDeleted;
	}
	
	
	public Student(String name, String email, String phone, String address, 
			int batchNumber, boolean isFeeDue, int feeDueAfter, boolean isDeleted) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.batchNumber = batchNumber;
		this.feeDueAfter = feeDueAfter;
		this.isFeeDue =isFeeDue;
		this.isDeleted = isDeleted;
	}


	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean getIsFeeDue() {
		return isFeeDue;
	}
	public void setIsFeeDue(boolean isFeeDue) {
		this.isFeeDue = isFeeDue;
	}
	public int getFeeDueAfter() {
		return feeDueAfter;
	}
	public void setFeeDueAfter(int feeDueAfter) {
		this.feeDueAfter = feeDueAfter;
	}
	public int getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isdelete) {
		this.isDeleted = isdelete;
	}
}

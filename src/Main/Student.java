package Main;

public class Student {
	private String number; // ����
	private String studentNumber;//�й�
	private String name;//�̸�
	private String grade; //�г�
	private String gender; //����
	private String phoneNumber; //����ȣ
	private String birthday; //����
	private String remarks; //Ư�̻���
	private String ratio;//A+ =1, ���(����)
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String isGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	

	@Override
	public String toString() {
		return "Student [number=" + number + ", studentNumber=" + studentNumber + ", name=" + name + 
				", grade=" + grade + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", birthday="
				+ birthday + ", remarks=" + remarks + ", ratio=" + ratio + "]";
	}
}

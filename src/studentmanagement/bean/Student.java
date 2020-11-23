package studentmanagement.bean;

public class Student {

	private int Roll_No;
	private String Name;
	private String Email_Id;
	private Long Mobile_No;
	private String Blood_Group;
	
	
	
	public Student(String name, String email_Id, Long mobile_No, String blood_Group) {
		super();
		Name = name;
		Email_Id = email_Id;
		Mobile_No = mobile_No;
		Blood_Group = blood_Group;
	}

	public Student(int roll_No, String name, String email_Id, Long mobile_No2, String blood_Group) {
		super();
		Roll_No = roll_No;
		Name = name;
		Email_Id = email_Id;
		Mobile_No = mobile_No2;
		Blood_Group = blood_Group;
	}
	
	public int getRoll_No() {
		return Roll_No;
	}
	public void setRoll_No(int roll_No) {
		Roll_No = roll_No;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail_Id() {
		return Email_Id;
	}
	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}
	public Long getMobile_No() {
		return Mobile_No;
	}
	public void setMobile_No(Long mobile_No) {
		Mobile_No = mobile_No;
	}
	public String getBlood_Group() {
		return Blood_Group;
	}
	public void setBlood_Group(String blood_Group) {
		Blood_Group = blood_Group;
	}
}

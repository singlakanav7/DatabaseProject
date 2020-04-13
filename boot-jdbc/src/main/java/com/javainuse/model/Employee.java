package com.javainuse.model;

public class Employee {

	String firstName;
	String mInt; 
	String lastName;
	String ssn;      
	String bdate;         
	String address;
	String sex;    
	String salary;
	String superssn;    
	String dno;   
	String email;
	
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getmInt() {
		return mInt;
	}
	public void setmInt(String mInt) {
		this.mInt = mInt;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getSuperssn() {
		return superssn;
	}
	public void setSuperssn(String superssn) {
		this.superssn = superssn;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", mInt=" + mInt + ", lastName=" + lastName + ", ssn=" + ssn
				+ ", bdate=" + bdate + ", address=" + address + ", sex=" + sex + ", salary=" + salary + ", superssn="
				+ superssn + ", dno=" + dno + ", email=" + email + "]";
	}
}
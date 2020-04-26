package com.javainuse.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Kanav Singla
 *
 */

@Entity
@Table(name = "Employee")
public class Employee {

	@Column(name = "first_name")
	String firstName;

	@Column(name = "mInt")
	String mInt;

	@Column(name = "lastName")
	String lastName;

	@Id
	@Column(name = "ssn")
	String ssn;

	@Column(name = "bdate")
	Date bdate;

	@Column(name = "address")
	String address;

	@Column(name = "sex")
	String sex;

	@Column(name = "salary")
	String salary;

	@Column(name = "superssn")
	String superssn;

	@Column(name = "dno")
	String dno;

	@Column(name = "email")
	String email;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String firstName, String mInt, String lastName, String ssn, Date bdate, String address, String sex,
			String salary, String superssn, String dno, String email) {
		this.firstName = firstName;
		this.mInt = mInt;
		this.lastName = lastName;
		this.ssn = ssn;
		this.bdate = bdate;
		this.address = address;
		this.sex = sex;
		this.salary = salary;
		this.superssn = superssn;
		this.dno = dno;
		this.email = email;
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

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bdate == null) ? 0 : bdate.hashCode());
		result = prime * result + ((dno == null) ? 0 : dno.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mInt == null) ? 0 : mInt.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		result = prime * result + ((superssn == null) ? 0 : superssn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bdate == null) {
			if (other.bdate != null)
				return false;
		} else if (!bdate.equals(other.bdate))
			return false;
		if (dno == null) {
			if (other.dno != null)
				return false;
		} else if (!dno.equals(other.dno))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mInt == null) {
			if (other.mInt != null)
				return false;
		} else if (!mInt.equals(other.mInt))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		if (superssn == null) {
			if (other.superssn != null)
				return false;
		} else if (!superssn.equals(other.superssn))
			return false;
		return true;
	}

}
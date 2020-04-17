package com.javainuse.model;


/** WorksOn Model class to assign project
 * @author Kanav Singla
 *
 */
public class WorksOn {

	String ssn;
	int pno;
	float hours;

	public WorksOn(String ssn, int pno, float hours) {
		super();
		this.ssn = ssn;
		this.pno = pno;
		this.hours = hours;
	}

	public WorksOn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public float getHours() {
		return hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "WorksOn [ssn=" + ssn + ", pno=" + pno + ", hours=" + hours + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(hours);
		result = prime * result + pno;
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
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
		WorksOn other = (WorksOn) obj;
		if (Float.floatToIntBits(hours) != Float.floatToIntBits(other.hours))
			return false;
		if (pno != other.pno)
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		return true;
	}

}

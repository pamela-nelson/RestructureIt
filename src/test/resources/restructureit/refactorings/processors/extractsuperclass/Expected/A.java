package restructureit.refactorings.processors.extractsuperclass;

public class A extends restructureit.refactorings.processors.extractsuperclass.ABSuper {
	int yearOfBirth;
	
	/**
	 * @return the yearOfBirth
	 */
	public int getYearOfBirth() {
		return yearOfBirth;
	}

	/**
	 * @param yearOfBirth the yearOfBirth to set
	 */
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
}

package restructureit.refactorings.processors.extractsuperclass;

public class A {
	String firstName;
	String secondName;
	int age;
	int yearOfBirth;
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the secondName
	 */
	public String getSecondName() {
		return secondName;
	}
	
	/**
	 * @param secondName the secondName to set
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

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

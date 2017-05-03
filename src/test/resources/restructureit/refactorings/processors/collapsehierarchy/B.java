package restructureit.refactorings.processors.collapsehierarchy;

public class B extends A implements InterfaceB {
	int age;
	
	public B() {
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
	
	public void printAge() {
		super.name = "Name: ";
		super.setName("John");
		System.out.println(super.name);
		System.out.println(age);
	}
}

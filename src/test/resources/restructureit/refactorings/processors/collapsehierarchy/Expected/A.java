package restructureit.refactorings.processors.collapsehierarchy;

public class A implements InterfaceA, InterfaceB {
	String name;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	int age;
	
	public int getAge() {
		return age;
	}
	
	public void printAge() {
		this.name = "Name: ";
		setName("John");
		System.out.println(this.name);
		System.out.println(age);
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}

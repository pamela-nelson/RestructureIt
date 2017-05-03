package restructureit.refactorings.processors.pullupfield;

public class B extends A {
	int age;
	
	public void setName(String name) {
		this.name = name;
		id = "1";
	}
}

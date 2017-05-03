package restructureit.refactorings.processors.pullupfield;

public class B extends A {
	String name;
	public String id;
	int age;
	
	public void setName(String name) {
		this.name = name;
		id = "1";
	}
}

package restructureit.refactorings.processors.collapsehierarchy;

public class C {
	A a = new A();
	A b = new A();
	E e = new E();
	
	public void printAge() {
		b.setAge(10);
		b.printAge();
	}
	
	public void setE() {
		e.setAge(10);
		e.setHeight(110);
		e.setWeight(7.5);
	}
}

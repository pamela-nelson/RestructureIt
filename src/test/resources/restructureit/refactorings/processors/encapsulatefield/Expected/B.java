package restructureit.refactorings.processors.encapsulatefield;

public class B {
	A a = new A();
	String name;

    public void name() {
    	name = a.getName();
		a.setName("John");
	}
}

package restructureit.refactorings.processors.encapsulatecollection;

import java.util.HashMap;

public class B {
	public void method() {
		A a = new A();
		a.namesMap = new HashMap<>();
		a.names.add("blah");
	}
}

package restructureit.refactorings.processors.pullupfield;

public class D {
	C c;
	
	void setCName() {
		c = new C();
		c.name = " ";
	}
}

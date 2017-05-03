package restructureit.refactorings.processors.pushdownmethod;

public class A {
	
	private String password;
	
	public void dontPushDownPrivateField () {
		password = "password";
		System.out.println(password);
	}
	
	public void pushDownMethod(String name) {
		System.out.println(name);
	}
	
	public void removeMethod() {
		
	}
}


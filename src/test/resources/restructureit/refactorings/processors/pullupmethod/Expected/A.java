package restructureit.refactorings.processors.pullupmethod;

public class A {
	String name = "";
	
	 public int pullUp(String param) {
	        param = name;
	        return 1;
	  }
}


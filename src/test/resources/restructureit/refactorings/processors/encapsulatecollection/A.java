package restructureit.refactorings.processors.encapsulatecollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class A {
	
	public ArrayList<String> names;
	private HashSet<String> namesSet;
	public Map<String, Integer> namesMap;
	
	public A() {
		names = new ArrayList<String>();
		names.add("Pamela");
		names.add("Sean");
		names.add("Ryan");
		names.remove("Ryan");
	}
	
	/**
	 * @return the namesSet
	 */
	public HashSet<String> getNamesSet() {
		return namesSet;
	}
	
	/**
	 * @param namesSet the namesSet to set
	 */
	public void setNamesSet(HashSet<String> namesSet) {
		this.namesSet = namesSet;
	}

}

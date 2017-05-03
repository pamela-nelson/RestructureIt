package restructureit.refactorings.processors.encapsulatecollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class A {
	
	private ArrayList<String> names = new ArrayList<String>();
	private HashSet<String> namesSet;
	public Map<String, Integer> namesMap;
	
	public A() {
		addName("Pamela");
		addName("Sean");
		addName("Ryan");
		removeName("Ryan");
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
	
	public List getNames() {
		return java.util.Collections.unmodifiableList(names);
	}

	public void addName(String name) {
		names.add(name);
	}

	public void removeName(String name) {
		names.remove(name);
	}

}

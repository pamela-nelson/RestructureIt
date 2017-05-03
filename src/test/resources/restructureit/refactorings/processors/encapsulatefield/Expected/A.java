package restructureit.refactorings.processors.encapsulatefield;

public class A {
    private String name = "EncapsulateField";
    private final String password = "secrets";

    public void changeName(String newName) {
        newName += "Refactoring";
        setName(newName);
    }

    public void changeOriginalName(String newName) {
        String temp = getName();
        setName(temp + "refactoring");
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
}


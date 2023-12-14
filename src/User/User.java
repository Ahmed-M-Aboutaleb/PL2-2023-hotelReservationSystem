package User;

import FC.CRUD;

public abstract class User implements CRUD {
    private String id;
    private String name;
    private int age;

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setID(String id) throws Exception {
        if(id.isEmpty())
            throw new Exception("Invalid id");
        this.id = id;
    }

    public void setName(String name) throws Exception {
        if (!name.matches("^[a-zA-Z]+$")) throw new Exception("Invalid name");
        this.name = name;
    }

    public void setAge(int age) throws Exception {
        if (age <= 0 || age > 150) throw new Exception("Invalid age");
        this.age = age;
    }
}

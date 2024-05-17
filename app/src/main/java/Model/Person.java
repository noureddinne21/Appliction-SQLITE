package Model;

public class Person {

    private int id;
    private int age;
    private String name;
    private String address;

    public Person() {}

    public Person(String name, String address,int age) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public Person(int id, String name, String address, int age) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

package module.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bk
 */
public class demo34 {

    private final Set<Person> person = new HashSet<>();

    public demo34 () {
        person.add(new Person("张三","男",20));
        person.add(new Person("张x","男",20));
        person.add(new Person("张r","男",20));
    }

    public static void main(String[] args) {
        demo34 demo34 = new demo34();
        demo34.person.add(new Person("1","2",19));
        System.out.println(demo34.person.size());
    }
}

class Person {
    private String name;
    private String gender;
    private int age;

    public Person(String name,String gender,int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

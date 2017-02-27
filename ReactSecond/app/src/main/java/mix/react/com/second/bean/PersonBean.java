package mix.react.com.second.bean;

/**
 * Created by codemanwang on 2017/2/24.
 */

public class PersonBean {

    private String name;
    private int age;

    public PersonBean(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

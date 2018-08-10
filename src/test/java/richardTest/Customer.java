package richardTest;

public class Customer {
    private int age = 0;

    /*
    public void setAge(int age) {
        this.age = age;
    }*/
    public void setAge1(int age11){
        if(age11<0 || age11>100){
            System.out.println("nianling bu hefa");
            return;
        }
        age = age11;
    }
    public void setAge(int age){
        if(age<0 || age>100){
            System.out.println("jiwgjwieg");
            return;

        }
        this.age = age;
    }
    public int getAge() {
        return age;
    }
}

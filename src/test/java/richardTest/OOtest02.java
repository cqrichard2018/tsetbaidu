package richardTest;

public class OOtest02 {
    public static void main(String[] args){
        Customer cm = new Customer();
        //System.out.println(cm.age);
        //cm.age = 30;
        //cm.age = -10;
        //System.out.println(cm.age);
        cm.setAge(-10);
        int aa = cm.getAge();

        System.out.println(aa);
    }
}

package car;

public class Test {
    public static void main(String[] args){
        Engine e1 = new Yamaha();
        e1.start();

        Engine e2 = new Honda();
        e2.start();

        Engine e3 = new Yamaha();
        Car cr = new Car(e3);
        cr.testEngine();
    }
}

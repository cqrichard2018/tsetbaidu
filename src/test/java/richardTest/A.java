package richardTest;

public interface A {
    public static final String success = "success";
    public static final double PI=3.14;
    byte MAZ_VALUE = 127;
    public abstract void m1();
    void m2();
    interface B{
        public abstract void m1();
    }
    interface C{
        public abstract void m2();
    }
    interface D{
        public abstract void m3();
    }
    interface E extends B,C,D{
        void m4();
    }
    class Myclass implements B,C{
        public void m1(){}
        public void m2(){}

    }
    class F implements E{
        public void m1(){}
        public void m2(){}
        public void m3(){}
        public void m4(){}
    }
}

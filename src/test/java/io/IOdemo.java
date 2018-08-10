package io;


import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOdemo {
    @Test
    public void fun(){
        FileWriter filenew = null;
        try {
            filenew = new FileWriter(new File("C:\\test\\a.txt"),true);
            filenew.write("hellowrodl233323323");
            filenew.flush();
            filenew.write(99);
            filenew.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            System.out.println("will execute every time");

        }

    }
    @Test
    public void fun2(){
        FileReader fr = null;
        try{
            fr = new FileReader(new File("C:\\test\\a.txt"));
            //fr.read();
            int i =0;
            while((i=fr.read())!= -1){
                System.out.print((char)fr.read());
            }

        }catch (Exception e){

        }finally {
            System.out.println("closed");
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void fun3(){
        FileWriter filenew = null;
        try {
            filenew = new FileWriter(new File("C:\\test\\ab.txt"),true);
            char[] ch = {'a','b','c','我','你'};
            filenew.write(ch);
            filenew.flush();
            /*
            filenew.write("hellowrold");
            filenew.flush();
            filenew.write("java");
            filenew.flush();
            filenew.write("selenium");
            filenew.flush();
            */
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            System.out.println("will execute every time");

        }
    }

    @Test
    public void fun4(){
        FileReader fr = null;
        try{
            fr = new FileReader(new File("C:\\test\\ab.txt"));
            //fr.read();
            int i =0;
            char[] cr = new char[2];
            while((i=fr.read(cr))!= -1){
                System.out.print(new String(cr));
            }

        }catch (Exception e){

        }finally {
            System.out.println("closed");
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

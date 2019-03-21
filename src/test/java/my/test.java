package my;

import java.io.IOException;

public class test {
    public static void main(String[]agrs)throws IOException
    {
        //System.out.println(test.class.getResource(""));
      //  System.out.println(test.class.getResource("/"));
        System.out.println(ClassLoader.getSystemResourceAsStream("log4j.properties"));
        ClassLoader system = ClassLoader.getSystemClassLoader();
        System.out.println( system.getResources("test1.json"));
    }
}

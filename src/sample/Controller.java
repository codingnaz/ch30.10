package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Controller {

    @FXML
    public Button chickTorun;

    public  Label output;


    public static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    public void runPro(){
        output.setText("its prints in consul. To close, push the stop\n from IDE because if you just click on the top cancel \nbutton other threats still runs in background");

        new Controller.Thread1();
        new Controller.Thread2();
    }

    public void printl(String c){
        output.setText(c);
    }
    static class Thread1 implements Runnable {

        public Thread1() {
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                set.add(i);
            }
        }
    }

    static class Thread2 implements Runnable {

        public Thread2() {
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            try {
                int j=0;
                while (true) {

                    System.out.println("  "+set.size());
                    synchronized (set) {
                        for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext();) {
                            iterator.next();
                        }
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

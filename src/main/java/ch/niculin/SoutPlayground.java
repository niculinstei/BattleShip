package ch.niculin;

import java.util.LinkedList;
import java.util.List;

public class SoutPlayground {

    public void print(Playground playground){
        int size = playground.getSize();
        System.out.println(playground.playgroundGetPointX());
        for(int j = 1; j <= size; j++){
            for(int i = 1; i <= size; i++){
                System.out.print("|__");
            }
            System.out.println(j + " ");
        }

    }
}

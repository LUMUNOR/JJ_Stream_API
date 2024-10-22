package ru.geekbrains.lesson1.homeWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    public static void main (String[] args){
        Random rnd = new Random();
        List<Integer> integerList = new ArrayList<>();

        for (int i=0; i < 10; i++){
            integerList.add(rnd.nextInt(10));
        }
        System.out.println(integerList);
        System.out.println(integerList.stream()
                .filter(integer -> integer % 2 == 0 ).toList());
        System.out.println(integerList.stream()
                .filter(integer -> integer % 2 == 0 )
                .mapToInt(integer -> integer)
                .average());

    }
}

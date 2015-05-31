package by.leonovich.notizieportale.dao;

import java.util.Scanner;

/**
 * Created by alexanderleonovich on 31.05.15.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Vvvedite chislo");
        int count1 = scn.nextInt();
        System.out.println("Vvvedite vtoroe chislo");
        int count2 = scn.nextInt();
        int result = count1 / count2;
        System.out.println("result = " + result);
    }
}

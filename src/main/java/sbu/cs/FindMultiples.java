package sbu.cs;

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class FindMultiples
{
    public static class divisbleBy3 extends Thread{
        private int n;
        ArrayList list3 = new ArrayList<>();
        public divisbleBy3(int n)
        {
            this.n = n;
        }
        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                if(i % 3 == 0)
                {
                    list3.add(i);
                }
            }

        }
        public ArrayList getValue(){
            return list3;
        }


    }
    public static class divisbleBy5 extends Thread {
        private int n;
        ArrayList list5 = new ArrayList<>();

        public divisbleBy5(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                if (i % 5 == 0) {
                    list5.add(i);
                }
            }
        }

        public ArrayList getValue() {
            return list5;
        }
    }
    public static class divisbleBy7 extends Thread{
        private int n;
        ArrayList list7 = new ArrayList<>();
        public divisbleBy7(int n)
        {
            this.n = n;
        }
        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                if(i % 7 == 0)
                {
                    list7.add(i);
                }
            }
        }

        public ArrayList getValue(){
            return list7;
        }

    }


    public static int getSum(int n) {
        int sum = 0;
        ArrayList newList = new ArrayList<>();
        divisbleBy3 task1 = new divisbleBy3(n);
        divisbleBy5 task2 = new divisbleBy5(n);
        divisbleBy7 task3  =new divisbleBy7(n);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i <task1.getValue().size() ; i++) {
            if (!newList.contains(task1.getValue().get(i)))
            {
                newList.add(task1.getValue().get(i));
            }
        }
        for (int i = 0; i <task2.getValue().size() ; i++) {
            if (!newList.contains(task2.getValue().get(i)))
            {
                newList.add(task2.getValue().get(i));
            }
        }
        for (int i = 0; i <task3.getValue().size() ; i++) {
            if (!newList.contains(task3.getValue().get(i)))
            {
                newList.add(task3.getValue().get(i));
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            sum += Integer.parseInt(newList.get(i).toString());
        }
        return sum ;
    }

    public static void main(String[] args) {
        System.out.println("please enter n :");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        getSum(n);
    }
}
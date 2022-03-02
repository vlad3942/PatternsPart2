package ru.ssau.patterns_example.decorator;

import ru.ssau.patterns_example.sub_classes.DuplicateModelNameException;
import ru.ssau.patterns_example.sub_classes.Moto;
import ru.ssau.patterns_example.sub_classes.TestClass;
import ru.ssau.patterns_example.sub_classes.Transport;

public class DecoratorMainTest {

    public static void main(String[] args) {
        //nonSynchronised();
        synchronised();
    }

    public static void nonSynchronised() {
        final Transport tr = new Moto("BMW", 0);
        try {
            tr.addModel("X5", 6.04);
            tr.addModel("Series 5", 4.25);
            tr.addModel("X3", 4.52);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        Transport sync = tr; //tr; //TestClass.synchronizedTransport(tr);
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(100);
                    sync.addModel("Uniq Model Name", 123);
                    System.out.println("Model is added");
                } catch (DuplicateModelNameException e) {

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        TestClass.viewAllModels(tr);
    }

    public static void synchronised() {
        final Transport tr = new Moto("BMW", 0);
        try {
            tr.addModel("X5", 6.04);
            tr.addModel("Series 5", 4.25);
            tr.addModel("X3", 4.52);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        Transport sync = TestClass.synchronizedTransport(tr);
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(100);
                    sync.addModel("Uniq Model Name", 123);
                    System.out.println("Model is added");
                } catch (DuplicateModelNameException e) {

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        TestClass.viewAllModels(tr);
    }
}

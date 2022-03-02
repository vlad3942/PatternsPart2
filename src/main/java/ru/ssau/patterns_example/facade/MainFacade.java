package ru.ssau.patterns_example.facade;

public class MainFacade {
    public static void main(String[] args) {
        AbstractFacadeOfProcess facade = new FacadeOfProcess();
        facade.runProcess();
    }
}

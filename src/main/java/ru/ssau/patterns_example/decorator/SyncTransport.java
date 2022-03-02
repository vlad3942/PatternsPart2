package ru.ssau.patterns_example.decorator;

import ru.ssau.patterns_example.sub_classes.DuplicateModelNameException;
import ru.ssau.patterns_example.sub_classes.NoSuchModelNameException;
import ru.ssau.patterns_example.sub_classes.Transport;

public class SyncTransport implements Transport {

    private final Transport transport;

    public SyncTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized String getMark() {
        return transport.getMark();
    }

    @Override
    public synchronized void setMark(String mark) {
        transport.setMark(mark);
    }

    @Override
    public synchronized String[] getModelsNames() {
        return transport.getModelsNames();
    }

    @Override
    public synchronized double getCostOfCurrentModel(String name) throws NoSuchModelNameException {
        return transport.getCostOfCurrentModel(name);
    }

    @Override
    public synchronized void setCostOfCurrentModel(String name, double cost) throws NoSuchModelNameException {
        transport.setCostOfCurrentModel(name, cost);
    }

    @Override
    public synchronized double[] getArrayOfModelsCosts() {
        return transport.getArrayOfModelsCosts();
    }

    @Override
    public synchronized void addModel(String name, double cost) throws DuplicateModelNameException {
        transport.addModel(name, cost);
    }

    @Override
    public synchronized void delModel(String name) throws NoSuchModelNameException {
        transport.delModel(name);
    }

    @Override
    public synchronized int getModelsLength() {
        return transport.getModelsLength();
    }

    @Override
    public synchronized void setModelName(String name, String name1) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setModelName(name, name1);
    }
}

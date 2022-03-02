package ru.ssau.patterns_example.adapter;

import java.io.OutputStream;

public interface IStringWriter {

    void writeStrings(String ... str);

    String[] readStrings();
}

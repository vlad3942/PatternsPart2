package ru.ssau.patterns_example.adapter;

import java.io.IOException;
import java.io.OutputStream;

public class StringWriterAdapter implements IStringWriter {

    private final OutputStream outputStream;

    public StringWriterAdapter(OutputStream os) {
        outputStream = os;
    }

    @Override
    public void writeStrings(String... str) {
        try  {
            for (String s:
                 str) {
                outputStream.write((s + "\n\r").getBytes());
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

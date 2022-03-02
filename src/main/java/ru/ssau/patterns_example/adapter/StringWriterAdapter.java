package ru.ssau.patterns_example.adapter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StringWriterAdapter implements IStringWriter {

    private final OutputStream outputStream;
    private final InputStream inputStream;

    public StringWriterAdapter(final OutputStream os, final InputStream is) {
        outputStream = os;
        inputStream = is;
    }

    @Override
    public void writeStrings(String... str) {
        try  {
            for (String s:
                 str) {
                outputStream.write((s + "\n").getBytes());
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] readStrings() {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();
            while (line != null) {
                list.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray(String[]::new);
    }
}

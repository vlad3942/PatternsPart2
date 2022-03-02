package ru.ssau.patterns_example.adapter;

import java.io.*;

public class Client {

    private final IStringWriter writer;

    private static final String[] ARR = new String[] {
            "String 1",
            "String 2",
            "String 3"
    };

    public Client(IStringWriter writer) {
        this.writer = writer;
    }

    public void writeStrings() {
        writer.writeStrings(ARR);
    }

    public static void main(String[] args) {
        try (
                final OutputStream os = new FileOutputStream("tmp.txt");
                final InputStream is = new FileInputStream("tmp.txt")
        ) {
            final IStringWriter sw = new StringWriterAdapter(os);
            final Client client = new Client(sw);
            client.writeStrings();
            final byte[] bytes = is.readAllBytes();
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

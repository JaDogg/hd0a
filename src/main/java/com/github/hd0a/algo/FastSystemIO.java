package com.github.hd0a.algo;

import java.io.*;

public class FastSystemIO implements AutoCloseable {
    private final BufferedWriter bufferedWriter;
    private final OutputStreamWriter outputStreamWriter;
    private final BufferedReader bufferedReader;
    private final InputStreamReader inputStreamReader;

    public FastSystemIO() {
        outputStreamWriter = new OutputStreamWriter(System.out);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    public void println(String line) {
        try {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException ignored) {
        }
    }

    public String readLine() {
        try {
            bufferedReader.readLine();
        } catch (IOException ignored) {

        }
        return null;
    }

    @Override
    public void close() throws Exception {
        bufferedWriter.close();
        outputStreamWriter.close();
        bufferedReader.close();
        inputStreamReader.close();
    }
}

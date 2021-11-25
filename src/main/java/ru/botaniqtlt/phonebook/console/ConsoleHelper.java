package ru.botaniqtlt.phonebook.console;

import java.io.*;

/**
 * Класс для упрощения работы с консольным вводом/выводом
 */
public class ConsoleHelper {

    private final InputStream in;

    private final PrintStream out;

    private final BufferedReader br;

    public ConsoleHelper(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        br = new BufferedReader(new InputStreamReader(in));
    }

    /**
     * Вывод строки на консоль (без переноса строки)
     *
     * @param str
     */
    public void prompt(String str) {
        out.print(str);
    }

    /**
     * Чтение числа с консоли
     *
     * @return
     */
    public int readInt() {
        while (true) {
            String line = readLine();
            if (line == null || line.isEmpty()) {
                continue;
            }
            return Integer.parseInt(line);
        }

    }

    /**
     * Чтение строки целиком
     *
     * @return
     */
    public String readLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            //нужно логирование по хорошему
            return null;
        }
    }

    public String readLine(String question) {
        prompt(question + "\n");
        return readLine();

    }
    public String readLine(String question, String defaultValue) {
        String str = readLine(String.format("%s [%s]",question,defaultValue));
        if(str == null|| str.isEmpty()){
            return defaultValue;
        }
        return str;

    }
}

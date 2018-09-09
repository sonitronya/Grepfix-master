package com.heartofthestone.gtm;

import org.kohsuke.args4j.CmdLineException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    public static void main(String[] args) throws CmdLineException {

        CmdArgs values = new CmdArgs(args);

        boolean r = values.regexp;
        boolean v = values.invert;
        boolean i = values.ignore;

        String word = values.word;

        File dir = new File(values.dir);

        if (!dir.exists()) {
            System.err.print("selected file path does not exist: ");
            System.err.println(dir);
            return;
        }

        doMain(dir, word, r, v, i);

        System.out.println("Done");

    }

    private static void doMain(File dir, String word, boolean r, boolean v, boolean i) {

        try {
            List<String> lines = Files.readAllLines(dir.toPath());

            for (String line : lines) {
                if (checker(line, word, r, v, i)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("something went wrong");
        }
    }

    private static boolean checker(String cur, String w, boolean r, boolean v, boolean i) {
        String current = cur;
        String word = w;

        if (i && !r) {
            current = cur.toLowerCase();

            word = word.toLowerCase();

        }

        boolean res = false;

        if (!r) {
            String[] cur1 = current.replaceAll(",|(\\.)", "").split("(,|\\. )| ");

            for (String ww: cur1) {
                if (Objects.equals(ww, word)) {
                    res = true;
                    break;
                }
            }
        } else {
            String[] cur1 = current.replaceAll(",|(\\.)", "").split("(,|\\. )| ");

            Pattern p = Pattern.compile(word);
            for (String ww: cur1) {
                Matcher m = p.matcher(ww);
                if (m.matches()) {
                    res = true;
                    break;
                }
            }
        }

        if (v) res = !res;
        return res;
    }
}

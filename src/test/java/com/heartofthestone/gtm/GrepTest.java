package com.heartofthestone.gtm;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GrepTest {

    @Test
    void test1() throws IOException, CmdLineException {
        String[] command = "мама .\\src\\test\\resources\\Files\\input1.txt".split(" ");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);
        String[] result = baos.toString().split("\n");
        assertTrue(Arrays.equals(result, (new String[]{"qwery dva dva dva мама.\r", "fds dsfsd мама dsfdf\r", "Done\r"})));
    }

    @Test
    void test2() throws IOException, CmdLineException {
        String[] command = "жди -v -i .\\src\\test\\resources\\Files\\input2.txt".split(" ");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);
        String[] result = baos.toString().split("\n");
        assertTrue(Arrays.equals(result, (new String[]{"-v is set\r", "-i is set\r", "\uFEFFЖди меня, и я вернусь.\r", "Желтые дожди...\r", "Done\r"})));
    }

    @Test
    void test3() throws IOException, CmdLineException {
        String[] command = "[a-z]+\\d+ -r -v .\\src\\test\\resources\\Files\\input3.txt".split(" ");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);
        String[] result = baos.toString().split("\n");
        assertTrue(Arrays.equals(result, (new String[]{"-r is set\r", "-v is set\r", "aaa bbbb ccccc ddddd\r", "qq bb 1\r", "abcd dcba qwerty uiop\r", "lol kek mda\r", "Done\r"})));
    }

    @Test
    void test4() throws IOException, CmdLineException {
        String[] command = "[a-z]+\\d+ -r -v .\\src\\test\\resources\\Files\\input228.txt".split(" ");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        System.setErr(ps);
        Grep.main(command);
        System.err.flush();
        System.setOut(old);
        String[] result = baos.toString().split("\n");
        assertTrue(Arrays.equals(result, (new String[]{"-r is set\r", "-v is set\r", "selected file path does not exist: .\\src\\test\\resources\\Files\\input228.txt\r"})));
    }

    @Test
    void test5() throws IOException, CmdLineException {
        String[] command = "lol -v".split(" ");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        System.setErr(ps);

        try {
            Grep.main(command);
        } catch (CmdLineException e) {
        }
        System.err.flush();
        System.setOut(old);

        String[] result = baos.toString().split("\n");
        assertTrue(Arrays.equals(result, (new String[]{"wrong input: Argument \"dir\" is required\r"})));
    }
}
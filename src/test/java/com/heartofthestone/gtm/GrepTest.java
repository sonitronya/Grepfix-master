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
        File input1 = new File("src"+ File.separator +"test" + File.separator + "resources"+ File.separator +"Files"+ File.separator,"input1.txt");
        String[] command = new String[]{"мама", String.valueOf(input1)};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);
        String ls = System.getProperty("line.separator");
        String[] result =  baos.toString().split(ls);
        assertTrue(Arrays.equals(result, (new String[]{"qwery dva dva dva мама.", "fds dsfsd мама dsfdf", "Done"})));
    }

    @Test
    void test2() throws IOException, CmdLineException {
        File input2 = new File("src"+ File.separator +"test" + File.separator + "resources"+ File.separator +"Files"+ File.separator,"input2.txt");
        String[] command = new String[] {"жди","-v","-i", String.valueOf(input2)};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);
        String ls = System.getProperty("line.separator");
        String[] result = baos.toString().split(ls);
        assertTrue(Arrays.equals(result, (new String[]{"-v is set", "-i is set", "\uFEFFЖди меня, и я вернусь.", "Желтые дожди...", "Done"})));
    }

    @Test
    void test3() throws IOException, CmdLineException {
        File input3 = new File("src"+ File.separator +"test" + File.separator + "resources"+ File.separator +"Files"+ File.separator,"input3.txt");
        String[] command = new String[]{ "[a-z]+\\d+", "-r", "-v", String.valueOf(input3)};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);
        String ls = System.getProperty("line.separator");
        String[] result = baos.toString().split(ls);
        assertTrue(Arrays.equals(result, (new String[]{"-r is set", "-v is set", "aaa bbbb ccccc ddddd", "qq bb 1", "abcd dcba qwerty uiop", "lol kek mda", "Done"})));
    }

    @Test
    void test4() throws IOException, CmdLineException {
        File input228 = new File("src"+ File.separator +"test" + File.separator + "resources"+ File.separator +"Files"+ File.separator,"input228.txt");
        String[] command = new String[]{ "[a-z]+\\d+", "-r", "-v",String.valueOf(input228)};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        System.setErr(ps);
        Grep.main(command);
        System.err.flush();
        System.setOut(old);
        String ls = System.getProperty("line.separator");
        String[] result = baos.toString().split(ls);
        assertTrue(Arrays.equals(result, (new String[]{"-r is set", "-v is set", "selected file path does not exist: " + String.valueOf(input228)})));
    }

    @Test
    void test5() throws IOException {
        String[] command =  new String[]{"lol -v"};
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
        String ls = System.getProperty("line.separator");
        String[] result = baos.toString().split(ls);
        assertTrue(Arrays.equals(result, (new String[]{"wrong input: Argument \"dir\" is required"})));
    }
}
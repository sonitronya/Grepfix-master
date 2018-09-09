package com.heartofthestone.gtm;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

class CmdArgs {

    @Option(name = "-r", usage = "sets regexp")
    boolean regexp;

    @Option(name = "-v", usage = "inverts filter rule")
    boolean invert;

    @Option(name = "-i", usage = "ignores words' capitalisation")
    boolean ignore;

    @Argument(required = true)
    String word;

    @Argument(metaVar = "dir", required = true, index = 1)
    String dir;

    CmdArgs(String[] args) throws CmdLineException {

        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.print("wrong input: ");
            System.err.println(e.getMessage());
            throw e;
        }

        if (regexp) System.out.println("-r is set");
        if (invert) System.out.println("-v is set");
        if (ignore) System.out.println("-i is set");

    }
}

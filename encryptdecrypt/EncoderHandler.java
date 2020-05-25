package encryptdecrypt;

import java.io.IOException;

public class EncoderHandler {
    String[] args;

    /* Default constructor for debugging purposes */
    public EncoderHandler() {
        this(new String[0]);
    }

    /* Arguments is the args from execution args */
    public EncoderHandler(String[] args) {
        this.args = args;
    }

    /* Handles the logic to create an Encoder object.
    * I would like to break down this method into more methods,
    * but it works. I filled it with comments. */
    public Encoder create() {
        Encoder encoder = new Encrypter();
        int key = 0;
        String data = "";
        String inputPath = "";
        String outputPath = "";
        EncoderAlgorithm alg = EncoderAlgorithm.SHIFT;

        // loop through every argument in the program arguments array
        for (int i = 0; i < args.length; i++) {
            // If the user does not include a value after a keyword, skip it.
            // Skip logic if the argument is a keyword
            if (args[i].contains("-") && args[i + 1].contains("-")) continue;
            if (!args[i].contains("-")) continue;

            switch (args[i].toLowerCase()) {
                case "-mode":
                    if (args[i + 1].equalsIgnoreCase("dec")) {
                        encoder = new Decrypter();
                    }
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    inputPath = args[i + 1];
                    break;
                case "-out":
                    outputPath = args[i + 1];
                    break;
                case "-alg":
                    if (args[i + 1].equalsIgnoreCase("unicode")) {
                        alg = EncoderAlgorithm.UNICODE;
                    }
                    break;
                default:
                    System.out.println("ERROR: Invalid argument; skipping arg");
            }
        }

        // Set encoder key
        encoder.setKey(key);

        // Set encoder algorithm
        encoder.setEncoderAlgorithm(alg);

        // Set preEncode. If -in argument exists and -data does not, setPreEncode from inputPath;
        // else, set preEncode to the data argument (or "" if data did not exist).
        if (!inputPath.equals("") && data.equals("")) {
            try {
                encoder.setPreEncodeFromFile(inputPath);
            } catch (IOException e) {
                System.out.println("ERROR: Problem reading preEncode text. preEncode defaults to \"\".");
                e.printStackTrace();
            }
        } else {
            encoder.setPreEncode(data);
        }

        // Set the output method. If outputPath exists, set the outputPath;
        // else, leave outputPath at its default "".
        if (!outputPath.equals("")) {
            encoder.setOutputPath(outputPath);
        }

        return encoder;
    }

    /* For debugging purposes */
    public void setArgs(String[] args) {
        this.args = args;
    }
}

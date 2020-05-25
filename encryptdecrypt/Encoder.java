package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Encoder {
    protected int key;
    protected String preEncode;
    protected String postEncode;
    protected EncoderAlgorithm alg;
    protected boolean willWriteToFile;
    protected String outputPath;

    /* This constructor sets default values so that an Encoder could be instantiated
    * and not be modified at all and not break the program */
    public Encoder() {
        key = 0;
        alg = EncoderAlgorithm.SHIFT;
        preEncode = "";
        postEncode = "";
        willWriteToFile = false;
        outputPath = "";
    }

    /* Encode the text based on the algorithm instance */
    public void encode() {
        switch (alg) {
            case SHIFT:
                encodeShift();
                break;
            case UNICODE:
                encodeUnicode();
                break;
        }
    }

    /* If I add more algorithms, I can simply add more abstract methods
    * IDE will throw compile time error if I don't add the new algorithms to
    * the Encrypter or Decrypter class. */
    protected abstract void encodeShift();
    protected abstract void encodeUnicode();

    /* Reads preEncode from file and stores value in preEncode */
    public void setPreEncodeFromFile(String inputPath) throws IOException {
        preEncode = new String(Files.readAllBytes(Paths.get(new File(inputPath).getName())));
    }

    /* Writes postEncode to a file */
    public void writePostEncodeToFile(String outputPath) {
        File file = new File(outputPath);
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.print(postEncode);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FileNotFoundException when writing to file.\n" +
                                "Text to write:\n\"" + postEncode + "\"");
            e.printStackTrace();
        }
    }

    /* Either prints postEncode to System.out or the specified file,
    * dependent on willWriteToFile */
    public void printPostEncode() {
        if (willWriteToFile) {
            writePostEncodeToFile(outputPath);
        } else {
            System.out.print(postEncode);
        }
    }


    /* GETTERS AND SETTERS */


    public void setKey(int key) {
        this.key = key;
    }

    public void setEncoderAlgorithm(EncoderAlgorithm alg) {
        this.alg = alg;
    }

    public void setPreEncode(String preEncode) {
        this.preEncode = preEncode;
    }

    /* This setter automatically sets willWriteFile to true. If outputPath is being set,
    * then we are DEFINITELY writing to a file. */
    public void setOutputPath(String outputPath) {
        willWriteToFile = true;
        this.outputPath = outputPath;
    }

    public String getPostEncode() {
        return postEncode;
    }
}

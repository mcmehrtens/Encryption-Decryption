package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        // Create a handler (my fancy word for factory, I guess)
        EncoderHandler handler = new EncoderHandler(args);

        // Create my encoder; could be an encoder or decoder
        Encoder encoder = handler.create();

        // Encode the text; all properties have been assigned to the encoder by the handler class
        encoder.encode();

        // Print out the encoded text to the specified output stream as assigned by the handler
        encoder.printPostEncode();
    }
}

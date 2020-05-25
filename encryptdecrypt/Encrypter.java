package encryptdecrypt;

public class Encrypter extends Encoder {
    /* Encrypts ONLY THE LETTERS of preEncode.
    * e.g. the letter Z in a key 1 shift would be the letter A */
    @Override
    protected void encodeShift() {
        // Loop through each letter of preEncode
        for (int i = 0; i < preEncode.length(); i++) {
            // Test to see if the char is a letter. If not, add the non-letter
            // character to postEncode.
            if (Character.isLetter(preEncode.charAt(i))) {
                boolean isUppercase = Character.isUpperCase(preEncode.charAt(i));
                int charAsInt = Character.toLowerCase(preEncode.charAt(i));

                // This char is what gets added to postEncode
                char outputChar;

                // Shifting logic
                outputChar = (char) ((charAsInt - 97 + key) % 26 + 97);

                // Add outputChar to postEncode with case sensitivity
                postEncode += isUppercase ? Character.toUpperCase(outputChar) : outputChar;
            } else {
                postEncode += preEncode.charAt(i);
            }
        }
    }

    /* Encrypts using unicode characters. All characters are encrypted. */
    @Override
    protected void encodeUnicode() {
        for (int i = 0; i < preEncode.length(); i++) {
            postEncode += (char) (preEncode.charAt(i) + key);
        }
    }
}

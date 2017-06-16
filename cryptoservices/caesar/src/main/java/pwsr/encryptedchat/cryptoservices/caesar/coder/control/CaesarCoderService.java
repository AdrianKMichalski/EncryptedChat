package pwsr.encryptedchat.cryptoservices.caesar.coder.control;

/**
 * @author Adrian Michalski
 */
public class CaesarCoderService {

    private static final int ALPHABET_SIZE = 26;

    public String shiftCharacters(String pMessage, int pShift) {
        StringBuilder result = new StringBuilder();

        for (char originalCharacter : pMessage.toCharArray()) {
            char shiftedCharacter = originalCharacter;

            if (Character.isLetter(originalCharacter)) {
                shiftedCharacter = (char) (originalCharacter + pShift);

                if ((Character.isLowerCase(originalCharacter) && shiftedCharacter > 'z')
                        || (Character.isUpperCase(originalCharacter) && shiftedCharacter > 'Z')) {
                    shiftedCharacter = (char) (originalCharacter - (ALPHABET_SIZE - pShift));
                }
            }

            result.append(shiftedCharacter);
        }

        return result.toString();
    }

}

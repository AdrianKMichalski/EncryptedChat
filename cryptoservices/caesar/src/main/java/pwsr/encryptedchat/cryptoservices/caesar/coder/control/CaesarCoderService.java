package pwsr.encryptedchat.cryptoservices.caesar.coder.control;

/**
 * @author Adrian Michalski
 */
public class CaesarCoderService {

    public String shiftCharacters(String pMessage, int pShift) {
        StringBuilder result = new StringBuilder();

        for (char originalCharacter : pMessage.toCharArray()) {
            char c = (char) (originalCharacter + pShift);
            if (c > 'z') {
                result.append((char) (originalCharacter - (26 - pShift)));

            } else {
                result.append((char) (originalCharacter + pShift));
            }
        }

        return result.toString();
    }

}

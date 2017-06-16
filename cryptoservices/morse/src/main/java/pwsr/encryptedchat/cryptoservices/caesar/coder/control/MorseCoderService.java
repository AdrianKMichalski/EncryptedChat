package pwsr.encryptedchat.cryptoservices.caesar.coder.control;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import java.util.stream.Stream;

/**
 * @author Adrian Michalski
 */
public class MorseCoderService {

    private static final BiMap<Character, String> LETTER_TO_MORSE =
            new ImmutableBiMap.Builder<Character, String>()
                    .put('a', ".-")
                    .put('b', "-...")
                    .put('c', "-.-.")
                    .put('d', "-..")
                    .put('e', ".")
                    .put('f', "..-.")
                    .put('g', "--.")
                    .put('h', "....")
                    .put('i', "..")
                    .put('j', ".---")
                    .put('k', "-.-")
                    .put('l', ".-..")
                    .put('m', "--")
                    .put('n', "-.")
                    .put('o', "---")
                    .put('p', ".--.")
                    .put('q', "--.-")
                    .put('r', ".-.")
                    .put('s', "...")
                    .put('t', "-")
                    .put('u', "..-")
                    .put('v', "...-")
                    .put('w', ".--")
                    .put('x', "-..-")
                    .put('y', "-.--")
                    .put('z', "--..")
                    .put(' ', "|")
                    .build();

    public String encode(String pMessage) {
        return pMessage.toLowerCase().chars()
                .mapToObj(i -> (char) i)
                .map(c -> LETTER_TO_MORSE.getOrDefault(c, ""))
                .reduce((accumulator, operator) -> accumulator + " " + operator)
                .orElse("");
    }

    public String decode(String pMessage) {
        return Stream.of(pMessage.split(" "))
                .map(morseLetter -> LETTER_TO_MORSE.inverse().getOrDefault(morseLetter, ' '))
                .reduce("", (a, b) -> a + b, (a, b) -> a + b);
    }

}

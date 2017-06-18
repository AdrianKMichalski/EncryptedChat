package pwsr.encryptedchat.cryptoservices.bacon.coder.control;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import java.util.stream.Stream;

/**
 * @author Adrian Michalski
 */
public class BaconCoderService {

    private static final BiMap<Character, String> LETTER_TO_BACON =
            new ImmutableBiMap.Builder<Character, String>()
                    .put('a', "aaaaa")
                    .put('b', "aaaab")
                    .put('c', "aaaba")
                    .put('d', "aaabb")
                    .put('e', "aabaa")
                    .put('f', "aabab")
                    .put('g', "aabba")
                    .put('h', "aabbb")
                    .put('i', "abaaa")
//                    .put('j', "abaaa")
                    .put('k', "abaab")
                    .put('l', "ababa")
                    .put('m', "ababb")
                    .put('n', "abbaa")
                    .put('o', "abbab")
                    .put('p', "abbba")
                    .put('q', "abbbb")
                    .put('r', "baaaa")
                    .put('s', "baaab")
                    .put('t', "baaba")
                    .put('u', "baabb")
//                    .put('v', "baabb")
                    .put('w', "babaa")
                    .put('x', "babab")
                    .put('y', "babba")
                    .put('z', "babbb")
                    .put(' ', "     ")
                    .build();

    public String encode(String pMessage) {
        return pMessage.toLowerCase().chars()
                .mapToObj(i -> (char) i)
                .map(c -> LETTER_TO_BACON.getOrDefault(c, ""))
                .reduce((accumulator, operator) -> accumulator + operator)
                .orElse("");
    }

    public String decode(String pMessage) {
        return Stream.of(pMessage.split("(?<=\\G.....)"))
                .map(morseLetter -> LETTER_TO_BACON.inverse().getOrDefault(morseLetter, ' '))
                .reduce("", (a, b) -> a + b, (a, b) -> a + b);
    }

}

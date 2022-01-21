package br.com.itau.challenge.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {

	public static Boolean checkStringSize(String text, Integer minSize) {
		return (text.length() >= minSize ? true : false);
	}

	public static Boolean checkStringSize(String text, Integer minSize, Integer maxSize) {
		return (text.length() >= minSize && text.length() <= maxSize ? true : false);
	}

	public static Boolean checkStringContainsSomething(String text, String rule) {
		return text.matches(rule);
	}

	public static Boolean checkRepeatedCharacters(String text) {
		char[] textCharacters = text.toCharArray();
		Map<Character, Boolean> dict = new HashMap<>();

		for (char character : textCharacters) {
			if (dict.get(character) != null)
				return false;

			dict.put(character, true);
		}
		return true;
	}

}

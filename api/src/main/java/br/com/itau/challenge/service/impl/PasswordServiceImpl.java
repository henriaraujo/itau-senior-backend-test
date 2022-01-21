package br.com.itau.challenge.service.impl;

import static br.com.itau.challenge.util.StringUtils.checkRepeatedCharacters;
import static br.com.itau.challenge.util.StringUtils.checkStringContainsSomething;
import static br.com.itau.challenge.util.StringUtils.checkStringSize;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.itau.challenge.exception.PasswordFormatException;
import br.com.itau.challenge.service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

	public static final Integer STRING_SIZE = 9;
	public static final String ANY_DIGIT = ".*([0-9]+).*";
	public static final String ANY_LETTER_LOWER_CASE = ".*([a-z]+).*";
	public static final String ANY_LETTER_UPPER_CASE = ".*([A-Z]+).*";
	public static final String ANY_SPECIAL_CHARACTER = ".*([!@#$%^&*()-+]+).*";
	Map<String, Boolean> resultDict = new HashMap<>();

	@Override
	public Boolean isValid(String passwordText) throws PasswordFormatException {
		resultDict.put("STRING_SIZE", checkStringSize(passwordText, STRING_SIZE));
		resultDict.put("ONE_DIGIT", checkStringContainsSomething(passwordText, ANY_DIGIT));
		resultDict.put("ONE_LETTER_LOWER_CASE", checkStringContainsSomething(passwordText, ANY_LETTER_LOWER_CASE));
		resultDict.put("ONE_LETTER_UPPER_CASE", checkStringContainsSomething(passwordText, ANY_LETTER_UPPER_CASE));
		resultDict.put("ONE_SPECIAL_CHARACTER", checkStringContainsSomething(passwordText, ANY_SPECIAL_CHARACTER));
		resultDict.put("NO_REPEATED_CHARACTER", checkRepeatedCharacters(passwordText));

		StringBuilder errors = new StringBuilder();
		for (Map.Entry<String, Boolean> entry : resultDict.entrySet()) {
			if (!entry.getValue()) {
				errors.append(entry.getKey()).append(" ");
			}
		}

		if (!errors.isEmpty()) {
			throw new PasswordFormatException(errors.toString());
		}
		return true;
	}

}

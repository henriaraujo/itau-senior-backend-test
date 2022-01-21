package br.com.itau.challenge.service;

import br.com.itau.challenge.exception.PasswordFormatException;

public interface PasswordService {

	public Boolean isValid(String Password) throws PasswordFormatException;
}

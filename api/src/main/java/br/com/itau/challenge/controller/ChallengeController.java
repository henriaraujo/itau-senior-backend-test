package br.com.itau.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.challenge.exception.PasswordFormatException;
import br.com.itau.challenge.parameter.InputPasswordParameter;
import br.com.itau.challenge.response.PasswordCheckResponse;
import br.com.itau.challenge.service.PasswordService;

@RestController
@RequestMapping("/v1")
public class ChallengeController {

	@Autowired
	private PasswordService passwordService;

	@PostMapping("/validity/password")
	private ResponseEntity<PasswordCheckResponse> checkPasswordValidity(@RequestBody InputPasswordParameter input) {
		try {
			passwordService.isValid(input.getPasswordText());
			return ResponseEntity.ok()
					.body(PasswordCheckResponse.builder().passwordText("SUCCESS: Password Valid!").build());
		} catch (PasswordFormatException e) {
			return ResponseEntity.badRequest().body(PasswordCheckResponse.builder()
					.passwordText("Validation Error for RULES: " + e.getErrorMessage()).build());
		}

	}

}

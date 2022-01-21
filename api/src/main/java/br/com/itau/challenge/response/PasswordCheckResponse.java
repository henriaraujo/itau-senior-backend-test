package br.com.itau.challenge.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordCheckResponse {

	private String passwordText;
}

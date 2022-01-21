package br.com.itau.challenge.parameter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data

public class InputPasswordParameter {

	@ApiModelProperty(example = "AbTp9!fok")
	private String passwordText;

	
	@JsonCreator 
	public InputPasswordParameter(@JsonProperty("passwordText") String  passwordText) {
		super();
		this.passwordText = passwordText;
	}
	
	
	
}

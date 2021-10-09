package br.com.cortei.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MensagemDTO {

	String mensagem;
	
	public MensagemDTO(String res) {
		this.mensagem = res;
	}
	
}

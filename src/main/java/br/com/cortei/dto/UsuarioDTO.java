package br.com.cortei.dto;

import br.com.cortei.model.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
	
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String usuario;
	
	Endereco endereco;

	public UsuarioDTO(String nome, String email, String usuario, Endereco endereco, Long id) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.usuario = usuario;
		this.endereco = endereco;
	}
}

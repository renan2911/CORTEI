package br.com.cortei.dto;

import br.com.cortei.model.Endereco;
import br.com.cortei.model.Usuario;
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
	
	private String telefone;
	
	private Long id_endereco; 
	
	private String cep;
	
	private String rua;

	private String bairro; 
	
	private String cidade; 
	
	private String estado;
	
	private String complemento;

	public UsuarioDTO(String nome, String email, Endereco endereco, Long id, String telefone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.id_endereco = endereco.getId();
		this.cep = endereco.getCep();
		this.rua = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
		this.complemento = endereco.getComplemento();

	}
	
	public static Usuario fromUsuario(UsuarioDTO user, Endereco end) {
		return new Usuario(user.getNome(), user.getEmail(), user.getTelefone(), end);
	}
}

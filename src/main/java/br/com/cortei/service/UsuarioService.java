package br.com.cortei.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cortei.dto.MensagemDTO;
import br.com.cortei.dto.UsuarioDTO;
import br.com.cortei.exception.DataBaseException;
import br.com.cortei.model.Endereco;
import br.com.cortei.model.Usuario;
import br.com.cortei.repository.EnderecoRepository;
import br.com.cortei.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public MensagemDTO salvarUsuario(Usuario usuario) {
		
		if(verificandoUsuario(usuario.getEmail()))
			throw new DataBaseException("Usuário informado já foi cadastrado!");
		
		if(verificaEndereco(usuario.getEndereco().getCep())) {
			Endereco enderecoRes = enderecoRepository.findByCep(usuario.getEndereco().getCep());
			enderecoRepository.save(enderecoRes);
		}
		enderecoRepository.save(usuario.getEndereco());
		usuarioRepository.save(usuario);
		
		return new MensagemDTO("Usuario criado com sucesso");
	}
	
	public List<UsuarioDTO> buscarUsuarios() {
		List<Usuario> usuario = usuarioRepository.findAll();
		
		List<UsuarioDTO> resposta = usuario.stream().map(obj -> new UsuarioDTO(obj.getNome_completo(), obj.getEmail(), obj.getUsuario(), obj.getEndereco(), obj.getId(), obj.getTelefone(), obj.getSenha())).collect(Collectors.toList());
		
		return resposta;
	}
	
	public MensagemDTO deleteUsuario(Long id) {
		try {
			usuarioRepository.deleteById(id);			
		}catch (Exception e) {
			throw new DataBaseException("Erro ao deletar usuário! Error: "+ e.getMessage());
		}
		
		return new MensagemDTO("Usuario removido! Id do usuário removido: "+ id);
	}
	
	public MensagemDTO atualizarUsuario(Long id, Usuario usuario) {
		
		if(!verificandoUsuario(id, usuario.getId()))
			throw new DataBaseException("Erro durante a atualização do usuario: "+ id);
		
		enderecoRepository.save(usuario.getEndereco());
		usuarioRepository.save(usuario);
		return new MensagemDTO("Usuario com id: "+id+ " foi atualizado!");
	}
	
	
	public Boolean verificandoUsuario(Long id, Long idUsuario) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(id != idUsuario || usuario.isEmpty())
			return false;
		
		return true;
	}
	
	public Boolean verificandoUsuario(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null)
			return false;
		
		return true;
	}
	
	public Boolean verificaEndereco(String cep) {
		Endereco endereco =  enderecoRepository.findByCep(cep);
		
		if(endereco == null)
			return false;
		
		return true;
	}
	
}

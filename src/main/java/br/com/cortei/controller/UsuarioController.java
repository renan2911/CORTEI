package br.com.cortei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cortei.dto.MensagemDTO;
import br.com.cortei.dto.UsuarioDTO;
import br.com.cortei.model.Usuario;
import br.com.cortei.service.UsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioController {

	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<MensagemDTO> criarConta(@RequestBody Usuario usuario) {
		return new ResponseEntity<MensagemDTO>(usuarioService.salvarUsuario(usuario), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarios(){
		return new ResponseEntity<List<UsuarioDTO>>(usuarioService.buscarUsuarios(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MensagemDTO> deletarUsuario(@PathVariable Long id){
		return new ResponseEntity<MensagemDTO>(usuarioService.deleteUsuario(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MensagemDTO> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
		return new ResponseEntity<MensagemDTO>(usuarioService.atualizarUsuario(id, usuario), HttpStatus.OK);
	}
}

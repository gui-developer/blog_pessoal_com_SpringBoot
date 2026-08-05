package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                // se encontrou, devolve 200 OK com a categoria no corpo
                .map(ResponseEntity::ok)
                // se não encontrou, devolve 404 Not Found (corpo vazio)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Usuario>> getByTipo(@PathVariable String email) {
        return ResponseEntity.ok(usuarioRepository.findByEmail(email));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuarios) {
        return usuarioRepository.findById(usuarios.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(usuarioRepository.save(usuarios)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> autenticar(@Valid @RequestBody Optional<UsuarioLogin> usuarioLogin) {
        return usuarioService.autenticarUsuario(usuarioLogin)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Usuario> tema = usuarioRepository.findById(id);
        if (tema.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        usuarioRepository.deleteById(id);
    }
}

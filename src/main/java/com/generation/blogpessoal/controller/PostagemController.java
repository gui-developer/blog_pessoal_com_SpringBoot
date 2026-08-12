package com.generation.blogpessoal.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.PostagemRepository;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// @RestController avisa ao Spring Boot que esta classe é um Controlador REST.
// Isso significa que ela vai receber requisições web e devolver os dados em formato JSON,
// que é o formato ideal para o Front-end consumir e exibir na tela.
@RestController

// @RequestMapping define o endereço principal (a URL) desta classe.
// Qualquer requisição que chegue no caminho "/postagens" será direcionada para cá.
@RequestMapping("/postagem")

// @CrossOrigin é uma anotação de segurança vital para projetos Full Stack.
// Permite que aplicações em servidores ou portas diferentes acessem esta API.
// Exemplo prático: O back-end roda na porta 8080 e o seu React roda na porta 3000.
// Sem essa liberação, o navegador do usuário bloqueia a comunicação com um erro de CORS.
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

    // @Autowired realiza a Injeção de Dependência.
    // O Spring cria e injeta a instância do PostagemRepository automaticamente na memória.
    // Isso evita a necessidade de criar objetos manualmente e garante a estabilidade do sistema.
    @Autowired
    private PostagemRepository postagemRepository;

    // @GetMapping indica que este método será acionado ao receber uma requisição HTTP do tipo GET.
    // GET é o verbo padrão utilizado para buscar ou listar informações no servidor.
    @GetMapping("/all")
    public ResponseEntity<List<Postagem>> getAll(){
        // ResponseEntity.ok() constrói uma resposta HTTP com o status 200 (OK), sinalizando sucesso.
        // O postagemRepository.findAll() vai até o MySQL, executa um "SELECT * FROM tb_postagens",
        // converte as linhas retornadas para uma Lista do Java e entrega tudo formatado dentro da resposta.
        return ResponseEntity.ok(postagemRepository.findAll());
    }
    //Fazendo uma busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable long id ){
        return postagemRepository.findById (id)
                //validação
                .map (resposta -> ResponseEntity.ok (resposta))
                .orElse (ResponseEntity.notFound ().build ());
    }

    //consulta personalizada
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>>getAllByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok (postagemRepository.findAllByTituloContainingIgnoreCase (titulo));
    }


    //Criação de dados
    @PostMapping("/NovaPostagem")
    public ResponseEntity<Postagem>post(@RequestBody Postagem postagem){
        return ResponseEntity.status (HttpStatus.CREATED)
                .body(postagemRepository.save(postagem));
    }

    //Atualização de dados
    @PutMapping("/atualizar")
    public ResponseEntity<Postagem>put(@RequestBody Postagem postagem){
        if (postagemRepository.existsById (postagem.getId ()))
            return ResponseEntity.ok (postagemRepository.save (postagem));

        return ResponseEntity.notFound ().build ();
    }

    //Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Postagem> postagem = postagemRepository.findById (id);
        if (postagem.isEmpty ())
            throw new ResponseStatusException (HttpStatus.NOT_FOUND);

        postagemRepository.deleteById (id);
    }

    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Tema tema;

    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Usuario usuario;

    public PostagemRepository getPostagemRepository () {
        return postagemRepository;
    }

    public void setPostagemRepository (PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public Tema getTema () {
        return tema;
    }

    public void setTema (Tema tema) {
        this.tema = tema;
    }

    public Usuario getUsuario () {
        return usuario;
    }

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
    }
}
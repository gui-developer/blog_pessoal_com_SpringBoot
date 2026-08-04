package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TemaController {

    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private PostagemRepository postagemRepository;


    @GetMapping
    public ResponseEntity<List<Tema>> getAll(){
        return ResponseEntity.ok (temaRepository.findAll());
    }

    //Busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tema> getById(@PathVariable long id ){
        return temaRepository.findById (id)
                //validação
                .map (resposta -> ResponseEntity.ok (resposta))
                .orElse (ResponseEntity.status (HttpStatus.NOT_FOUND).build ());
    }

    //Consulta personalizada
    @GetMapping("/descricao{descricao}")
    public ResponseEntity<List<Tema>>getAllByDescricao(@PathVariable String descricao){
        return ResponseEntity.ok (temaRepository.findAllByDescricaoContainingIgnoreCase (descricao));
    }

    //Criação de dados
    @PostMapping("/criar")
    public ResponseEntity<Tema>post(@Valid @RequestBody Tema temas){

        temas.setId (null);

        return ResponseEntity.status (HttpStatus.CREATED)
                .body(temaRepository.save (temas));
    }

    //Atualização de dados
    @PutMapping
    public ResponseEntity<Tema> put(@Valid @RequestBody Tema temas){
        return temaRepository.findById(temas.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(temaRepository.save(temas)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Tema> tema = temaRepository.findById (id);
        if (tema.isEmpty ())
            throw new ResponseStatusException (HttpStatus.NOT_FOUND);
        postagemRepository.deleteById (id);
    }
}

package com.generation.blogpessoal.repository;
import com.generation.blogpessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    // Busca todos os registros de Tema cuja "descricao" contém o texto informado,
    // ignorando maiúsculas e minúsculas.
    //
    // Como funciona o método (nome do padrão do Spring Data):
    // - findAllByDescricao: procura pela propriedade "descricao" da entidade Tema
    // - Containing: significa "contém" (busca substring)
    // - IgnoreCase: não diferencia 'a' de 'A' (case-insensitive)
    //
    // Exemplo:
    // - se descricao no banco for "Programação Java"
    // - e você chamar passando "java"
    // - o resultado vai incluir esse registro.
    List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);

}

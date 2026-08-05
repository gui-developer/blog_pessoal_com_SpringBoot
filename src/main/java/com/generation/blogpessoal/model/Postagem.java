package com.generation.blogpessoal.model; // Ajuste para o pacote do seu projeto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

// 1. MAPEAMENTO DO BANCO DE DADOS (JPA/Hibernate)

// @Entity diz ao Spring: "Esta classe é uma entidade do banco de dados. Crie uma tabela para ela!"
@Entity
// @Table define o nome real da tabela física no seu banco de dados (ex: MySQL, PostgreSQL)
@Table(name = "tb_postagens")
public class Postagem {

    // @Id avisa o banco de dados que esta variável será a Chave Primária (Primary Key) da tabela.
    @Id
    // @GeneratedValue diz que o ID será gerado automaticamente pelo banco (Ex: 1, 2, 3...)
    // A estratégia IDENTITY é a mais comum para bancos como MySQL (usa o auto_increment).
    // Nota de Tech Lead: Usamos "Long" (objeto) em vez de "long" (primitivo) porque o ID
    // começa nulo antes de a postagem ser guardada no banco de dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 2. VALIDAÇÕES DE SEGURANÇA (Bean Validation)
    // Elas garantem que ninguém vai enviar dados inválidos para a sua API!

    // @NotBlank impede que o título seja nulo ou que o utilizador envie apenas espaços vazios ("")
    @NotBlank(message = "O atributo título é Obrigatório! ")
    // @Size define os limites mínimo e máximo de letras aceites no banco de dados para este campo.
    @Size(min = 5, max = 100, message ="O atributo título deve conter no mínimo 05 e no máximo 100 caracteres") // Corrigido de "texto" para "título"
    private String titulo;

    @NotBlank(message = "O atributo texto é Obrigatório! ")
    @Size(min = 10, max = 1000, message ="O atributo texto deve conter no mínimo 10 e no máximo 1000 caracteres")
    private String texto;


    // 3. LOGÍSTICA E AUDITORIA

    // @UpdateTimestamp é uma anotação inteligente do Hibernate.
    // Sempre que você criar uma postagem ou atualizar uma existente, o Spring vai olhar para o
    // relógio do servidor e gravar a data/hora exata automaticamente. Você não precisa fazer "new Date()".
    @UpdateTimestamp
    private LocalDateTime data ;

    // IMPORTANTE: Para manter o Encapsulamento que você aprendeu, não se esqueça de criar
    // os métodos Getters e ‘Setters’

    // Adicione isto na sua classe Postagem
    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Tema tema;

    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Usuario usuario;


    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getTitulo () {
        return titulo;
    }

    public void setTitulo (String titulo) {
        this.titulo = titulo;
    }

    public String getTexto () {
        return texto;
    }

    public void setTexto (String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData () {
        return data;
    }

    public void setData (LocalDateTime data) {
        this.data = data;
    }
}
package com.generation.blogpessoal.repository; // Pacote padrão para repositórios

import com.generation.blogpessoal.Model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

// 1. É UMA INTERFACE E NÃO UMA CLASSE!
// No Spring, você não precisa escrever o código que faz o "INSERT INTO" ou "SELECT * FROM" no banco.
// Você apenas cria uma Interface e o Spring cria toda a lógica de banco de dados automaticamente para você na memória!

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    // 2. OS GENERICS <Postagem, Long>
    // É aqui que você explica para o "operário" (JpaRepository) com quem ele vai trabalhar:
    // - Postagem: É a Entidade (@Entity) que este repositório vai gerenciar.
    // - Long: É o tipo de dado da Chave Primária (@Id) da sua entidade. Lembra que você criou "private Long id;"?

}
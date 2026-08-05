package util;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;

public class TestBuilder {
    public static Usuario criarUsuario(Long id, String nome, String email, String senha) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setId(id);
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        novoUsuario.setFoto("-");
        return novoUsuario;
    }

    public static UsuarioLogin criarUsuarioLogin(String email, String senha) {
        UsuarioLogin usuarioLogin = new UsuarioLogin();
        usuarioLogin.setEmail(email);
        usuarioLogin.setSenha(senha);
        return usuarioLogin;
    }
}
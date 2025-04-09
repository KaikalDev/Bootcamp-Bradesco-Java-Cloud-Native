package kaique.luan.dev.repository;

import kaique.luan.dev.models.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    public void save(Usuario usuario){
        System.out.println("SAVE - Recebendo o usuário na camada de repositório");
        System.out.println(usuario);
    }
    public void update(Usuario usuario){
        System.out.println("UPDATE - Recebendo o usuário na camada de repositório");
        System.out.println(usuario);
    }
    public void remove(Integer id){
        System.out.printf("DELETE/id - Recebendo o id: %d para excluir um usuário%n", id);
        System.out.println(id);
    }
    public List<Usuario> listAll(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("gleyson","password"));
        usuarios.add(new Usuario("frank","masterpass"));
        return usuarios;
    }
    public Usuario finById(Integer id){
        System.out.printf("GET/id - Recebendo o id: %d para localizar um usuário%n", id);
        Usuario usuario = new Usuario("gleyson","password");
        usuario.setId(id);
        return usuario;
    }
}

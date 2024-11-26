package ucb.login.service;

import ucb.login.model.Usuario;

import java.util.List;

public interface UsuarioService {


    /**
     * Obtem todos os usuarios do banco de dados
     */
    List<Usuario> getAllUsers();

    
    /**
     * Obtem um usuario
     */
    Usuario getUser(String userLogin, String senha);


    /**
     * Atualiza um usuário do banco
     * @return se usuario foi atualizado
     */
    boolean updateUser(Usuario user);


    /**
     * Deleta um usuário
     * @return se o usuário foi deletado
     */
    boolean deleteUser(Usuario user);


    /**
     * Insere um usuário no sistema
     * @return se o usuário foi deletado
     */
    boolean insertUser(Usuario user);

}

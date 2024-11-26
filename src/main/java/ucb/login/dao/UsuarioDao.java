package ucb.login.dao;

import ucb.login.model.Usuario;

import java.util.List;

public interface UsuarioDao {

    public List<Usuario> getAllUsers();
    public Usuario getUser(String userLogin);
    public boolean updateUser(Usuario user);
    public boolean deleteUser(Usuario user);
    public boolean insertUser(Usuario user);


}

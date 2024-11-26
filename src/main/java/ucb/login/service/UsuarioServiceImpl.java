package ucb.login.service;

import ucb.login.dao.UsuarioDao;
import ucb.login.dao.UsuarioDaoImpl;
import ucb.login.model.Usuario;

import java.util.List;
import java.util.Objects;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;

    public UsuarioServiceImpl() {
        usuarioDao = new UsuarioDaoImpl();
    }



    public List<Usuario> getAllUsers() {
        return usuarioDao.getAllUsers();
    }



    public Usuario getUser(String userLogin, String senha) {
        if (userLogin == null || userLogin.isEmpty()) {
            throw new IllegalArgumentException("Deve ser informado um login válido");
        }

        Usuario usuario = usuarioDao.getUser(userLogin);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario não encontrado");
        }

        if (senhaEhValida(usuario, senha)) {
            return usuario;
        }

        throw new IllegalArgumentException("Senha invalida.");
    }

    

    public boolean updateUser(Usuario user) {
        validaUsuarioValido(user);
        validaSenha(user);
        return usuarioDao.updateUser(user);
    }



    public boolean deleteUser(Usuario user) {
        validaUsuarioValido(user);
        return usuarioDao.deleteUser(user);
    }



    public boolean insertUser(Usuario user) {
        validaUsuarioValido(user);
        validaSenha(user);
        return usuarioDao.insertUser(user);
    }



    private boolean senhaEhValida(Usuario usuario, String senha) {
        if (usuario != null && Objects.equals(usuario.getSenha(), senha)) {
            return true;
        }

        return false;
    }



    // Metodo que valida se o input de usuario informado é valido
    private void validaUsuarioValido(Usuario user) {
        if (user == null || user.getUser() == null) {
            throw new IllegalArgumentException("Deve ser informado um usuário válido");
        }
    }

    
    private void validaSenha(Usuario user) {
        if (user.getSenha() == null || user.getSenha().length() <  3) {
            throw new IllegalArgumentException("A senha do usuário deve ter no mínimo 3 dígitos");
        }
    }
}

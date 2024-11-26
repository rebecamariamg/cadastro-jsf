package ucb.login.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ucb.login.configuration.Logger;
import ucb.login.model.Usuario;
import ucb.login.service.UsuarioService;
import ucb.login.service.UsuarioServiceImpl;


@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController {

    private final UsuarioService service;
    private final Logger log;


    public UsuarioController() {
        service = new UsuarioServiceImpl();
        log = new Logger();
    }


    public List<Usuario> getAllUsers() {
        log.info("Obtendo todos os usuarios");
        return service.getAllUsers();
    }


    public Usuario getUser(String userLogin, String senha) {
        log.info("Obtendo usuario com login: " + userLogin);
        return service.getUser(userLogin, senha);
    }


    public void updateUser(Usuario user) {
        log.info("Atualizando usuario: " + user);
        service.updateUser(user);
    }


    public void deleteUser(Usuario user) {
        log.info("Deletando usuario: " + user);
        service.deleteUser(user);
    }

    
    public boolean adicionaUser(Usuario user) {
        log.info("Criando usuário: " + user);
        return service.insertUser(user);
    }

}

package ucb.login.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import lombok.Data;
import lombok.SneakyThrows;
import ucb.login.controller.UsuarioController;

@Data
@ManagedBean(name = "loginBean")
@RequestScoped
public class Usuario {

    private String user;
    private String senha;
    private String perfil;
    private boolean lembrar;
    private String curso;

    private UsuarioController usuarioController = new UsuarioController();


    public void login() {
        try {
            
            Usuario usuario = usuarioController.getUser(user, senha);
    
            this.perfil = usuario.perfil;
            this.lembrar = usuario.lembrar;
            this.curso = usuario.curso;
    
            FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("usuarios.xhtml");
        
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new javax.faces.application.FacesMessage("Usuário ou senha inválida"));
                    
        }
    }


    public void cadastrar() {
        try {
            usuarioController.adicionaUser(this);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new javax.faces.application.FacesMessage("Usuário ou senha inválida"));
        }
    }


    public void excluir(Usuario usuario) {
        try {
            usuarioController.deleteUser(usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new javax.faces.application.FacesMessage("Usuário ou senha inválida"));
        }
    }

}

package ucb.login.dao;

import ucb.login.configuration.DatabaseConfig;
import ucb.login.configuration.Logger;
import ucb.login.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {

    private final Connection sql;
    private final Logger log;

    public UsuarioDaoImpl() {
        sql = DatabaseConfig.getConnection();
        log = new Logger();
    }




    // Método para obter todos os usuários
    public List<Usuario> getAllUsers() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM user";

        try (PreparedStatement stmt = sql.prepareStatement(query);
        
            ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {
                    Usuario usuario = getAlunoFromResultSetQuery(rs);
                    usuarios.add(usuario);
                }

                log.info("Encontrado " + usuarios.size() + " usuários no banco.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }




    // Metodo para obter um usuário específico pelo login
    public Usuario getUser(String userLogin) {
        Usuario user = null;
        String query = "SELECT * FROM user WHERE user = ?";

        try (PreparedStatement stmt = sql.prepareStatement(query)) {

            stmt.setString(1, userLogin);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    user = getAlunoFromResultSetQuery(rs);
                }
                log.info("Encontrado com o " + userLogin + " o usuario: " + user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }




    // Método para atualizar um usuário
    public boolean updateUser(Usuario user) {
        String query = "UPDATE user SET user = ?, senha = ?, perfil = ?, lembrar = ?, curso = ?  WHERE user = ?";

        try (PreparedStatement stmt = sql.prepareStatement(query)) {

            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getSenha());
            stmt.setString(3, user.getPerfil());
            stmt.setBoolean(4, user.isLembrar());
            stmt.setString(5, user.getCurso());
            
            stmt.setString(6, user.getUser());

            int changes = stmt.executeUpdate();

            log.info("Atualizado o usuário: " + user);

            return changes > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




    // Método para deletar um usuário
    public boolean deleteUser(Usuario user) {
        String query = "DELETE FROM user WHERE user = ?";

        try (PreparedStatement stmt = sql.prepareStatement(query)) {
            stmt.setString(1, user.getUser());
            int deleted = stmt.executeUpdate();

            log.info("Deletado o usuário: " + user + ", Is deleted? " + deleted);

            return deleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }




    // Método para inserir um novo usuário
    public boolean insertUser(Usuario user) {
        String query = "INSERT INTO user (user, senha, perfil, lembrar, curso) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = sql.prepareStatement(query)) {

            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getSenha());
            stmt.setString(3, user.getPerfil());
            stmt.setBoolean(4, user.isLembrar());
            stmt.setString(5, user.getCurso());

            int rowsAffected = stmt.executeUpdate();

            log.info("Inserindo usuário: " + user);

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }




    private Usuario getAlunoFromResultSetQuery(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();

        String nome = rs.getString("user");
        String senha = rs.getString("senha");
        String perfil = rs.getString("perfil");
        Boolean lembrar = rs.getBoolean("lembrar");
        String curso = rs.getString("curso");

        usuario.setUser(nome);
        usuario.setSenha(senha);
        usuario.setPerfil(perfil);
        usuario.setLembrar(lembrar);
        usuario.setCurso(curso);

        return usuario;
    }


}

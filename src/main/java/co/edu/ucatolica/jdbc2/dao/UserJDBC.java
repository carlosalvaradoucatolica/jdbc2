package co.edu.ucatolica.jdbc2.dao;

import co.edu.ucatolica.jdbc2.config.Conexion;
import co.edu.ucatolica.jdbc2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJDBC {
    private static final String SQL_SELECT = "SELECT id, username, password"
            + "FROM user";

    private static final String SQL_SELECT_BY_ID = "SELECT id, username, password"
            + "FROM user WHERE id = ?";

    private static final String SQL_INSERT = "INSERT INTO user(username, password)"
            +"VALUES(?, ?);";

    private static final String SQL_UPDATE = "UPDATE user "
            + "SET username = ?, password = ? WHERE id = ?;";

    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?;";

    public List<User> listar(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        List<User> users = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                user = new User(id, username, password);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return users;
    }

    public User encontrar(User user){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, user.getId()); //setea el parametro que se define arriba en los private static final cón ?, se setea porque es de tipo entero y se menciona el indice del ? que se quiere modificar
            rs = stmt.executeQuery();
            rs.next(); //En Caso de que exista nos posicionamos en el primer registro devuelto, por lo que no se recorre con un while por ejemplo, esto hace que se posicione en ese registro devuelto

            String username = rs.getString("username");
            String password = rs.getString("password");

            //Se asignan los valores sobre el objeto que ya tenemos
            user.setUsername(username);
            user.setPassword(password);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return user;
    }

    public int insertar(User user){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(User user){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getUsername());
            stmt.setInt(3, user.getId());


            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(User user){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, user.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}

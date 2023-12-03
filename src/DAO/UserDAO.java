package DAO;

import DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<UserDTO> list = new ArrayList<>();

    public void registerUser(UserDTO objUserDto) {
        String SQLCommand = "INSERT INTO users (id, name, email, cpf, phone, password) VALUES (?, ?, ?, ?, ?, ?)";

        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, objUserDto.getId());
            pstm.setString(2, objUserDto.getName());
            pstm.setString(3, objUserDto.getEmail());
            pstm.setString(4, objUserDto.getCpf());
            pstm.setString(5, objUserDto.getPhone());
            pstm.setString(6, objUserDto.getPassword());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UserDAO" + erro);
        }
    }

    public boolean authenticateUser(String email, String password) {
        String SQLCommand = "SELECT * FROM users WHERE email = ? AND password = ?";
        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, email);
            pstm.setString(2, password);

            rs = pstm.executeQuery();

            return rs.next(); // Se houver um próximo, o usuário existe

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UserDAO" + erro);
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<UserDTO> searchUser() {
        String sql = "SELECT * FROM users";

        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                UserDTO objUserDTO = new UserDTO();
                objUserDTO.setId(rs.getString("id"));
                objUserDTO.setName(rs.getString("name"));
                objUserDTO.setEmail(rs.getString("email"));
                objUserDTO.setCpf(rs.getString("cpf"));
                objUserDTO.setPhone(rs.getString("phone"));
                objUserDTO.setPassword(rs.getString("password"));

                list.add(objUserDTO);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "UserDAO Search" + error);
        }

        return list;
    }

    public void deleteUser(String userId) {
        String SQLCommand = "DELETE FROM users WHERE id = ?";
        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, userId);
            pstm.executeUpdate();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UserDAO Delete" + erro);
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser(UserDTO objUserDto) {
        String SQLCommand = "UPDATE users SET name=?, email=?, cpf=?, phone=?, password=? WHERE id=?";

        conn = new ConnectionDAO().connectBD();

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, objUserDto.getName());
            pstm.setString(2, objUserDto.getEmail());
            pstm.setString(3, objUserDto.getCpf());
            pstm.setString(4, objUserDto.getPhone());
            pstm.setString(5, objUserDto.getPassword());
            pstm.setString(6, objUserDto.getId());

            pstm.executeUpdate();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UserDAO Update" + erro);
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
     public UserDTO getUserById(String userId) {
        String SQLCommand = "SELECT * FROM users WHERE id = ?";
        conn = new ConnectionDAO().connectBD();
        UserDTO user = null;

        try {
            pstm = conn.prepareStatement(SQLCommand);
            pstm.setString(1, userId);

            rs = pstm.executeQuery();

            if (rs.next()) {
                user = new UserDTO();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setCpf(rs.getString("cpf"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UserDAO" + erro);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}

package DAO;

import DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserDAO {
    Connection conn;
    PreparedStatement pstm;

    public void registerUser(UserDTO objUserDto) {
        String SQLCommand = "insert into users (id, name, email, cpf, phone, password) values (?, ?, ?, ?, ?, ?)";

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
}

package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conn;
    PreparedStatement pstm;

    public void cadastrarUsuario(UsuarioDTO objusuariodto) {
        String comandoSQL = "insert into usuarios (nome, senha, email, cpf, telefone) values (?, ?, ?, ?, ?)";

        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(comandoSQL);
            pstm.setString(1, objusuariodto.getNome());
            pstm.setString(2, objusuariodto.getSenha());
            pstm.setString(3, objusuariodto.getEmail());
            pstm.setString(4, objusuariodto.getCpf());
            pstm.setString(5, objusuariodto.getTelefone());

            pstm.execute();
            pstm.close();
            
        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "UsuarioDAO" + erro);
        
        }

    }
     public ArrayList<UserDTO> searchUser() {
        String sql = "Select * from users";
        
         conn = new ConnectionDAO().connectBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                UserDTO objUserDTO = new UserDTO();
                objUserDTO.setId(rs.getInt("id"));
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
}
}

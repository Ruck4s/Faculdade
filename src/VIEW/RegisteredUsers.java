package VIEW;

import DAO.UserDAO;
import DTO.UserDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisteredUsers extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton deleteButton;
    private JButton editButton;  // Botão para editar usuário
    private JButton backButton;

    public RegisteredUsers() {
        // ... (o restante do seu construtor permanece inalterado)

        editButton = new JButton("Editar Usuário");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String userId = (String) model.getValueAt(selectedRow, 0);

                    // Obter informações atuais do usuário
                    UserDAO userDAO = new UserDAO();
                    UserDTO currentUser = userDAO.getUserById(userId);

                    // Exibir um formulário de edição
                    showEditUserForm(currentUser);
                } else {
                    JOptionPane.showMessageDialog(RegisteredUsers.this, "Selecione um usuário para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // ... (restante do seu código)

        buttonPanel.add(editButton);  // Adicionando o botão de editar ao painel de botões
    }

    private void showEditUserForm(UserDTO currentUser) {
        // Crie um formulário de edição de usuário e passe o usuário atual para ele
        EditUserForm editUserForm = new EditUserForm(currentUser, this);
        editUserForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editUserForm.setVisible(true);
    }

    // Adicione este método para atualizar a tabela após a edição
    public void updateUserInTable(UserDTO updatedUser) {
        // Encontrar a linha correspondente ao usuário na tabela
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(updatedUser.getId())) {
                // Atualizar os dados na tabela
                model.setValueAt(updatedUser.getName(), i, 1);
                model.setValueAt(updatedUser.getEmail(), i, 2);
                model.setValueAt(updatedUser.getCpf(), i, 3);
                model.setValueAt(updatedUser.getPhone(), i, 4);
                break;
            }
        }
    }
}

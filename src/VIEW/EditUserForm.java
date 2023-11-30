package VIEW;

import DAO.UserDAO;
import DTO.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserForm extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField cpfField;
    private JTextField phoneField;
    private JButton saveButton;

    private UserDTO currentUser;
    private RegisteredUsers parentFrame;

    public EditUserForm(UserDTO user, RegisteredUsers parent) {
        super("Editar Usuário");

        this.currentUser = user;
        this.parentFrame = parent;

        // Inicialize os componentes, campos de texto, botões etc.
        nameField = new JTextField(currentUser.getName());
        emailField = new JTextField(currentUser.getEmail());
        cpfField = new JTextField(currentUser.getCpf());
        phoneField = new JTextField(currentUser.getPhone());

        saveButton = new JButton("Salvar Alterações");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualize os dados do usuário e salve no banco de dados
                updateUserData();
                dispose();  // Feche a janela após salvar as alterações
            }
        });

        // ... (restante do seu código para inicializar os componentes e configurar o layout)
    }

    private void updateUserData() {
        // Atualize os campos do objeto currentUser com base nos dados dos campos de texto
        currentUser.setName(nameField.getText());
        currentUser.setEmail(emailField.getText());
        currentUser.setCpf(cpfField.getText());
        currentUser.setPhone(phoneField.getText());

        // Atualize os dados no banco de dados usando o método updateUser do UserDAO
        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(currentUser);

        // Atualize a tabela na janela principal
        parentFrame.updateUserInTable(currentUser);
    }
}

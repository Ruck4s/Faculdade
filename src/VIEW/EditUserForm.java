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

        nameField = new JTextField(currentUser.getName());
        emailField = new JTextField(currentUser.getEmail());
        cpfField = new JTextField(currentUser.getCpf());
        phoneField = new JTextField(currentUser.getPhone());

        saveButton = new JButton("Salvar Alterações");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser.setName(nameField.getText());
                currentUser.setEmail(emailField.getText());
                currentUser.setCpf(cpfField.getText());
                currentUser.setPhone(phoneField.getText());

                UserDAO userDAO = new UserDAO();
                userDAO.updateUser(currentUser);

                parentFrame.updateUserInTable(currentUser);

                dispose();
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(cpfField);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel(""));
        formPanel.add(saveButton);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }
}

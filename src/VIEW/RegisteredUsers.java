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
        super("Registered Users");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] columnNames = {"ID", "Nome", "Email", "CPF", "Telefone"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        deleteButton = new JButton("Deletar Usuário");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String userId = (String) model.getValueAt(selectedRow, 0);

                    int option = JOptionPane.showConfirmDialog(
                            RegisteredUsers.this,
                            "Você tem certeza que deseja deletar este usuário?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        deleteSelectedUser(userId);
                    }
                } else {
                    JOptionPane.showMessageDialog(RegisteredUsers.this, "Selecione um usuário para deletar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OptionsScreen optionsScreen = new OptionsScreen();
                optionsScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                optionsScreen.setVisible(true);
            }
        });

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(editButton);  // Adicionando o botão de editar ao painel de botões
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        populateTable();
    }

    public void addRow(String[] rowData) {
        model.addRow(rowData);
    }

    private void populateTable() {
        UserDAO userDAO = new UserDAO();
        ArrayList<UserDTO> userList = userDAO.searchUser();

        for (UserDTO user : userList) {
            addRow(new String[]{user.getId(), user.getName(), user.getEmail(), user.getCpf(), user.getPhone()});
        }
    }

    private void deleteSelectedUser(String userId) {
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUser(userId);

        model.setRowCount(0);
        populateTable();
    }

    // Adicione este método para exibir o formulário de edição
    private void showEditUserForm(UserDTO currentUser) {
        EditUserForm editUserForm = new EditUserForm(currentUser, this);
        editUserForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editUserForm.setVisible(true);
    }

    // Adicione este método para atualizar a tabela após a edição do usuário
    public void updateUserInTable(UserDTO updatedUser) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(updatedUser.getId())) {
                model.setValueAt(updatedUser.getName(), i, 1);
                model.setValueAt(updatedUser.getEmail(), i, 2);
                model.setValueAt(updatedUser.getCpf(), i, 3);
                model.setValueAt(updatedUser.getPhone(), i, 4);
                break; // Saia do loop após encontrar e atualizar o usuário
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisteredUsers::new);
    }
}

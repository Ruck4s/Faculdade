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
    private JButton editButton;
    private JButton backButton;

    public RegisteredUsers() {
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

        deleteButton = new JButton("Deletar Usuário");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String userId = (String) model.getValueAt(selectedRow, 0);
                    deleteSelectedUser(userId);
                } else {
                    JOptionPane.showMessageDialog(RegisteredUsers.this, "Selecione um usuário para deletar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editButton = new JButton("Editar Usuário");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String userId = (String) model.getValueAt(selectedRow, 0);
                    UserDAO userDAO = new UserDAO();
                    UserDTO currentUser = userDAO.getUserById(userId);
                    showEditUserForm(currentUser);
                } else {
                    JOptionPane.showMessageDialog(RegisteredUsers.this, "Selecione um usuário para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Email");
        model.addColumn("CPF");
        model.addColumn("Telefone");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

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

    private void showEditUserForm(UserDTO currentUser) {
        EditUserForm editUserForm = new EditUserForm(currentUser, this);
        editUserForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editUserForm.setVisible(true);
    }

    public void updateUserInTable(UserDTO updatedUser) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(updatedUser.getId())) {
                model.setValueAt(updatedUser.getName(), i, 1);
                model.setValueAt(updatedUser.getEmail(), i, 2);
                model.setValueAt(updatedUser.getCpf(), i, 3);
                model.setValueAt(updatedUser.getPhone(), i, 4);
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisteredUsers::new);
    }
}

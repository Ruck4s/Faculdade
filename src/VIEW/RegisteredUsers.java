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

    public RegisteredUsers() {
        super("Registered Users");

        String[] columnNames = {"id", "nome", "email", "cpf", "telefone"};
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

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(deleteButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisteredUsers::new);
    }
}

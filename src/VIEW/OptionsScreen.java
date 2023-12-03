
package VIEW;

import VIEW.RegisteredUsers;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OptionsScreen extends JFrame implements ActionListener {
    JButton registeredUsersButton;
    JButton gamesButton;
    JButton backButton; // Novo botão de voltar

    OptionsScreen() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(new Color(0x111111));
        this.setPreferredSize(new Dimension(1280, 720));

        registeredUsersButton = createStyledButton("Gerenciamento de usuários");
        registeredUsersButton.addActionListener(this);

        gamesButton = createStyledButton("Gerenciamentos de Carros");
        gamesButton.addActionListener(this);

        backButton = createStyledButton("Voltar para Login"); // Configurar o novo botão
        backButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(registeredUsersButton);
        buttonPanel.add(gamesButton);
        buttonPanel.add(backButton); // Adicionar o botão de voltar
        buttonPanel.setOpaque(false);

        this.add(Box.createVerticalGlue());
        this.add(buttonPanel);
        this.add(Box.createVerticalGlue());

        this.pack();
        this.setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(new Color(255, 255, 255));
        button.setBackground(new Color(0x333333));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registeredUsersButton) {
            // Ir para a tela de Registered Users
            this.dispose();
            RegisteredUsers registeredUsers = new RegisteredUsers();
            registeredUsers.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            registeredUsers.setVisible(true);
        } else if (e.getSource() == gamesButton) {
            // Ir para a tela de Games
            this.setVisible(false);
            CarsView carsView = new CarsView();
            carsView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            carsView.setVisible(true);
        } else if (e.getSource() == backButton) {
           this.setVisible(false);
            FormUserLogin formUserLogin = new FormUserLogin();
            formUserLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            formUserLogin.setVisible(true);
        }
    }
}
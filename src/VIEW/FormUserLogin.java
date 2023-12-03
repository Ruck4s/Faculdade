package VIEW;

import DAO.UserDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormUserLogin extends JFrame implements ActionListener{
    JTextField fieldEmail;
    JTextField fieldPassword;
    JButton loginButton;
    JButton registerButton;

    FormUserLogin() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(new Color(0x111111));
        this.setPreferredSize(new Dimension(1280, 720));

        fieldEmail = new JTextField("email");
        fieldEmail.setPreferredSize(new Dimension(350, 40));
        fieldEmail.setMaximumSize(new Dimension(350, 40));
        fieldEmail.setFont(new Font("Consolas", Font.PLAIN, 18));
        fieldEmail.setForeground(new Color(255, 255, 255, 128));
        fieldEmail.setBackground(new Color(0x222222));
        fieldEmail.setCaretColor(Color.white);
        fieldEmail.setHorizontalAlignment(JTextField.LEFT);
        fieldEmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fieldEmail.getText().equals("email")) {
                    fieldEmail.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (fieldEmail.getText().isEmpty()) {
                    fieldEmail.setText("email");
                }
            }
        });

        fieldPassword = new JTextField("senha");
        fieldPassword.setPreferredSize(new Dimension(350, 40));
        fieldPassword.setMaximumSize(new Dimension(350, 40));
        fieldPassword.setFont(new Font("Consolas", Font.PLAIN, 18));
        fieldPassword.setForeground(new Color(255, 255, 255, 128));
        fieldPassword.setBackground(new Color(0x222222));
        fieldPassword.setCaretColor(Color.white);
        fieldPassword.setHorizontalAlignment(JTextField.LEFT);
        fieldPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fieldPassword.getText().equals("senha")) {
                    fieldPassword.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (fieldPassword.getText().isEmpty()) {
                    fieldPassword.setText("senha");
                }
            }
        });

        loginButton = new JButton("ENTRAR");
        loginButton.addActionListener(this);

        registerButton = new JButton("CADASTRAR");
        registerButton.addActionListener(this);

        Box caixaVertical = Box.createVerticalBox();
        caixaVertical.add(fieldEmail);
        caixaVertical.add(Box.createRigidArea(new Dimension(0, 10)));
        caixaVertical.add(fieldPassword);

        Box caixaHorizontal = Box.createHorizontalBox();
        caixaHorizontal.add(loginButton);
        caixaHorizontal.add(Box.createRigidArea(new Dimension(10, 0)));
        caixaHorizontal.add(registerButton);

        Box caixaPrincipal = Box.createVerticalBox();
        caixaPrincipal.add(Box.createVerticalGlue());
        caixaPrincipal.add(caixaVertical);
        caixaPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        caixaPrincipal.add(caixaHorizontal);
        caixaPrincipal.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        this.add(caixaPrincipal);
        this.add(Box.createVerticalGlue());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                registerButton.requestFocus();
                registerButton.requestFocusInWindow();
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

 @Override
public void actionPerformed(ActionEvent e) {
    if(e.getSource() == loginButton) {
        // Obtenha os dados dos campos de entrada
        String email = fieldEmail.getText();
        String password = fieldPassword.getText();

        // Autentique o usuário no banco de dados
        UserDAO userDAO = new UserDAO();
        if (userDAO.authenticateUser(email, password)) {
            // Usuário autenticado com sucesso, exibir tela de opções
               showOptionsScreen();
        } else {
          JOptionPane.showMessageDialog(this, "Usuário não encontrado. Verifique suas credenciais.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
        }
        } else if(e.getSource() == registerButton) {
            this.setVisible(false);
            FormUserRegister formUserRegister = new FormUserRegister();
            formUserRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            formUserRegister.setVisible(true);
        }
    }
     private void showOptionsScreen() {
        // Criar e exibir a tela de opções após o login
        OptionsScreen optionsScreen = new OptionsScreen();
        optionsScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsScreen.setVisible(true);
        this.setVisible(false);
    }
}

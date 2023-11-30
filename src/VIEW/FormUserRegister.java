package VIEW;

import DAO.UserDAO;
import DTO.UserDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;

import javax.swing.*;

public class FormUserRegister extends JFrame implements ActionListener{
    JTextField fieldName;
    JTextField fieldEmail;
    JTextField fieldCpf;
    JTextField fieldPhone;
    JTextField fieldPassword;
    
    JButton registerButton;
    JButton backButton;

    FormUserRegister() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x111111));
        this.setPreferredSize(new Dimension(1280, 720));

        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(0x111111));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;

        fieldName = createSingleField("Nome");
        fieldEmail = createSingleField("Email");
        fieldCpf = createFlexField("CPF");
        fieldPhone = createFlexField("Telefone");
        fieldPassword = createSingleField("Senha");


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        contentPane.add(fieldName, gbc);
        
         gbc.gridy = 1;
        contentPane.add(fieldEmail, gbc);
            
        registerButton = createButton("Cadastrar");
        backButton = createButton("Voltar");
        
        
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        contentPane.add(createFlexContainer(fieldCpf, fieldPhone), gbc);
      
        
        gbc.gridy = 3;
        contentPane.add(fieldPassword, gbc);

        gbc.gridy = 4;
        contentPane.add(createFlexContainer(registerButton, backButton), gbc);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                registerButton.requestFocus();
                registerButton.requestFocusInWindow();
            }
        });

        this.add(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    FormUserRegister(FormUserLogin aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private JTextField createFlexField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setPreferredSize(new Dimension(250, 40));
        field.setMaximumSize(new Dimension(250, 40));
        field.setFont(new Font("Consolas", Font.PLAIN, 18));
        field.setForeground(new Color(255, 255, 255, 128));
        field.setBackground(new Color(0x222222));
        field.setCaretColor(Color.white);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                }
            }
        });
        return field;
    }

    private JTextField createSingleField(String placeholder) {
        JTextField field = new JTextField(placeholder);
        field.setPreferredSize(new Dimension(510, 40));
        field.setMaximumSize(new Dimension(510, 40));
        field.setFont(new Font("Consolas", Font.PLAIN, 18));
        field.setForeground(new Color(255, 255, 255, 128));
        field.setBackground(new Color(0x222222));
        field.setCaretColor(Color.white);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                }
            }
        });
        return field;
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        return button;
    }

    private JPanel createFlexContainer(Component c1, Component c2) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(0x111111));
        painel.add(c1, BorderLayout.WEST);
        painel.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.CENTER);
        painel.add(c2, BorderLayout.EAST);
        return painel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
        String name, password, email, cpf, phone;
        
        name = fieldName.getText();
        email = fieldEmail.getText();
        cpf = fieldCpf.getText();
        phone = fieldPhone.getText();
        password = fieldPassword.getText();

        UserDTO objUserDto = new UserDTO();
        String uuid = UUID.randomUUID().toString();
        
        objUserDto.setId(uuid);
        objUserDto.setName(name);
        objUserDto.setEmail(email);
        objUserDto.setCpf(cpf);
        objUserDto.setPhone(phone);
        objUserDto.setPassword(password);
        
        UserDAO obtUserDao = new UserDAO();
        obtUserDao.registerUser(objUserDto);
        } else if (e.getSource() == backButton) {
            this.setVisible(false);
            FormUserLogin formUserLogin = new FormUserLogin();
            formUserLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            formUserLogin.setVisible(true);
        }
    }
}
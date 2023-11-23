package VIEW;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class TelaCadastro extends JFrame implements ActionListener{

    private Tela telaLogin;
    JTextField campoNome;
    JTextField campoEmail;
    JTextField campoCpf;
    JTextField campoTelefone;
    JTextField campoSenha;
    JTextField campoConfirmarSenha;
    JButton botaoCadastrar;
    JButton botaoCancelar;

    TelaCadastro(Tela telaLogin) {
        this.telaLogin = telaLogin;
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

        campoNome = criaCampoTexto2("Nome");
        campoEmail = criaCampoTexto2("Email");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        contentPane.add(campoNome, gbc);

        gbc.gridy = 1;
        contentPane.add(campoEmail, gbc);

        campoCpf = criaCampoTexto("CPF");
        campoTelefone = criaCampoTexto("Telefone");
        campoSenha = criaCampoTexto("senha");
        campoConfirmarSenha = criaCampoTexto("Confirmar senha");
        botaoCadastrar = criaBotao("Cadastrar");
        botaoCancelar = criaBotao("Cancelar");

        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String cpf = campoCpf.getText();
        String telefone = campoTelefone.getText();
        String senha = campoSenha.getText();
        
        
        UsuarioDTO objusuariodto = new UsuarioDTO();
        objusuariodto.setNome(nome);
        objusuariodto.setEmail(email);
        objusuariodto.setCpf(cpf);
        objusuariodto.setTelefone(telefone);
        objusuariodto.setSenha(senha);
        
        UsuarioDAO objusuariodao = new UsuarioDAO();
        objusuariodao.cadastrarUsuario(objusuariodto);
        
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        contentPane.add(criaPainelDuasColunas(campoCpf, campoTelefone), gbc);

        gbc.gridy = 3;
        contentPane.add(criaPainelDuasColunas(campoSenha, campoConfirmarSenha), gbc);

        gbc.gridy = 4;
        contentPane.add(criaPainelDuasColunas(botaoCadastrar, botaoCancelar), gbc);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                botaoCadastrar.requestFocus();
                botaoCadastrar.requestFocusInWindow();
            }
        });

        this.add(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JTextField criaCampoTexto(String placeholder) {
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

    private JTextField criaCampoTexto2(String placeholder) {
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

    private JButton criaBotao(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        return button;
    }

    private JPanel criaPainelDuasColunas(Component c1, Component c2) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(0x111111));
        painel.add(c1, BorderLayout.WEST);
        painel.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.CENTER);
        painel.add(c2, BorderLayout.EAST);
        return painel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCadastrar) {
            System.out.println("Botao cadastrar clicado");
        } else if (e.getSource() == botaoCancelar) {
            // System.out.println("Botao cancelar clicado");
            this.setVisible(false);
            telaLogin.setVisible(true);
        }
    }
}
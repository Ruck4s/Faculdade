package VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Tela extends JFrame implements ActionListener{

    JTextField campoTexto1;
    JTextField campoTexto2;
    JButton botaoEntrar;
    JButton botaoCadastrar;

    Tela() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.getContentPane().setBackground(new Color(0x111111));
        this.setPreferredSize(new Dimension(1280, 720));

        campoTexto1 = new JTextField("email");
        campoTexto1.setPreferredSize(new Dimension(350, 40));
        campoTexto1.setMaximumSize(new Dimension(350, 40));
        campoTexto1.setFont(new Font("Consolas", Font.PLAIN, 18));
        campoTexto1.setForeground(new Color(255, 255, 255, 128));
        campoTexto1.setBackground(new Color(0x222222));
        campoTexto1.setCaretColor(Color.white);
        campoTexto1.setHorizontalAlignment(JTextField.LEFT);
        campoTexto1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoTexto1.getText().equals("email")) {
                    campoTexto1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campoTexto1.getText().isEmpty()) {
                    campoTexto1.setText("email");
                }
            }
        });

        campoTexto2 = new JTextField("senha");
        campoTexto2.setPreferredSize(new Dimension(350, 40));
        campoTexto2.setMaximumSize(new Dimension(350, 40));
        campoTexto2.setFont(new Font("Consolas", Font.PLAIN, 18));
        campoTexto2.setForeground(new Color(255, 255, 255, 128));
        campoTexto2.setBackground(new Color(0x222222));
        campoTexto2.setCaretColor(Color.white);
        campoTexto2.setHorizontalAlignment(JTextField.LEFT);
        campoTexto2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoTexto2.getText().equals("senha")) {
                    campoTexto2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campoTexto2.getText().isEmpty()) {
                    campoTexto2.setText("senha");
                }
            }
        });

        botaoEntrar = new JButton("ENTRAR");
        botaoEntrar.addActionListener(this);

        botaoCadastrar = new JButton("CADASTRAR");
        botaoCadastrar.addActionListener(this);

        Box caixaVertical = Box.createVerticalBox();
        caixaVertical.add(campoTexto1);
        caixaVertical.add(Box.createRigidArea(new Dimension(0, 10)));
        caixaVertical.add(campoTexto2);

        Box caixaHorizontal = Box.createHorizontalBox();
        caixaHorizontal.add(botaoEntrar);
        caixaHorizontal.add(Box.createRigidArea(new Dimension(10, 0)));
        caixaHorizontal.add(botaoCadastrar);

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
                botaoCadastrar.requestFocus();
                botaoCadastrar.requestFocusInWindow();
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoEntrar) {
            System.out.println("Botao entrar clicado");
        } else if(e.getSource() == botaoCadastrar) {
            // System.out.println("Botao cadastrar clicado");
            this.setVisible(false);
            TelaCadastro telaCadastro = new TelaCadastro(this);
            telaCadastro.setVisible(true);
        }
    }
    
}
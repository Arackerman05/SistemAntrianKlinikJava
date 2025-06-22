package view;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import database.KoneksiDB;

public class LoginForm extends JFrame {

    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Login Admin");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tfUsername = new JTextField(15);
        pfPassword = new JPasswordField(15);
        btnLogin = new JButton("Login");

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Username:"));
        panel.add(tfUsername);
        panel.add(new JLabel("Password:"));
        panel.add(pfPassword);
        panel.add(new JLabel());
        panel.add(btnLogin);
        
        

        add(panel);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        String username = tfUsername.getText();
        String password = new String(pfPassword.getPassword());

        try {
            Connection conn = KoneksiDB.getConnection();
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login berhasil!");
                dispose(); // tutup form login
                new MainFrame(); // buka menu utama
            } else {
                JOptionPane.showMessageDialog(this, "Login gagal!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);
    }
}

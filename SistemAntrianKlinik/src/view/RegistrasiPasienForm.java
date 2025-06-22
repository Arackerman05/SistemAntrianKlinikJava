package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import database.KoneksiDB;

public class RegistrasiPasienForm extends JFrame {

    private JTextField tfNama, tfUmur;
    private JComboBox<String> cbJenisKelamin;
    private JTextArea taKeluhan;
    private JButton btnSimpan;

    public RegistrasiPasienForm() {
        setTitle("Form Registrasi Pasien");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel Utama
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen
        JLabel lblNama = new JLabel("Nama:");
        tfNama = new JTextField(20);
        JLabel lblUmur = new JLabel("Umur:");
        tfUmur = new JTextField(5);
        JLabel lblJK = new JLabel("Jenis Kelamin:");
        cbJenisKelamin = new JComboBox<>(new String[] { "Laki-laki", "Perempuan" });
        JLabel lblKeluhan = new JLabel("Keluhan:");
        taKeluhan = new JTextArea(3, 20);
        btnSimpan = new JButton("Simpan");
        JButton btnKembali = new JButton("Kembali ke Menu Utama");
        btnKembali.addActionListener(e -> {
            dispose();
            new MainFrame();
        });


        // Layout
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblNama, gbc);
        gbc.gridx = 1; panel.add(tfNama, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblUmur, gbc);
        gbc.gridx = 1; panel.add(tfUmur, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(lblJK, gbc);
        gbc.gridx = 1; panel.add(cbJenisKelamin, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(lblKeluhan, gbc);
        gbc.gridx = 1; panel.add(new JScrollPane(taKeluhan), gbc);

        gbc.gridx = 1; gbc.gridy = 4; panel.add(btnSimpan, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(btnKembali, gbc);

        add(panel);
        setVisible(true);

        // Action Button
        btnSimpan.addActionListener(e -> simpanPasien());
    }

    private void simpanPasien() {
        String nama = tfNama.getText();
        int umur = Integer.parseInt(tfUmur.getText());
        String jenisKelamin = cbJenisKelamin.getSelectedItem().toString();
        String keluhan = taKeluhan.getText();

        try {
            Connection conn = KoneksiDB.getConnection();
            String sql = "INSERT INTO pasien (nama, umur, jenis_kelamin, keluhan) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setInt(2, umur);
            ps.setString(3, jenisKelamin);
            ps.setString(4, keluhan);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Data pasien berhasil disimpan!");
                tfNama.setText("");
                tfUmur.setText("");
                taKeluhan.setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Untuk tes form langsung
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrasiPasienForm::new);
    }
}

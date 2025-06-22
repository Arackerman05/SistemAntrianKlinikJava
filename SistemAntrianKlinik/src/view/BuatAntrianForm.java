package view;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import database.KoneksiDB;

public class BuatAntrianForm extends JFrame {

    private JComboBox<String> cbPasien, cbDokter;
    private JButton btnDaftar, btnKembali;

    public BuatAntrianForm() {
        setTitle("Form Antrian");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cbPasien = new JComboBox<>();
        cbDokter = new JComboBox<>();
        btnDaftar = new JButton("Buat Antrian");
        btnKembali = new JButton("Kembali ke Menu Utama");

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10)); // Ubah jadi 4 baris
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Pilih Pasien:"));
        panel.add(cbPasien);
        panel.add(new JLabel("Pilih Dokter:"));
        panel.add(cbDokter);
        panel.add(new JLabel("")); // Kosongkan kolom kiri
        panel.add(btnDaftar);
        panel.add(new JLabel("")); // Kosongkan kolom kiri
        panel.add(btnKembali);     // Tombol kembali di kanan

        add(panel);

        loadPasien();
        loadDokter();

        btnDaftar.addActionListener(e -> buatAntrian());

        // Aksi tombol kembali
        btnKembali.addActionListener(e -> {
            dispose();         // Tutup form ini
            new MainFrame();   // Buka menu utama
        });

        setVisible(true);
    }

    private void loadPasien() {
        try {
            Connection conn = KoneksiDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nama FROM pasien");
            while (rs.next()) {
                cbPasien.addItem(rs.getInt("id") + " - " + rs.getString("nama"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDokter() {
        try {
            Connection conn = KoneksiDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nama FROM dokter");
            while (rs.next()) {
                cbDokter.addItem(rs.getInt("id") + " - " + rs.getString("nama"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void buatAntrian() {
        try {
            Connection conn = KoneksiDB.getConnection();

            int idPasien = Integer.parseInt(cbPasien.getSelectedItem().toString().split(" - ")[0]);
            int idDokter = Integer.parseInt(cbDokter.getSelectedItem().toString().split(" - ")[0]);

            // Hitung nomor antrian terakhir
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(nomor_antrian) AS max_antrian FROM antrian");
            int nomorAntrian = 1;
            if (rs.next()) {
                nomorAntrian = rs.getInt("max_antrian") + 1;
            }

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO antrian (nomor_antrian, id_pasien, id_dokter) VALUES (?, ?, ?)");
            ps.setInt(1, nomorAntrian);
            ps.setInt(2, idPasien);
            ps.setInt(3, idDokter);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Antrian berhasil dibuat! No: " + nomorAntrian);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal membuat antrian.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BuatAntrianForm::new);
    }
}

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

public class HapusAntrianForm extends JFrame {
    
    private JComboBox<String> cbAntrian;
    private JButton btnHapus, btnKembali;

    

    public HapusAntrianForm() {
        setTitle("Hapus Antrian");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cbAntrian = new JComboBox<>();
        btnHapus = new JButton("Hapus");
        btnKembali = new JButton("Kembali ke Menu Utama");


        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JLabel("Pilih Antrian:"));
        panel.add(cbAntrian);
        panel.add(new JLabel(""));
        panel.add(btnHapus);
        panel.add(new JLabel(""));
        panel.add(btnKembali);
        btnKembali.addActionListener(e -> {
        dispose();
        new MainFrame();
         });


        

        add(panel);

        loadAntrian();

        btnHapus.addActionListener(e -> hapusAntrian());

        setVisible(true);
    }

    private void loadAntrian() {
        try {
            Connection conn = KoneksiDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                SELECT a.id, p.nama, d.nama AS nama_dokter
                FROM antrian a
                JOIN pasien p ON a.id_pasien = p.id
                JOIN dokter d ON a.id_dokter = d.id
            """);
            while (rs.next()) {
                int id = rs.getInt("id");
                String namaPasien = rs.getString("nama");
                String namaDokter = rs.getString("nama_dokter");
                cbAntrian.addItem(id + " - " + namaPasien + " ke " + namaDokter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void hapusAntrian() {
        if (cbAntrian.getSelectedItem() == null) return;

        int id = Integer.parseInt(cbAntrian.getSelectedItem().toString().split(" - ")[0]);
        try {
            Connection conn = KoneksiDB.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM antrian WHERE id = ?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Antrian berhasil dihapus.");
                cbAntrian.removeItem(cbAntrian.getSelectedItem());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HapusAntrianForm::new);
    }
}

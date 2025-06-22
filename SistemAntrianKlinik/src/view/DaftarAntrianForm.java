package view;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import database.KoneksiDB;

public class DaftarAntrianForm extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public DaftarAntrianForm() {
    setTitle("Daftar Antrian Pasien");
    setSize(600, 450);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    model = new DefaultTableModel();
    table = new JTable(model);

    model.addColumn("No");
    model.addColumn("Nama Pasien");
    model.addColumn("Dokter");
    model.addColumn("Waktu Daftar");

    JScrollPane scrollPane = new JScrollPane(table);

    // Panel bawah untuk tombol kembali
    JButton btnKembali = new JButton("Kembali ke Menu Utama");
    btnKembali.addActionListener(e -> {
        dispose();
        new MainFrame();
    });

    JPanel bottomPanel = new JPanel();
    bottomPanel.add(btnKembali);

    add(scrollPane, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);

    loadAntrian();
    setVisible(true);
}


    private void loadAntrian() {
        try {
            Connection conn = KoneksiDB.getConnection();
            String sql = """
                SELECT a.id, p.nama AS nama_pasien, d.nama AS nama_dokter, a.waktu_daftar
                FROM antrian a
                JOIN pasien p ON a.id_pasien = p.id
                JOIN dokter d ON a.id_dokter = d.id
                ORDER BY a.nomor_antrian ASC
            """;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("nama_pasien"),
                    rs.getString("nama_dokter"),
                    rs.getTimestamp("waktu_daftar")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data antrian.");
            e.printStackTrace();
        }
    }

    // Untuk test standalone
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DaftarAntrianForm::new);
    }
}

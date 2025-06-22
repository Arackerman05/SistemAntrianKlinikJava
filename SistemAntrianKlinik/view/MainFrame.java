package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Sistem Antrian Klinik");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnRegistrasi = new JButton("Registrasi Pasien");
        JButton btnAntrian = new JButton("Buat Antrian");
        JButton btnDaftar = new JButton("Lihat Antrian");
        JButton btnHapus = new JButton("Hapus Antrian");
        JButton btnKeluar = new JButton("Keluar");

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(btnRegistrasi);
        panel.add(btnAntrian);
        panel.add(btnDaftar);
        panel.add(btnHapus);
        panel.add(btnKeluar);

        add(panel);

        btnRegistrasi.addActionListener(e -> new RegistrasiPasienForm());
        btnAntrian.addActionListener(e -> new BuatAntrianForm());
        btnDaftar.addActionListener(e -> new DaftarAntrianForm());
        btnHapus.addActionListener(e -> new HapusAntrianForm());
        btnKeluar.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

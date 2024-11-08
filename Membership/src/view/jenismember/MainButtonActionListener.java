package view.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonActionListener implements ActionListener {

    // Menyimpan referensi ke objek MainFrame, untuk berinteraksi dengan frame utama aplikasi
    private MainFrame mainFrame;

    // Konstruktor untuk inisialisasi MainFrame
    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    // Method yang akan dipanggil ketika tombol ditekan
    @Override
    public void actionPerformed(ActionEvent e) {
        // Tambahkan logika yang diperlukan di sini
        // Misalnya, membuka frame lain atau menampilkan pesan
    }
}

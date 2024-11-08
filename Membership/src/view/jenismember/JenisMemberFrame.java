package view.jenismember;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import model.JenisMember;
import dao.JenisMemberDao;

public class JenisMemberFrame extends JFrame {

    // List untuk menyimpan data jenis member yang akan ditampilkan di tabel
    private List<JenisMember> jenisMemberList;
    // TextField untuk input nama jenis member
    private JTextField textFieldNama;
    // Model tabel untuk menampilkan data jenis member
    private JenisMemberTableModel tableModel;
    // Data akses objek JenisMemberDao untuk berinteraksi dengan database
    private JenisMemberDao jenisMemberDao;

    // Konstruktor untuk inisialisasi frame dan komponen UI
    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        // Inisialisasi jenisMemberDao dan mengisi list dengan data dari database
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();

        // Mengatur properti JFrame
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 400);

        // Label untuk input nama
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);
        this.add(labelInput);

        // TextField untuk nama jenis member
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);
        this.add(textFieldNama);

        // Button untuk menyimpan jenis member baru
        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);
        this.add(button);

        // Tabel untuk menampilkan daftar jenis member
        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);
        this.add(scrollableTable);

        // Mengatur model tabel dengan data jenis member
        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        // Mengatur ActionListener untuk tombol simpan
        JenisMemberButtonSimpanActionListener actionListener = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        button.addActionListener(actionListener);
    }

    // Method untuk mendapatkan nama yang diinputkan pengguna
    public String getNama() {
        return textFieldNama.getText();
    }

    // Method untuk menampilkan pesan alert
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    // Method untuk menambahkan jenis member baru ke dalam list dan memperbarui tampilan tabel
    public void addJenisMember(JenisMember jenisMember) {
        jenisMemberList.add(jenisMember);
        tableModel.fireTableDataChanged();  // Menginformasikan tabel untuk memperbarui data
    }
}

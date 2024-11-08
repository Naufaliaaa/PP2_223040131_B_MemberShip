package view.jenismember;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import model.JenisMember;
import dao.JenisMemberDao;

public class JenisMemberFrame extends JFrame {

    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 400);

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);
        this.add(labelInput);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);
        this.add(textFieldNama);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);
        this.add(button);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);
        this.add(scrollableTable);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        JenisMemberButtonSimpanActionListener actionListener = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        button.addActionListener(actionListener);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public void addJenisMember(JenisMember jenisMember) {
        jenisMemberList.add(jenisMember);
        tableModel.fireTableDataChanged();
    }
}

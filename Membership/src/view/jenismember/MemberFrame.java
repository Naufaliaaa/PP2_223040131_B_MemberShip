package view.member;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import model.*;
import dao.MemberDao;
import dao.JenisMemberDao;

public class MemberFrame extends JFrame {

    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<JenisMember> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 400);

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);
        this.add(labelInput);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);
        this.add(textFieldNama);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 10);
        this.add(labelJenis);

        comboJenis = new JComboBox<>(new DefaultComboBoxModel<>(jenisMemberList.toArray(new JenisMember[0])));
        comboJenis.setBounds(15, 120, 350, 30);
        this.add(comboJenis);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 160, 100, 40);
        this.add(button);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 210, 350, 150);
        this.add(scrollableTable);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getSelectedJenisMember() {
        return (JenisMember) comboJenis.getSelectedItem();
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public void addMember(Member member) {
        memberList.add(member);
        tableModel.fireTableDataChanged();
    }

        comboJenis = new JComboBox<>();
    comboJenis.setBounds(15, 120, 150, 30);
    this.add(comboJenis);

    JButton button = new JButton("Simpan");
    button.setBounds(15, 160, 100, 40);
    this.add(button);

    JTable table = new JTable();
    JScrollPane scrollableTable = new JScrollPane(table);
    scrollableTable.setBounds(15, 220, 350, 200);
    this.add(scrollableTable);

    tableModel = new MemberTableModel(memberList);
    table.setModel(tableModel);

    MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
    button.addActionListener(actionListener);

    this.add(textFieldNama);
    this.add(labelInput);
    this.add(labelJenis);
    this.add(comboJenis);

    this.setSize(400, 500);
    this.setLayout(null);

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }
    public String getNama() {
    return textFieldNama.getText();
}

public JenisMember getJenisMember() {
    return jenisMemberList.get(comboJenis.getSelectedIndex());
}

public void addMember(Member member) {
    tableModel.add(member);
    textFieldNama.setText("");
}

public void showAlert(String message) {
    JOptionPane.showMessageDialog(this, message);
}

}

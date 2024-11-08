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

    // Constructor
    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 500);

        // Create and add the "Nama" label
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);
        this.add(labelInput);

        // Create and add the text field for member name input
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);
        this.add(textFieldNama);

        // Create and add the "Jenis Member" label
        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 10);
        this.add(labelJenis);

        // Create and add the combo box for selecting "Jenis Member"
        comboJenis = new JComboBox<>(new DefaultComboBoxModel<>(jenisMemberList.toArray(new JenisMember[0])));
        comboJenis.setBounds(15, 120, 350, 30);
        this.add(comboJenis);

        // Create and add the "Simpan" button
        JButton button = new JButton("Simpan");
        button.setBounds(15, 160, 100, 40);
        this.add(button);

        // Create and add the table to display members
        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 210, 350, 150);
        this.add(scrollableTable);

        // Set the table model
        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        // Add action listener for the "Simpan" button
        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);
    }

    // Get the name entered in the text field
    public String getNama() {
        return textFieldNama.getText();
    }

    // Get the selected "Jenis Member" from the combo box
    public JenisMember getSelectedJenisMember() {
        return (JenisMember) comboJenis.getSelectedItem();
    }

    // Show an alert dialog with the given message
    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    // Add a member to the table and clear the input fields
    public void addMember(Member member) {
        memberList.add(member);
        tableModel.fireTableDataChanged();
        textFieldNama.setText("");  // Clear the name input field
    }

    // Populate the combo box with types of "Jenis Member"
    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember);
        }
    }
}

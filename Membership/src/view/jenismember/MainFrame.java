package view.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Member;
import view.jenismember.JenisMemberFrame;
import view.member.MemberFrame;
import dao.JenisMemberDao;
import dao.MemberDao;

public class MainFrame extends JFrame {

    // Frame untuk mengelola JenisMember dan Member
    private JenisMemberFrame jenisMemberFrame;
    private MemberFrame memberFrame;

    // Tombol untuk membuka frame JenisMember dan Member
    private JButton buttonJenisMember;
    private JButton buttonMember;

    // Objek DAO untuk JenisMember dan Member
    private JenisMemberDao jenisMemberDao;
    private MemberDao memberDao;

    // Konstruktor MainFrame
    public MainFrame() {
        // Pengaturan frame utama
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);

        // Inisialisasi DAO
        this.jenisMemberDao = new JenisMemberDao();
        this.memberDao = new MemberDao();

        // Inisialisasi frame jenisMember dan member dengan DAO terkait
        this.jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        this.memberFrame = new MemberFrame(memberDao, jenisMemberDao);

        // Set layout untuk frame
        this.setLayout(new FlowLayout());

        // Inisialisasi ActionListener untuk tombol
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        // Inisialisasi tombol dan menambahkan listener
        this.buttonJenisMember = new JButton("Jenis Member");
        this.buttonMember = new JButton("Member");

        this.buttonJenisMember.addActionListener(actionListener);
        this.buttonMember.addActionListener(actionListener);

        // Menambahkan tombol ke frame
        this.add(buttonJenisMember);
        this.add(buttonMember);
    }

    // Getter untuk buttonJenisMember
    public JButton getButtonJenisMember() {
        return buttonJenisMember;
    }

    // Getter untuk buttonMember
    public JButton getButtonMember() {
        return buttonMember;
    }

    // Method untuk menampilkan frame JenisMember
    public void showJenisMemberFrame() {
        if (jenisMemberFrame == null) {  // Memastikan hanya ada satu instance dari JenisMemberFrame
            jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        }
        jenisMemberFrame.setVisible(true);  // Menampilkan frame JenisMember
    }

    // Method untuk menampilkan frame Member
    public void showMemberFrame() {
        if (memberFrame == null) {  // Memastikan hanya ada satu instance dari MemberFrame
            memberFrame = new MemberFrame(memberDao, jenisMemberDao);
            memberFrame.populateComboJenis();  // Mengisi dropdown jenis member di MemberFrame
        }
        memberFrame.setVisible(true);  // Menampilkan frame Member
    }

    // Method main untuk menjalankan aplikasi
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame f = new MainFrame();
                f.setVisible(true);  // Menampilkan frame utama
            }
        });
    }
}

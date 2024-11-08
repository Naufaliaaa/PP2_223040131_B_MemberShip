package view.member;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.UUID;
import model.*;
import dao.MemberDao;

public class MemberButtonSimpanActionListener implements ActionListener {

    // Referensi ke MemberFrame yang menampilkan data member
    private MemberFrame memberFrame;
    // Data akses objek MemberDao untuk berinteraksi dengan database
    private MemberDao memberDao;

    // Konstruktor untuk menginisialisasi MemberFrame dan MemberDao
    public MemberButtonSimpanActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    // Method yang akan dijalankan saat tombol "Simpan" diklik
    @Override
    public void actionPerformed(ActionEvent e) {
        // Mengambil nama dari MemberFrame
        String nama = this.memberFrame.getNama();

        // Validasi: cek apakah nama kosong
        if (nama.isEmpty()) {
            // Tampilkan pesan peringatan jika nama kosong
            this.memberFrame.showAlert("Nama tidak boleh kosong");
        } else {
            // Mendapatkan objek JenisMember dari MemberFrame
            JenisMember jenisMember = this.memberFrame.getJenisMember();
            
            // Membuat objek Member baru dan mengatur nilai id, nama, dan jenisMember
            Member member = new Member();
            member.setId(UUID.randomUUID().toString());  // Generate id unik untuk member
            member.setNama(nama);  // Mengatur nama member
            member.setJenisMember(jenisMember);  // Mengatur jenisMember

            // Menambahkan objek Member ke tampilan MemberFrame
            this.memberFrame.addMember(member);
            
            // Menyimpan data member ke database menggunakan MemberDao
            this.memberDao.insert(member);
        }
    }
}

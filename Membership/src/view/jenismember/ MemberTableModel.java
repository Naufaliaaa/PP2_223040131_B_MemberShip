package view.member;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Member;

class MemberTableModel extends AbstractTableModel {

    // Nama kolom untuk tabel
    private String[] columnNames = {"Nama", "Jenis Member"};
    // Data yang akan ditampilkan di tabel, berupa list dari objek Member
    private List<Member> data;

    // Konstruktor untuk menginisialisasi data tabel
    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    // Mengembalikan jumlah kolom pada tabel
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Mengembalikan jumlah baris pada tabel
    @Override
    public int getRowCount() {
        return data.size();
    }

    // Mengembalikan nama kolom berdasarkan indeks
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Mengambil nilai pada sel tertentu di tabel
    @Override
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);  // Mendapatkan objek Member pada baris tertentu
        String value = "";

        switch (col) {
            case 0:
                value = rowItem.getNama();  // Mendapatkan nama member untuk kolom pertama
                break;
            case 1:
                value = rowItem.getJenisMember().getNama();  // Mendapatkan nama jenis member untuk kolom kedua
                break;
        }

        return value;
    }

    // Menentukan apakah sel dapat diedit. Di sini diset false agar tidak bisa diedit
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // Menambahkan data member baru ke tabel dan memperbarui tampilan
    public void add(Member value) {
        data.add(value);  // Menambahkan objek Member baru ke list data
        fireTableRowsInserted(data.size() - 1, data.size() - 1);  // Memberitahu tabel untuk memperbarui baris terakhir
    }
}

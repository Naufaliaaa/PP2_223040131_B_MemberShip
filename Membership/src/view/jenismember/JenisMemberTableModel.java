package view.jenismember;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.JenisMember;

class JenisMemberTableModel extends AbstractTableModel {

    // Nama kolom untuk tabel
    private String[] columnNames = {"Nama"};
    // List yang menyimpan data JenisMember
    private List<JenisMember> data;

    // Konstruktor untuk inisialisasi data
    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    // Mengembalikan jumlah kolom dalam tabel
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Mengembalikan jumlah baris dalam tabel
    @Override
    public int getRowCount() {
        return data.size();
    }

    // Mengembalikan nama kolom berdasarkan indeks
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Mengembalikan nilai di setiap sel berdasarkan indeks baris dan kolom
    @Override
    public Object getValueAt(int row, int col) {
        JenisMember rowItem = data.get(row);  // Mengambil data JenisMember di baris yang diminta
        String value = "";

        // Menentukan data yang ditampilkan berdasarkan kolom
        switch (col) {
            case 0:
                value = rowItem.getNama();  // Kolom pertama menunjukkan nama jenis member
                break;
        }

        return value;
    }

    // Mengatur apakah sel dapat diedit; di sini semua sel tidak dapat diedit
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // Menambahkan JenisMember baru ke dalam tabel
    public void add(JenisMember value) {
        data.add(value);
        // Memberitahu tabel untuk memperbarui tampilan setelah data baru ditambahkan
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}

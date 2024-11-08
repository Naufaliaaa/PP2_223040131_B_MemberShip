package model;

public class JenisMember {
    private String id;    // ID jenis member
    private String nama;   // Nama jenis member

    // Constructor kosong untuk inisialisasi objek
    public JenisMember() {}

    // Method setter untuk id
    public void setId(String id) {
        this.id = id;
    }

    // Method getter untuk id
    public String getId() {
        return id;
    }

    // Method setter untuk nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method getter untuk nama
    public String getNama() {
        return nama;
    }
}

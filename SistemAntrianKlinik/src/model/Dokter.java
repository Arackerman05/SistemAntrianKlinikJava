package model;

public class Dokter {
    private int id;
    private String nama;
    private String spesialisasi;

    public Dokter(int id, String nama, String spesialisasi) {
        this.id = id;
        this.nama = nama;
        this.spesialisasi = spesialisasi;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getSpesialisasi() { return spesialisasi; }

    public void setNama(String nama) { this.nama = nama; }
    public void setSpesialisasi(String spesialisasi) { this.spesialisasi = spesialisasi; }
}

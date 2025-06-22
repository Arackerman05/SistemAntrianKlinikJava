package model;

public class Pasien {
    private int id;
    private String nama;
    private int umur;
    private String jenisKelamin;
    private String keluhan;

    public Pasien(int id, String nama, int umur, String jenisKelamin, String keluhan) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.keluhan = keluhan;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public int getUmur() { return umur; }
    public String getJenisKelamin() { return jenisKelamin; }
    public String getKeluhan() { return keluhan; }

    public void setNama(String nama) { this.nama = nama; }
    public void setUmur(int umur) { this.umur = umur; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
    public void setKeluhan(String keluhan) { this.keluhan = keluhan; }
}

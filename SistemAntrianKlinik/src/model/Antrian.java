package model;

import java.sql.Timestamp;

public class Antrian {
    private int id;
    private int nomorAntrian;
    private int idPasien;
    private int idDokter;
    private Timestamp waktuDaftar;

    public Antrian(int id, int nomorAntrian, int idPasien, int idDokter, Timestamp waktuDaftar) {
        this.id = id;
        this.nomorAntrian = nomorAntrian;
        this.idPasien = idPasien;
        this.idDokter = idDokter;
        this.waktuDaftar = waktuDaftar;
    }

    public int getId() { return id; }
    public int getNomorAntrian() { return nomorAntrian; }
    public int getIdPasien() { return idPasien; }
    public int getIdDokter() { return idDokter; }
    public Timestamp getWaktuDaftar() { return waktuDaftar; }

    public void setNomorAntrian(int nomorAntrian) { this.nomorAntrian = nomorAntrian; }
    public void setIdPasien(int idPasien) { this.idPasien = idPasien; }
    public void setIdDokter(int idDokter) { this.idDokter = idDokter; }
}

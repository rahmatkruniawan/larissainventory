package com.inventory.larissa.larissainventory;

import java.io.Serializable;

/**
 * Created by USER on 16/07/2017.
 */

public class Retur implements Serializable{

    String noRetur, kodeBarang, namaBarang, qty, tglMasuk,keterangan, tanggal , bulan , tahun, namaKaryawan;

    public Retur() {


    }

    public Retur(String noRetur, String kodeBarang, String namaBarang, String qty, String tglMasuk, String keterangan, String tanggal, String bulan, String tahun, String namaKaryawan) {
        this.noRetur = noRetur;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.qty = qty;
        this.tglMasuk = tglMasuk;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
        this.namaKaryawan = namaKaryawan;
    }

    public String getNoRetur() {
        return noRetur;
    }

    public void setNoRetur(String noRetur) {
        this.noRetur = noRetur;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }
}

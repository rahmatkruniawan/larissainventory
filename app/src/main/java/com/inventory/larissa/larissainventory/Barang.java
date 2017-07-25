package com.inventory.larissa.larissainventory;

import java.io.Serializable;

/**
 * Created by USER on 16/07/2017.
 */

public class Barang implements Serializable{

    String id , noBarang, kodeBarang, namaBarang, qty, tglMasuk, kodeSuplier, namaSuplier, telpSuplier, tanggal , bulan , tahun;

    public Barang() {
    }

    public Barang(String id, String noBarang, String kodeBarang, String namaBarang, String qty, String tglMasuk, String kodeSuplier, String namaSuplier, String telpSuplier, String tanggal, String bulan, String tahun) {
        this.id = id;
        this.noBarang = noBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.qty = qty;
        this.tglMasuk = tglMasuk;
        this.kodeSuplier = kodeSuplier;
        this.namaSuplier = namaSuplier;
        this.telpSuplier = telpSuplier;
        this.tanggal = tanggal;
        this.bulan = bulan;
        this.tahun = tahun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoBarang() {
        return noBarang;
    }

    public void setNoBarang(String noBarang) {
        this.noBarang = noBarang;
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

    public String getKodeSuplier() {
        return kodeSuplier;
    }

    public void setKodeSuplier(String kodeSuplier) {
        this.kodeSuplier = kodeSuplier;
    }

    public String getNamaSuplier() {
        return namaSuplier;
    }

    public void setNamaSuplier(String namaSuplier) {
        this.namaSuplier = namaSuplier;
    }

    public String getTelpSuplier() {
        return telpSuplier;
    }

    public void setTelpSuplier(String telpSuplier) {
        this.telpSuplier = telpSuplier;
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
}

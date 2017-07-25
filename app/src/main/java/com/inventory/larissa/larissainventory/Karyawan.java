package com.inventory.larissa.larissainventory;

import java.io.Serializable;

/**
 * Created by USER on 17/07/2017.
 */

public class Karyawan implements Serializable {
    String nik , nama, tgl, templatlahir, jk, nohp, agama, alamat, kelurahan , kota, provinsi, kodepos, status, pendidikan , department, jabatan, email;

    public Karyawan() {
    }

    public Karyawan(String nik, String nama, String tgl, String templatlahir, String jk, String nohp, String agama, String alamat, String kelurahan, String kota, String provinsi, String kodepos, String status, String pendidikan, String department, String jabatan, String email) {
        this.nik = nik;
        this.nama = nama;
        this.tgl = tgl;
        this.templatlahir = templatlahir;
        this.jk = jk;
        this.nohp = nohp;
        this.agama = agama;
        this.alamat = alamat;
        this.kelurahan = kelurahan;
        this.kota = kota;
        this.provinsi = provinsi;
        this.kodepos = kodepos;
        this.status = status;
        this.pendidikan = pendidikan;
        this.department = department;
        this.jabatan = jabatan;
        this.email = email;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getTemplatlahir() {
        return templatlahir;
    }

    public void setTemplatlahir(String templatlahir) {
        this.templatlahir = templatlahir;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

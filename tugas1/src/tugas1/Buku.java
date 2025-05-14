/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas1;

/**
 *
 * @author farhan
 */
public class Buku {
     private String kode_buku;  

    public String getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }
    private String judul;
    private String pengarang;
    private String penerbit;
    private String tahun_terbit;
    private String edisi;


    public Buku(String kode_buku,String judul,String pengarang,String penerbit,String tahun_terbit,String edisi) {
        this.kode_buku=kode_buku;
        this.judul = judul;  
         this.pengarang = pengarang;  
          this.penerbit = penerbit;  
           this.tahun_terbit = tahun_terbit; 
            this.edisi = edisi;  
    }

    

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getTahun_terbit() {
        return tahun_terbit;
    }

    public void setTahun_terbit(String tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }

    public String getEdisi() {
        return edisi;
    }

    public void setEdisi(String edisi) {
        this.edisi = edisi;
    }
    
    
}
package week8;

public abstract class Produk {
    protected int no;
    protected String nama;
    protected long harga;

    public Produk(int no, String nama, long harga) {
        this.no = no;
        this.nama = nama;
        this.harga = harga;
    }

    public int getNo() {
        return no;
    }

    public String getNama() {
        return nama;
    }

    public long getHarga() {
        return harga;
    }

    public abstract String getTipe();
}
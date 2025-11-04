package week8;

public class Transaksi {
    private static int nextId = 1;
    private int noTransaksi;
    private Produk produk;
    private long sisaPembayaran;
    private String status;
    private int lamaCicilan;
    private long bayarPerBulan;

    public Transaksi(Produk produk, long sisaPembayaran, String status, int lamaCicilan, long bayarPerBulan) {
        this.noTransaksi = nextId++;
        this.produk = produk;
        this.sisaPembayaran = sisaPembayaran;
        this.status = status;
        this.lamaCicilan = lamaCicilan;
        this.bayarPerBulan = bayarPerBulan;
    }

    public int getNoTransaksi() {
        return noTransaksi;
    }

    public Produk getProduk() {
        return produk;
    }

    public long getSisaPembayaran() {
        return sisaPembayaran;
    }

    public String getStatus() {
        return status;
    }

    public long getHargaPembayaranSelanjutnya() {
        if (status.equals("FINISHED")) {
            return 0;
        }
        if (lamaCicilan > 0) {
            return bayarPerBulan;
        } else {
            return sisaPembayaran;
        }
    }

    public void bayar(long jumlah) {
        this.sisaPembayaran -= jumlah;
        if (this.sisaPembayaran <= 0) {
            this.sisaPembayaran = 0;
            this.status = "FINISHED";
        }
    }
}
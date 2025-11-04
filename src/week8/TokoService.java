package week8;

import java.util.ArrayList;
import java.util.List;

public class TokoService {

    private List<Produk> daftarBarang = new ArrayList<>();
    private List<Transaksi> daftarTransaksi = new ArrayList<>();

    public TokoService() {
        initBarang();
    }

    private void initBarang() {
        daftarBarang.add(new Elektronik(1, "Kulkas", 480000));
        daftarBarang.add(new Elektronik(2, "TV", 1280000));
        daftarBarang.add(new Komputer(3, "Laptop", 600000));
        daftarBarang.add(new Komputer(4, "PC", 1200000));
    }

    public List<Produk> getDaftarBarang() {
        return daftarBarang;
    }

    public List<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public Produk findProdukByNo(int no) {
        for (Produk p : daftarBarang) {
            if (p.getNo() == no) {
                return p;
            }
        }
        return null;
    }

    public Transaksi findTransaksiByNo(int no) {
        for (Transaksi t : daftarTransaksi) {
            if (t.getNoTransaksi() == no) {
                return t;
            }
        }
        return null;
    }

    public void createTransaksiCashLunas(Produk produk) {
        Transaksi trx = new Transaksi(produk, 0, "FINISHED", 0, 0);
        daftarTransaksi.add(trx);
    }

    public void createTransaksiCashSimpan(Produk produk) {
        Transaksi trx = new Transaksi(produk, produk.getHarga(), "ON PROGRESS", 0, 0);
        daftarTransaksi.add(trx);
    }

    public Transaksi createTransaksiKredit(Produk produk, int lamaCicilan) {
        long bayarPerBulan = produk.getHarga() / lamaCicilan;
        long sisa = produk.getHarga() - bayarPerBulan;

        Transaksi trx = new Transaksi(produk, sisa, "ON PROGRESS", lamaCicilan, bayarPerBulan);
        daftarTransaksi.add(trx);
        return trx;
    }

    public void prosesPembayaran(Transaksi trx) {
        long harusBayar = trx.getHargaPembayaranSelanjutnya();
        if (harusBayar > 0) {
            trx.bayar(harusBayar);
        }
    }
}
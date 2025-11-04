package week8;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private TokoService service = new TokoService();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main program = new Main();
        program.runMenu();
    }

    public void runMenu() {
        while (true) {
            tampilMenuUtama();
            int pilihan = getPilihan();
            switch (pilihan) {
                case 1:
                    menuPesanBarang();
                    break;
                case 2:
                    menuLihatPesanan();
                    break;
                case 0: // <-- TAMBAHAN BARU
                    System.out.println("Terima kasih. Program ditutup.");
                    scanner.close(); // Menutup scanner sebelum keluar
                    return; // Keluar dari metode runMenu() dan menghentikan loop
                default:
                    System.out.println("Pilihan tidak valid.");
            }
            System.out.println();
        }
    }

    private void tampilMenuUtama() {
        System.out.println("---Program Toko Elektronik---");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
        System.out.println("0. Keluar"); // <-- TAMBAHAN BARU
        System.out.print("Pilihan : ");
    }

    private void menuPesanBarang() {
        tampilDaftarBarang();
        System.out.print("Pilih : ");
        int pilihanBarang = getPilihan();
        Produk produk = service.findProdukByNo(pilihanBarang);

        if (produk == null) {
            System.out.println("Barang tidak ditemukan.");
            return;
        }

        System.out.println("Pilih : " + produk.getNo());
        System.out.println("Nama  : " + produk.getNama());
        System.out.println("Tipe  : " + produk.getTipe());
        System.out.println("Harga : " + produk.getHarga());
        System.out.println("---Tipe pembayaran---");
        System.out.println("1. Cash");
        System.out.println("2. Credit");
        System.out.print("Pilih : ");
        int pilihanBayar = getPilihan();

        if (pilihanBayar == 1) {
            prosesBayarCash(produk);
        } else if (pilihanBayar == 2) {
            prosesBayarKredit(produk);
        } else {
            System.out.println("Tipe pembayaran tidak valid.");
        }
    }

    private void tampilDaftarBarang() {
        System.out.println("---Daftar Barang---");
        for (Produk p : service.getDaftarBarang()) {
            System.out.println("No    : " + p.getNo());
            System.out.println("Nama  : " + p.getNama());
            System.out.println("Tipe  : " + p.getTipe());
            System.out.println("Harga : " + p.getHarga());
            System.out.println("---------------------");
        }
    }

    private void prosesBayarCash(Produk produk) {
        System.out.print("Bayar (Y/N): ");
        String konfirmasi = scanner.nextLine().trim();
        if (konfirmasi.equalsIgnoreCase("Y")) {
            System.out.println("Harga Pembayaran : " + produk.getHarga());
            System.out.println("Bayar            : " + produk.getHarga());
            service.createTransaksiCashLunas(produk);
            System.out.println("Transaksi telah dibayar lunas");
        } else {
            service.createTransaksiCashSimpan(produk);
            System.out.println("Transaksi telah disimpan");
        }
    }

    private void prosesBayarKredit(Produk produk) {
        int lamaCicilan = 0;
        while (true) {
            System.out.print("Lama Cicilan (3/6/9/12): ");
            lamaCicilan = getPilihan();
            if (lamaCicilan == 3 || lamaCicilan == 6 || lamaCicilan == 9 || lamaCicilan == 12) {
                break;
            }
            System.out.println("Pilihan cicilan tidak valid.");
        }

        Transaksi trx = service.createTransaksiKredit(produk, lamaCicilan);
        long bayarPerBulan = trx.getHargaPembayaranSelanjutnya();

        System.out.println("Harga Pembayaran : " + bayarPerBulan);
        System.out.println("Bayar            : " + bayarPerBulan);
        System.out.println("Transaksi telah dibayar");
    }

    private void menuLihatPesanan() {
        tampilDaftarTransaksi();

        System.out.print("Pilih No Transaksi : ");
        int noTrx = getPilihan();

        // Menambahkan cek jika input adalah 0, kembali ke menu utama
        if (noTrx == 0) {
            return;
        }

        Transaksi trx = service.findTransaksiByNo(noTrx);

        if (trx == null) {
            System.out.println("Transaksi tidak ditemukan.");
            return;
        }

        prosesBayarCicilan(trx);
    }

    private void tampilDaftarTransaksi() {
        System.out.println("---Daftar Pesanan---");
        if (service.getDaftarTransaksi().isEmpty()) {
            System.out.println("(Tidak ada pesanan)");
            return;
        }
        for (Transaksi t : service.getDaftarTransaksi()) {
            Produk p = t.getProduk();
            System.out.println("No               : " + t.getNoTransaksi());
            System.out.println("Nama             : " + p.getNama());
            System.out.println("Tipe             : " + p.getTipe());
            System.out.println("Status           : " + t.getStatus());
            System.out.println("Sisa Pembayaran  : " + t.getSisaPembayaran());
            System.out.println("-------------------------");
        }
    }

    private void prosesBayarCicilan(Transaksi trx) {
        System.out.println("Pilih No Transaksi : " + trx.getNoTransaksi());
        System.out.println("Nama             : " + trx.getProduk().getNama());
        System.out.println("Tipe             : " + trx.getProduk().getTipe());
        System.out.println("Status           : " + trx.getStatus());
        System.out.println("Sisa Pembayaran  : " + trx.getSisaPembayaran());

        if (trx.getStatus().equals("FINISHED")) {
            System.out.println("Transaksi telah lunas");
            return;
        }

        long harusBayar = trx.getHargaPembayaranSelanjutnya();
        System.out.println("Harga Pembayaran : " + harusBayar);

        System.out.print("Bayar            : ");
        scanner.nextLine();

        service.prosesPembayaran(trx);

        if (trx.getStatus().equals("FINISHED")) {
            System.out.println("Transaksi telah dibayar lunas");
        } else {
            System.out.println("Transaksi telah dibayar");
        }
    }

    private int getPilihan() {
        try {
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            return pilihan;
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka.");
            scanner.nextLine();
            return -1;
        }
    }
}
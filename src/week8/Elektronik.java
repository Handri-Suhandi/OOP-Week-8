package week8;

public class Elektronik extends Produk {

    public Elektronik(int no, String nama, long harga) {
        super(no, nama, harga);
    }

    @Override
    public String getTipe() {
        return "Electronic";
    }
}
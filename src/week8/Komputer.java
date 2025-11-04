package week8;

public class Komputer extends Produk {

    public Komputer(int no, String nama, long harga) {
        super(no, nama, harga);
    }

    @Override
    public String getTipe() {
        return "Computer";
    }
}
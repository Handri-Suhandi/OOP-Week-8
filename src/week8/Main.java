package week8;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Item> ListOfItems = new ArrayList<Item>();
    static ArrayList<Payment> ListOfPayments = new ArrayList<Payment>();
    static Scanner s = new Scanner(System.in);

    public static void seedData(){
        ListOfItems.add(new Item("Kulkas", "Elektronik", 4800000));
        ListOfItems.add(new Item("TV", "Elektronik", 1280000));
        ListOfItems.add(new Item("Laptop", "Komputer", 6000000));
        ListOfItems.add(new.Item("PC", "Komputer", 12000000));
    }

    public static void printItem(Item item){
        System.out.printf("Nama     : "+item.getName());
        System.out.printf("Tipe     : "+item.getType());
        System.out.printf("Harga    : "+item.getPrice());
    }

    public static void main(String[] args) {
        int opt = 0;
        int id = 0;
        seedData();
        do{
            System.out.printf("---Program Toko Elektronik---");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("0. Keluar");
            opt = s.nextInt();
            if(opt == 1){
                System.out.println("---Daftar Barang---");
                for(int i = 0; i<ListOfItems.size(); i++){
                    System.out.println("No  : "+(i+1));
                    printItem(ListOfItems.get(i));
                    System.out.println("-------------------");
                }
                System.out.println("Pilih   : ");
            }
            else if (opt == 2) {

            }
            else if (opt == 0) {

            }
            else{
                System.out.println("Salah Input");
            }
        }while (opt != 0);
    }
}
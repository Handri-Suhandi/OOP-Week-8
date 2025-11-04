package week8;

public class Item {
    private String Name;
    private String Type;
    private int price;

    public Item(String name, String type, int price) {
        this.Name = name;
        this.Type = type;
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
import java.util.UUID;

public class Item {
    private final String name;
    private String uuid;
    private int quantity;
    private float price;
    private final String category;

    public Item(String name, int quantity, float price, String category) {
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    // setters
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

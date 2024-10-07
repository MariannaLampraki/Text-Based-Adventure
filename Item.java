public class Item {
    private final String itemName;
    private final boolean givable;
    public String place;
    public Item(String itemName, boolean givable) {
        this.itemName = itemName;
        this.givable = givable;
    }
    public String getItemName() {
        return itemName;
    }
    public boolean isGivable() {
        return givable;
    }
}

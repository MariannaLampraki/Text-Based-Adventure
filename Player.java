import java.util.HashMap;

public class Player {
    public HashMap<String, Item> inventory = new HashMap<>();
    private Area currentPos;
    public Player() {}
    public String getInventory() {
        return inventory.keySet().toString();
    }
    public void addToInventory(Item item) {
        if (inventory.size() < 10) {
            inventory.put(item.getItemName(), item);
            currentPos.removeItem(item);
        }
        else {
            System.out.println("Your inventory is too heavy. Maybe leave something behind.");
        }
    }
    public void removeFromInventory(String item) {
        if (inventory.containsKey(item)) {
            currentPos.addItem(inventory.get(item));
            inventory.get(item).place = "on the ground there is a " + inventory.get(item).getItemName();
            inventory.remove(item);
        }
    }
    public void giveItemToEntity(Item item, Entity entity) {
        if (!entity.hasKey() && (entity.getKey() == item) && item.isGivable()) {
            entity.setHasKey(true);
            inventory.remove(item.getItemName());
        }
    }
    public Area getLocation() {
        return currentPos;
    }
    public void setLocation(Area location) {
        currentPos = location;
    }
}

import java.util.HashMap;

public class Area {
    private final String description;
    private HashMap<String, Item> items = new HashMap<>();
    private HashMap<String, Entity> entities = new HashMap<>();
    public Area(String description) {
        this.description = description;
    }
    public void getDescription() {
        StringBuilder newDescription = new StringBuilder(description);
        if (items != null) {
            for (Item item : items.values()) {
                newDescription.append(item.place);
            }
            for (Entity entity : entities.values()) {
                newDescription.append(entity.place);
            }
        }
        System.out.println(newDescription);
    }
    public void removeItem(Item item) {
        if (!items.isEmpty()) {
            items.remove(item.getItemName());
        }
    }
    public void addItem(Item item) {
        items.put(item.getItemName(), item);
    }
    public void addEntity(Entity entity) {
        entities.put(entity.name, entity);
    }
    public HashMap<String, Item> getItems() {
        return items;
    }
    public void removeAnimal(Entity entity) {
        entities.remove(entity.name);
    }
}

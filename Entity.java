public class Entity {
    public Area position;
    private final Item key;
    private boolean hasKey = false;
    public String name;
    public String place;
    public Entity(Area position, Item key, String name) {
        this.position = position;
        this.key = key;
        this.name = name;
    }
    public Item getKey() {
        return key;
    }
    public boolean hasKey() {
        return hasKey;
    }
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
}

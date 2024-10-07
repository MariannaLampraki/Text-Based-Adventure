import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // area creation
        Area start = new Area("You are standing in a garden. To the EAST there is a" +
                " house. \nTo the WEST there is a road. To the NORTH there is a forest. To the SOUTH there is the side-garden.");
        Area ehouse = new Area("You are facing the front of the house. " +
                "The door and windows are barred with wooden planks.");
        Area shouse = new Area("You are at the side garden of the house." +
                " There is a bird bath next to you and a small dog-house further away.");
        Area whouse = new Area("You are standing on the inner side a " +
                "white-painted fence. On the other side is the road. Next to you is a post box.");
        Area nhouse = new Area("You are at the side of the house.");


        // commands
        var commands = """
                Your commands are:
                 inventory : shows inventory
                 remove [item] : removes item from inventory \
                (leave them in your current position in case you want them back)
                 look around : describes the area you \
                are in
                 commands : shows this command list
                 go [west/east/south/north] : takes you to the specific \
                location of the map
                 go back : takes you to the start
                 take [item name] : removes this item from it's \
                current location and puts it in your inventory
                 give [animal] [item] : obvious\s
                 [action] [thing] : \
                like 'break glass' or 'dig dirt' or something""";
        System.out.println(commands);


        // player
        Player player = new Player();
        player.setLocation(start);


        // item placement
        Item apple = new Item("apple", true);
        apple.place = "\nThere is an apple on the floor.";
        start.addItem(apple);

        Item letter = new Item("letter", false);
        letter.place = "\nThere is a letter in the post office.";
        whouse.addItem(letter);

        Item shovel = new Item("shovel", true);
        shovel.place = "\nLeaning on a tree there is a shovel.";

        Item bone = new Item("bone", true);
        bone.place = "\nIn the hole there is a bone";

        Item worm = new Item("worm", true);
        worm.place = "\nOn the ground near the bird's bath there is a worm.";
        shouse.addItem(worm);

        Item hammer = new Item("hammer", true);
        hammer.place = "\nInside the dog's house there is a hammer.";

        Entity dirt = new Entity(ehouse, shovel, "dirt");
        dirt.place = "\nThere is a patch of freshly dug dirt near the door.";
        ehouse.addEntity(dirt);
        Entity bird = new Entity(nhouse, worm, "bird");
        bird.place = "\nThere is a bird sitting on a shovel.";
        nhouse.addEntity(bird);
        Entity dog = new Entity(shouse, bone, "dog");
        dog.place = "\nIn the dog house there is a dog.";
        shouse.addEntity(dog);
        Entity window = new Entity(nhouse, hammer, "window");
        window.place = "\nThere is a locked window that can easily be broken with something heavy.";
        nhouse.addEntity(window);

        HashMap<String, Entity> animals = new HashMap<>();
        animals.put("dirt", dirt);
        animals.put("bird", bird);
        animals.put("dog", dog);
        animals.put("window", window);

        start.getDescription();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!Objects.equals(input, "quit")) {
            Scanner sc1 = new Scanner(input).useDelimiter(" ");
            String word = sc1.next();
            if (word.contains("inventory")) {
                System.out.println(player.getInventory());
            }
            else if (word.contains("remove")) {
                var removedItem = sc1.next();
                if (!player.getInventory().contains(removedItem)) {
                    System.out.println("Your inventory does not contain "+removedItem);
                }
                player.removeFromInventory(removedItem);
            }
            else if (input.contains("look around")) {
                player.getLocation().getDescription();
            }
            else if (input.contains("commands")) {
                System.out.println(commands);
            }
            else if (input.contains("go")) {
                var requiredLocation = sc1.next();
                switch (requiredLocation) {
                    case "east":
                        player.setLocation(ehouse);
                        player.getLocation().getDescription();
                        break;
                    case "south":
                        player.setLocation(shouse);
                        player.getLocation().getDescription();
                        break;
                    case "north":
                        player.setLocation(nhouse);
                        player.getLocation().getDescription();
                        break;
                    case "west":
                        player.setLocation(whouse);
                        player.getLocation().getDescription();
                        break;
                    default:
                        System.out.println("Invalid location");
                        break;
                }
            }
            else if (input.contains("take")) {
                String takenItem = sc1.next();
                if (player.getLocation().getItems().keySet().toString().contains(takenItem)) {
                    player.addToInventory(player.getLocation().getItems().get(takenItem));
                }
                else {
                    System.out.println("This location does not contain requested item");
                }
            }
            else if (word.equals("give") || word.equals("dig") || word.equals("break")) {
                String animalGiven = sc1.next();
                if ((player.getLocation()==animals.get(animalGiven).position)&&!(animals.get(animalGiven).hasKey())) {
                    String itemGiven = sc1.next();
                    while (itemGiven.equals("with")) {
                        itemGiven = sc1.next();
                    }
                    if((itemGiven.equals(animals.get(animalGiven).getKey().getItemName()))&&(player.getInventory().contains(itemGiven))) {
                        player.giveItemToEntity(player.inventory.get(itemGiven), animals.get(animalGiven));
                        player.getLocation().removeAnimal(animals.get(animalGiven));
                    }
                    else if (!player.getInventory().contains(itemGiven)) {
                        System.out.println("You don't have that item.");
                    }
                    else {
                        System.out.println("You can't give that item to that entity.");
                    }
                }
                player.getLocation().getDescription();
            }
            else {
                System.out.println("I don't understand you");
            }
            sc1.close();
            if ((player.getLocation()==dirt.position)&&(dirt.hasKey())){
                ehouse.addItem(bone);
            }
            if ((player.getLocation()==bird.position)&&(bird.hasKey())){
                nhouse.addItem(shovel);
            }
            if ((player.getLocation()==dog.position)&&(dog.hasKey())){
                shouse.addItem(hammer);
            }
            if ((player.getLocation()==window.position)&&(window.hasKey())){
                System.out.println("You broke the window and got inside the house\n\nCongrats! You won!");
                break;
            }
            input = sc.nextLine();
        }
        sc.close();
    }
}
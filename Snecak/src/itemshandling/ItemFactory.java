package itemshandling;

import java.util.Random;
    public class ItemFactory {

        public ItemBase createItem(String itemType) {
            if (itemType == null) {
                return null; // Invalid input, return null
            }

            ItemBase newItem = null;
            switch (itemType.toLowerCase()) {
                case "weapon":
                    newItem = createWeapon();
                    break;
                case "armor":
                    newItem = createArmor();
                    break;
                case "consumable":
                    newItem = createConsumable();
                    break;
                default:
                    System.out.println("Invalid item type: " + itemType);
            }
            return newItem;
        }

        private Weapon createWeapon() {
            Weapon weapon = new Weapon(0, 0, 0);
            weapon.InitializeName(); // Initialize weapon names
            return weapon;
        }

        private Armor createArmor() {
            Armor armor = new Armor(0, 0, 0);
            armor.InitializeName(); // Initialize armor names
            return armor;
        }

        private Consumable createConsumable() {
            Consumable consumable = new Consumable(0, 0, 0);
            consumable.InitializeName(); // Initialize consumable names
            return consumable;
        }
    }


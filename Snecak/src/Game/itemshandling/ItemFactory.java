package Game.itemshandling;

import Game.players.Player;

public class ItemFactory {

        public static ItemBase createItem(Player player) {
            ItemType itemType = ItemBase.getRandomItemType();
            switch (itemType) {
                case CONSUMABLE:
                    Consumable consumable = new Consumable(0, 0, 0);
                    consumable.setRandomHealth(player);
                    consumable.setRandomDamage(player);
                    return consumable;
                case ARMOR:
                    Armor armor = new Armor(0, 0, 0);
                    armor.setRandomProtection(player);
                    armor.setRandomDamage(player);
                    return armor;
                case WEAPON:
                    Weapon weapon = new Weapon(0, 0, 0);
                    weapon.setRandomDamage(player);
                    return weapon;
                default:
                    return null;
            }
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


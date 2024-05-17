package game.abilities;

public class AbilityFactory {

    public static AbilityType fromString(String abilityName) {
        try {
            return AbilityType.valueOf(abilityName.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            return null; // or handle exception as needed
        }
    }
}
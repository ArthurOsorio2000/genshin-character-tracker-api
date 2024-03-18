package GenshinCharacterTracker.api;

import java.util.ArrayList;

public class Talent {
    private int itemCode;
    private String name;
    private String region;
    private int dayCycle;
    private String[] daysActive = setDaysActive(dayCycle);
    private ArrayList<CharacterTemplate> Characters = new ArrayList<CharacterTemplate>();
    
    public Talent(){
    }

    public Talent(int itemCode, String name, String region, int dayCycle){
        this.itemCode = itemCode;
        this.name = name;
        this.region = region;
        this.dayCycle = dayCycle;

    }
    
    public int getItemCode() {return itemCode;}
    public String getName() {return name;}
    public String getRegion() {return region;}
    public int getDayCycle() {return dayCycle;}
    public String[] getDaysActive() {return daysActive;}
    public ArrayList<CharacterTemplate> getCharacters() {return Characters; }

    public void addCharacter(CharacterTemplate inputCharacter){
        Characters.add(inputCharacter);
    }

    private static String[] setDaysActive(int input){
        String[] output = {"null", "null", "Sunday"};
        switch(input){
            case 0:
            output[0] = "Monday";
            output[1] = "Thursday";
                break;
            case 1:
            output[0] = "Tuesday";
            output[1] = "Friday";
                break;
            case 2:
            output[0] = "Wednesday";
            output[1] = "Saturday";
                break;
            default:
                System.out.println("your input: " + input + " is not a valid dayCycle.");
        }
        return output;
    }
}

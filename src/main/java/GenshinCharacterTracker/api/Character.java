package GenshinCharacterTracker.api;

public class Character extends CharacterTemplate {
    //variables from template
    private int charID;
    private String name;
    private String region;
    private int talentCode;
    //variables for player entry
    private int level;
    private int[] talentLevels = new int[3];
    //add a variable to hold a weapon object.

    public Character(){
    }

    public Character(int charID, String name, String region, int talentCode, int level, int[] talentLevels) {
        super(name, region, talentCode);
        this.charID = charID;
        this.level = level;
        this.talentLevels = talentLevels;
    }

    public int getCharID() {return charID;}
    public String getName() {return name;}
    public String getRegion() {return region;}
    public int getTalentCode() {return talentCode;}
    public int getLevel() {return level;}
    public int[] getTalentLevels() {return talentLevels;}
}

package GenshinCharacterTracker.api;

public class CharacterTemplate {
    private String name;
    private String region;
    private int talentCode;
    private String talent;
    //add a talent converter so that after parsing all talents, takes talent codes, aligns them with a talent
    //and converts the talent to the talent code
    
    public CharacterTemplate(){
    }

    public CharacterTemplate (String name, String region, int talentCode){
        this.name = name;
        this.region = region;
        this.talentCode = talentCode;
        this.talent = UI.compareTalent(talentCode);
    }

    public String getName() {return name;}
    public String getRegion() {return region;}
    public int getTalentCode() {return talentCode;}
    public String getTalent() {return talent;}
}
package GenshinCharacterTracker.api;

import java.util.Map;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;

public class UI extends JFrame{
    //Creating shared variables
    //Create Objects
    //!!maybe put this in a database to be automatically created later
    //should I put this into its own class?
    private static ArrayList<Talent> talentList = Tools.talentLoader("src\\main\\java\\GenshinCharacterTracker\\api\\DataImportFiles\\talentData.txt");
    private static ArrayList<CharacterTemplate> characterList = Tools.characterLoader("src\\main\\java\\GenshinCharacterTracker\\api\\DataImportFiles\\CharacterData.txt");
    private static int todaysCycle = Tools.calculateDayCycle();

    //show both pre and post daily reset
    private static Map<String,ArrayList<Talent>> rawTalentList = Tools.getFarmableTalents(talentList, todaysCycle);
    private static ArrayList<Talent> farmableTalents = rawTalentList.get("farmableTalents");
    private static ArrayList<Talent> preFarmableTalents = rawTalentList.get("preFarmableTalents");

    //1. Create a tool in Tools to create a list of farmable characters
    //2. Fill this list with characters that can be farmed today
    //use the list in both the GUI and the terminal program
    private static ArrayList<CharacterTemplate> farmableCharacterTemplates = new ArrayList<CharacterTemplate>();

    public UI(){
    }

    //create getters
    public ArrayList<Talent> getTalentList() {return talentList;}
    public ArrayList<CharacterTemplate> getCharacterList() {return characterList;}
    public int getTodaysCycle()  {return todaysCycle;}
    //get processesed list of talents
    public ArrayList<Talent> getFarmableTalents()  {return farmableTalents;}
    //get yesterday's processed list of talents
    public ArrayList<Talent> getPreFarmableTalents()  {return preFarmableTalents;}
    //get list of farmable characters
    public ArrayList<CharacterTemplate> getFarmableCharacterTemplates()  {return farmableCharacterTemplates;}

    //------------------------------------------------GUI------------------------------------------------
    public static void createGUI(){
        //create Swing UI:
        JFrame mainFrame = new JFrame();//creating instance of JFrame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create grid:
        //1. first column lists all farmable talents today and shows talent book images
        //2. second column lists all characters that can be farmed in the same row as their talent book


        //create panel with all characters so user can select characters user is using
        JPanel allCharactersPanel = new JPanel();
        allCharactersPanel.setBackground(Color.decode("#282c34"));
        allCharactersPanel.setPreferredSize(new Dimension(400, 300));
        
        mainFrame.getContentPane().add(allCharactersPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);//making the frame visible
    }

    //----------------------------------------------Terminal---------------------------------------------
    //used to test the functionality of the program prior to SWING GUI integration
    public static void createTerminal(){
        talentCharacterFiller(talentList, characterList);
        //Display to console:
        System.out.println();
        System.out.println("Yesterdays talents can be farmed before reset: ");
        for(Talent talent: preFarmableTalents){
            System.out.println(talent.getName() + " - " + talent.getRegion());
        }
        System.out.println();
        //print a list of talents that can be farmed today
        System.out.println("Today's talents can be farmed at reset: ");
        for(Talent talent: farmableTalents){
            System.out.println(talent.getName() + " - " + talent.getRegion());
        }
        
        System.out.println();

        //prints a list of Characters who use yesterday's talents

        //prints a list of Characters who use today's talents
        System.out.println("Character's that can be farmed for today: ");
        for(CharacterTemplate character: characterList){
            for(Talent talent : farmableTalents){
                if(character.getTalentCode() == talent.getItemCode()){
                    //!!maybe stick these in a list to be sorted into their regions or smth
                    farmableCharacterTemplates.add(character);
                    System.out.println(talent.getName() + " - " + character.getName());
                }
            }
        }
    }

    //-----------------------------------------Population Tools----------------------------------------
    public static void addCharacter(String name, String region, int talentType){
        CharacterTemplate newCharData = new CharacterTemplate(name, region, talentType);
        characterList.add(newCharData);
    }

    public static String compareTalent(int talentCode){
        String output = "";
        for(Talent talent : talentList){
            if(talentCode == talent.getItemCode()){
                output = talent.getName();
            }
        }
        return output;
    }

    public static void talentCharacterFiller(
    ArrayList<Talent> talentListInput,
    ArrayList<CharacterTemplate> characterListInput){
        for(CharacterTemplate character : characterListInput){
            boolean talentFound = false;
            while (!talentFound){
                for(Talent talent : talentListInput){
                    if(character.getTalentCode() == talent.getItemCode()){
                        talent.addCharacter(character);
                        talentFound = true;
                    }
                }
            if(!talentFound){
                    System.out.println("Talent not found for " + character.getName());
            }
            }
        }
    }

    public static void readTalentCharacterList(){
        for(Talent talent : talentList){
            System.out.println("Talent: " + talent.getName());
            for(CharacterTemplate character : talent.getCharacters()){
                System.out.println(character.getName() + " is talented in: " + talent.getName());
            }
        }
    }
}
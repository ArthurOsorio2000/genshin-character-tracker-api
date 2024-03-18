package GenshinCharacterTracker.api;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Tools {
    //calculate what day it is based on your calendar and at reset what the talent for today is
    public static int calculateDayCycle(){
        //Gets the date of today in a numerical format: Sunday = 0, Monday = 1 etc.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2;

        //Mods the answer to 3 - 2 to get the dayCycle unless it is Sunday. If it is sunday, it will output 3 to signify
        //all Talents are available
        int output;
        if(dayOfWeek != - 1){
            output = (dayOfWeek % 3);
        }
        else{
            output = 3;
        }
        return output;
    }

    //read and automatically import talent data from text file inputted
    public static ArrayList<Talent> talentLoader(String filePath){
        ArrayList<Talent> outputList = new ArrayList<Talent>();
        try {
            File inputFile = new File(filePath);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                //skip // so I can add commments into the text file
                if(input.startsWith("//")){
                    continue;
                }
                //navigate through the text file line by line: this is what is operated on each line
                String[] inputData = input.split(", ");
                Talent newTalentData = new Talent(Integer.parseInt(inputData[0]), inputData[1], inputData[2], Integer.parseInt(inputData[3]));
                outputList.add(newTalentData);
                System.out.println("New talent " + newTalentData.getName() + " was added.");
            }
            scanner.close();
        //error handling
        }catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return outputList;
    }
    
    //read and automatically import character template data from text file inputted
    public static ArrayList<CharacterTemplate> characterLoader(String filePath){
        ArrayList<CharacterTemplate> outputList = new ArrayList<CharacterTemplate>();
        try {
            File inputFile = new File(filePath);
            Scanner scanner = new Scanner(inputFile);
            int talentType = -1;
            String currentRegion = "";

            //navigate through the text file line by line: this is what is operated on each line
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                switch(input.substring(0, 1)){
                    //if it's a comment, continue
                    case "/":
                        continue;

                    //if it's tagged "!" it's the region of the following characters                    
                        case "!":
                        String[] regionRead = input.split("!");
                        currentRegion = regionRead[1];
                        continue;

                    //if it's tagged "~" it's the talent of the following characters                    
                        case "~":
                        String[] talentRead = input.split("~");
                        talentType = Integer.parseInt(talentRead[1]);
                        continue;

                    //if it's tagged "#" it's a new character that uses the region and talent                       
                    case "#":
                    String[] newCharacterName = input.split("#");
                    CharacterTemplate newCharData = new CharacterTemplate(newCharacterName[1], currentRegion, talentType);
                    outputList.add(newCharData);
                    System.out.println("New character " + newCharData.getName() + " was added.");
                    continue;
                }
            }
            scanner.close();
        //error handling
        }catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return outputList;
    }

    //create a hashmap so that two arrays can be outputted at the same time to prevent looping through an array twice
    //big O notation and all that
    public static Map<String, ArrayList<Talent>> getFarmableTalents(ArrayList<Talent> talentList, int todaysCycle){
        ArrayList<Talent> farmableTalents = new ArrayList<Talent>();
        ArrayList<Talent> preFarmableTalents = new ArrayList<Talent>();

        for(Talent talent: talentList){
            if(talent.getDayCycle() == todaysCycle || todaysCycle == 3){
                farmableTalents.add(talent);
            }

            //this is optional and can be removed - finds talents that can be farmed before reset
            //if I could update the get-day-cycle to align to the time which the servers are based in, this may
            //be more comprehensive
            if(talent.getDayCycle() == todaysCycle - 1 || todaysCycle + 3 == 3){
                preFarmableTalents.add(talent);
            }
        }

        Map<String, ArrayList<Talent>> output = new HashMap<String, ArrayList<Talent>>();
        output.put("farmableTalents", farmableTalents);
        output.put("preFarmableTalents", preFarmableTalents);
        return output;
    }
}

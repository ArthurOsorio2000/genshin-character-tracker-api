package GenshinCharacterTracker.api;

import java.util.ArrayList;
import java.util.List;

public class Player {
    int userID;
    String userName;
    List<CharacterTemplate> characterList = new ArrayList<>();

    public Player(){
    }

    public Player(int userID, String userName, List<CharacterTemplate> characterList){
        this.userID = userID;
        this.userName = userName;
        this.characterList = characterList;
    }

    public void addCharacter(){
        //input a character
    }
}

package GenshinCharacterTracker.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
//Springboot imports
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class API extends UI {
    //------------------------------------------------API------------------------------------------------


//    //return today's day cycle
//    @CrossOrigin
//	@RequestMapping("day-cycle")
//	public int apiTodayCycle() {
//		return getTodaysCycle();
//	}
    
    //return today's farmable talents
    @CrossOrigin
	@RequestMapping("/talents/farmable-today")
	public ArrayList<Talent> apiFarmableToday() {
		return getFarmableTalents();
	}

    //return yesterday's farmable talents
    @CrossOrigin
	@RequestMapping("/talents/farmable-yesterday")
	public ArrayList<Talent> apiFarmableYesterday() {
		return getPreFarmableTalents();
	}

//    //return list of all characters
//    @CrossOrigin
//	@RequestMapping("character/full-list")
//	public ArrayList<CharacterTemplate> apiFullCharacterList() {
//		return getCharacterList();
//	}

//    //return list of characters farmable today
//    @CrossOrigin
//    @RequestMapping("character/farmable-today")
//	public ArrayList<CharacterTemplate> apiCharactersFarmableToday() {
//		return getFarmableCharacterTemplates();
//	}


    //--------------------------------------------API Testing--------------------------------------------

    //testing URLs
	@RequestMapping("/holly")
	public String helloHolly() {
		return "Hello, Holly!";
	}

    //testing taking inputs
    @GetMapping("/getvar/{input}")
    @ResponseBody
    public String getVar(@PathVariable String input) {
        return "var: " + input;
    }

    int counter = 0;
    //testing returning updated values
    @RequestMapping("/counter")
	public String counter() {
        counter++;
		return String.format("Site visited: %d times", counter);
	}

    //testing proper formatting
    @RequestMapping(value = "/proper", method = RequestMethod.GET)
    @ResponseBody
	public String properRequestMapping() {
        return "Done properly";
	}
}

package com.rpgdatabase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpgdatabase.entity.Feat;
import com.rpgdatabase.entity.User;
import com.rpgdatabase.entity.UserCharacter;
import com.rpgdatabase.service.UserCharacterService;
import com.rpgdatabase.utility.HtmlUtility;
import com.rpgdatabase.utility.Pair;
import com.rpgdatabase.utility.TestData;

@Controller
public class UserCharacterController {
	@Autowired
	UserCharacterService service;

    @ModelAttribute("curUser")
    public User getCurUser(HttpSession session) {
        return (User) session.getAttribute("curUser");
    }
    
    // DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG DEBUG
    @GetMapping(value = "create-test-character")
    public void createTestCharacter() {
    	TestData.createTestCharacter(service);
    }
    // END DEBUG END DEBUG END DEBUG END DEBUG END DEBUG END DEBUG END DEBUG 
	
	@GetMapping(value = "/list-user-characters")
	public String showCharactersPage(ModelMap model, HttpSession session){
		List<UserCharacter> characters = service.getCharacters(getCurUser(session).getUsername());
		if(characters != null) {
			for(UserCharacter character : characters)
				formatBio(character);
			model.put("characters", characters);
		}
		return "list-user-characters";
	}
	
	@GetMapping(value = "/add-user-character")
	public String showAddCharacterPage(){
		return "add-user-character";
	}
	
	@PostMapping(value = "/add-user-character")
	public String addCharacter(HttpSession session, @RequestParam String characterName, @RequestParam String characterBio){
		service.createCharacter(getCurUser(session).getUsername(), characterName, characterBio);
		return "redirect:list-user-characters";
	}
	
	@GetMapping(value = "/delete-user-character")
	public String deleteCharacter(ModelMap model, @RequestParam String id){
		service.deleteCharacter(id);
		return "redirect:list-user-characters";
	}
	
	@GetMapping(value = "/view-user-character")
	public String viewCharacter(ModelMap model, @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		formatBio(character);
		formatFeats(model, character);
		model.put("character", character);
		return "view-user-character";
	}
	
	@GetMapping(value = "/add-user-character-feat")
	public String addCharacterFeat(ModelMap model, @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		formatBio(character);
		formatFeats(model, character);
		character.addFeature("", "", "");
		model.put("character", character);
		model.put("editFeat", character.features.size() - 1);
		return "view-user-character";
	}
	
	@GetMapping(value = "/edit-user-character-name")
	public String editCharacterName(ModelMap model,  @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		formatBio(character);
		formatFeats(model, character);
		model.put("character", character);
		model.put("editName", true);
		return "view-user-character";
	}
	
	@PostMapping(value = "/edit-user-character-name")
	public String saveCharacterName(ModelMap model,  @RequestParam String id, @RequestParam String name){
		UserCharacter character = service.getCharacter(id);
		character.setName(name);
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	@GetMapping(value = "/edit-user-character-bio")
	public String editCharacterBio(ModelMap model,  @RequestParam String id){
		UserCharacter character = service.getCharacter(id);
		formatFeats(model, character);
		model.put("character", character);
		model.put("editBio", true);
		return "view-user-character";
	}
	
	@PostMapping(value = "/edit-user-character-bio")
	public String saveCharacterBio(ModelMap model,  @RequestParam String id, @RequestParam String bio){
		UserCharacter character = service.getCharacter(id);
		character.setBio(bio);
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	@GetMapping(value = "/edit-user-character-feat")
	public String editCharacterFeat(ModelMap model,  @RequestParam String id, @RequestParam int index){
		UserCharacter character = service.getCharacter(id);
		
		formatBio(character);
		formatFeats(model, character, index);
		
		model.put("character", character);
		model.put("editFeat", index);
		return "view-user-character";
	}
	
	@PostMapping(value = "/add-roll-to-user-character-feat")
	public String addRollToFeat(ModelMap model, @RequestParam Map<String, String> requestParams) {//@RequestParam String id, @RequestParam int index, @RequestParam String name, @RequestParam String desc, @RequestParam String selfRoll){
		// extract parameters
		String id = (String) 					requestParams.remove("id");
		int index = Integer.parseInt(			requestParams.remove("index"));
		String name = (String) 					requestParams.remove("name");
		String desc = (String) 					requestParams.remove("desc");
		String selfRoll = (String) 				requestParams.remove("selfRoll");
		
		UserCharacter character = service.getCharacter(id);
		Feat editedFeat;
		if(index == character.features.size())
			editedFeat = character.addFeature(name, desc, selfRoll); // must be a new feature
		else
			editedFeat = character.changeFeature(index, name, desc, selfRoll);
		setOutrolls(editedFeat, requestParams);
		editedFeat.addOutRoll("1d20", "[Target Feat]");
		
		formatBio(character);
		formatFeats(model, character, index);
		model.put("character", character);
		model.put("editFeat", index);
		
		return "view-user-character";
	}
	
	@PostMapping(value = "/add-target-to-user-character-feat")
	public String addTargetToFeat(ModelMap model, @RequestParam Map<String, String> requestParams) {//@RequestParam String id, @RequestParam int index, @RequestParam String name, @RequestParam String desc, @RequestParam String selfRoll, @RequestParam int outRollIndex){
		// extract parameters
		String id = (String) 					requestParams.remove("id");
		int index = Integer.parseInt(			requestParams.remove("index"));
		String name = (String) 					requestParams.remove("name");
		String desc = (String) 					requestParams.remove("desc");
		String selfRoll = (String) 				requestParams.remove("selfRoll");
		int outRollIndex  = Integer.parseInt(	requestParams.remove("outRollIndex"));
		
		UserCharacter character = service.getCharacter(id);
		Feat editedFeat;
		if(index == character.features.size())
			editedFeat = character.addFeature(name, desc, selfRoll); // must be a new feature
		else
			editedFeat = character.changeFeature(index, name, desc, selfRoll);
		setOutrolls(editedFeat, requestParams);
		editedFeat.outRoll.get(outRollIndex).second.add("[Target Feat]");

		formatBio(character);
		formatFeats(model, character, index);
		model.put("character", character);
		model.put("editFeat", index);
		
		return "view-user-character";
	}
	
	@PostMapping(value = "/edit-user-character-feat")
	public String saveCharacterFeat(ModelMap model, @RequestParam Map<String, String> requestParams) {//@RequestParam String id, @RequestParam int index, @RequestParam String name, @RequestParam String desc, @RequestParam String selfRoll){
		
		// extract parameters
		String id = (String) 			requestParams.remove("id");
		int index = Integer.parseInt(	requestParams.remove("index"));
		String name = (String) 			requestParams.remove("name");
		String desc = (String) 			requestParams.remove("desc");
		String selfRoll = (String) 		requestParams.remove("selfRoll");
		
		UserCharacter character = service.getCharacter(id);
		Feat feat;
		if(index == character.features.size())
			feat = character.addFeature(name, desc, selfRoll); // must be a new feature
		else
			feat = character.changeFeature(index, name, desc, selfRoll);
		setOutrolls(feat, requestParams);
		
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	@GetMapping(value = "/delete-user-character-feat")
	public String deleteCharacterFeat(ModelMap model, @RequestParam String id, @RequestParam int index){
		UserCharacter character = service.getCharacter(id);
		character.removeFeature(index);
		service.updateCharacter(character);
		return "redirect:view-user-character?id=" + id;
	}
	
	// PRIVATE HELPER METHODS
	
	private void setOutrolls(Feat feat, Map<String,String> mixedRollsTargets) {
		// we expect that only the outrolls and targets are in the given map,
		// and that they are labeled as follows:
		// # = outRoll
		// #_# = target of outroll
		int outRollIndex;
		int targetIndex;
		System.out.println(mixedRollsTargets.toString());
		for(Map.Entry<String, String> entry : mixedRollsTargets.entrySet()) {
			
			// it's a target
			if(entry.getKey().contains("_")) {
				targetIndex = entry.getKey().lastIndexOf('_'); // temporary index holder
				outRollIndex = Integer.parseInt(entry.getKey().substring(0, targetIndex));
				targetIndex = Integer.parseInt(entry.getKey().substring(++targetIndex));
				
				// grow the containers if necessary
				fitOutrolls(feat.outRoll, outRollIndex);
				fitTargets(feat.outRoll.get(targetIndex).second, targetIndex);
				
				// now add it in
				feat.outRoll.get(outRollIndex).second.set(targetIndex, entry.getValue());
			}
			// if it's an outroll
			else {
				try {
				outRollIndex = Integer.parseInt(entry.getKey());
				fitOutrolls(feat.outRoll, outRollIndex);
				feat.outRoll.get(outRollIndex).first = entry.getValue();
				} catch(NumberFormatException e) {/*not an outroll*/};
			}
		}
	}
	
	private void fitOutrolls(List<Pair<String, List<String>>> outRoll, int index) {
		// make sure there is space for it
		Pair<String, List<String>> curData;
		while(index >= outRoll.size()) {
			curData = new Pair<String, List<String>>();
			curData.second = new ArrayList<String>();
			outRoll.add(curData);
		}
	}
	
	private void fitTargets(List<String> targets, int index) {
		// make sure there is space for it
		while(index >= targets.size())
			targets.add("");
	}
	
	private void formatBio(UserCharacter character) {
		character.bio = HtmlUtility.formatForTextArea(character.bio);
	}
	
	private void formatFeats(ModelMap model, UserCharacter character) {
		formatFeats(model, character, -1);
	}
	
	private void formatFeats(ModelMap model, UserCharacter character, int exceptThisFeatIndex) {
		character.compileFeatureMods();
		int totalRows;
		int[] featRows = new int[character.features.size()];
		for(int i = 0; i < character.features.size(); ++i){
			
			// count the total number rows necessary
			totalRows = 0;
			for(int j = 0; j < character.features.get(i).outRoll.size(); ++j)
				totalRows += character.features.get(i).outRoll.get(j).second.size();
			if(totalRows == 0)
				totalRows = 1;
			featRows[i] = totalRows;
			
			// ignore formatting a feat (because it' being edited)
			if(i == exceptThisFeatIndex)
				continue;

			// handle feat formatting
			character.features.get(i).description = HtmlUtility.formatForTextArea(character.features.get(i).getDescription());
		}
		model.addAttribute("featRows", featRows);
	}
}

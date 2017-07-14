package au.com.phoenixhsl.api.matches.model;

import java.util.ArrayList;
import java.util.List;

import com.thirdpartyservices.api.matches.model.ThirdPartyMatch;

public class ImportMessage {

	private int numberOfSavedMatches;
	private int numberOfUnsavedMatches;

	private List<ThirdPartyMatch> savedMatches;
	private List<ThirdPartyMatch> unsavedMatches;
	
	public ImportMessage() {
		savedMatches = new ArrayList<>();
		unsavedMatches = new ArrayList<>();
	}

	public void addSavedMatch(ThirdPartyMatch match) {
		savedMatches.add(match);
	}
	
	public void addUnsavedMatch(ThirdPartyMatch match) {
		unsavedMatches.add(match);
	}
	
	public int getNumberOfSavedMatches() {

		//necessary for the json serializer order
		numberOfSavedMatches = savedMatches.size();
		return numberOfSavedMatches;
	}
	
	public int getNumberOfUnsavedMatches() {
		
		//necessary for the json serializer order
		numberOfUnsavedMatches = unsavedMatches.size();
		return numberOfUnsavedMatches;
	}
	
	public List<ThirdPartyMatch> getSavedMatches() {
		return savedMatches;
	}
	
	public List<ThirdPartyMatch> getUnsavedMatches() {
		return unsavedMatches;
	}
}
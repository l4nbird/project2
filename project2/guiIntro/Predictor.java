//Landon Bird
//OOP Project 1
package guiIntro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Predictor {

	private ArrayList<Instance> instances;
	private String fn;

	public Predictor(String file) {
		instances = new ArrayList<Instance>();
		fn = file;
		readFile();
	}
	
	//private method to read data from passed filename and path, this is called in constructor
	private void readFile() {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fn);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				String[] pieces = line.split(",");
				Instance temp = new Instance(pieces[0], Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]), pieces[3], pieces[4]);
				instances.add(temp);
			} 
		}catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");				
		}
	}

	//public wrapper for writeFile
	public void saveFile() {
		writeFile(this.fn);
	}

	//prints instances objects to string into the file is initially read from
	private void writeFile(String fn) {
		FileWriter fw;
		try {
			fw = new FileWriter(fn);
			BufferedWriter myOutFile = new BufferedWriter(fw);
			myOutFile.write(this.simpleString());
			myOutFile.flush();
			myOutFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("***There was an error writting the file***!");
			e.printStackTrace();
		}		
	}
	
	// return a list of all possible activities
	public String[] getActivities (){
		ArrayList<String> toReturn = new ArrayList<String>();
		for (Instance instance : instances) {
			if (!toReturn.contains(instance.getActivity())) { 
				toReturn.add(instance.getActivity());
			}
		}
		String[] toReturnArray = new String[toReturn.size()];
		int index = 0;
		for (String string : toReturn) {
			toReturnArray[index] = string;
			index++;
		}
		return toReturnArray;
	}
	
	//  queries instances in arrayList and returns what it thinks would be a good activity for today given an instance of weather conditions (using score method from Instance class)
	public String using(Instance newInstance) {
		Instance match = null;
		int highestScore = 0;
		String activityToReturn = "";
		
		for (Instance instance : instances) {
			if (instance.score(newInstance) >= highestScore) { // if an instance was closer to the weather than any previous one
				highestScore = instance.score(newInstance); // change highest score to this value
				match = instance; // set match variable to instance object that was closest
			}
		}
		if (highestScore == 0) { // if nothing similar could be found, default to take a nap
			activityToReturn = "take a nap";
		}
		else {
			activityToReturn = match.getActivity(); // grab activity of closest matching instance
		}
		
		return activityToReturn;
	}
	//  queries instances in arrayList and returns what it thinks would be a good activity for today given an instance of weather conditions (using score method from Instance class)
	public Instance getpredictionInstance(Instance newInstance) {
		Instance match = null;
		int highestScore = 0;
		
		for (Instance instance : instances) {
			if (instance.score(newInstance) >= highestScore) { // if an instance was closer to the weather than any previous one
				highestScore = instance.score(newInstance); // change highest score to this value
				match = instance; // set match variable to instance object that was closest
			}
		}
		if (highestScore == 0) { // if nothing similar could be found, default to take a nap
			return null;
		}
		else {
			return match; // grab activity of closest matching instance
		}
		
	}	
	// wrapper function for adding new Instance to arrayList
	public void add(Instance newInstance) {
		instances.add(newInstance);
	}
	//returns the size of the class
	public int getSize() {
		return instances.size();
	}
	//removes the instance corresponding to a sent in index
	public void removeInst(int index) {
		instances.remove(index);
	}
	
	// is passed a new Instance - if the exact weather conditions are already in the arrayList, update the activity
	// if not, add the new weather Instance to the arrayList
	public void updating(Instance newInstance) {
		boolean update = false;
		for (Instance instance : instances) {
			if (instance.equals(newInstance)) { // if it finds a weather match, change the activity
					update = true; // will be used to check if the we need to add a new record later in this method
					instance.setActivity(newInstance.getActivity());
			}
		}
		if (!update) {
			add(newInstance); // if there isn't a weather match in the data, add a new instance to the ArrayList
		}
	}
	
	//toString - just uses the Instance class toString to print all weather/activity instances
	//also numbers each instance
	public String toString() {
		String toReturn = "";
		int count = 0;
		for (Instance instance : instances) {
			count += 1;
			toReturn += count + ". " + instance + "\n";
		}
		return toReturn;
	}
	public String simpleString() {
		String toReturn = "";
		for (Instance instance : instances) {
			toReturn += instance + "\n";
		}
		return toReturn;
	}
	
}


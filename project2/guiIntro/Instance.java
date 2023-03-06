//Landon Bird
//OOP Project 1
package guiIntro;

public class Instance {
	private int temperature;
	private int humidity;
	private String windy;
	private String activity;
	private String conditions;
	
	// default constructor if passed no information
	public Instance() {
		setTemperature(0);
		setHumidity(0);
		setWindy("FALSE");
		setActivity("none");
		setConditions("none");
	}
	
	// constructor if all data and an activity are passed (this will be used likely to update data)
	public Instance(String c, int t, int h, String w, String a) {
		setConditions(c);
		setTemperature(t);
		setHumidity(h);
		setWindy(w);
		setActivity(a);
	}
	
	// constructor with just weather information and no activity, this will be used with the using method
	public Instance(String c, int t, int h, String w) {
		setConditions(c);
		setTemperature(t);
		setHumidity(h);
		setWindy(w);
		setActivity("none");
	}
	
	// used to determine which would be the closest match to a given instance (used in using method of Predictor class)
	public int score(Instance newInstance) {
		int returnScore = 0;
		if (this.equals(newInstance)) { //if the weather is equal set score to 4 (max)
			returnScore = 4;
			return returnScore; // return a max score of 4
		}
		if (this.windy.equals(newInstance.getWindy())) { // if windy is the same add 1 to the return score
			returnScore += 1;
		}
		if (Math.abs(this.temperature - newInstance.getTemperature()) <= 4){ // if the difference in temperature is within 4 degrees, add 1 to return score
			returnScore += 1;
		}
		if (Math.abs(this.humidity - newInstance.getHumidity()) <= 5) { // if the difference in humidity is within 5 degrees, add 1 to return score
			returnScore += 1;
		}
		if (this.conditions.equals(newInstance.getConditions())) { // if the conditions are the same, add 1 to return score
			returnScore += 1;
		}
		
		return returnScore;
	}

		//self explanatory getters and setters for this class below
	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public String getWindy() {
		return windy;
	}

	public void setWindy(String windy) {
		this.windy = windy;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	
	//very important equals method
	public boolean equals(Instance rhs) {
		if (temperature == rhs.getTemperature() && humidity == rhs.getHumidity() && windy.equals(rhs.getWindy()) && conditions.equals(rhs.getConditions())) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return (conditions + "," + temperature + "," + humidity + "," + windy + "," + activity);
	}
}

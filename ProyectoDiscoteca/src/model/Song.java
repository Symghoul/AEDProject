package model;

public class Song {

	private String name;
	private String gener;
	private String duration;
	private int positionInList;
	
	public Song(String n, String g, String d) {
		
		name = n;
		gener = g;
		duration = d;
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGener() {
		return gener;
	}
	
	public void setGener(String gener) {
		this.gener = gener;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public int getPositionInList() {
		return positionInList;
	}

	public void setPositionInList(int positionInList) {
		this.positionInList = positionInList;
	}

	public int convertD(String duration) {
		
		String[] time = duration.split(":");
		int timeS = Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
		return timeS;
	}
}

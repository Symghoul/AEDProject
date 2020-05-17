package model;

public class Song implements Comparable<String>{

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
	
	@Override
	public int compareTo(String o) {

		char[] char1 = name.toCharArray();
		char[] char2= o.toCharArray();
		int aux = 0;
		boolean still = false;
		int value;
		if(char1.length<char2.length)
			value = char1.length;
		else {
			value = char2.length;
		}
		for(int i=0; i<value &&!still; i++ ) {
			
			if(char1[i]<char2[i]) {
				aux = -1;
				still = true;
			}else if(char1[i]<char2[i]) {
				aux = 1;
				still = true;
			}
		}
		return aux;
	}

}

package model;

import java.util.ArrayList;

public class Gener implements Comparable<String> {

	private String type;
	private int positionInList;
	private ArrayList<Song> songs;
	
	public Gener(String t) {
		
		type= t;
		songs = new ArrayList<Song>();
	}
	
	public String getType() {
		return type;
	}
	
	public int getPositionInList() {
		return positionInList;
	}
	
	public void setPositionInList(int positionInList) {
		this.positionInList = positionInList;
	}
	
	public void addSong(Song s) {
		
		int pos = searchPositionToOrder(s);
		s.setPositionInList(pos);
		songs.add(pos+1,s);
	}
	
	public int searchPositionToOrder(Song s) {
		
		int pos = 0;
		
		for(int i = 0; i < songs.size(); i++) {
			if(songs.get(i).compareTo(s.getName())>0) {
				pos = songs.get(i).getPositionInList();
			}
		}
		
		return pos;
	}

	@Override
	public int compareTo(String o) {

		char[] char1 = type.toCharArray();
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

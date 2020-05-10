package model;

import java.util.ArrayList;

public class Gener {

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
			if(songs.get(i).getName().compareToIgnoreCase(s.getName())>0) {
				pos = songs.get(i).getPositionInList();
			}
		}
		
		return pos;
	}

}

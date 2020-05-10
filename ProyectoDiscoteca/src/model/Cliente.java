package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Cliente {

	private String name;
	private String cedula;
	private ArrayList<Gener> geners;
	private HashMap<Double, Song> likedSongs;
	
	public Cliente(String n, String c) {
		
		name = n;
		cedula = c;
		geners = new ArrayList<Gener>();
		likedSongs = new HashMap<Double, Song>();
	}
	
	
	public Cliente(String n, String c, ArrayList<Gener> g, HashMap<Double, Song> lS) {
		
		name = n;
		cedula = c;
		geners = g;
		likedSongs = lS;
	}


	public String getName() {
		return name;
	}


	public String getCedula() {
		return cedula;
	}


	public ArrayList<Gener> getGeners() {
		return geners;
	}
	
	public void addGener(Gener g) {
		
		int pos = searchPositionToOrder(g);
		g.setPositionInList(pos);
		geners.add(pos+1,g);
	}
	
	public int searchPositionToOrder(Gener s) {
		
		int pos = 0;
		
		for(int i = 0; i < geners.size(); i++) {
			if(geners.get(i).getType().compareToIgnoreCase(s.getType())>0) {
				pos = geners.get(i).getPositionInList();
			}
		}
		
		return pos;
	}


	public HashMap<Double, Song> getLikedSongs() {
		return likedSongs;
	}
	
	public void addLikedSong(Double l, Song s) {
		
		likedSongs.put(l, s);
	}
}

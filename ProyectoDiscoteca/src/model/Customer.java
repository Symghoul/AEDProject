package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer implements Comparable<String>{

	private String name;
	private String cedula;
	private ArrayList<Gener> geners;
	private HashMap<Double, Song> likedSongs;
	
	public Customer(String n, String c) {
		
		name = n;
		cedula = c;
		geners = new ArrayList<Gener>();
		likedSongs = new HashMap<Double, Song>();
	}
	
	
	public Customer(String n, String c, ArrayList<Gener> g, HashMap<Double, Song> lS) {
		
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


	@Override
	public int compareTo(String o) {

		char[] char1 = cedula.toCharArray();		
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

package model;

import java.util.ArrayList;

import graph.EdgeL;
import graph.EdgeM;
import graph.GraphL;
import interfaces.IGraph;

public class SonLatino {

	private String name;
	private ArrayList<Gener> genersCatalog;
	private GraphL<Customer, EdgeL<Gener, Double>> genersliked;
	private IGraph<Song, EdgeM<Song, Integer>> songs;
	
	public SonLatino(String n) {
		
		name = n;
	}
}

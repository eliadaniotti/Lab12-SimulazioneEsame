package it.polito.tdp.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		model.creaGrafo(2014);
		SimpleWeightedGraph<Distretto, DefaultWeightedEdge> grafo = model.getGrafo();
		
		List<Distretto> dd = new LinkedList<Distretto>(model.getDistretti());
		
		Distretto d2 = dd.get(1);
		System.out.println(d2.toString());
		
		System.out.println(LatLngTool.distance(d2.getCentro(),dd.get(6).getCentro(), LengthUnit.KILOMETER));
	}

}

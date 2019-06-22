package it.polito.tdp.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.lang.Object;
import java.time.LocalDate;

import com.javadocmd.simplelatlng.*;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.javadocmd.simplelatlng.LatLngTool.Bearing;

import it.polito.tdp.db.EventsDao;

public class Model {
	EventsDao dao = new EventsDao();
	List<Integer> anni;
	List<Distretto> distretti;
	Distretto centrale = null;
	SimpleWeightedGraph<Distretto, DefaultWeightedEdge> grafo;
	
	public Model() {
		 anni = new LinkedList<Integer>(dao.listAllAnni());
			}
	
	public void creaGrafo(int anno) {
		distretti = new LinkedList<Distretto>(dao.listAllDistretti(anno));
		grafo = new SimpleWeightedGraph<Distretto, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		Graphs.addAllVertices(grafo, distretti);
		for(Distretto v : grafo.vertexSet())
			for(Distretto v2 : grafo.vertexSet())
				if(!v.equals(v2)) {
					DefaultWeightedEdge e = new DefaultWeightedEdge();
					grafo.addEdge(v, v2, e);
					grafo.setEdgeWeight(v, v2, LatLngTool.distance(v.getCentro(),v2.getCentro(), LengthUnit.KILOMETER));
					
				}
	}
	
	public List<Event> getCrimini(LocalDate data){
		return dao.listAllDatesEvents(data);
	}
	
	public List<Distretto> getDistretti(){
		return distretti;
	}
	
	public List<Integer> getAnni(){
		return anni;
	}
	
	public SimpleWeightedGraph<Distretto, DefaultWeightedEdge> getGrafo(){
		return grafo;
	}
	
	public List<Distretto> getAdiacenti(Distretto d) {
		List<Distretto> adiacenti = new LinkedList<Distretto>();
		
		while(adiacenti.size() != 6) {
			Distretto best = null;
			for(Distretto a : distretti) {
				if(best==null && !adiacenti.contains(a) && !a.equals(d))
					best=a;
				else if(!a.equals(d) && !adiacenti.contains(a) && LatLngTool.distance(d.getCentro(),a.getCentro(), LengthUnit.KILOMETER) < LatLngTool.distance(d.getCentro(),best.getCentro(), LengthUnit.KILOMETER))
					best=a;
			}
			adiacenti.add(best);
		}
		
		return adiacenti;
	}

	
	public Distretto getDistrettoTranquillo() {
		for(Distretto d : distretti)
			if(d.getCrimini() < centrale.getCrimini() || centrale==null)
				centrale = d;
		return centrale;
	}
	
	public int simula(LocalDate data, int n) {
		Simulatore sim = new Simulatore();
		sim.init(data, n);
		sim.run();
	}
	
}

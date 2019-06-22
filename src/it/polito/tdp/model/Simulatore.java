package it.polito.tdp.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Simulatore {
	
	//modello
	Model model = new Model();
	List<Event> crimini;
	SimpleWeightedGraph<Distretto, DefaultWeightedEdge> grafo;
	List<Agente> agenti;
	
	//parametri
	LocalDate data;
	int numAgenti;
	
	//output
	int malgestiti;
	
	PriorityQueue<Event> coda;
	private Object LatLng;
	
	public void init(LocalDate data, int n) {
		this.data=data;
		numAgenti=n;
		malgestiti=0;
		agenti = new LinkedList<Agente>();
		crimini = new LinkedList<Event>(model.getCrimini(data));
		coda = new PriorityQueue<Event>(crimini);
		grafo = new SimpleWeightedGraph<Distretto, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		grafo = model.getGrafo();
		
		for(int i=1; i<=n; i++)
			agenti.add(new Agente(i,model.getDistrettoTranquillo().getCentro()));
	}
	
	public int run() {
        Event e;
        Agente vicino = null;
        
        while((e = coda.poll()) != null) {
        	switch(e.getTIPO()) {
        	case CRIMINE:
        		LatLng posCrimine = new LatLng(e.getGeo_lat(), e.getGeo_lon());
        		for(Agente a : agenti) {
        			if(LatLngTool.distance(a.getPosizione(), posCrimine, LengthUnit.KILOMETER) < LatLngTool.distance(vicino.getPosizione(), posCrimine, LengthUnit.KILOMETER) || vicino==null)
        				vicino=a;
        		}
        		
        		vicino.setLibero(false);
        		vicino.setPosizione(posCrimine);
        		
        		if(LatLngTool.distance(vicino.getPosizione(), posCrimine, LengthUnit.KILOMETER) > 15)
        			malgestiti++;
        		
        }

	}
}

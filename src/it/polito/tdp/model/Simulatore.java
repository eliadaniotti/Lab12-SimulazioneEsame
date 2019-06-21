package it.polito.tdp.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Simulatore {
	
	//modello
	Model model = new Model();
	List<Event> crimini;
	
	//parametri
	LocalDate data;
	int anno;
	int mese;
	int giorno;
	int numAgenti;
	
	public void init() {
		data = LocalDate.of(anno, mese, dayOfMonth);
		 crimini = new LinkedList<Event>(model.getCrimini(data));
			
	}
}

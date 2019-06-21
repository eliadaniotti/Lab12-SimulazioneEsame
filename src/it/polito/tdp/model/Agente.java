package it.polito.tdp.model;

import com.javadocmd.simplelatlng.LatLng;

public class Agente {
	int id;
	LatLng posizione;
	boolean libero;
	public Agente(int id, LatLng posizione) {
		super();
		this.id = id;
		this.posizione = posizione;
		this.libero = true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LatLng getPosizione() {
		return posizione;
	}
	public void setPosizione(LatLng posizione) {
		this.posizione = posizione;
	}
	public boolean isLibero() {
		return libero;
	}
	public void setLibero(boolean libero) {
		this.libero = libero;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agente other = (Agente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}

package it.polito.tdp.model;

import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class Distretto {
	int id;
	LatLng centro;
	int crimini;
	
	public Distretto(int id, LatLng centro, int crimini) {
		super();
		this.id = id;
		this.centro = centro;
		this.crimini=crimini;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LatLng getCentro() {
		return centro;
	}
	public void setCentro(LatLng centro) {
		this.centro = centro;
	}
	
	public int getCrimini() {
		return crimini;
	}
	public void setCrimini(int crimini) {
		this.crimini = crimini;
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
		Distretto other = (Distretto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "id=" + id;
	}
	
}

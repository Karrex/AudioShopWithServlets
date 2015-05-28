package org.oa.tp.data;

import com.google.gson.annotations.SerializedName;

public class Album {

	@SerializedName("id")
	private final long id;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("year")
	private int year;
	
	public Album(long id, String name, int year) {
		
		this.id = id;
		this.name = name;
		this.year = year;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Album other = (Album) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", year=" + year + "]";
	}
	
	
	
	
}

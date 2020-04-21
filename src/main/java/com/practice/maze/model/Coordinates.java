package com.practice.maze.model;

public class Coordinates {

	private int xCoordinate;
	private int yCoordinate;
	
	public Coordinates(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	public int getXCoordinate() {
		return xCoordinate;
	}
	public int getYCoordinate() {
		return yCoordinate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoordinate;
		result = prime * result + yCoordinate;
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
		Coordinates other = (Coordinates) obj;
		if (xCoordinate != other.xCoordinate)
			return false;
		if (yCoordinate != other.yCoordinate)
			return false;
		return true;
	}
	
	public String toString() {
		return "["+xCoordinate+","+yCoordinate+"]";
	}

	
}

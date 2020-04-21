package com.practice.maze;

import com.practice.maze.enums.Direction;
import com.practice.maze.model.Coordinates;

public class TravelStatus {

	private Coordinates coordinates;
	private Direction direction;
	
	public TravelStatus(Coordinates coordinates, Direction direction) {
		this.coordinates = coordinates;
		this.direction = direction;
	}
	
	public TravelStatus(TravelStatus travelStatus) {
		this.coordinates = travelStatus.coordinates;
		this.direction = travelStatus.direction;
	} 
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void moveForward() {
		coordinates=getForwardCoordinates() ;
	}
	
	public Coordinates getForwardCoordinates() {
		
		switch(direction){
		case EAST : return new Coordinates(coordinates.getXCoordinate()+1, coordinates.getYCoordinate()); 
		case WEST : return new Coordinates(coordinates.getXCoordinate()-1, coordinates.getYCoordinate()); 
		case NORTH : return new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()+1); 
		case SOUTH : return new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()-1); 
		default: return null;
		}
	}
	
//    public void moveBackward() {
//		
//		switch(direction){
//		case EAST : coordinates=new Coordinates(coordinates.getXCoordinate()-1, coordinates.getYCoordinate()); 
//					direction= Direction.WEST;	break;
//		case WEST : coordinates=new Coordinates(coordinates.getXCoordinate()+1, coordinates.getYCoordinate()); 
//					direction= Direction.EAST;	break;
//		case NORTH : coordinates=new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()-1); 
//					direction= Direction.SOUTH;	break;
//		case SOUTH : coordinates=new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()+1); 
//					direction= Direction.NORTH;	
//		}
//	}
    
    public void turnLeft() {
    	
    	switch(direction){
		case EAST : coordinates=new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()+1); 
		            direction= Direction.NORTH;	break;
		case WEST : coordinates=new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()-1);
					direction= Direction.SOUTH;	break;
		case NORTH : coordinates=new Coordinates(coordinates.getXCoordinate()-1, coordinates.getYCoordinate());
					direction= Direction.WEST;	break;
		case SOUTH : coordinates=new Coordinates(coordinates.getXCoordinate()+1, coordinates.getYCoordinate());
					direction= Direction.EAST;	
		}
    }
    
   public Coordinates getLeftCoordinates() {
    	
    	switch(direction){
		case EAST : return new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()+1); 

		case WEST : return new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()-1);
					
		case NORTH : return  new Coordinates(coordinates.getXCoordinate()-1, coordinates.getYCoordinate());

		case SOUTH : return new Coordinates(coordinates.getXCoordinate()+1, coordinates.getYCoordinate());
		
		default: return null;
		}
    }
    
    public void turnRight() {
    	
    	switch(direction){
		case EAST : coordinates=new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()-1); 
					direction= Direction.SOUTH;	break;
		case WEST : coordinates=new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()+1); 
					direction= Direction.NORTH;	break;
		case NORTH : coordinates=new Coordinates(coordinates.getXCoordinate()+1, coordinates.getYCoordinate()); 
					direction= Direction.EAST;	break;
		case SOUTH : coordinates=new Coordinates(coordinates.getXCoordinate()-1, coordinates.getYCoordinate()); 
					direction= Direction.WEST;	
		}
    }
    
public Coordinates getRightCoordinates() {
    	
    	switch(direction){
		case EAST : return new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()-1); 

		case WEST : return new Coordinates(coordinates.getXCoordinate(), coordinates.getYCoordinate()+1);
					
		case NORTH : return  new Coordinates(coordinates.getXCoordinate()+1, coordinates.getYCoordinate());

		case SOUTH : return new Coordinates(coordinates.getXCoordinate()-1, coordinates.getYCoordinate());
		
		default: return null;
		}
    }

}

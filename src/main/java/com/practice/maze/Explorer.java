package com.practice.maze;

import java.util.ArrayList;
import java.util.List;

import com.practice.maze.enums.Direction;
import com.practice.maze.enums.MazeCellType;
import com.practice.maze.model.Coordinates;

public class Explorer {
	
	private Maze maze;
	private TravelStatus travelStatus;
	private List<Coordinates> travelList;

	public Explorer(Maze maze)
	{
		this();
		this.maze=maze;
		start();
	}
	
	public Explorer() {
		travelList=new ArrayList<>();
	}
	
	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
		start();
	}

	
	public void setTravelStatus(TravelStatus travelStatus) throws IllegalArgumentException {
		
		if(maze.getCellTypeAt(travelStatus.getCoordinates())==MazeCellType.WALL)
				throw new IllegalArgumentException();
	
		this.travelStatus=new TravelStatus(travelStatus);
	}
	
	public TravelStatus getTravelStatus() {
		return travelStatus;
	}

//	private void setTravelStatus(TravelStatus travelStatus) {
//		this.travelStatus = travelStatus;
//	}
	
	public MazeCellType cellTypeInFront() {
		Coordinates forwardCoordinates = travelStatus.getForwardCoordinates();
		return maze.getCellTypeAt(forwardCoordinates);
	}
	
	protected void start() {
		Coordinates startCoordinates = maze.getStartPoint();
		travelStatus=new TravelStatus(startCoordinates,Direction.NORTH);
		
		//reset travel list
		travelList.clear();
		travelList.add(travelStatus.getCoordinates());
	}
	
	
	
	public boolean moveForward() {
		if(canMoveForward()) {
		travelStatus.moveForward();
		travelList.add(travelStatus.getCoordinates());
		return true;
		}
		else return false;
	}

	public boolean moveLeft() {
		if(canMoveLeft()) {
		travelStatus.turnLeft();
		travelList.add(travelStatus.getCoordinates());
		return true;
		}
		else return false;
	}
	public boolean moveRight() {
		if(canMoveRight()) {
		travelStatus.turnRight();
		travelList.add(travelStatus.getCoordinates());
		return true;
		}
		else return false;
	}
	
	public boolean canMoveForward() {
		Coordinates forwardCoordinates = travelStatus.getForwardCoordinates();
		return maze.getCellTypeAt(forwardCoordinates)==MazeCellType.SPACE || 
				maze.getCellTypeAt(forwardCoordinates)==MazeCellType.EXIT;
	}
	
	public boolean canMoveLeft() {
		Coordinates forwardCoordinates = travelStatus.getLeftCoordinates();
		return maze.getCellTypeAt(forwardCoordinates)==MazeCellType.SPACE || 
				maze.getCellTypeAt(forwardCoordinates)==MazeCellType.EXIT;
	}
	
	public boolean canMoveRight() {
		Coordinates forwardCoordinates = travelStatus.getRightCoordinates();
		return maze.getCellTypeAt(forwardCoordinates)==MazeCellType.SPACE || 
				maze.getCellTypeAt(forwardCoordinates)==MazeCellType.EXIT;
	}

	public List<Coordinates> getTravelList() {
		return travelList;
	}

	public boolean hasCompletedMaze() {
		return maze.getCellTypeAt(travelStatus.getCoordinates())==MazeCellType.EXIT;
	}
}

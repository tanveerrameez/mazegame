package com.practice.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.practice.maze.enums.MazeCellType;
import com.practice.maze.model.Coordinates;

public class Maze {
	private Coordinates startCoordinates;
	private Coordinates endCoordinates;
	private int totalNumberOfWalls;
	private int totalNumberOfSpaces;
	private char [][] maze ; 


	public Maze(Reader mazeSource) {
		createMaze(mazeSource);
	}
	
	/**
     * Reads the Reader source and extracts each line from the Reader source into Line object 
     * separating the columns into variables of Line class
     * @param source The source to read the lines from 
     * @return List of Line objects
     */
    private void createMaze(Reader mazeSource){
    	
    	List<String> lines=new ArrayList<>();
    	
    	try(BufferedReader br = new BufferedReader(mazeSource);){
		    String fileLine = null;
	       
		    //load each lines into an element of List
			while ((fileLine = br.readLine()) != null) {
				lines.add(fileLine);
			}
			
			//Assuming each fileLine is of same length, take length of first fileLine
			maze=new char[lines.get(0).length()][lines.size()];
			
			//start populating the maze from the list
			for(int y=lines.size()-1; y>=0;y--) {
				
				String line=lines.get(lines.size()-1-y);
				for(int x=0;x<line.length();x++) {
						maze[y][x]=line.charAt(x);
						if(maze[y][x]=='S') {
							startCoordinates=new Coordinates(x, y);
						}else if(maze[y][x]=='F') {
								endCoordinates=new Coordinates(x, y);	
						}
						else if(maze[y][x]=='X') {
							totalNumberOfWalls++;
						}
						else if(maze[y][x]==' ') {
							totalNumberOfSpaces++;
						}
				}
			}
			
				
			
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	printMaze();
    }
	
    public void printMaze() {
    	for(int i=maze.length-1;i>=0;i--) {
    		for(int j=0; j<maze[0].length;j++) {
    			System.out.print(maze[i][j]);
    		}
    		System.out.println();
    	}
    }
    
	public MazeCellType getCellTypeAt(Coordinates coordinate) {
		
		if(coordinate.getYCoordinate()<0 || coordinate.getYCoordinate()<0
			||	coordinate.getYCoordinate()>=maze.length
				|| coordinate.getXCoordinate()>=maze[0].length)
			throw new IllegalArgumentException("Coordinates "+coordinate + " is outside the Maze");
		
		char cellValue =  maze[coordinate.getYCoordinate()][coordinate.getXCoordinate()];
		return getCellType( cellValue);
	}
	
	public MazeCellType getCellTypeAt(int x, int y) {
		char cellValue =  maze[y][x];
		return getCellType( cellValue);
	}
	
	public MazeCellType getCellType(char cellValue) {
		switch(cellValue) {
		case 'X': return MazeCellType.WALL;
		case ' ': return MazeCellType.SPACE;
		case 'S': return MazeCellType.ENTRY;
		case 'F': return MazeCellType.EXIT;
		}
		return null;
	}
	
	public Coordinates getStartPoint() {
		return startCoordinates;
	}
	
	public Coordinates getStartEnd() {
		return endCoordinates;
	}
	public int getMazeLength()
	{
		return maze.length;
	}
	public int getMazeWidth()
	{
		return maze[0].length;
	}
	public int getTotalNumberOfWalls() {
		return totalNumberOfWalls;
	}

	public int getTotalNumberOfSpaces() {
		return totalNumberOfSpaces;
	}
	
//	public Direction getStartDirection() {
//
//		if(getCellTypeAt(startCoordinates.getXCoordinate(),startCoordinates.getYCoordinate()+1)==MazeCellType.SPACE)
//			return Direction.NORTH;
//		else if(getCellTypeAt(startCoordinates.getXCoordinate(),startCoordinates.getYCoordinate()-1)==MazeCellType.SPACE)
//			return Direction.SOUTH;
//		if(getCellTypeAt(startCoordinates.getXCoordinate()+1,startCoordinates.getYCoordinate())==MazeCellType.SPACE)
//			return Direction.EAST;
//		else if(getCellTypeAt(startCoordinates.getXCoordinate()-1,startCoordinates.getYCoordinate())==MazeCellType.SPACE)
//			return Direction.WEST;
//		else return null;
//	}
}

package com.practice.maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.practice.maze.enums.Direction;
import com.practice.maze.enums.MazeCellType;
import com.practice.maze.model.Coordinates;

public class MazeGameTest {
	
	private Maze maze;
	private Explorer explorer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}


	
	@Before
    public void init() {
		
		try {
			maze=new Maze(openFile("src/main/resources/Maze1.txt"));
			explorer=new Explorer(maze);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

    }
	
	/**
	 * The explorer should be able to drop in to the Start point (facing north)
	 * The start point should match the 'S' in the maze input file.
	 */
	@Test
	public void testStartLocation() {
		
		explorer.setMaze(maze);
		TravelStatus travelStatus = explorer.getTravelStatus();
		assertEquals(3, travelStatus.getCoordinates().getXCoordinate());
		assertEquals(11, travelStatus.getCoordinates().getYCoordinate());
		assertEquals(Direction.NORTH, travelStatus.getDirection());
		
	}
	
	/**
	 * An explorer on a maze must be able to move forward
	 */
	@Test
	public void testMoveForward() {


		//explorer default to start point
		TravelStatus travelStatus = explorer.getTravelStatus();
		//The explorer is at the start point 'S'.
		//since given the travel status in the maze file can have direction EAST to move forward
		//at the S position, we change direction to 'EAST' to make move forward possible
		travelStatus.setDirection(Direction.EAST);
		explorer.setTravelStatus(travelStatus);
		
		boolean hasMovedforward = explorer.moveForward();
		assertTrue(hasMovedforward);
		TravelStatus forwardTravelStatus = explorer.getTravelStatus();
		//assert direction remains unchanged
		assertEquals(travelStatus.getDirection(), forwardTravelStatus.getDirection());
		assertEquals(travelStatus.getCoordinates().getXCoordinate()+1,forwardTravelStatus.getCoordinates().getXCoordinate());
		assertEquals(travelStatus.getCoordinates().getYCoordinate(),forwardTravelStatus.getCoordinates().getYCoordinate());
	}
	
	/**
	 * An explorer on a maze must be able to turn left  (changing direction the explorer is facing)
	 */
	@Test
	public void testTurnLeft() {
		
		//explorer default to start point, i.e. The explorer is at the start point 'S'.
		//Since given the travel status in the maze file Maze1.txt cannot turn LEFT
		//we place the explorer in another cell so that LEFT turn is possible from that cell
		//in real scenerio, explorer cannot be put in an arbitrary cell, but must move toward a cell  (if not a wall)
		//from start point
		TravelStatus travelStatus=new TravelStatus(new Coordinates(6,8), Direction.WEST);
		explorer.setTravelStatus(travelStatus);
		boolean hasMovedLeft = explorer.moveLeft();
		assertTrue(hasMovedLeft);
		TravelStatus leftTravelStatus = explorer.getTravelStatus();
		//assert direction remains unchanged
		assertEquals(Direction.SOUTH, leftTravelStatus.getDirection());
		assertEquals(travelStatus.getCoordinates().getXCoordinate(),leftTravelStatus.getCoordinates().getXCoordinate());
		assertEquals(travelStatus.getCoordinates().getYCoordinate()-1,leftTravelStatus.getCoordinates().getYCoordinate());
		
	}
	
	/**
	 * An explorer on a maze must be able to turn right (changing direction the explorer is facing)
	 */
	@Test
	public void testTurnRight() {
		
		//explorer default to start point, i.e. The explorer is at the start point 'S'.
		//Since given the travel status in the maze file Maze1.txt cannot turn LEFT
		//we place the explorer in another cell so that LEFT turn is possible from that cell
		//in real scenerio, explorer cannot be put in an arbitrary cell, but must move toward a cell  (if not a wall)
		//from start point
		TravelStatus travelStatus=new TravelStatus(new Coordinates(11,2), Direction.EAST);
		explorer.setTravelStatus(travelStatus);
		boolean hasMovedRight = explorer.moveRight();
		assertTrue(hasMovedRight);
		TravelStatus rightTravelStatus = explorer.getTravelStatus();
		//assert direction remains unchanged
		assertEquals(Direction.SOUTH, rightTravelStatus.getDirection());
		assertEquals(travelStatus.getCoordinates().getXCoordinate(),rightTravelStatus.getCoordinates().getXCoordinate());
		assertEquals(travelStatus.getCoordinates().getYCoordinate()-1,rightTravelStatus.getCoordinates().getYCoordinate());
		
	}
	
	/**
	 * An explorer on a maze must be able to declare what is in front of them
	 */
	
	@Test
	public void testGetForwardTravelStatus() {
		TravelStatus travelStatus=new TravelStatus(new Coordinates(8,8), Direction.EAST);
		explorer.setTravelStatus(travelStatus);
		MazeCellType cellType = explorer.cellTypeInFront();
		assertEquals(MazeCellType.SPACE, cellType);
		
		
	}
	
	/**
	 * An explorer on a maze must be able to declare all movement options from their given location
	 */
	@Test
	public void testMovementOptions() {
		
		TravelStatus travelStatus=new TravelStatus(new Coordinates(8,8), Direction.EAST);
		explorer.setTravelStatus(travelStatus);
		MazeCellType cellType = explorer.cellTypeInFront();
		assertEquals(MazeCellType.SPACE, cellType);
		assertEquals(true,explorer.canMoveForward());
		assertEquals(false,explorer.canMoveLeft());
		assertEquals(false,explorer.canMoveRight());
	}
	
	/**
	 *  An explorer on a maze must be able to report a record of where they have been in an understandable fashion
	 */
	
	@Test
	public void testRecordMovement() {
		List<Coordinates> travelList = explorer.getTravelList();
		assertEquals(1, travelList.size());
		TravelStatus travelStatus = new TravelStatus(new Coordinates(3,5), Direction.EAST);
		explorer.setTravelStatus(travelStatus);
		boolean hasMovedForward=explorer.moveForward();
		assertEquals(true,hasMovedForward);
		hasMovedForward=explorer.moveForward();
		assertEquals(true,hasMovedForward);
		hasMovedForward=explorer.moveForward();
		assertEquals(true,hasMovedForward);
		boolean hasMovedLeft = explorer.moveLeft();
		assertEquals(true,hasMovedLeft);
		hasMovedForward = explorer.moveForward();
		assertEquals(true,hasMovedForward);
		travelList = explorer.getTravelList();
		assertEquals(6, travelList.size());
		Coordinates lastCoordinates = travelList.get(5);
		assertEquals(new Coordinates(6,7),lastCoordinates);
	}
	
	/**
	 * After a maze has been created I should be able to put in a co ordinate and know what exists at that point
	 */
	@Test
	public void testGetCellType() {

		assertEquals(MazeCellType.WALL,  maze.getCellTypeAt(new Coordinates(12,2)));
	}
	
	/**
	 * After a maze has been created the number of walls should be available to me
	 */
	@Test
	public void testNumberOfWalls() {

		assertEquals(149,maze.getTotalNumberOfWalls());
	}
	
	/**
	 * After a maze has been created the number of  empty spaces should be available to me
	 */
	@Test
	public void testNumberOfSpaces() {
		assertEquals(74,maze.getTotalNumberOfSpaces());
	}
	
	
	/**
	 * Test if travelling to cells outside the maze
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTravelOutsideMazeTopRight_NotAllowed() {
		TravelStatus travelStatus = new TravelStatus(new Coordinates(30,50), Direction.EAST);
		explorer.setTravelStatus(travelStatus);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTravelOutsideMazeBottomLeft_NotAllowed() {
		TravelStatus travelStatus = new TravelStatus(new Coordinates(-30,-50), Direction.EAST);
		explorer.setTravelStatus(travelStatus);
	}
	
	/**
	 * Test if travelling to wall
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTravelOnWall_NotAllowed() {
		TravelStatus travelStatus = new TravelStatus(new Coordinates(5,0), Direction.EAST);
		explorer.setTravelStatus(travelStatus);
	}
	
	
	
	/**
	 * 
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 */
   private FileReader openFile(String filename) throws FileNotFoundException {
    	
        File file=new File(filename);
        return new FileReader(file);
    }

}

package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Class that generates a 3d maze using growing tree algorithm 
 * @author Administrator
 *
 */
public class GrowingTreeGenerator extends Maze3dGeneratorBase {
	/**
	 * interface that can be implemented to define how to choose a position from a list of possible positions
	 */
	private PositionChooser chooser;
	
	/**
	 * ctor that uses RandomCellChooser as default
	 */
	public GrowingTreeGenerator() {
		this.chooser = new RandomCellChooser();
	}
	/**
	 * ctor that receives PositionChooser 
	 * @param chooser the chooser that will be used to choose a position from a list of possible positions
	 */
	public GrowingTreeGenerator(PositionChooser chooser) {
		this.chooser = chooser;
	}
	
	private Random rand = new Random();	
	
	/**
	 * function to choose a random position based on a 2d intersection by Z of a 3d maze 
	 * @param maze2d 2d maze intersection by Z of a 3d maze 
	 * @param z the value of a Z that the intersection was taken from
	 * @return the random Position
	 */
	private Position chooseRandomPosition(int[][] maze2d, int z) {
		
		int x, xLen, y, yLen;
		//we need to skip the walls outside the maze, here we skip the walls in the end of the array
		xLen = maze2d[0].length-1;
		yLen = maze2d.length-1;
		//we need to skip the walls outside the maze, here we skip the walls in the start of the array
		x = rand.nextInt(xLen)+1;
		y = rand.nextInt(yLen)+1;	
		//verify that we didn't receive an outside nor inside wall
		while (x == xLen || y == yLen || maze2d[x][y] == Maze3d.WALL || x %2 != 0 || y %2 != 0) {
			x = rand.nextInt(xLen)+1;
			y = rand.nextInt(yLen)+1;	
		}
		
		return new Position(x, y, z);
	}
	
	@Override
	public Maze3d generate(int x, int y, int z) {
		//if we got an odd number, add another level so that when we count zero-based we have an odd amount of levels
		if (z%2 != 0){
			z++;
		}
		Maze3d maze = new Maze3d(x, y, z);
		//get cross section for the lowest 2d maze for start position 
		int [][] maze2d0 = maze.getCrossSectionByZ(0);
		//get cross section for the highest 2d maze for end position
		int [][] maze2dn = maze.getCrossSectionByZ(z);
		//we choose random position before setting walls on all axis
		Position startPos = chooseRandomPosition(maze2d0, 0);
		Position goalPos = chooseRandomPosition(maze2dn, z+1);
		maze.setStartPosition(startPos);
		maze.setGoalPosition(goalPos);
		
				
		//Initialize all the maze with walls
		initialize(maze);	
		//set start and exit as free
		maze.setFree(startPos.x, startPos.y, startPos.z);
		maze.setFree(goalPos.x, goalPos.y, goalPos.z);	
		
		List<Position> cells = new ArrayList<Position>();

		cells.add(startPos);
		
		while (!cells.isEmpty()) {
			//choose cell from the list using the chooser
			Position pos = chooser.choose(cells);	
			
			// Find the unvisited neighbors of this cell
			List<Position> neighbors = findUnvisitedNeighbors(maze, pos);	
			
			if (!neighbors.isEmpty()) {
				// Choose a random neighbor
				int idx = rand.nextInt(neighbors.size());
				Position neighbor = neighbors.get(idx);
				
				// Carve a passage between current cell and the neighbor
				carvePassageBetweenCells(maze, pos, neighbor);
				cells.add(neighbor);
			} 
			else {
				cells.remove(pos);
			}	
		}		
			
		return maze;
	}
	/**
	 * finds unvisited positions
	 * @param maze the maze that we need to evaluate
	 * @param p currentPosition
	 * @return list of all unvisited positions
	 */
	private List<Position> findUnvisitedNeighbors(Maze3d maze, Position p) {
		List<Position> ret = new ArrayList<Position>();
		//verify that it's not out of the x end wall and not of all the z walls
		if (p.x+2<maze.getX()-1 && p.z > 0 && p.z < maze.getZ()-1) {
			Position pos = new Position(p.x+2, p.y, p.z);
			if (maze.isWall(pos))
				ret.add(pos);
		}
		//try back on x axis
		//verify that it's not out of the x start wall and not of all the z walls
		if (p.x-2>0 && p.z > 0 && p.z < maze.getZ()-1) {
			Position pos = new Position(p.x-2, p.y, p.z);
			if (maze.isWall(pos))
				ret.add(pos);
		}
		//try forward on y axis
		//verify that it's not out of the y end wall and not of all the z walls
		if (p.y+2<maze.getY()-1 && p.z > 0 && p.z < maze.getZ()-1) {
			Position pos = new Position(p.x, p.y+2, p.z);
			if (maze.isWall(pos))
				ret.add(pos);
		}
		//try back on y axis
		//verify that it's not out of the y end wall and not of all the z walls
		if (p.y-2>0 && p.z > 0 && p.z < maze.getZ()-1) {
			Position pos = new Position(p.x, p.y-2, p.z);
			if (maze.isWall(pos))
				ret.add(pos);
		}
		//try forward on z axis
		//verify that it's not out of the z end wall
		if (p.z+2<maze.getZ()) {
			Position pos = new Position(p.x, p.y, p.z+2);
			if (maze.isWall(pos))
				ret.add(pos);
		}
		//try back on z axis
		//verify that it's not out of the z start wall
		if (p.z-2>0) {
			Position pos = new Position(p.x, p.y, p.z-2);
			if (maze.isWall(pos))
				ret.add(pos);
		}
		
		return ret;
	}	
	/**
	 * carves passage in a maze between two adjacent positions
	 * @param maze the maze to carve a passage
	 * @param pos intial position
	 * @param neighbor neighbor position
	 */
	private void carvePassageBetweenCells(Maze3d maze, Position pos, Position neighbor) {
		if (neighbor.x == pos.x + 2) {
			maze.setFree(pos.x + 1, pos.y, pos.z);
			maze.setFree(pos.x + 2, pos.y, pos.z);
		}
		else if (neighbor.x == pos.x - 2) {
			maze.setFree(pos.x - 1, pos.y, pos.z);
			maze.setFree(pos.x - 2, pos.y, pos.z);
		}
		else if (neighbor.y == pos.y + 2) {
			maze.setFree(pos.x, pos.y + 1, pos.z);
			maze.setFree(pos.x, pos.y + 2, pos.z);
		}
		else if (neighbor.y == pos.y - 2) {
			maze.setFree(pos.x, pos.y - 1, pos.z);
			maze.setFree(pos.x, pos.y - 2, pos.z);
		}
		else if (neighbor.z == pos.z + 2) {
			maze.setFree(pos.x, pos.y, pos.z + 1);
			maze.setFree(pos.x, pos.y, pos.z + 2);
		}
		else if (neighbor.z == pos.z - 2) {
			maze.setFree(pos.x, pos.y, pos.z - 1);
			maze.setFree(pos.x, pos.y, pos.z - 2);
		}
	}
	/**
	 * set all walls in the maze
	 * @param maze the maze to initialize
	 */
	private void initialize(Maze3d maze) {
		for (int xPos = 0; xPos < maze.getX(); xPos++) {
			for (int yPos = 0; yPos < maze.getY() ; yPos++) {
				for (int zPos = 0; zPos < maze.getZ() ; zPos++) {
					maze.setWall(xPos, yPos, zPos);
				}
			}
		}
	}
	
}

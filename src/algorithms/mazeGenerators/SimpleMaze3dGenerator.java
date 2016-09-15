package algorithms.mazeGenerators;

import java.util.Random;
/**
 * Class that generates a 3d maze using random wall placement inside the maze
 * @author Administrator
 *
 */
public class SimpleMaze3dGenerator extends Maze3dGeneratorBase {

	private Random rand = new Random();
	
	private static final float WALL_RATIO = 0.5F;
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
		//verify that we didn't receive an outside wall
		while (x == xLen || y == yLen ||  maze2d[x][y] == Maze3d.WALL) {
			x = rand.nextInt(xLen)+1;
			y = rand.nextInt(yLen)+1;	
		}
		
		return new Position(x, y, z);
	}
	@Override
	/**
	 * generates a 3d maze
	 */
	public Maze3d generate(int x, int y, int z) {
		Maze3d maze = new Maze3d(x, y, z);
		//cross section for the entrance
		int [][] maze2d0 = maze.getCrossSectionByZ(0);
		//cross section for the exit
		int [][] maze2dn = maze.getCrossSectionByZ(z);
		//we choose random position before setting walls on all axis
		Position start = chooseRandomPosition(maze2d0, 0);
		Position end = chooseRandomPosition(maze2dn, z+1);
		maze.setStartPosition(start);
		maze.setGoalPosition(end);
		
		maze.generateWalls();
		
		maze.setFree(start.x, start.y, start.z);
		
		int allWalls = (int)(x*y*z*WALL_RATIO);
		for (int i = 0; i < allWalls && !done; i++) {
			int xPos = rand.nextInt(x)+1;
			int yPos = rand.nextInt(y)+1;
			int zPos = rand.nextInt(z)+1;
			
			maze.setWall(xPos, yPos, zPos);
		}
		
		return maze;
	}

}

package algorithms.mazeGenerators;
import java.util.*;
/**
 * class that defines a maze using a 3d int matrix 
 * @author Administrator
 *
 */
public class Maze3d {
	private int[][][] maze;
	private int x;
	private int y;
	private int z;
	private Position startPosition;
	private Position goalPosition;
	
	public static final int FREE = 0;
	public static final int WALL = 1;
	/**
	 * ctor
	 * @param x desired length of x axis  
	 * @param y desired length of y axis
	 * @param z desired length of z axis
	 */
	public Maze3d(int x, int y, int z) {
		//to allow walls from all sides
		this.x = x+2;
		this.y = y+2;
		this.z = z+2;
		maze = new int[this.x][this.y][this.z];
	}
	/**
	 * returns the maze
	 * @return the maze
	 */
	public int[][][] getMaze() {
		return maze;
	}
	
	/**
	 * returns the x of the maze
	 * @return the x of the maze
	 */
	public int getX() {
		return x;
	}
	/**
	 * returns the y of the maze
	 * @return the y of the maze
	 */
	public int getY() {
		return y;
	}
	/**
	 * returns the z of the maze
	 * @return the z of the maze
	 */
	public int getZ() {
		return z;
	}
	/**
	 * returns the start Position of the maze
	 * @return the start Position of the maze
	 */
	public Position getStartPosition() {
		return startPosition;
	}
	/**
	 * sets the start position
	 * @param startPosition the start Position of the maze
	 */
	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}
	/**
	 * returns the Goal Position of the maze
	 * @return the Goal Position of the maze
	 */
	public Position getGoalPosition() {
		return goalPosition;
	}
	/**
	 * sets the Goal position
	 * @param goalPosition the Goal Position of the maze
	 */
	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	/**
	 * set a wall in a maze in a given coordinate
	 * @param x coordinate of x for the wall
	 * @param y coordinate of y for the wall
	 * @param z coordinate of z for the wall
	 */
	public void setWall(int x, int y, int z) {
		maze[x][y][z] = WALL;
	}
	/**
	 * set a free space in a maze in a given coordinate
	 * @param x coordinate of x for the free space
	 * @param y coordinate of y for the free space
	 * @param z coordinate of z for the free space
	 */
	public void setFree(int x, int y, int z) {
		maze[x][y][z] = FREE;
	}
	/**
	 * returns a 2d int matrix based on a given x coordinate
	 * @param x coordinate of x for the 2d matrix
	 * @return the 2d matrix
	 */
	public int [][] getCrossSectionByX(int x) {
		int [][] ret = new int[y][z];
		for (int yPos = 0; yPos < y ; yPos++) {
			for (int zPos = 0; zPos < z ; zPos++) {
				ret[yPos][zPos] = this.maze[x][yPos][zPos];
			}
		}
		return ret;
	}
	
	/**
	 * returns a 2d int matrix based on a given y coordinate
	 * @param y coordinate of y for the 2d matrix
	 * @return the 2d matrix
	 */
	public int [][] getCrossSectionByY(int y) {
		int [][] ret = new int[x][z];
		for (int xPos = 0; xPos < x; xPos++) {
			for (int zPos = 0; zPos < y ; zPos++) {
				ret[xPos][zPos] = this.maze[xPos][y][zPos];
			}
		}
		return ret;
	}
	
	/**
	 * returns a 2d int matrix based on a given z coordinate
	 * @param z coordinate of z for the 2d matrix
	 * @return the 2d matrix
	 */
	public int [][] getCrossSectionByZ(int z) {
		int [][] ret = new int[x][y];
		for (int xPos = 0; xPos < x; xPos++) {
			for (int yPos = 0; yPos < y ; yPos++) {
				ret[xPos][yPos] = this.maze[xPos][yPos][z];
			}
		}
		return ret;
	}
	/**
	 * returns all possible moves for a position
	 * @param p position to check for 
	 * @return all possible moves for a position
	 */
	public Position [] getPossibleMoves(Position p) {
		ArrayList<Position> ret = new ArrayList<Position> ();
		//try forward on x axis
		if (p.x+1<this.x) {
			Position pos = new Position(p.x+1, p.y, p.z);
			if (isFree(pos))
				ret.add(pos);
		}
		//try back on x axis
		if (p.x-1>0) {
			Position pos = new Position(p.x-1, p.y, p.z);
			if (isFree(pos))
				ret.add(pos);
		}
		//try forward on y axis
		if (p.y+1<this.y) {
			Position pos = new Position(p.x, p.y+1, p.z);
			if (isFree(pos))
				ret.add(pos);
		}
		//try back on y axis
		if (p.y-1>0) {
			Position pos = new Position(p.x, p.y-1, p.z);
			if (isFree(pos))
				ret.add(pos);
		}
		//try forward on z axis
		if (p.z+1<this.z) {
			Position pos = new Position(p.x, p.y, p.z+1);
			if (isFree(pos))
				ret.add(pos);
		}
		//try back on z axis
		if (p.z-1>0) {
			Position pos = new Position(p.x, p.y, p.z-1);
			if (isFree(pos))
				ret.add(pos);
		}
		return (Position[]) ret.toArray(new Position[ret.size()]);  
	}
	/**
	 * returns true if the position is free
	 * @param p position to check for
	 * @return true if the position is free
	 */
	public boolean isFree(Position p) {
		return (this.maze[p.x][p.y][p.z] == FREE);
	}
	/**
	 * returns true if the position is wall
	 * @param p position to check for
	 * @return true if the position is wall
	 */
	public boolean isWall(Position p) {
		return (this.maze[p.x][p.y][p.z] == WALL);
	}
	/**
	 * generate walls for the x axis
	 */
	private void generateWallsX() {	
		for (int yPos = 0; yPos < y ; yPos++) {
			for (int zPos = 0; zPos < z ; zPos++) {
				setWall(0, yPos, zPos);
				setWall(x-1, yPos, zPos);
			}
		}
	}
	/**
	 * generate walls for the y axis
	 */
	private void generateWallsY() {	
		for (int xPos = 0; xPos < x; xPos++) {
			for (int zPos = 0; zPos < z ; zPos++) {
				setWall(xPos, 0, zPos);
				setWall(xPos, y-1, zPos);
			}
		}
	}
	/**
	 * generate walls for the z axis
	 */
	private void generateWallsZ() {	
		for (int xPos = 0; xPos < x; xPos++) {
			for (int yPos = 0; yPos < y ; yPos++) {
				setWall(xPos, yPos, 0);
				setWall(xPos, yPos, z-1);
			}
		}
	}
	/**
	 * generate all walls
	 */
	public void generateWalls() {
		generateWallsX();
		generateWallsY();
		generateWallsZ();
	}
	/**
	 * prints the maze
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int zPos = 0; zPos < z ; zPos++) {
			for (int yPos = 0; yPos < y ; yPos++) {
				for (int xPos = 0; xPos < x; xPos++) {
					if (startPosition.equals(xPos, yPos, zPos)) 
						sb.append("E");
					else if (goalPosition.equals(xPos, yPos, zPos))
						sb.append("X");
					else
						sb.append(maze[xPos][yPos][zPos]);
				}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * c'tor from byte array
	 * @param arr byte array
	 */
	public Maze3d(byte[] arr) {
		int k = 0;
		this.x = arr[k++];
		this.y = arr[k++];
		this.z = arr[k++];
		maze = new int[x][y][z];		
		
		Position startPos = new Position(arr[k++], arr[k++], arr[k++]);
		this.setStartPosition(startPos);
		Position goalPos = new Position(arr[k++], arr[k++], arr[k++]);
		this.setGoalPosition(goalPos);
		for (int xPos = 0; xPos < x; xPos++) {
			for (int yPos = 0; yPos < y ; yPos++) {
				for (int zPos = 0; zPos < z ; zPos++) {
					maze[xPos][yPos][zPos] = arr[k++];
				}
			}
		}
	}
	/**
	 * convert to byte array for storage
	 * @return byte array representation of the maze
	 */
	public byte[] toByteArray() {
		ArrayList<Byte> arr = new ArrayList<Byte>();
		arr.add((byte)x);
		arr.add((byte)y);
		arr.add((byte)z);
		arr.add((byte)startPosition.x);
		arr.add((byte)startPosition.y);
		arr.add((byte)startPosition.z);
		arr.add((byte)goalPosition.x);
		arr.add((byte)goalPosition.y);
		arr.add((byte)goalPosition.z);
		
		for (int xPos = 0; xPos < x; xPos++) {
			for (int yPos = 0; yPos < y ; yPos++) {
				for (int zPos = 0; zPos < z ; zPos++) {
					arr.add((byte)maze[xPos][yPos][zPos]);
				}
			}
		}
				
		byte[] bytes = new byte[arr.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)arr.get(i);
		}
		return bytes;
	}

}

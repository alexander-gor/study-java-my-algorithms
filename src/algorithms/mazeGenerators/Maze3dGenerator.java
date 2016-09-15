package algorithms.mazeGenerators;
/**
 * interface that defines an API for the Maze3dGenerator
 * @author Administrator
 *
 */
public interface Maze3dGenerator {
	/**
	 * generates a maze
	 * @param x length of x axis
	 * @param y length of y axis
	 * @param z length of z axis
	 * @return generated maze
	 */
	Maze3d generate (int x, int y, int z);
	/**
	 * generates a maze and measures how much time did it take
	 * @param x length of x axis
	 * @param y length of y axis
	 * @param z length of z axis
	 * @return amount of seconds it took to generate the maze
	 */
	String measureAlgorithmTime(int x, int y, int z);
	public void setDone(boolean done);
}

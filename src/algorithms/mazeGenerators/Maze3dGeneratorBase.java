package algorithms.mazeGenerators;
/**
 * abstract class that implements basic methods 
 * @author Administrator
 *
 */
public abstract class Maze3dGeneratorBase implements Maze3dGenerator {
	/**
	 * generates a maze
	 * @param x length of x axis
	 * @param y length of y axis
	 * @param z length of z axis
	 * @return generated maze
	 */
	public abstract Maze3d generate (int x, int y, int z);
	/**
	 * generates a maze and measures how much time did it take
	 * @param x length of x axis
	 * @param y length of y axis
	 * @param z length of z axis
	 * @return amount of seconds it took to generate the maze
	 */
	public String measureAlgorithmTime(int x, int y, int z) {
		Long startTime = System.currentTimeMillis();
		this.generate(x,y,z);
		Long endTime = System.currentTimeMillis();
		return String.valueOf(endTime - startTime);
	}
}

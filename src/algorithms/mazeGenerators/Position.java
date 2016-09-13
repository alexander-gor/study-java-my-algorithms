package algorithms.mazeGenerators;
/**
 * class that defines a position in a maze
 * @author Administrator
 *
 */
public class Position {
	/**
	 * location on the x axis
	 */
	public int x;
	/**
	 * location on the y axis
	 */
	public int y;
	/**
	 * location on the z axis
	 */
	public int z;
	/**
	 * ctor of the Position
	 * @param x location on the x axis
	 * @param y location on the y axis
	 * @param z location on the z axis
	 */
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * prints the Position
	 */
	@Override
	public String toString() {
		return "{" + x + "," + y + "," + z + "}";
	}
	/**
	 * checks for equality
	 */
	@Override
	public boolean equals(Object obj) {
		Position pos = (Position)obj;
		return (this.x == pos.x && this.y == pos.y && this.z == pos.z);
	}
	/**
	 * checks for equality based on position
	 * @param x location on the x axis
	 * @param y location on the y axis
	 * @param z location on the z axis
	 * @return true if the location are equal
	 */
	public boolean equals(int x, int y, int z) {
		return (this.x == x && this.y == y && this.z == z);
	}
	/**
	 * generates a hashcode
	 */
	@Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + x;
        hash = hash * 31 + y;
        hash = hash * 13 + z;
        return hash;
    }
}

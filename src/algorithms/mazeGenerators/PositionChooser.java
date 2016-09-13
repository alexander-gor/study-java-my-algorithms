package algorithms.mazeGenerators;

import java.util.List;
/**
 * Interface that defines the position chooser
 * @author Administrator
 *
 */
public interface PositionChooser {
	/**
	 * chooses a position from a list of positions
	 * @param cells list of positions
	 * @return a position from a list of positions
	 */
	public Position choose(List<Position> cells);
}

package algorithms.mazeGenerators;

import java.util.List;
/**
 * class that implements of a position chooser by choosing last position from the list
 * @author Administrator
 *
 */
public class LastCellChooser implements PositionChooser {
	/**
	 * chooses a position from a list of positions
	 * @param cells list of positions
	 * @return a position from a list of positions
	 */
	@Override
	public Position choose(List<Position> cells) {
		return cells.get(cells.size()-1);
	}

}

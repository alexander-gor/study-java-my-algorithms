package algorithms.mazeGenerators;

import java.util.List;
import java.util.Random;
/**
 * class that implements of a position chooser by choosing last position from the list
 * @author Administrator
 *
 */
public class RandomCellChooser implements PositionChooser {

	private Random rand = new Random();	
	/**
	 * chooses a position from a list of positions
	 * @param cells list of positions
	 * @return a position from a list of positions
	 */
	@Override
	public Position choose(List<Position> cells) {
		int idx = rand.nextInt(cells.size());
		return cells.get(idx);
	}

}

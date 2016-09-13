package algorithms.search;

import java.util.ArrayList;
import java.util.List;
/**
 * class that defines a solution to a searchable problem
 * @author Administrator
 *
 * @param <T> type of state, in 3dmaze it's a Position
 */
public class Solution<T> {
	// collection of state that make a solution
	private List<State<T>> states = new ArrayList<State<T>>();
	//getter of collection of state that make a solution
	public List<State<T>> getStates() {
		return states;
	}
	//setter of collection of state that make a solution
	public void setStates(List<State<T>> states) {
		this.states = states;
	}
	
	@Override
	/**
	 * prints the solution
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}

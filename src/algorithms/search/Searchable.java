package algorithms.search;

import java.util.List;
/**
 * Interface that represents a problem that we can search for a solution for
 * @author Administrator
 *
 * @param <T> type of state, in 3dmaze it's a Position
 */
public interface Searchable<T> {
	/**
	 * Returns the initial state
	 * @return the initial state
	 */
	State<T> getStartState();
	/**
	 * Returns the goal state
	 * @return the goal state
	 */
	State<T> getGoalState();
	/**
	 * Returns all the states we can go to from the current state
	 * @param s the current state
	 * @return list of possible states
	 */
	List<State<T>> getAllPossibleStates(State<T> s);
	/**
	 * Returns the move cost from the current state to a given neighbor
	 * @param currState current state
	 * @param neighbor neighbor state
	 * @return move cost
	 */
	double getMoveCost(State<T> currState, State<T> neighbor);
}

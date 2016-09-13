package algorithms.search;

import java.util.List;
/**
 * abstract class that implements some base methods of the Searcher
 * @author Administrator
 *
 * @param <T> type of state, in 3dmaze it's a Position
 */
public abstract class CommonSearcher<T> implements Searcher<T> {

	protected int evaluatedNodes;

	@Override
	/**
	 * returns number of nodes that were evaluated during the search
	 */
	public int getNumberOfNodesEvaluated() {		
		return evaluatedNodes;
	}
	/**
	 * builds a solution by backtracking from the goal state
	 * @param goalState state to start backtracking from
	 * @return solution to the searchable problem 
	 */
	protected Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		
		State<T> currState = goalState;
		//get all the states from the solution
		List<State<T>> states = sol.getStates();
		while (currState != null) {		
			//put each state to the start, thus pushing all others so that we get a solution from start to stop 
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		return sol;
	}

}

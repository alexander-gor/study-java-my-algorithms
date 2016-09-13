package algorithms.search;

import java.util.ArrayList;
import java.util.List;
/**
 * DFS search algorithm implementation
 * @author Administrator
 *
 * @param <T> type of state, in 3dmaze it's a Position
 */
public class DFS<T> extends CommonSearcher<T> {
	//list of states that were visited, so we won't evaluate them again
	private List<State<T>> visitedList = new ArrayList<State<T>>();
	@Override
	/**
	 * searches for a solution for the searchable problem
	 */
	public Solution<T> search(Searchable<T> s) {
		return dfs(s, s.getStartState());
	}
	/**
	 * dfs recursive algorithm implementation
	 * @param s searchable problem
	 * @param currState the state that we are at
	 * @return solution to the maze
	 */
	private Solution<T> dfs(Searchable<T> s, State<T> currState) {
		State<T> goal = s.getGoalState();
		//found exit, backtrace
		if (currState.equals(goal)) {
			return backTrace(currState);
		}
		else {
			List<State<T>> neighbors = s.getAllPossibleStates(currState);
			visitedList.add(currState);
			
			for (State<T> n : neighbors) {
				if (!visitedList.contains(n)){//we didn't evaluate this path, start evaluate now, because it's Depth First.
					visitedList.add(n);
					n.setCameFrom(currState);
					evaluatedNodes++;
					//start evaluating using recursion from the neighbor
					Solution<T> sol = dfs(s,n);
					if (sol!= null) //evaluation found a soltion, return it
						return sol;
				}
			}
		}
		return null;
	}

}

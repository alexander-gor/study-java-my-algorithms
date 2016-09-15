package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
/**
 * BFS search algorithm implementation
 * @author Administrator
 *
 * @param <T> type of state, in 3dmaze it's a Position
 */
public class BFS<T> extends CommonSearcher<T> {
	//to store the open nodes
	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	//to store the closed nodes
	private Set<State<T>> closedList = new HashSet<State<T>>();
	@Override
	/**
	 * searches for a solution for the searchable problem
	 */
	public Solution<T> search(Searchable<T> s) {
		State<T> startState = s.getStartState();
		openList.add(startState);
		
		while (!done && !openList.isEmpty()) {
			State<T> currState = openList.poll();
			evaluatedNodes++;
			closedList.add(currState);
			
			State<T> goalState = s.getGoalState();
			//found exit, backtrace
			if (currState.equals(goalState)) {
				return backTrace(currState);
			}
			
			List<State<T>> neighbors = s.getAllPossibleStates(currState);
			
			for (State<T> neighbor : neighbors) {
				
				if (!openList.contains(neighbor) && !closedList.contains(neighbor)) { //need to evaluate this node, add to openList 
					neighbor.setCameFrom(currState);
					neighbor.setCost(currState.getCost() + s.getMoveCost(currState, neighbor));
					openList.add(neighbor);
				}
				else {//node was evalated
					double newPathCost = currState.getCost() + s.getMoveCost(currState, neighbor);
					if (neighbor.getCost() > newPathCost) {//check if new cost is better
						neighbor.setCost(newPathCost);
						neighbor.setCameFrom(currState);
						
						if (!openList.contains(neighbor)) {//add to evaluation list
							openList.add(neighbor);
						} 
						else { // must notify the priority queue about the change of cost
							openList.remove(neighbor);
							openList.add(neighbor);
						}
					}
				}			
			}
		}
		return null;
	}

}

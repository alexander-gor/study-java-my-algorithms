package algorithms.search;
/**
 * interface that defines a searcher for a seachable problem
 * @author Administrator
 *
 * @param <T> type of state, in 3dmaze it's a Position
 */
public interface Searcher<T> {
    /**
     * the search method
     * @param s searchable problem
     * @return solution to the problem
     */
	public Solution<T> search(Searchable<T> s);
    
    // get how many nodes were evaluated by the algorithm
    /**
     * returns amount of nodes evaluated
     * @return amount of nodes evaluated
     */
	public int getNumberOfNodesEvaluated();
}

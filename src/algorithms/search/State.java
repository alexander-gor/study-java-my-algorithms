package algorithms.search;
/**
 * class that defines a state in searchable problem
 * @author Administrator
 *
 * @param <T> type of state, in 3dmaze it's a Position
 */
public class State<T> implements Comparable<State<T>> {
	//state that indicates where we came from
	private State<T> cameFrom;
	//cost that it takes to arrive to this state
	private double cost;
	//the state itself
	private T value;
	//getter for the state that indicates where we came from
	public State<T> getCameFrom() {
		return cameFrom;
	}
	//setter for the state that indicates where we came from
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	//getter for the cost that it takes to arrive to this state
	public double getCost() {
		return cost;
	}
	//setter for the cost that it takes to arrive to this state
	public void setCost(double cost) {
		this.cost = cost;
	}
	//getter for the state itself
	public T getValue() {
		return value;
	}
	//setter for the state itself
	public void setValue(T value) {
		this.value = value;
	}
	/**
	 * ctor
	 * @param value specific value of the state
	 */
	public State(T value) {		
		this.value = value;
	}
	
	@Override
	/**
	 * prints the state
	 */
	public String toString() {
		return value.toString();
	}
	
	@Override
	/**
	 * equality check
	 */
	public boolean equals(Object obj) {
		State<T> s = (State<T>)obj;
		return s.value.equals(this.value);
	}
	@Override
	/**
	 * hashcode generation
	 */
    public int hashCode() {
        return value.hashCode();
    }
	@Override
	/**
	 * compare the two states based on their cost, for bfs algorithm, so we choose best first
	 */
	public int compareTo(State<T> s) {
		return (int)(this.getCost() - s.getCost());	
		// return > 0 if this > s
		//        < 0 if this < s
		//        = 0 if this == s
	}
	
}

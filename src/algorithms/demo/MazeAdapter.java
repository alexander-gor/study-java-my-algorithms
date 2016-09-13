package algorithms.demo;

import java.util.ArrayList;
import java.util.List;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;
/**
 * adapter class for the demo
 * @author Administrator
 *
 */
public class MazeAdapter implements Searchable<Position> {
	//maze to solve
	private Maze3d maze;
	/**
	 * ctor
	 * @param maze maze to solve
	 */
	public MazeAdapter(Maze3d maze) {
		this.maze = maze;
	}	
	
	@Override
	/**
	 * getter for the start Position
	 */
	public State<Position> getStartState() {
		Position startPos = maze.getStartPosition();
		State<Position> startState = new State<Position>(startPos);
		return startState;	
	}

	@Override
	/**
	 * getter for the goal Position
	 */
	public State<Position> getGoalState() {
		Position goalPos = maze.getGoalPosition();
		State<Position> goalState = new State<Position>(goalPos);
		return goalState;	
	}

	@Override
	/**
	 * get all possible positions from a given state
	 */
	public List<State<Position>> getAllPossibleStates(State<Position> s) {
		Position currPos = s.getValue();
		
		Position[] moves = maze.getPossibleMoves(currPos);
		List<State<Position>> states = new ArrayList<State<Position>>();
		
		for (Position pos: moves) {
			states.add(new State<Position>(pos));
		}
		return states;		
	}

	@Override
	/**
	 * get the cost of a move between two neighbors 
	 */
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {		
		return 1; // in the maze all moves have the same cost
	}

}

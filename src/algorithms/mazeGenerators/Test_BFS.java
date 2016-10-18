/**
 * 
 */
package algorithms.mazeGenerators;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.demo.MazeAdapter;
import algorithms.search.BFS;
import algorithms.search.Solution;

/**
 * @author Administrator
 *
 */
public class Test_BFS {

	/**
	 * Test method for {@link algorithms.search.BFS#search(algorithms.search.Searchable)}.
	 */
	@Test
	public void testSearch() {
		Maze3dGenerator generator = new GrowingTreeGenerator();
		Maze3d maze = generator.generate(10, 10, 10);
		assertNotNull(maze);
		
		MazeAdapter adapter = new MazeAdapter(maze);
		BFS<Position> bfs = new BFS<Position>();
		Solution<Position> solution = bfs.search(adapter);
		assertNotNull(solution);
		assertTrue(bfs.getNumberOfNodesEvaluated() > 0);
		
	}
	
	/**
	 * Test method for {@link algorithms.search.BFS#search(algorithms.search.Searchable)}.
	 * tests that code doesn't throw exception if maze is null
	 */
	@Test
	public void testMazeNull() {

		Maze3d maze = null;
		assertNull(maze);
		
		MazeAdapter adapter = new MazeAdapter(maze);
		BFS<Position> bfs = new BFS<Position>();
		Solution<Position> solution = bfs.search(adapter);
		assertNull(solution);
		assertTrue(bfs.getNumberOfNodesEvaluated() == 0);
		
	}
	
	/**
	 * Test method for {@link algorithms.search.BFS#search(algorithms.search.Searchable)}.
	 * tests that code doesn't throw exception if StartPosition is null
	 */
	@Test
	public void testStartPositionIsNull() {

		Maze3dGenerator generator = new GrowingTreeGenerator();
		Maze3d maze = generator.generate(10, 10, 10);
		assertNotNull(maze);
		maze.setStartPosition(null);
		
		MazeAdapter adapter = new MazeAdapter(maze);
		BFS<Position> bfs = new BFS<Position>();
		Solution<Position> solution = bfs.search(adapter);
		assertNull(solution);
		assertTrue(bfs.getNumberOfNodesEvaluated() == 0);
		
	}


}

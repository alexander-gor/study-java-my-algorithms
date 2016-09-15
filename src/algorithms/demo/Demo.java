package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
/**
 * demo class
 * @author Administrator
 *
 */
public class Demo {

	/**
	 * run the demo
	 */
	public void run() {		
		Maze3dGenerator generator = new GrowingTreeGenerator();
		Maze3d maze = generator.generate(10, 10, 10);
		System.out.println(maze);
		
		MazeAdapter adapter = new MazeAdapter(maze);
		BFS<Position> bfs = new BFS<Position>();
		Solution<Position> solution = bfs.search(adapter);
		System.out.println(solution);
		System.out.println(bfs.getNumberOfNodesEvaluated());
		
		DFS<Position> dfs = new DFS<Position>();
		Solution<Position> solutionDfs = dfs.search(adapter);
		System.out.println(solutionDfs);
		System.out.println(dfs.getNumberOfNodesEvaluated());
		
		// save it to a file
		OutputStream out;
		try {
			out = new MyCompressorOutputStream(
					new FileOutputStream("1.maz"));
			byte[] arr = maze.toByteArray();
			
			out.write(arr.length);
			out.write(arr);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		InputStream in;
		try {
			in = new MyDecompressorInputStream(
				new FileInputStream("1.maz"));
			int size = in.read();			
			byte b[]=new byte[size];
			in.read(b);
			in.close();	
			
			Maze3d loaded = new Maze3d(b);
			System.out.println("maze loaded from file:");
			System.out.println(loaded);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

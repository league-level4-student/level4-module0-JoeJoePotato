package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random r = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start

		int v = r.nextInt(width);
		int z = r.nextInt(height);
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cs[v][z]);
		maze.cs[0][r.nextInt(height)].setWestWall(false);
		maze.cs[width-1][r.nextInt(height)].setEastWall(false);
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);

		// B. Get an ArrayList of unvisited neighbors using the current cell and the
		// method below
		ArrayList<Cell> p = getUnvisitedNeighbors(currentCell);

		// C. if has unvisited neighbors,
		if (p.size() > 0) {
			// C1. select one at random.
			Cell c = p.get(r.nextInt(p.size()));
			// C2. push it to the stack
			uncheckedCells.push(c);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, c);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = c;
			currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		} else {

			// D. if all neighbors are visited

			// D1. if the stack is not empty
			if (uncheckedCells.size() != 0) {
				// D1a. pop a cell from the stack

				// D1b. make that the current cell
				currentCell = uncheckedCells.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == (c2.getX() + 1) && c1.getY() == (c2.getY())) {
			c1.setWestWall(false);
			c2.setEastWall(false);
			
		} else if (c1.getX() == (c2.getX() - 1) && c1.getY() == (c2.getY())) {
			c1.setEastWall(false);
			c2.setWestWall(false);
			
		} else if (c1.getX() == (c2.getX()) && c1.getY() == (c2.getY() + 1)) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
			
		} else if (c1.getX() == (c2.getX()) && c1.getY() == (c2.getY() - 1)) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
			
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> p = new ArrayList<Cell>();
		
		if (x - 1 > -1 && maze.cs[x - 1][y].hasBeenVisited() == false) {
			p.add(maze.cs[x - 1][y]);
		}
		if (y - 1 > -1 && maze.cs[x][y - 1].hasBeenVisited() == false) {
			p.add(maze.cs[x][y - 1]);
		}
		
			if (y + 1 < height && maze.cs[x][y + 1].hasBeenVisited() == false) {
			p.add(maze.cs[x][y + 1]);
		}
		if (x + 1 < width && maze.cs[x + 1][y].hasBeenVisited() == false) {
			p.add(maze.cs[x + 1][y]);
		}
		
		return p;
	}
}

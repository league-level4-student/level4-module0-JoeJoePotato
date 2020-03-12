package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import _02_Pixel_Art.Pixel;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] c;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		cellSize = (w) / cpr;
		// 3. Initialize the cell array to the appropriate size.
		c = new Cell[cpr][cpr];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				c[i][j] = new Cell(i, j, cellSize);
			}
		}
	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive memeber to true of false
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				Random r = new Random();
				int dtwo = r.nextInt(3);
				if (dtwo == 1) {
					c[i][j].isAlive = true;
				} else {
					c[i][j].isAlive = false;
				}

			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterate through the cells and set them all to dead.
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				c[i][j].isAlive = false;
			}
		}

		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				if (c[i][j].isAlive == true) {
					Color c = new Color(130, 255, 200);
					g.setColor(c);
					g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
				}
			}
		}

		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for (int i = 0; i < livingNeighbors.length; i++) {
			for (int j = 0; j < livingNeighbors[i].length; j++) {
				livingNeighbors[i][j] = getLivingNeighbors(i, j);
			}
		}

		// 8. check if each cell should live or die

		for (int b = 0; b < livingNeighbors.length; b++) {
			for (int z = 0; z < livingNeighbors[b].length; z++) {
				if (livingNeighbors[b][z] == 3) {
					c[b][z].isAlive = true;
				} else if (livingNeighbors[b][z] != 2) {
					c[b][z].isAlive = false;
				}
			}
		}

		repaint();
	}

	// 9. Complete the method.
	// It returns an int of 8 or less based on how many
	// living neighbors there are of the
	// cell identified by x and y
	public int getLivingNeighbors(int x, int y) {
		int peeps = 0;
		if (x-1>-1 && y-1>-1 && c[x - 1][y - 1].isAlive == true) {
			peeps++;
		}
		if (x-1>-1 && c[x - 1][y].isAlive == true) {
			peeps++;
		}
		if (y-1>-1 && c[x][y - 1].isAlive == true) {
			peeps++;
		}
		if (x-1>-1 && y+1<50 && c[x - 1][y + 1].isAlive == true) {
			peeps++;
		}
		if (x+1<50 && y-1>-1 && c[x + 1][y - 1].isAlive == true) {
			peeps++;
		}

		if (y+1<50 && c[x][y + 1].isAlive == true) {
			peeps++;
		}
		if (x+1<50 && c[x + 1][y].isAlive == true) {
			peeps++;
		}
		if (x+1<50 && y+1<50 && c[x + 1][y + 1].isAlive == true) {
			peeps++;
		}

		return peeps;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		if (c[e.getX() / cellSize][e.getY() / cellSize].isAlive == true) {
			c[e.getX() / cellSize][e.getY() / cellSize].isAlive = false;
		} else {
			c[e.getX() / cellSize][e.getY() / cellSize].isAlive = true;
		}

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}

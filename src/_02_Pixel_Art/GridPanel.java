package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JPanel;



public class GridPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private int windowWidth;
	private int windowHeight;
	private int pixelWidth;
	private int pixelHeight;
	private int rows;
	private int cols;
	private static final String DATA_FILE = "src/_02_Pixel_Art/DATA_FILE";
	//1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel[][] ps=load();
	
	private Color color;
	
	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.BLACK;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		
		ps=new Pixel[rows][cols];
		
		//3. Iterate through the array and initialize each element to a new pixel.
		for (int i = 0; i < ps.length; i++) {
			for (int j = 0; j < ps[i].length; j++) {
				ps[i][j]=new Pixel(i,j);
			}
		}
		
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void clickPixel(int mouseX, int mouseY) {
		//5. Use the mouseX and mouseY variables to change the color
		//   of the pixel that was clicked. *HINT* Use the pixel's dimensions.
	ps[mouseX/pixelWidth][mouseY/pixelHeight].color=color;
	System.out.println(mouseX/windowWidth*cols);
	System.out.println(mouseY/windowHeight*rows);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//4. Itera0te through the array.
		//   For every pixel in the list, fill in a rectangle using the pixel's color.
		//   Then, use drawRect to add a grid pattern to your display.
		for (int i = 0; i < ps.length; i++) {
			for (int j = 0; j < ps[i].length; j++) {
				g.setColor(ps[i][j].color);
				g.fillRect(ps[i][j].x*windowWidth/cols, ps[i][j].y*windowHeight/rows, windowWidth/cols, windowHeight/rows);
				Color b=new Color(0,0,0);
				g.setColor(b);
				g.drawRect(ps[i][j].x*windowWidth/cols, ps[i][j].y*windowHeight/rows, windowWidth/cols, windowHeight/rows);
			
			}
		}
		
	}
	public  int getR() {
		return rows;
	}
	public  int getC() {
		return cols;
	}
	private  Pixel[][] load() {
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE));
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (Pixel[][]) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return new Pixel[getR()][getC()];
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return new Pixel[getR()][getC()];
		}
	}
	
	
}

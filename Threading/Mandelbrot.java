  /*

     * Mandelbrot.java

     *
     * Version:1.0	  	10/18/2015
     * @ author   		Prajesh Jhumkhawala
     * @ author   		Ashwini Singh
     
     */



import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.*;

/**
 * This Class extends the Mandelbrot class and implements the Runnable interface
 *
 */

class Thread_Im extends Mandelbrot implements Runnable {

	public Thread_Im(String s, int y, int x, Vector aVector) {
    	/*
    	 * Parameterized constructor which calls the constructor of Mandelbrot class
    	 */

		super(s, y, x, aVector);
	}

	public void run() {
    	
		/*
    	 * When we start the thread, this method is called
    	 */
		createSet();

	}

}

/**
 * This Class extends JFrame and contains the display implementations and also the logic for calculating the color at various coordinates.
 * It also contains the main class from where the execution starts
 *
 */

class Mandelbrot extends JFrame {
	private Vector aVector;
	static int rgb[][] = new int[800][800];
	String name;
	int x, y;
	private final int MAX = 5000;
	private final int LENGTH = 800;
	private final double ZOOM = 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];

	public Mandelbrot() {

	}

	public Mandelbrot(String name, int y, int x, Vector aVector) {
    	/*
    	 * initializes the values of name,x and y.
    	 * It also calls the initColors() and creates the image and sets its visibility as true
    	 * It also sets the screen coordinates
    	 *  
    	 */

		super("Mandelbrot Set");
		this.name = name;
		this.x = x;
		this.y = y;
		this.aVector = aVector;

		setBounds(0, 0, LENGTH, LENGTH);

		if (y == -1 && x == -1) //If it is the first thread, then set visibility as true and create the image 
		{
			this.setVisible(true);
			theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		initColors();

		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void createSet() {
		/*
		 * This contains the main logic for determining the color at a particular pixel.
		 * 
		 */
		synchronized (aVector) //Synchronized is used so that each thread entering can complete its execution before another thread starts.
		{ 
			double zx, zy, cX, cY;
			zx = zy = 0;
			cX = (x - LENGTH) / ZOOM;
			cY = (y - LENGTH) / ZOOM;
			int iter = 0;
			double tmp;
			while ((zx * zx + zy * zy < 10) && (iter < MAX - 1)) { // this is the part for the parallel part
				tmp = zx * zx - zy * zy + cX; // this is the part for the parallel part
				zy = 2.0 * zx * zy + cY; // this is the part for the parallel part
				zx = tmp; // this is the part for the parallel part
				iter++; // this is the part for the parallel part
			}			// this is the part for the parallel part
			if (iter > 0) // this is the part for the parallel part
			{
				// theImage.setRGB(x, y, colors[iter]); // this is the part for the parallel part
				rgb[x][y] = colors[iter]; //Store the color values in the rgb array at the pixel ocation in array 
			
			} else { // this is the part for the parallel part
				System.out.println("X " + x + "Y " + y);
				rgb[x][y] = (iter | (iter << 8));//Store the color values in the rgb array at the pixel ocation in array
				// theImage.setRGB(x, y, iter | (iter << 8)); // this is the part for the parallel part
			}
		}
	}

	public void initColors() {
		/*
		 * Initializes the color array and the value from the particular array is used 
		 */
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index / 256f, 1, index / (index + 8f));
		}
	}

	public void paint1() {
		/*
		 * Uses the setImage method and sets the color ar a particular coordinates with the help of the RGB array 
		 */
		
		for (int i = 0; i < 800; i++) { // System.out.println(i+"
			for (int j = 0; j < 800; j++) { // "+rgb[i][0]+" "+rgb[i][1]);
				theImage.setRGB(i, j, rgb[i][j]);

			}
		}
		repaint(); //Calls the paint() method.

	}

	@Override
	public void paint(Graphics g) {
		/*
		 * Draws the image
		 */
		g.drawImage(theImage, 0, 0, this);
	}

	public static void main(String[] args) {
		try {
			Vector aVector = new Vector();
			for (int i = 0; i < 800; i++) {
				for (int j = 0; j < 800; j++) {
					Thread_Im aMandelbrot = new Thread_Im("" + i + j, i, j, aVector);

					Thread t = new Thread(aMandelbrot);
					boolean b = true;
					while (b) {
						if (Thread.activeCount() < 4) {
							t.start();
							b = false;
						} else {
							Thread.sleep(100);
							b = true;
						}
					}
					// System.out.println("ACTIVE COUNT"+Thread.activeCount());

				}
			}
			Mandelbrot aMandelbrot = new Mandelbrot("", -1, -1, aVector);
			aMandelbrot.paint1();
		} catch (Exception e) {
		}
	}
}
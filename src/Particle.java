import java.awt.Color;

public class Particle {

	protected int xPos;
	private int yPos;
	private boolean doneMoving = false;
	public static int MAXMOVE = 50;
	private Coordinate[] prevCoords;
	private int alpha = 255;
	private Color color;
	
	private double xVel;
	private double yVel;
	private double angle;
	private int speed;
	private int moveCount = 0;
	
	public Particle(Firework f) {
		xPos = f.getX();
		yPos = f.getY();
		color = f.getColor();
		
		angle = (Math.random() * (2*Math.PI));
		speed = (int)(Math.random() * 10 + 5);
		xVel = (int)(Math.cos(angle) * speed);
		yVel = (int)(Math.sin(angle) * speed);
		prevCoords = new Coordinate[MAXMOVE];
	}
	
	public void changeColor() {
		int change = 5;
		if (alpha > change) {
			alpha -= change;
		}
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	}
	
	public void move() {
		if (moveCount < MAXMOVE) {	
			//constant in x
			xPos += xVel;
			//changes in y
			yPos += yVel;
			yVel += 0.5;
			prevCoords[moveCount] = new Coordinate(xPos, yPos);
			moveCount++;
		}
		else {
			doneMoving = true;
		}
	}
	
	public boolean done() {
		return doneMoving;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public Color getColor() {
		return color;	
	}
	
	public Coordinate[] getPrevCoords() {
		//System.out.println(prevCoords);
		return prevCoords;
	}
}

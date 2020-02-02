import java.awt.Color;

public class Firework {

	private Color color;
	private Coordinate pos;
	private int speed;
	private int distance;
	private boolean readyToExplode = false;
	private boolean particlesMade = false;
	private Particle[] particles;
	
	private int shape;
	public static final int RANDOM = 0;
	public static final int SQUARE = 1;
	
	public Firework(int x, int y, Color c) {
		pos = new Coordinate(x, y);
		color = c;
		speed = (int)(Math.random() * 5 + 10);
		distance = (int)((float) pos.y() / 2) + (int)(Math.random() * (Drawing.HEIGHT / 10));
		//System.out.println(y + " " + distance);
	}

	public int getShape() {
		return shape;
	}
	
	public boolean particlesMade() {
		return particlesMade;
	}
	
	public void setParticlesMade(boolean b) {
		particlesMade = b;
	}
	
	public void makeParticles(int num) {
		Particle[] particles = new Particle[num];
		for (int i = 0; i < num; i++) {
			particles[i] = new Particle(this);	
		}
		this.particles = particles;
		particlesMade = true;
	}
	
	public void setParticles(Particle[] part) {
		particles = part;
	}
	
	public Particle[] getParticles() {
		return particles;
	}
	
	public Color getColor() {
		return color;
	}

	public Coordinate getPos() {
		return pos;
	}

	public int getDistance() {
		return distance;
	}
	
	public int getX() {
		return pos.x();
	}

	public int getY() {
		return pos.y();
	}

	public boolean ready() {
		return readyToExplode;
	}
	
	public void moveUp() {
		if (pos.y() > distance) {
			pos = new Coordinate(pos.x(), pos.y() - speed);
		}
		else {
			readyToExplode = true;
		}
	}
}

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Drawing extends JPanel implements ActionListener, MouseMotionListener, MouseWheelListener, MouseListener {

	private JFrame frame;
	public static int WIDTH = 1000;
	public static int HEIGHT = 700;
	public static Color[] COLORS = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.MAGENTA};
	private int mouseX;
	private int mouseY;
	private boolean mouseDown = false;
	private Color drawColor = Color.RED;
	private int colorIndex = 0;
	private ArrayList<Firework> fireworks = new ArrayList<Firework>();
	private Timer timer;
	private int timerTicks = 0;
	private int numParticles = 30;
	private int[] buildingWidths;
	private int[] buildingHeights;
	private Coordinate[] starPositions;
	private Coordinate[] orion;
	private Coordinate[] dipper;
	private Coordinate[] cassiopeia;
	private Coordinate[] cancer;
	private Coordinate[] triangulum;
	
	//private ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
	
	public static void main(String[] args) {
		Drawing d = new Drawing();
	}

	public Drawing() {
		
		buildingWidths = new int[60];
		buildingHeights = new int[60];
		for (int i = 0; i < buildingWidths.length; i++) {
			buildingWidths[i] = (int)(Math.random() * 30 + 40);
			buildingHeights[i] = (int)(Math.random() * 50 + 150);
		}
		starPositions = new Coordinate[50];
		for (int i = 0; i < starPositions.length; i++) {
			starPositions[i] = new Coordinate((int)(Math.random() * WIDTH), (int)(Math.random() * ((HEIGHT * 2) / 3)));
		}
		
		orion = new Coordinate[31];
		orion[0] = new Coordinate(591, 146);
		orion[1] = new Coordinate(591, 193);
		orion[2] = new Coordinate(612, 192);
		orion[3] = new Coordinate(629, 147);
		orion[4] = new Coordinate(612, 192);
		orion[5] = new Coordinate(591, 193);
		orion[6] = new Coordinate(601, 239);
		orion[7] = new Coordinate(618, 264);
		orion[8] = new Coordinate(679, 236);
		orion[9] = new Coordinate(714, 279);
		orion[10] = new Coordinate(800, 283);
		orion[11] = new Coordinate(807, 256);
		orion[12] = new Coordinate(796, 246);
		orion[13] = new Coordinate(807, 256);
		orion[14] = new Coordinate(800, 283);
		orion[15] = new Coordinate(800, 306);
		orion[16] = new Coordinate(799, 334);
		orion[17] = new Coordinate(783, 339);
		orion[18] = new Coordinate(800, 335);
		orion[19] = new Coordinate(800, 306);
		orion[20] = new Coordinate(800, 283);
		orion[21] = new Coordinate(714, 279);
		orion[22] = new Coordinate(695, 372);
		orion[23] = new Coordinate(677, 381);
		orion[24] = new Coordinate(655, 389);
		orion[25] = new Coordinate(618, 264);
		orion[26] = new Coordinate(655, 389);
		orion[27] = new Coordinate(645, 461);
		orion[28] = new Coordinate(740, 450);
		orion[29] = new Coordinate(695, 372);
		orion[30] = new Coordinate(714, 279);

		dipper = new Coordinate[8];
		dipper[0] = new Coordinate(173, 187);
		dipper[1] = new Coordinate(264, 171);
		dipper[2] = new Coordinate(311, 197);
		dipper[3] = new Coordinate(377, 230);
		dipper[4] = new Coordinate(493, 221);
		dipper[5] = new Coordinate(478, 290);
		dipper[6] = new Coordinate(398, 293);
		dipper[7] = new Coordinate(377, 230);
		
		cassiopeia = new Coordinate[5];
		cassiopeia[0] = new Coordinate(263, 320);
		cassiopeia[1] = new Coordinate(331, 402);
		cassiopeia[2] = new Coordinate(418, 386);
		cassiopeia[3] = new Coordinate(480, 458);
		cassiopeia[4] = new Coordinate(552, 383);
		
		cancer = new Coordinate[5];
		cancer[0] = new Coordinate(50, 131);
		cancer[1] = new Coordinate(87, 246);
		cancer[2] = new Coordinate(193, 320);
		cancer[3] = new Coordinate(87, 246);
		cancer[4] = new Coordinate(57, 331);
		
		triangulum = new Coordinate[4];
		triangulum[0] = new Coordinate(722, 122);
		triangulum[1] = new Coordinate(899, 68);
		triangulum[2] = new Coordinate(893, 208);
		triangulum[3] = new Coordinate(722, 122);
		
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);

		timer = new javax.swing.Timer(20, this);
		timer.start();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
	}

	private void drawConstellation(Graphics g, Coordinate[] constellation) {
		for (int i = 0; i < constellation.length; i++) {
			int x = constellation[i].x();
			int y = constellation[i].y();
			int minAlpha = 170;
			g.setColor(new Color(230, 230, 230, (int)(Math.random() * (256 - minAlpha) + minAlpha)));
			g.fillOval(x, y, 4, 4);
			if (i + 1 < constellation.length) {
				int nextX = constellation[i+1].x();
				int nextY = constellation[i+1].y();
				g.setColor(new Color(230, 230, 230, 70));
				g.drawLine(x, y, nextX, nextY);
			}
		}
	}
	public void drawStars(Graphics g) {
		int minAlpha = 170;
		for (int i = 0; i < starPositions.length; i++) {
			g.setColor(new Color(230, 230, 230, (int)(Math.random() * (256 - minAlpha) + minAlpha)));
			g.fillOval(starPositions[i].x(), starPositions[i].y(), 3, 3);
		}
		drawConstellation(g, orion);
		drawConstellation(g, dipper);
		drawConstellation(g, cassiopeia);
		drawConstellation(g, cancer);
		drawConstellation(g, triangulum);
	}
	
	public void drawMoon(Graphics g) {
		g.setColor(new Color(230, 230, 230));
		g.fillOval((WIDTH * 4) / 5, HEIGHT / 4, 50, 50);
		g.setColor(Color.BLACK);
		g.fillOval((WIDTH * 4) / 5 - 10, HEIGHT / 4 + 10, 40, 40);
	}
	public void drawCity(Graphics g) {
		int numBuildings = buildingWidths.length / 3;
		
		Color[] layerColors = {new Color(30, 30, 30), new Color(45, 45, 45), new Color(60, 60, 60)};
		for (int colorInd = 0; colorInd < layerColors.length; colorInd++) {
			int x = 0;
			g.setColor(layerColors[colorInd]);
			int indStart = colorInd * numBuildings;
			int indEnd = indStart + numBuildings;
			for (int i = indStart; i < indEnd; i++) {
				g.fillRect(x, 3 * buildingHeights[i], buildingWidths[i], HEIGHT);
				x += (buildingWidths[i] + 10);
			}
		}
	}
	
	public void drawFireworks(Graphics g) {
		for (int i = 0; i < fireworks.size(); i++) {
			Firework f = fireworks.get(i);
			g.setColor(f.getColor());
			if (!f.ready()) {
				g.fillRect(f.getX(), f.getY(), 3, 3);
			}
			else {
				if (!f.particlesMade()) {
					f.makeParticles(numParticles);
				}
				if (f.getParticles() != null) {

					for (int j = 0; j < f.getParticles().length; j++) {
						Particle p = f.getParticles()[j];
						g.setColor(p.getColor());
						Coordinate[] prev = p.getPrevCoords();
						for (int k = 0; k < prev.length; k++) {
							if (prev[k] != null) {
								g.fillRect(prev[k].x(), prev[k].y(), 3, 3);
							}
						}
					}	
				}
			}
		}
		repaint();
	}

	public void drawExplosion(Graphics g, Firework f) {
		int[] xPos = new int[numParticles];
		int[] yPos = new int[numParticles];
		for (int i = 0; i < numParticles; i++) {
			xPos[i] = f.getX();
			yPos[i] = f.getY();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		drawMoon(g);
		drawStars(g);
		drawCity(g);
		g.setColor(Color.WHITE);
		g.drawString("Hold down the left mouse button to create a fireworks show! Scroll to change the color of the fireworks.", 225, 50);
		g.setColor(drawColor);
		g.fillRect(mouseX - 3, mouseY - 3, 5, 5);
		drawFireworks(g);
		//g.setColor(Color.WHITE);
		/*for (Coordinate c : temp) {
			g.fillOval(c.x(), c.y(), 4, 4);
		}*/
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			colorIndex++;
			if (colorIndex >= COLORS.length) {
				colorIndex = 0;
			}
		}
		else {
			colorIndex--;
			if (colorIndex < 0) {
				colorIndex = COLORS.length - 1;
			}
		}
		drawColor = COLORS[colorIndex];
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*System.out.println(e.getX() + " " + e.getY());
		temp.add(new Coordinate(e.getX(), e.getY()));
		for (int i = 0; i < temp.size(); i++) {
			System.out.println("cassiopeia[" + i + "] = new Coordinate(" + temp.get(i).x() + ", " + temp.get(i).y() + ");");
		}*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timerTicks++;
		//System.out.println(timerTicks);
		if (timerTicks >= 10) {
			timerTicks = 0;
			if (mouseDown) {
				Firework f = new Firework(mouseX, mouseY, drawColor);
				fireworks.add(f);
				repaint();
			}
			int random = (int)(Math.random() * 5);
		}
		//System.out.println(mouseDown);
		for (Firework f : fireworks) {
			if (!f.ready()) {
				f.moveUp();
			}
			else {
				boolean allDone = true;
				if (f.getParticles() != null) {
					for (Particle part : f.getParticles()) {
						part.move();
						part.changeColor();
						if (!part.done()) {
							allDone = false;
						}
					}
					if (allDone) {
						f.setParticles(null);
					}
				}
			}
			repaint();
		}
	}
}

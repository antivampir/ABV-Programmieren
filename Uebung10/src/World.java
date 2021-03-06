import java.awt.Graphics;

// displays Tiles als Welt
public class World {
	
	private Handler handler;
	private int width, height;	//gr��e der Welt = Anzahl der Tiles x und y Richtung
	private int[][] tileIDs;	// IDs der unterschiedlichen Tiles gespeichert in zweidimensionalem array[xcoordinate][ycoordinate]
	private int spawnX, spawnY;
	
	// L�dt unsere erstellte Welt von unserer World.txt �ber den Path in GameState
	public World(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
	}
	public void update() {
		
	}

	// Loops f�r die einzelnen Koordinaten und ordnet diesen die Tiles zu
	// ruft Tile getTile auf um die Tile Id herauszufinden
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth())/ Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILEHEIGHT + 1);;
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {

				// Tiles werden zu Pixel konvertiert um die 64x64 px gro�en teils in der richtigen gr��e anzuzeigen
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
	}

	// ruft die Tiles auf und zeigt sie in den Koordinaten 
	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tileIDs[x][y]];				//in Tile gespeicherter Array mit index der ID an der Stelle x, y in tileIDs 

		// falls eine Tile Id aufgerufen wird, die es nicht gibt wird eine groundTile angezeigt
		if (t==null) {									// falls an indexstelle kein tile gespeichert ist, ground tile	(vermeidung eines errors
			return Tile.groundTile;																						//bei fehlerhafter world.txt)
		}
		return t;
	}

	//wei�t die IDs ihrem Index in tileIDs zu
	private void loadWorld(String path) {
		String file	= Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");	// Teilt den World.txt String anhand von Leerzeichen/blank space
		
		// Ersten vier Zahlen der txt
		width  = Utils.parseInt(tokens[0]); // L�nge der Welt
		height = Utils.parseInt(tokens[1]);	// H�he
		spawnX = Utils.parseInt(tokens[2]);	// Startpunkt des Players auf X-Achse
		spawnY = Utils.parseInt(tokens[3]);	// Startpunkt des Players auf Y-Achse
		
		tileIDs = new int[width][height];	//tile IDs sind gespeichert wie ein Koordinatensystem in Tileskalierung (x=1 => 64 Pixel)
		
		for(int y = 0; y < height; y++) {	//Zuweisung einer ID zu jedem "Punkt" des Koordinatensystems
			for(int x = 0; x < width; x++) {
				tileIDs[x][y] = Utils.parseInt(tokens[(x + y * width +4)]); // x=0,y=0 -> tokens[4], x=1, y=0 -> t[5]; x=1, y=1, width = 20 -> t[25] usw.
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
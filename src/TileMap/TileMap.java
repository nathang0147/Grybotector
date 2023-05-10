package TileMap;

import UserInterface.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileMap {
    //position
    private  double x;
    private  double y;
    private  int xmin;
    private  int xmax;
    private  int ymin;
    private int ymax;
    private double tween = 0.02;
    // map
    private int[][] map;
    private  int tilesize;
    private int numRows;
    private int numCols;
    private int height;
    private int width;
    //tile
    private BufferedImage tileset;
    private int NumtileinCOL;
    private Tile [][]tiles;

    // bounds
    private int rowOffset;
    private int colOffset;
    private int numRowtodraw;
    private int numColtodraw;

    public TileMap(int tilesize) {
        this.tilesize = tilesize;
        numRowtodraw= GamePanel.HEIGHT/tilesize+2;
        numColtodraw= GamePanel.WIDTH/tilesize+2;

    }
    public void loadTiles(String s) {
        try {
            tileset = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            NumtileinCOL = tileset.getWidth() / tilesize;
            tiles = new Tile[12][NumtileinCOL];
            BufferedImage subimage;
            for (int col = 0; col < NumtileinCOL; col++) {
                    for (int i = 0; i < 12; i++) {
                        if(i==0 && col==0){
                        subimage= tileset.getSubimage(col,i,tilesize,tilesize);
                        tiles[0][0]=new Tile(subimage,Tile.NORMAL);
                        }else {
                        subimage = tileset.getSubimage(col * tilesize, i * tilesize, tilesize, tilesize);
                        tiles[i][col] = new Tile(subimage, Tile.BLOCK);
                    }
                }
            }
            System.out.println(tiles.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loadMap(String s) {
        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());

            map = new int[numRows][numCols];

            width = numCols * tilesize;
            height = numRows * tilesize;

            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymax = 0;

            String delims = ",";
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getTilesize() {
        return tilesize;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTileType(int row, int col){
        int rc = map[row][col];
        int r = rc/ NumtileinCOL;
        int c = rc% NumtileinCOL;
        return tiles[r][c].getType();
    }
    public void setPosition(double x, double y){
        this.x += (x - this.x) ;
        this.y += (y - this.y) ;
        fixedCam();

        rowOffset = (int) -this.y / tilesize;
        colOffset = (int) -this.x / tilesize;

    }

    public void fixedCam(){
        if(x<xmin)x=xmin;
        if(y<ymin)y=ymin;
        if(x>xmax)x=xmax;
        if(y>ymax)y=ymax;
    }
    public void draw (Graphics2D g) {
        for (int row = rowOffset; row < rowOffset + numRowtodraw; row++) {
            if (row >= numRows) break;
            for (int col = 0; col <= colOffset + numColtodraw; col++) {
                if (col >= numCols) break;
                if (map[row][col] == 0) continue;
                int rc = map[row][col];
                int r = rc / NumtileinCOL;
                int c = rc % NumtileinCOL;
                g.drawImage(tiles[r][c].getImg(), (int) x + col * tilesize, (int) y + row * tilesize, null);
            }
        }
    }
}

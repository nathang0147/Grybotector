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
    private  int x;
    private  int y;
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
        numRowtodraw= GamePanel.WIDTH/tilesize+2;
        numColtodraw= GamePanel.HEIGHT/tilesize+2;

    }
    public void loadTiles(String s) {
        try {


            tileset = ImageIO.read(getClass().getResourceAsStream(s));
            NumtileinCOL = tileset.getWidth() / tilesize;
            tiles = new Tile[12][NumtileinCOL];
            BufferedImage subimage;

            for (int i = 0; i < 12; i++) {
                for (int col = 0; col < NumtileinCOL; col++) {
                    subimage = tileset.getSubimage(col * tilesize, i * tilesize, tilesize, tilesize);
                    tiles[i][col] = new Tile(subimage, Tile.BLOCK);
                }
            }
            subimage = tileset.getSubimage(0,0,tilesize, tilesize);
            tiles[0][0] = new Tile(subimage, Tile.NORMAL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loadMap(String s) {
        try {


            InputStream ip = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(ip));
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tilesize;
            height = numRows * tilesize;
            for (int row = 0; row < numRows; row++) {

                String line = br.readLine();
                String[] data = line.split(",");
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(data[col]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        return tiles [r][c].getType();
    }
    public void setPosition(double x, double y){
        this.x += (x - this.x) + tween;
        this.y += (y - this.y) + tween;
        fixedCam();

        rowOffset = (int) -this.x /tilesize;
        colOffset = (int) -this.y / tilesize;

    }

    public void fixedCam(){
        if(y < ymin) y = ymin;
        if(y>ymax) y = ymax;
        if(x < xmin) x = xmin;
        if(x > xmax) x  = xmax;
    }
    public void draw (Graphics2D g){
        for(int row = rowOffset; row <= numRowtodraw + rowOffset; row ++){
            if(row >= numRows) break;
            for(int col = colOffset; col <= numColtodraw + colOffset; col++){
                if(col >= numCols) break;
                if(map[row][col] == 0) continue;

                int rc = map[row][col];
                int r = rc/ NumtileinCOL;
                int c = rc% NumtileinCOL;

                g.drawImage(tiles[r][c].getImg(),
                        (int)x + col*tilesize,
                        (int) y + row*tilesize,
                        null);
            }
        }
    }
}

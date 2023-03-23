package TileMap;

<<<<<<< HEAD
public class TileMap {

=======
import UserInterface.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileMap {
    //position
    private  int x;
    private  int y;
    private  int xmin;
    private  int xmax;
    private  int ymin;
    private int ymax;
    private int tween;
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
        numColtodraw=GamePanel.HEIGHT/tilesize+2;

    }
    public void loadTiles(String s) throws IOException {
        tileset= ImageIO.read(getClass().getResourceAsStream(s));
        NumtileinCOL=tileset.getWidth()/tilesize;
        tiles= new Tile[12][NumtileinCOL];
        BufferedImage subimage;
        for (int i = 0; i < 12; i++) {
            for (int col = 0; col < NumtileinCOL; col++) {
                subimage= tileset.getSubimage(col*tilesize,i*tilesize,tilesize,tilesize);
                tiles[i][col]=new Tile(subimage,Tile.BLOCK);
            }
        }
    }
    public void loadMap(String s) throws IOException {
        InputStream ip= getClass().getResourceAsStream(s);
        BufferedReader br= new BufferedReader(new InputStreamReader(ip));
        numCols=Integer.parseInt(br.readLine());
        numRows=Integer.parseInt(br.readLine());
        width= numCols*tilesize;
        height=numRows*tilesize;
        for (int row = 0; row < numRows; row++) {

            String line= br.readLine();
            String[] data=line.split(",");
            for (int col = 0; col < numCols; col++) {
                map[row][col]=Integer.parseInt(data[col]);
            }
        }
    }
>>>>>>> origin/Menu
}

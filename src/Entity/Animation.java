package Entity;

import java.awt.image.BufferedImage;

public class Animation {
    // use for store frame
    private BufferedImage[] frames;
    // use for determining the current frame in the array
    private int currentFrame;
    // use to determine the start time of draw frames[]
    private  long startTime;
    //use to determine the delay between two frame
    private long delay;
    // sử dụng để xem hàm đã vẽ bất kì frame nào chưa
    private boolean playedOnce;

    public Animation(){
        playedOnce=false;
    }
    public void setFrames(BufferedImage[] frames){
        this.frames=frames;
        currentFrame=0; // bắt đầu từ frames[0]
        startTime=System.nanoTime();
        playedOnce=true;
    }
    public void setDelay(long d){ this.delay=d;}
    // dùng để update animation
    public void update(){
        if(delay==-1){
            return ; // stop update
        }
        long elapsed= (System.nanoTime()-startTime)/1000000;
        // khi mà elapsed time(thời gian vẽ) > delay thì
        if(elapsed>delay){
            currentFrame++;
            startTime=System.nanoTime();
            // cập nhật  lại startTime để bắt đầu update lại ( tạo tính liên tục khi animation vẽ)

        }
        if(currentFrame==frames.length){
            currentFrame=0;
            // trả lại currentframe về 0 để sau đó thực hiện animation khác
            playedOnce=true;

        }
    }
    public  int getCurrentFrame(){
        return  currentFrame;
    }
    public BufferedImage getImage(){
        return frames[currentFrame];
        // khi nào lấy 1 frame từ frames ra để loop thì dùng hàm này
    }
    public boolean hasPlayedOnece(){
        return playedOnce;
        // hàm này để xác định rằng chỉ có 1 hành động được thực thi (VD: chạy thì không thể bắn)
    }



}

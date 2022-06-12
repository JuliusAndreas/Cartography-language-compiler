package finalproject;

import java.awt.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Pen {

    private static Pen pen;
    private Point position=new Point(0,0);
    private boolean down = false;
    private Canvas canvas;        
    
    private Pen(){    
    }
    
    public static synchronized Pen getPen(){
        if(pen==null){
            pen=new Pen();
        }
        return pen;
    }
    
    public void draw(Point dest){
        
        canvas.getGraphicsContext2D().strokeLine(position.x, position.y, dest.x, dest.y);
        
        setPosition(dest);
    }
    
    public boolean isDown(){
        return down;
    }
    
    public void putDown(){
        this.down=true;
    }
    
    public void pickUp(){
        this.down=false;
    }
    
    public void setCanvas(Canvas canvas){
        this.canvas=canvas;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    
    public void setColor(int r,int g,int b){
        canvas.getGraphicsContext2D().setStroke(Color.rgb(r, g, b));
    }
    
    public void setStyle(String style){
        switch(style){
            case "DASHED":
                canvas.getGraphicsContext2D().setLineDashes(20);
                break;
            case "DOTTED":
                canvas.getGraphicsContext2D().setLineDashes(8);
                break;
            case "SOLID" :
                canvas.getGraphicsContext2D().setLineDashes(0);
                break;
        }
    }
    
    public void setSize(int size){
        canvas.getGraphicsContext2D().setLineWidth(size);
    }
}

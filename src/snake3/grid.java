/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Andrei Golovkin
 */
public class grid {
    private int timer = 3005;
    private int posX, posY;
    private Rectangle view;
    private boolean checked = false;
    
    public grid(double x, double y, double width, double height, int posX, int posY){
        view = new Rectangle(width, height);
        view.setTranslateX(x);
        view.setTranslateY(y);
        view.getStyleClass().add("empty");
        view.getStyleClass().add("orange-head");
        view.getStyleClass().add("white-tail");
        
        this.posX = posX;
        this.posY = posY;
    }
    
    public void setHeadStyle(String a){
        view.getStyleClass().set(1, a);
    }
    public void setTailStyle(String a){
        view.getStyleClass().set(2, a);
    }
    
    public void changeType(String a){
        view.getStyleClass().set(0, a);
        if(a.equals("tail")){
            timer = 0;
        }
    }
    
    public String getType(){
        return view.getStyleClass().get(0);
    }
    
    public void compare(int l){
        if(timer < l){
            timer++;
        }
        else if(timer == l){
            timer = 3005;
            changeType("empty");
        }
        
        checked = false;
    }
    
    public Node getView(){
        return view;
    }
    public void setTimer(int t){
        timer = t;
    }
    public boolean isChecked(){
        return checked;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public void checked(){
        checked = true;
    }
}

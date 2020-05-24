/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

/**
 *
 * @author Andrei Golovkin
 */
public class AI {
    private String dir = "right";
    private int foodX, foodY;
    private int fieldWidth, fieldHeight;
    
    public String getDir(int x, int y){
        if(x < foodX){
            if(foodX - x > x + fieldWidth - foodX){
                dir = "left";
            }
            else{
                dir = "right";
            }
            //System.out.println(x + " " + foodX + " " + fieldWidth + " " + (foodX - x) + " " + (x + fieldWidth - foodX) + " " + dir);
        }
        else if(x > foodX){
            if(x - foodX > fieldWidth - x + foodX){
                dir = "right";
            }
            else{
                dir = "left";
            }
        }
        else if(y > foodY){
            if(y - foodY > fieldHeight - y + foodY){
                dir = "down";
            }
            else{
                dir = "up";
            }
        }
        else if(y < foodY){
            if(foodY - y > y + fieldHeight - foodY){
                dir = "up";
            }
            else{
                dir = "down";
            }
        }
        
        return dir;
    }
    
    public void setSize(int width, int height){
        this.fieldWidth = width;
        this.fieldHeight = height;
    }
    
    public void setFoodCell(int x, int y){
        foodX = x;
        foodY = y;
    }
}

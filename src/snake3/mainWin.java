/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

import javafx.stage.Stage;

/**
 *
 * @author Andrei Golovkin
 */
public class mainWin extends Stage{
    private double width, height;
    
    public mainWin(double width, double height){
        System.out.println("Window initialization...");
        this.width = width;
        this.height = height;
        
        setResizable(false);
        setOnCloseRequest((EventHandler) -> {
            System.out.println("Programe is closing...");
            System.exit(0);
            System.out.println("Programe is closed");
        });
        
        setTitle("Snake");
        setMenu();
        show();
    }
    
    public void setCustomization(){
        System.out.println("Go to customization screen...");
        setScene(new customization(width, height, this));
        System.out.println("Customization screen is ready");
    }
    public void setGame(String headStyle, String tailStyle, boolean ai){
        System.out.println("Run the game...");
        setScene(new game(width, height, this, headStyle, tailStyle, ai));
        System.out.println("Game is ready");
    }
    public void setMenu(){
        System.out.println("Go to menu...");
        setScene(new menu(width, height, this));
        System.out.println("Menu is ready");
    }
}

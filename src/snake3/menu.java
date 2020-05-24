/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 *
 * @author Andrei Golovkin
 */
public class menu extends Scene{
    private Button exit, play, AIMode;
    private lib a = new lib();
    private mainWin win;
    
    public menu(double width, double height, mainWin win) {
        super(new Pane(), width, height);
        this.win = win;
        
        //System.out.println("Menu initialization...");
        //System.out.println("Create buttons...");
        
        play = a.createButton("Play", 150, 50, (ActionEvent) -> {
            this.win.setCustomization();
        });
        AIMode = a.createButton("AI mode", 150, 50, (ActionEvent) -> {
            this.win.setGame("orange-head", "white-tail", true);
        });
        exit = a.createButton("exit", 150, 50, (ActionEvent) -> {
            //System.out.println("Programe is closing...");
            System.exit(0);
            //System.out.println("Programe is closed");
        });
        
        TilePane root = new TilePane(Orientation.VERTICAL);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(play, AIMode, exit);
        
        //System.out.println("Upload styles to menu...");
        
        getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        
        root.getStyleClass().add("back");
        
        setRoot(root);
    }
    
}

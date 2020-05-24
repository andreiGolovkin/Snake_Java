/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Andrei Golovkin
 */
public class Snake3 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Game initialization...");
        mainWin a = new mainWin(661, 551);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

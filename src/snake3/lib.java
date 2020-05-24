/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Andrei Golovkin
 */
public class lib {
    public void println(Object a){
        System.out.println(a);
    }
    
    public void print(Object a){
        System.out.print(a);
    }
    
    public Button createButton(String name, double width, double height, EventHandler<ActionEvent> action){
        Button a = new  Button(name);
        a.setPrefSize(width, height);
        a.setOnAction(action);
        a.setOnMouseEntered((MouseEvent) -> {
            a.setStyle("-fx-border-color: white; -fx-text-fill: white;");
        });
        a.setOnMouseExited((MouseEvent) -> {
            a.setStyle("");
        });
        a.setOnMousePressed((MouseEvent) -> {
            a.setPrefSize(144, 48);
        });
        a.setOnMouseReleased((MouseEvent) -> {
            a.setPrefSize(width, height);
        });
        
        return a;
    }
}

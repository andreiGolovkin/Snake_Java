/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author Andrei Golovkin
 */
public class customization extends Scene{
    lib a = new lib();
    mainWin win;
    String[] headStyles = new String[6], tailStyles = new String[6];
    int headStylesIndex = 0, tailStylesIndex = 0;
    Button play, nextHead, prevHead, nextTail, prevTail;
    
    public customization(double width, double height, mainWin win) {
        super(new Pane(), width, height);
        this.win = win;
        
        grid head = new grid(295, 160, 70, 70, 0, 0);
        head.changeType("head");
        grid body1 = new grid(head.getView().getTranslateX(), head.getView().getTranslateY()+77, 70, 70, 0, 0);
        body1.changeType("tail");
        grid body2 = new grid(body1.getView().getTranslateX(), body1.getView().getTranslateY()+77, 70, 70, 0, 0);
        body2.changeType("tail");
        
        headStyles[0] = "orange-head";
        headStyles[1] = "red-head";
        headStyles[2] = "blue-head";
        headStyles[3] = "purple-head";
        headStyles[4] = "pink-head";
        headStyles[5] = "white-head";
        
        tailStyles[0] = "white-tail";
        tailStyles[1] = "red-tail";
        tailStyles[2] = "blue-tail";
        tailStyles[3] = "purple-tail";
        tailStyles[4] = "pink-tail";
        tailStyles[5] = "orange-tail";
        
        play = a.createButton("Play", 150, 50, (ActionEvent) -> {
            win.setGame(headStyles[headStylesIndex], tailStyles[tailStylesIndex], false);
        });
        play.setOnMousePressed((MouseEvent) -> {
            play.setPrefSize(144, 48);
            play.setTranslateX(play.getTranslateX()+3);
            play.setTranslateY(play.getTranslateY()+1);
        });
        play.setOnMouseReleased((MouseEvent) -> {
            play.setPrefSize(150, 50);
            play.setTranslateX(play.getTranslateX()-3);
            play.setTranslateY(play.getTranslateY()-1);
        });
        
        nextHead = a.createButton("->", 50, 50, (ActionEvent) -> {
            headStylesIndex++;
            if(headStylesIndex > headStyles.length-1){
                headStylesIndex = 0;
            }
            head.setHeadStyle(headStyles[headStylesIndex]);
        });
        nextHead.setOnMousePressed((MouseEvent) -> {
            nextHead.setPrefSize(48, 48);
            nextHead.setTranslateX(nextHead.getTranslateX()+1);
            nextHead.setTranslateY(nextHead.getTranslateY()+1);
        });
        nextHead.setOnMouseReleased((MouseEvent) -> {
            nextHead.setPrefSize(50, 50);
            nextHead.setTranslateX(nextHead.getTranslateX()-1);
            nextHead.setTranslateY(nextHead.getTranslateY()-1);
        });
        
        prevHead = a.createButton("<-", 50, 50, (ActionEvent) -> {
            headStylesIndex--;
            if(headStylesIndex < 0){
                headStylesIndex = headStyles.length-1;
            }
            head.setHeadStyle(headStyles[headStylesIndex]);
        });
        prevHead.setOnMousePressed((MouseEvent) -> {
            prevHead.setPrefSize(48, 48);
            prevHead.setTranslateX(prevHead.getTranslateX()+1);
            prevHead.setTranslateY(prevHead.getTranslateY()+1);
        });
        prevHead.setOnMouseReleased((MouseEvent) -> {
            prevHead.setPrefSize(50, 50);
            prevHead.setTranslateX(prevHead.getTranslateX()-1);
            prevHead.setTranslateY(prevHead.getTranslateY()-1);
        });
        
        nextTail = a.createButton("->", 50, 50, (ActionEvent) -> {
            tailStylesIndex++;
            if(tailStylesIndex > tailStyles.length-1){
                tailStylesIndex = 0;
            }
            body1.setTailStyle(tailStyles[tailStylesIndex]);
            body2.setTailStyle(tailStyles[tailStylesIndex]);
        });
        nextTail.setOnMousePressed((MouseEvent) -> {
            nextTail.setPrefSize(48, 48);
            nextTail.setTranslateX(nextTail.getTranslateX()+1);
            nextTail.setTranslateY(nextTail.getTranslateY()+1);
        });
        nextTail.setOnMouseReleased((MouseEvent) -> {
            nextTail.setPrefSize(50, 50);
            nextTail.setTranslateX(nextTail.getTranslateX()-1);
            nextTail.setTranslateY(nextTail.getTranslateY()-1);
        });
        
        prevTail = a.createButton("<-", 50, 50, (ActionEvent) -> {
            tailStylesIndex--;
            if(tailStylesIndex < 0){
                tailStylesIndex = tailStyles.length-1;
            }
            body1.setTailStyle(tailStyles[tailStylesIndex]);
            body2.setTailStyle(tailStyles[tailStylesIndex]);
        });
        prevTail.setOnMousePressed((MouseEvent) -> {
            prevTail.setPrefSize(48, 48);
            prevTail.setTranslateX(prevTail.getTranslateX()+1);
            prevTail.setTranslateY(prevTail.getTranslateY()+1);
        });
        prevTail.setOnMouseReleased((MouseEvent) -> {
            prevTail.setPrefSize(50, 50);
            prevTail.setTranslateX(prevTail.getTranslateX()-1);
            prevTail.setTranslateY(prevTail.getTranslateY()-1);
        });
        
        prevHead.setTranslateX(330-135);
        prevHead.setTranslateY(head.getView().getTranslateY() + 10);
        
        prevTail.setTranslateX(330-135);
        prevTail.setTranslateY(body1.getView().getTranslateY() + 48);
        
        play.setTranslateX(330+50+10-135);
        play.setTranslateY(490);
        
        nextHead.setTranslateX(330+50+10+150+10-135);
        nextHead.setTranslateY(head.getView().getTranslateY() + 10);
        
        nextTail.setTranslateX(330+50+10+150+10-135);
        nextTail.setTranslateY(body1.getView().getTranslateY() + 48);
        
        Pane root = new Pane();
        root.getChildren().addAll(prevHead, play, nextHead, nextTail, prevTail, body1.getView(), body2.getView(), head.getView());
        
        setRoot(root);
        
        getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        
        root.getStyleClass().add("back");
    }
    
}

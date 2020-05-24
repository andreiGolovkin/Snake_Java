/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

import java.util.ArrayList;
import javafx.geometry.Point2D;

/**
 *
 * @author Andrei Golovkin
 */
public class AI {

    private grid[][] field;
    private ArrayList<ArrayList<grid>> paths = new ArrayList<>();
    private ArrayList<grid> border = new ArrayList<>(), path = new ArrayList<>();
    private boolean found;
    private Point2D[] moves = new Point2D[4];
    
    double time;

    public AI(grid[][] field) {
        this.field = field;

        moves[0] = new Point2D(1, 0);
        moves[1] = new Point2D(0, 1);
        moves[2] = new Point2D(-1, 0);
        moves[3] = new Point2D(0, -1);
    }
    
    public String getDir(grid head){
        String dir = "";
        if(!path.isEmpty()){
            //System.out.println(path.size());
            if(path.get(0).getPosX() > head.getPosX()){
                if(path.get(0).getPosX() - head.getPosX() == 1){
                    dir = "right";
                }
                else{
                    dir = "left";
                }
            }
            else if (path.get(0).getPosX() < head.getPosX()){
                if(head.getPosX() - path.get(0).getPosX() == 1){
                    dir = "left";
                }
                else{
                    dir = "right";
                }
            }
            else if (path.get(0).getPosY() > head.getPosY()){
                if(path.get(0).getPosY() - head.getPosY() == 1){
                    dir = "down";
                }
                else{
                    dir = "up";
                }
            }
            else if (path.get(0).getPosY() < head.getPosY()){
                if(head.getPosY() - path.get(0).getPosY() == 1){
                    dir = "up";
                }
                else{
                    dir = "down";
                }
            }
            path.remove(0);
        }
        
        return dir;
    }

    private ArrayList<grid> copy(ArrayList<grid> array) {
        ArrayList<grid> ans = new ArrayList<>();

        for (grid n : array) {
            ans.add(n);
        }

        return ans;
    }

    public void findPath(grid head) {
        
        //System.out.println("Lists initialization...");
        paths.add(new ArrayList<>());
        paths.get(0).add(head);
        border.add(head);
        found = false;
        //System.out.println("Loop is going to start: " + (!border.isEmpty() && !found));
        //System.out.println("Lists are initializated...");
        //int num = 0;
        while (!border.isEmpty() && !found) {
            //System.out.println("Iteration nr " + num);
            //num++;
            for (Point2D n : moves) {
                int cellX = border.get(0).getPosX() + (int) n.getX();
                int cellY = border.get(0).getPosY() + (int) n.getY();

                if (cellX < 0) {
                    cellX = field.length - 1;
                } else if (cellX >= field.length) {
                    cellX = 0;
                }

                if (cellY < 0) {
                    cellY = field[0].length - 1;
                } else if (cellY >= field[0].length) {
                    cellY = 0;
                }

                if ((field[cellX][cellY].getType().equals("empty") || field[cellX][cellY].getType().equals("path")) && !field[cellX][cellY].isChecked()) {
                    border.add(field[cellX][cellY]);
                    field[cellX][cellY].checked();
                    ArrayList<grid> a;
                    a = copy(paths.get(0));
                    a.add(field[cellX][cellY]);
                    paths.add(a);
                } else if (field[cellX][cellY].getType().equals("food")) {
                    //System.out.println("found");
                    ArrayList<grid> a;
                    a = copy(paths.get(0));
                    a.add(field[cellX][cellY]);
                    a.remove(0);
                    path = a;
                    //System.out.println(path.size());
                    found = true;
                }
            }
            
            //System.out.print(border.size() + " ");
            border.remove(0);
            //System.out.println(border.size());
            paths.remove(0);
        }
        
        for(grid n : path){
            if(!n.getType().equals("food")){
                n.changeType("path");
            }
        }
        
        paths.clear();
        border.clear();
    }

    /*private void start() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!border.isEmpty() && !found) {
                            //check the surrounding

                            for (Point2D n : moves) {
                                int cellX = border.get(0).getPosX() + (int) n.getX();
                                int cellY = border.get(0).getPosY() + (int) n.getY();

                                if (cellX < 0) {
                                    cellX = field.length - 1;
                                } else if (cellX >= field.length) {
                                    cellX = 0;
                                }

                                if (cellY < 0) {
                                    cellY = field[0].length - 1;
                                } else if (cellY >= field[0].length) {
                                    cellY = 0;
                                }

                                if (field[cellX][cellY].getType().equals("empty")) {
                                    ArrayList<grid> a;
                                    a = copy(paths.get(0));
                                    a.add(field[cellX][cellY]);
                                    field[cellX][cellY].setType("border");
                                    border.add(field[cellX][cellY]);
                                    paths.add(a);
                                } else if (field[cellX][cellY].getType().equals("finish")) {
                                    ArrayList<grid> a;
                                    a = copy(paths.get(0));
                                    a.add(field[cellX][cellY]);
                                    end(a);
                                    found = true;
                                }
                            }

                            if (!border.get(0).getType().equals("start") && !found) {
                                border.get(0).setType("checked");
                            }

                            border.remove(0);
                            paths.remove(0);
                            System.out.println(System.nanoTime() - time);
                        }
                    }
                });
            }
        }, 0, 1);
    }*/
}

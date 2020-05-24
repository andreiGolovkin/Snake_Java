/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake3;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Andrei Golovkin
 */
public class game extends Scene {

    private grid[][] field = new grid[60][50];
    private Timer t = new Timer();
    private boolean up = false, left = false, right = true, down = false, foodIsPlaced = true;
    private int headX = 10, headY = 20, delay = 0, delayLim = 70, length = 1;
    private Random rand = new Random();
    private mainWin win;
    private AI ai;

    public game(double width, double height, mainWin win, String headStyle, String tailStyle, boolean ai) {
        super(new Pane(), width, height);

        this.ai = null;
        if (ai) {
            System.out.println("AI initialization...");
            this.ai = new AI(field);
        }

        init(width, height, win, headStyle, tailStyle);

        if (ai) {
            System.out.println("Start to find the way...");
            this.ai.findPath(field[headX][headY]);
        }
    }

    private void init(double width, double height, mainWin win, String headStyle, String tailStyle) {
        this.win = win;

        for (int n = 0; n < field.length; n++) {
            for (int i = 0; i < field[n].length; i++) {
                field[n][i] = new grid(1 + n * 11, 1 + i * 11, 10, 10, n, i);
                field[n][i].setHeadStyle(headStyle);
                field[n][i].setTailStyle(tailStyle);
            }
        }

        Pane root = new Pane();
        for (grid[] n : field) {
            for (grid i : n) {
                root.getChildren().add(i.getView());
            }
        }

        field[headX][headY].changeType("head");
        field[headX - 1][headY].changeType("tail");
        field[headX - 1][headY].setTimer(1);
        int foodX = rand.nextInt(field.length - 1), foodY = rand.nextInt(field[0].length - 1);
        field[foodX][foodY].changeType("food");

        getStylesheets().add(getClass().getResource("Style.css").toExternalForm());

        root.getStyleClass().add("back");

        setRoot(root);
        start();

        if (ai == null) {
            setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode() == KeyCode.W && !up && !down) {
                    up = true;
                    right = false;
                    down = false;
                    left = false;

                    delay = 90;
                } else if (ke.getCode() == KeyCode.D && !left && !right) {
                    up = false;
                    right = true;
                    down = false;
                    left = false;

                    delay = 90;
                } else if (ke.getCode() == KeyCode.S && !up && !down) {
                    up = false;
                    right = false;
                    down = true;
                    left = false;

                    delay = 90;
                } else if (ke.getCode() == KeyCode.A && !left && !right) {
                    up = false;
                    right = false;
                    down = false;
                    left = true;

                    delay = 90;
                }

                if (ke.getCode() == KeyCode.SPACE) {
                    delayLim = 20;
                }
            });
            setOnKeyReleased((KeyEvent ke) -> {
                if (ke.getCode() == KeyCode.SPACE) delayLim = 70;
            });

        } else delayLim = 5;
    }

    private void dirReq() {
        String dir = ai.getDir(field[headX][headY]);
        if (dir.equals("up") && !up && !down) {
            up = true;
            right = false;
            down = false;
            left = false;

            delay = 90;
        } else if (dir.equals("down") && !up && !down) {
            up = false;
            right = false;
            down = true;
            left = false;

            delay = 90;
        } else if (dir.equals("right") && !left && !right) {
            up = false;
            right = true;
            down = false;
            left = false;

            delay = 90;
        } else if (dir.equals("left") && !left && !right) {
            up = false;
            right = false;
            down = false;
            left = true;

            delay = 90;
        }
    }

    private void move() {
        field[headX][headY].changeType("tail");
        if (up) {
            headY--;
        }
        if (down) {
            headY++;
        }
        if (right) {
            headX++;
        }
        if (left) {
            headX--;
        }

        if (headX < 0) {
            headX = field.length - 1;
        }
        if (headX > field.length - 1) {
            headX = 0;
        }
        if (headY < 0) {
            headY = field[headX].length - 1;
        }
        if (headY > field[headX].length - 1) {
            headY = 0;
        }

        if (field[headX][headY].getType().equals("food")) {
            length++;
        }
        if (field[headX][headY].getType().equals("tail")) {
            System.out.println("game over!");
            win.setMenu();
        }
        field[headX][headY].changeType("head");
    }

    public boolean thisScene() {
        return win.getScene() == this;
    }

    private void start() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (thisScene()) {
                            if (delay >= delayLim) {
                                if (ai != null) {
                                    dirReq();
                                }
                                move();
                                foodIsPlaced = false;
                                for (grid[] n : field) {
                                    for (grid i : n) {
                                        if(i.getType().equals("path")){
                                            i.changeType("empty");
                                        }
                                        i.compare(length);
                                        if (i.getType().equals("food")) {
                                            foodIsPlaced = true;
                                        }
                                    }
                                }

                                if (!foodIsPlaced) {
                                    while (!foodIsPlaced) {
                                        int foodX = rand.nextInt(field.length - 1), foodY = rand.nextInt(field[0].length - 1);
                                        if (field[foodX][foodY].getType().equals("empty")) {
                                            field[foodX][foodY].changeType("food");
                                            foodIsPlaced = true;
                                        }
                                    }
                                }
                                
                                if (ai != null) {
                                    ai.findPath(field[headX][headY]);
                                }

                                delay = 0;
                            } else {
                                delay++;
                            }
                        }
                    }
                });
            }
        }, 0, 5);
    }

}

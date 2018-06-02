import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class SimpleGuiExample {
/*
    public void drawRandomCircles() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 10; ++i) {
            int x = rand.nextInt(400) + 1; // get integer in range 1-400
            int y = rand.nextInt(300) + 1; // get integer in range 1-300
            int r = 5 * (rand.nextInt(4) + 1); // get integer in 5,10,15,20
            d.setColor(Color.red);
            d.fillCircle(x, y, r);
        }
        gui.show(d);
    }

    public void drawRandomLines(Line arr[]) {

        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random lines Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 10; ++i) {
            // int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            // int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            // int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            // int y2 = rand.nextInt(400) + 1; // get integer in range 1-400
            // int r = 5*(rand.nextInt(4) + 1); // get integer in 5,10,15,20
            arr[i] = generateRandomLine(arr[i], d);
            d.setColor(Color.BLACK);
            this.drawLine(arr[i], d);

        }
        gui.show(d);
    }

    private Line generateRandomLine(Line l, DrawSurface d) {
        Line l1;
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(400) + 1; // get integer in range 1-400
        return l1 = new Line(x1, y1, x2, y2);

    }

    private void drawLine(Line l, DrawSurface d) {
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
        this.drawPoints(l, d);
    }

    private void drawPoints(Line l, DrawSurface d) {
        d.setColor(Color.blue);
        d.fillCircle((int)l.middle().getX(), ((int)l.middle().getY()), 3);
    }
    private void intersectionPoint(Line l1, Line l2, DrawSurface d) {
        if(this.i==true){
      //**************************************************************      
        d.setColor(Color.red);
        d.fillCircle((int)l.middle().getX(), ((int)l.middle().getY()), 3);
    }
    }
    public static void main(String[] args) {
        SimpleGuiExample example = new SimpleGuiExample();
        example.drawRandomCircles();
        Line[] arr = new Line[10];
        SimpleGuiExample example2 = new SimpleGuiExample();
        example2.drawRandomLines(arr);

    }*/
}
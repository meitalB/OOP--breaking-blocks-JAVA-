import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;
/**
 * AbstractArtDrawing.
 * The program draw random lines with intersection and middle point.
 */
public class AbstractArtDrawing {
    /**
     * The function draws randomly ten lines on the surface according to
     * two points on the surface each time.
     * The function draws on the surface all the middle points of the
     * lines in blue.
     * The function draws points intersection of the line on the surface in red.
     */
    public void drawRandomLines() {
        Line[] arr = new Line[10];
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random lines Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 10; ++i) {
            arr[i] = generateRandomLine(arr[i], d);
            d.setColor(Color.BLACK);
            this.drawLines(arr[i], d);
        }
        //Calculate the intersections
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k <= i; k++) {
                this.intersectionPoint(arr[i], arr[k], d);
            }
        }
       gui.show(d);
        }
    /**
     * The function draws randomly line on the surface.
     * @param l is the line we receive.
     * @param d is the DrawSurface we work on.
     * @return random line
     */
    private Line generateRandomLine(Line l, DrawSurface d) {
        Line l1;
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(400) + 1; // get integer in range 1-400
        l1 = new Line(x1, y1, x2, y2);
        return l1;
    }
    /**
     * The function draws lines on the surface.
     * @param l is the line we receive.
     * @param d is the DrawSurface we work on.
     */
    private void drawLines(Line l, DrawSurface d) {
       d.drawLine((int) l.start().getX(), (int) l.start().getY(),
               (int) l.end().getX(), (int) l.end().getY());
       this.drawPoints(l, d);
    }
    /**
     * The function draws points in the middle of the line on the surface.
     * @param l is the line we receive.
     * @param d is the DrawSurface we work on.
     */
    private void drawPoints(Line l, DrawSurface d) {
        d.setColor(Color.blue);
        d.fillCircle((int) l.middle().getX(), ((int) l.middle().getY()), 3);
    }
    /**
     * The function draws points in the intersection lines on the surface.
     * @param l1 is the line we receive.
     * @param l2 is the line we receive.
     * @param d is the DrawSurface we work on.
     */
    private void intersectionPoint(Line l1, Line l2, DrawSurface d) {
        if (l1.intersectionWith(l2) != null) {
            Point p1 = l1.intersectionWith(l2);
            d.setColor(Color.red);
            d.fillCircle((int) p1.getX(), ((int) p1.getY()), 3);
        }
    }
    /**
     * this function call to start function.
     * @param args variable from the user.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }

}

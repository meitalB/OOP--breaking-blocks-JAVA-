package backround;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * class ColorsParser - define color or image object and has some functions.
 */
public class ColorsParser {
    private Image image;
    private Color color;

    /**
     * Constructor - define ColorParser with no parameters.
     */
    public ColorsParser() {
        this.image = null;
        this.color = null;
    }

    /**
     * The function get a array of string and define an image object.
     * @param strImage array of string.
     * @return the method return the image.
     */
    public Image imageFromString(String[] strImage) {
        String[] parts = strImage[1].split("\\)");
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(parts[0]);
            if (is == null) {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(parts[0]);
            }
            this.image = ImageIO.read(is);
        } catch (IOException e) {
            this.image = null;
        }
        return this.image;
    }

    /**
     * The function get a array of string and define an color object.
     * @param strColor array of string.
     * @return the new color.
     */
    public java.awt.Color colorFromString(String[] strColor) {
        if (strColor[1].equals("RGB")) {
            String[] partThree = strColor[2].split("\\)");
            String[] part = partThree[0].split(",");
            this.color = new Color(Integer.parseInt(part[0]), Integer.parseInt(part[1]),
                    Integer.parseInt(part[2]));

        } else {
            String[] colorName = strColor[1].split("\\)");
            try {
                this.color = (Color) Color.class.getField(colorName[0]).get(null);
            } catch (Exception e) {
                return null;
            }
        }
        return this.color;
    }

    /**
     * The function defines the color value of the color object.
     * @param colorNew value of the color.
     */
    public void setColor(Color colorNew) {
        this.color = colorNew;
    }

    /**
     * The function defines the image value of the image object.
     *
     * @param imageNew value of the image.
     */
    public void setImage(Image imageNew) {
        this.image = imageNew;
    }

}

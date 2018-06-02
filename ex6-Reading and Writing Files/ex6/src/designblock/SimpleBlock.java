package designblock;

import backround.ColorsParser;
import object.Block;
import geometricshape.Rectangle;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;

/**
 * Class SimpleBlock - implement BlockCreator.
 * is for creating a block with the values and objects.
 */
public class SimpleBlock implements BlockCreator {
    private String symbol;
    private Integer height;
    private Integer width;
    private Integer hitPoints;
    private Color color;
    private Image image;
    private Color stroke;
    private Map<Integer, Color> colorMap;
    private Map<Integer, Image> imageMap;
    private Map<Integer, String> hitMap;

    /**
     * constructor to a SimpleBlock with null values.
     *
     * @param hitPoints define the hit points.
     * @param color     define  the block color.
     * @param image     define the block image.
     * @param stroke    define the stroke of block.
     * @param colorMap  define the map of colors.
     * @param imageMap  define the map of images.
     * @param hitMap    define the map of hits.
     */
    public SimpleBlock(Integer hitPoints,
                       Color color, Image image, Color stroke,
                       Map<Integer, Color> colorMap,
                       Map<Integer, Image> imageMap,
                       Map<Integer, String> hitMap) {
        this.symbol = symbol;
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.color = color;
        this.image = image;
        this.stroke = stroke;
        this.colorMap = colorMap;
        this.imageMap = imageMap;
        this.hitMap = hitMap;
    }

    /**
     * the function creates a blockwith the values x,y.
     *
     * @param xpos is the x position of the block
     * @param ypos is the y position of the block
     * @return the new block
     */
    @Override
    public Block create(int xpos, int ypos) {
        Block block = null;
        Rectangle rect = new Rectangle(xpos, ypos, this.width, this.height);
        if (this.hitPoints != null || this.width != null || this.height != null
                || this.symbol != null || this.stroke != null) {
            if (this.color != null || this.image != null || this.colorMap.size() > 1
                    || this.imageMap.size() > 1) {
                block = new Block(rect, this.color, this.image, this.colorMap, this.imageMap,
                        this.hitPoints, this.stroke);
            }
        }
        return block;
    }

    /**
     * the function sets the height.
     *
     * @param newHeight the number of the height.
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    /**
     * the function sets the width.
     *
     * @param newWidth the number of the width.
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    /**
     * the function sets the symbol.
     *
     * @param newSymbol the symbol.
     */
    public void setSymbol(String newSymbol) {
        this.symbol = newSymbol;
    }

    /**
     * the function returns the height.
     *
     * @return the number of the height.
     */
    public Integer getHeight() {
        return this.height;
    }

    /**
     * the function returns the width.
     *
     * @return the number of the width.
     */
    public Integer getWidth() {
        return this.width;
    }

    /**
     * the function returns the symbol.
     *
     * @return the symbol.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * the function returns the color.
     *
     * @return the color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * the function returns the image.
     *
     * @return the image.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * the function returns the stroke.
     *
     * @return the stroke.
     */
    public Color getStroke() {
        return this.stroke;
    }

    /**
     * the function returns the color map.
     *
     * @return the color map.
     */
    public Map<Integer, Color> getColorMap() {
        return this.colorMap;
    }

    /**
     * the function returns the image map.
     *
     * @return the image map.
     */
    public Map<Integer, Image> getImageMap() {
        return this.imageMap;
    }

    /**
     * the function sets the color map.
     *
     * @param place    is the place in the map.
     * @param colorNew is the colorNew.
     */
    public void setColorMap(int place, Color colorNew) {
        this.colorMap.put(place, colorNew);
    }

    /**
     * the function sets the image map.
     *
     * @param place    is the place in the map.
     * @param imageNew is the image.
     */
    public void setImageMap(int place, Image imageNew) {
        this.imageMap.put(place, imageNew);
    }

    /**
     * the function update the image map or color map.
     */
    public void updateImageColorMap() {
        for (int i = 0; i < this.hitPoints; i++) {
            if (this.hitMap.containsKey(i)) {
                String[] hitMapColorImage = this.hitMap.get(i).split("\\(");
                ColorsParser colorsParser = new ColorsParser();
                if (hitMapColorImage[0].equals("color")) {
                    Color c = null;
                    c = colorsParser.colorFromString(hitMapColorImage);
                    this.colorMap.put(i, c);
                } else if (hitMapColorImage[0].equals("image")) {
                    Image img = null;
                    img = colorsParser.imageFromString(hitMapColorImage);
                    this.imageMap.put(i, img);

                }
            }

        }
    }

}

package engine.windows;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tool {
    public static BufferedImage rotate(BufferedImage bimg, Double angle) {
        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));
        int originalWidth = bimg.getWidth();
        int originalHeight = bimg.getHeight();
        int newWidth = (int) Math.floor(originalWidth * cos + originalHeight * sin);
        int newHeight = (int) Math.floor(originalHeight * cos + originalWidth * sin);
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((newWidth - originalWidth) / 2, (newHeight - originalHeight) / 2);
        graphic.rotate(angle, originalWidth / 2, originalHeight / 2);
        graphic.drawRenderedImage(bimg, null);
        graphic.dispose();
        return trimImage(rotated);
    }

    private static BufferedImage trimImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int top = height / 2;
        int bottom = top;
        int left = width / 2 ;
        int right = left;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (image.getRGB(x, y) != 0){
                    top    = Math.min(top, y);
                    bottom = Math.max(bottom, y);
                    left   = Math.min(left, x);
                    right  = Math.max(right, x);
                }
            }
        }
        return image.getSubimage(left, top, right - left + 1, bottom - top + 1);
    }

    public static BufferedImage ScaleImage(BufferedImage originalImage, double scale) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int width = (int) (originalWidth * scale);
        int height = (int) (originalHeight * scale);
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        graphics.dispose();
        return image;
    }

    public static BufferedImage rotateCenter(BufferedImage bimg, Double angle) {
        int newSize = Math.max(bimg.getWidth(), bimg.getHeight());
        int dx = newSize - bimg.getWidth();
        int dy = newSize - bimg.getHeight();
        BufferedImage centered = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
        AffineTransform centeredTx = new AffineTransform();
        centeredTx.translate(dx / 2, dy / 2);

        Graphics2D graphic = centered.createGraphics();
        graphic.drawImage(bimg, centeredTx, null);
        graphic.dispose();

        BufferedImage rotated = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);

        AffineTransform rotatedTx = new AffineTransform();
        rotatedTx.translate(newSize / 2, newSize / 2);
        rotatedTx.rotate(angle);
        rotatedTx.translate(-newSize / 2, -newSize / 2);
        Graphics2D rG = rotated.createGraphics();
        rG.drawImage(centered, rotatedTx, null);
        rG.dispose();
        return rotated;
    }

    public static BufferedImage rotateCenterAndResize(BufferedImage bimg, Double angle) {
        return rotateByAnchor(bimg, angle, bimg.getWidth()/2, bimg.getHeight()/2);
    }

    public static BufferedImage rotateByAnchor(BufferedImage bimg, Double angle, float x, float y) {
        int newSize = Math.max(bimg.getWidth(), bimg.getHeight()) * 2;
        int dx = (int) (newSize / 2 - x);
        int dy = (int) (newSize / 2 - y);
        BufferedImage centered = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
        AffineTransform centeredTx = new AffineTransform();
        centeredTx.translate(dx, dy);

        Graphics2D graphic = centered.createGraphics();
        graphic.drawImage(bimg, centeredTx, null);
        graphic.dispose();

        BufferedImage rotated = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);

        AffineTransform rotatedTx = new AffineTransform();
        rotatedTx.translate(newSize / 2, newSize / 2);
        rotatedTx.rotate(angle);
        rotatedTx.translate(-newSize / 2, -newSize / 2);
        Graphics2D rG = rotated.createGraphics();
        rG.drawImage(centered, rotatedTx, null);
        rG.dispose();
        return rotated;
    }
}

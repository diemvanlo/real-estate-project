package data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Image360Vr {
    public static void writeToImageFile(InputStream is) throws IOException {
//        OutputStream os = new FileOutputStream(new File("src/data/image.jpg"));
//        byte[] content = new byte[1024];
//        int size = 0;
//        if (is != null) {
//            while ((size = is.read(content)) != -1) {
//                os.write(content, 0, size);
//            }
//        }
        OutputStream os = new FileOutputStream(new File("photoo.jpg"));
        byte[] content = new byte[1024];
        int size = 0;
        if (is != null) {
            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }
        }
    }

    private static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}

package com.veve.glyphreport;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.junit.Assert.assertNotNull;

public class ImageTest {

    @Test
    public void testImageMarkup() throws Exception {
        File file = new File("src/test/res/image.jpeg");
        InputStream is = new FileInputStream(file);
        BufferedImage buffOriginalImage = ImageIO.read(is);
        Graphics2D g = buffOriginalImage.createGraphics();
        g.setColor(Color.RED);
        g.fillRect(100, 200, 300, 400);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(buffOriginalImage, "jpg", os);
        File out = new File("src/test/res/page_out.jpg");
        out.createNewFile();
        FileOutputStream fos = new FileOutputStream(out);
        fos.write(os.toByteArray());
        fos.close();
        assertNotNull(os);
    }

}

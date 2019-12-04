package cn.stylefeng.guns.core.util;

import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class A {
//    public static void main(String[] args) throws IOException {
//
//        // 来源图
//        BufferedImage bi1 = ImageIO.read(new File("D:/20191204210145.jpg"));
//        // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
//        BufferedImage image = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_ARGB);
//
//        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
//
//        Graphics2D g2 = image.createGraphics();
//        image = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(),
//                Transparency.TRANSLUCENT);
//        g2 = image.createGraphics();
//
//        // 将背景设置为透明。如果注释该段代码，默认背景为白色.也可通过g2.setPaint(paint) 设置背景色
//        g2.setComposite(AlphaComposite.Clear);
//        g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
//        g2.setClip(shape);
//        // 使用 setRenderingHint 设置抗锯齿
//        g2.drawImage(bi1, 0, 0, null);
//        g2.dispose();
//
//        try {
//            // 输出图地址
//            ImageIO.write(image, "PNG", new File("D:/20191204210145xxx.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        makeRoundedCorner("D:/20191204210145.jpg","D:/2019120421014522.jpg",200,200);
    }

    public static boolean makeRoundedCorner(String srcImageFile, String outFilePath,Integer width,Integer height) {
        try {
            File file = new File(srcImageFile);
            BufferedImage image = ImageIO.read(file);
            int w = image.getWidth();
            int h = image.getHeight();
            if (width != null) {
                w = width;
            }
            if (height != null){
                h = height;
            }
            BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            output = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = output.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillRoundRect(0, 0, w, h, 720, 720);
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(image, 0, 0, w, h, null);
            g2.dispose();
            return ImageIO.write(output, "PNG", new File(outFilePath));
        } catch (IOException e) {
            e.getMessage();
        }
        return false;
    }
}

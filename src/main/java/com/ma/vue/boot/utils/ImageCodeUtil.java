package com.ma.vue.boot.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author zhangYu cretaeby 2017/9/15
 */
public class ImageCodeUtil {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 60;

    private static Random random = new Random();

    public static BufferedImage generateCaptcha(String code) {
        BufferedImage image = new BufferedImage(WIDTH + 80, HEIGHT + 60, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        drawBackground(graphics);
        char[] rands = code.toCharArray();
        drawRands(graphics, rands);
        shear(graphics, WIDTH + 80, HEIGHT + 60, graphics.getColor());
        return cutImage(image);
    }

    public static String generateCheckCode() {
        // 定义验证码的字符表
        String chars = "0123456789abcdefghjknpqrstuvxyzABCDEFGHJKNPQRSTUVXYZ";
        int length = chars.length() - 1;
        char[] rands = new char[5];
        for (int i = 0; i < 5; i++) {
            int rand = random.nextInt(length);
            rands[i] = chars.charAt(rand);
        }
        return new String(rands);

    }

    private static Color randomColor() {
        return new Color(random.nextInt(50), random.nextInt(50), random.nextInt(50));
    }

    private static void drawRands(Graphics g, char[] rands) {
//        g.setColor(new Color(255 - bgColor.getRed(), 255 - bgColor.getGreen(), 255 - bgColor.getBlue()));
        g.setColor(randomColor());
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));
        System.out.println("fonts = " + g.getFont());
        int startx = random.nextInt(20);
        int[] fontWidth = {50, 64, 78, 92, 106};
        g.drawString("" + rands[0], startx + fontWidth[0], 58);
        g.drawString("" + rands[1], startx + fontWidth[1], 58);
        g.drawString("" + rands[2], startx + fontWidth[2], 58);
        g.drawString("" + rands[3], startx + fontWidth[3], 58);
        g.drawString("" + rands[4], startx + fontWidth[4], 58);

        line(g, random.nextInt(2) + 3, g.getColor());

    }

    private static void drawBackground(Graphics g) {
        Color color = new Color(235 + random.nextInt(20), 235 + random.nextInt(20), 235 + random.nextInt(20));

        // 画背景

        g.setColor(color);

        g.fillRect(0, 0, WIDTH + 80, HEIGHT + 60);

    }

    private static void point(Graphics g, int pointNum) {
        for (int i = 0; i < pointNum; i++)

        {
            int x = random.nextInt(WIDTH);

            int y = random.nextInt(HEIGHT);

            int red = random.nextInt(150);

            int green = random.nextInt(150);

            int blue = random.nextInt(150);

            g.setColor(new Color(red, green, blue));

            g.drawOval(x, y, 1, 0);
        }
    }

    private static void line(Graphics g, int lineCount, Color color) {
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(WIDTH);
            int ys = random.nextInt(HEIGHT);
            int xe = xs + random.nextInt(WIDTH) + 40;
            int ye = ys + random.nextInt(HEIGHT) + 30;
            g.setColor(color);
            g.drawLine(xs, ys, xe, ye);
        }
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        int frames = 2;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (3.1415926D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            g.setColor(color);
            g.drawLine((int) d, i, 0, i);
            g.drawLine((int) d + w1, i, w1, i);
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(5) + 20;

        int frames = random.nextInt(80) + 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (3.1415926D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            g.setColor(color);
            g.drawLine(i, (int) d, i, 0);
            g.drawLine(i, (int) d + h1, i, h1);

        }
    }

    private static BufferedImage cutImage(BufferedImage image) {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, 0, 0, WIDTH, HEIGHT, 40, 30, 160, 70, null);
        return bufferedImage;
    }
}

package org.wuheng.framework.lucene5.crawler;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-7-16
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class ImageMarkText {
    protected Logger logger=Logger.getLogger(ImageMarkText.class);
    /**
     * 图片添加水印
     * @param srcImgPath 需要添加水印的图片的路径
     * @param outImgPath 添加水印后图片输出路径
     * @param markContentColor 水印文字的颜色
     * @param markContent 水印的文字
     */
    public void mark(String srcImgPath, String outImgPath, Color markContentColor, String markContent) {
        try {
            // 读取原图片信息
            File srcImgFile=new File(srcImgPath);
            Image srcImg=ImageIO.read(srcImgFile);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);

            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            Font font = new Font("微软雅黑", Font.PLAIN, 20);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色

            g.setFont(font);
            int x = srcImgWidth - getWatermarkLength(markContent, g) - 3;
            int y = srcImgHeight - 3;

            g.drawString(markContent, x, y);
            g.dispose();

            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            logger.error("图片路径不存在或者图片不存在");
            e.printStackTrace();
        }
    }

    /**
     * 获取水印文字总长度
     * @param waterMarkContent 水印的文字
     * @param g
     * @return 水印文字总长度
     */
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void main(String[] args) {
        // 原图位置, 输出图片位置, 水印文字颜色, 水印文字
        new ImageMarkText().mark("e:/images/map.jpg", "e:/images/afterWatermark.jpg", Color.pink, "水印效果测试");
    }
}
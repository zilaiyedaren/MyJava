package org.myself.web.spring.utils;

import org.springframework.web.servlet.view.AbstractView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-7-5
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class ImageView extends AbstractView {
    private BufferedImage image;

    public ImageView(BufferedImage image) {
        this.image = image;
    }

    /**
     *生成验证码图片
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control","no-store.no-cache,must-revalidate");
        response.addHeader("Cache-control","post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");

        response.setContentType("image/jpeg");

        ServletOutputStream outputStream=response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);

        try {
            outputStream.flush();
        } finally {
            outputStream.close();
        }
    }
}

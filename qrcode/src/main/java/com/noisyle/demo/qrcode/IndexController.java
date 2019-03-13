package com.noisyle.demo.qrcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Controller
public class IndexController {
    final static private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public void index(HttpServletResponse res,
            @RequestParam(required = false, defaultValue = "oFmnD5M92Sz52pKQb88FyJXPmTBQ") String text) {
        try {
            logger.debug("Create image with watermark: {}", text);
            res.setContentType("image/png");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Cache-Control", "no-cache");
            ImageIO.write(createWatermark(text), "png", res.getOutputStream());
        } catch (IOException e) {
            logger.error("Create image failed!", e);
        }
    }

    @RequestMapping("/barcode")
    public void barcode(HttpServletResponse res,
            @RequestParam(required = false, defaultValue = "oFmnD5M92Sz52pKQb88FyJXPmTBQ") String text) {
        try {
            res.setContentType("image/png");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Cache-Control", "no-cache");
            ImageIO.write(createBarcode(text, 50, null), "png", res.getOutputStream());
        } catch (WriterException e) {
            logger.error("Create image failed!", e);
        } catch (IOException e) {
            logger.error("Create image failed!", e);
        }
    }

    @RequestMapping("/source")
    public void source(HttpServletResponse res) {
        try {
            res.setContentType("image/png");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Cache-Control", "no-cache");
            ImageIO.write(ImageIO.read(new ClassPathResource("fin_bg.png").getInputStream()), "png",
                    res.getOutputStream());
        } catch (IOException e) {
            logger.error("Create image failed!", e);
        }
    }

    @RequestMapping("/marked")
    public void marked(HttpServletResponse res,
            @RequestParam(required = false, defaultValue = "oFmnD5M92Sz52pKQb88FyJXPmTBQ") String text) {
        try {
            String background = "fin_bg.png";
            res.setContentType("image/png");
            res.setHeader("Pragma", "no-cache");
            // BufferedImage image = createBarcode(text, 5, new
            // MatrixToImageConfig(0x08000000, 0x0800008F));
            BufferedImage image = createWatermark(text);
            ImageIO.write(applyWatermark(background, image), "png", res.getOutputStream());
        } catch (IOException e) {
            logger.error("Create image failed!", e);
        }
    }

    private BufferedImage createBarcode(String text, int height, MatrixToImageConfig config) throws WriterException {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, 105);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, codeWidth, height, null);
        if (config == null) {
            config = new MatrixToImageConfig();
        }
        return MatrixToImageWriter.toBufferedImage(bitMatrix, config);
    }

    private BufferedImage createWatermark(String text) {
        BufferedImage watermark = new BufferedImage(text.length() * 10 + 40, 40, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = watermark.createGraphics();
        Color color = new Color(255, 255, 255, 16);
        graphics.setColor(color);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        graphics.setFont(font);
        graphics.drawString(text, 5, 30);
        graphics.dispose();

        return watermark;
    }

    private BufferedImage applyWatermark(String background, BufferedImage mark) throws IOException {
        BufferedImage bg = ImageIO.read(new ClassPathResource(background).getInputStream());

        Graphics2D graphics = (Graphics2D) bg.getGraphics();

        int canvasHeight = bg.getHeight();
        int canvasWidth = bg.getWidth();
        int markHeight = mark.getHeight();
        int markWidth = mark.getWidth();
        logger.info("markWidth: {}, markHeight: {}", markWidth, markHeight);
        int margin_x = 0;
        int margin_y = 0;
        int interval = 0;
        int radius = Math.max(canvasHeight, canvasWidth);

        graphics.rotate(Math.toRadians(45));

        for (int i = 0; i * markHeight <= radius * 2; i++) {
            interval = new Random(new Date().getTime() + i).nextInt(markWidth);
            logger.info("interval: {}", interval);
            for (int j = 0; j * markWidth <= radius * 2; j++) {
                int x = -radius + j * (markWidth + margin_x) + interval;
                int y = -radius + i * (markHeight + margin_y);
                graphics.drawImage(mark, x, y, mark.getWidth(), mark.getHeight(), null);
            }
        }

        graphics.dispose();

        return bg;
    }

}

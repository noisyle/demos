package com.noisyle.demo.font;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageController {
    final static private Logger logger = LoggerFactory.getLogger(ImageController.class);
    
    /**
     * 从配置文件注入标准字体文件路径
     */
    @Value("${poster.regular-font}")
    private String regularFont = "";
    /**
     * 从配置文件注入粗体字体文件路径
     */
    @Value("${poster.bold-font}")
    private String boldFont = "";

    @RequestMapping("/image")
    public void createPoster(HttpServletResponse res) {
        String illust = "image/illust.png";
        String article = "无论过去发生过什么，你要相信，最好的尚未到来。即使生活给你一千个伤心的理由。你也要找一千零一个开心的借口，不管这世界多么残酷，都要保持一颗释然的心，用你的笑容冰释所有冷漠，睡前原谅所有的人与事情。";
        String nickname = "冷风吹过川普的头发";

        try {
            // 调用工具类生成海报
            BufferedImage image = new PosterBuilder()
                .setWidth(670)
                .setNickname(nickname)
//                .setRegularFontPath(regularFont)
//                .setBoldFontPath(boldFont)
                .illust().path(illust).and()
                .article().text(article).and()
                .buildImage();
            
            res.setContentType("image/png");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Cache-Control", "no-cache");
            ImageIO.write(image, "png", res.getOutputStream());
        } catch (Exception e) {
            logger.error("Create image failed!", e);
        }
    }
}

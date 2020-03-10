package com.noisyle.demo.font;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PosterController {
    final static private Logger logger = LoggerFactory.getLogger(PosterController.class);
    
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

    /**
     * 演示调用工具类生成海报图片
     * @param res 用来输出二进制流
     */
    @RequestMapping("/image")
    public void getPosterImage(HttpServletResponse res) {
        String illust = "image/illust.png";
        String article = "无论过去发生过什么，你要相信，最好的尚未到来。即使生活给你一千个伤心的理由。你也要找一千零一个开心的借口，不管这世界多么残酷，都要保持一颗释然的心，用你的笑容冰释所有冷漠，睡前原谅所有的人与事情。";
        String nickname = "冷风吹过川普的头发";

        try {
            // 调用工具类生成海报图片
            BufferedImage image = new PosterBuilder()
                    // 普通字体
//                    .setRegularFontPath(regularFont)
                    // 粗体字体
//                    .setBoldFontPath(boldFont)
                    // 设置海报宽度
                    .setWidth(670)
                    // 设置插图
                    .illust().path(illust).and()
                    // 设置文本
                    .article().text(article).and()
                    // 用户昵称
                    .setNickname(nickname)
                    .buildImage();
            
            res.setContentType("image/png");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Cache-Control", "no-cache");
            ImageIO.write(image, "png", res.getOutputStream());
        } catch (Exception e) {
            logger.error("Create image failed!", e);
        }
    }


    /**
     * 演示调用工具类生成海报Base64编码字符串
     */
    @ResponseBody
    @RequestMapping("/base64")
    public Object getPosterBase64() {
        String illust = "image/illust.png";
        String article = "无论过去发生过什么，你要相信，最好的尚未到来。即使生活给你一千个伤心的理由。你也要找一千零一个开心的借口，不管这世界多么残酷，都要保持一颗释然的心，用你的笑容冰释所有冷漠，睡前原谅所有的人与事情。";
        String nickname = "冷风吹过川普的头发";

        try {
            // 调用工具类生成海报Base64编码字符串
            String base64 = new PosterBuilder()
                    // 普通字体
//                    .setRegularFontPath(regularFont)
                    // 粗体字体
//                    .setBoldFontPath(boldFont)
                    // 设置海报宽度
                    .setWidth(670)
                    // 设置插图
                    .illust().path(illust).and()
                    // 设置文本
                    .article().text(article).and()
                    // 用户昵称
                    .setNickname(nickname)
                    .buildBase64();
            
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .cacheControl(CacheControl.noCache())
                    .body(base64);
            
        } catch (Exception e) {
            logger.error("Create image failed!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

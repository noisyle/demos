package com.noisyle.demo.font;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

/**
 * 海报构造器工具类
 */
public class PosterBuilder {
    final private Logger logger = LoggerFactory.getLogger(getClass());
    
    private int width = 670;
    private int height = 0;
    private String nickname = "";
    

    private Font regularFont = new Font(Font.SERIF, Font.PLAIN, 32);
    private Font boldFont = new Font(Font.SERIF, Font.PLAIN, 32);
    private Illust illust = null;
    private Article article = null;
    
    private PathMatchingResourcePatternResolver resolover = new PathMatchingResourcePatternResolver();
    
    /**
     * 设置海报总宽度，单位像素
     * @param width 总宽度，单位像素，默认670
     * @return  PosterBuilder
     */
    public PosterBuilder setWidth(int width) {
        this.width = width;
        return this;
    }
    
    protected int getWidth() {
        return this.width;
    }
    
    protected int getHeight() {
        return this.height;
    }
    
    protected String getNickname() {
        return this.nickname;
    }
    
    /**
     * 设置用户昵称
     * @param nickname 用户昵称
     * @return PosterBuilder
     */
    public PosterBuilder setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    /**
     * 设置标准字体
     * @param regularfont Font对象
     */
    public PosterBuilder setRegularFont(Font font) {
        this.regularFont = font;
        return this;
    }

    /**
     * 设置标准字体
     * @param String 字体文件路径，支持classpath:前缀
     * @throws IOException 
     * @throws FontFormatException 
     */
    public PosterBuilder setRegularFontPath(String path) throws FontFormatException, IOException {
        this.regularFont = Font.createFont(Font.TRUETYPE_FONT, resolover.getResource(path).getInputStream());
        return this;
    }

    public Font getRegularFont() {
        logger.debug("使用标准字体: {}", regularFont.getFontName());
        return regularFont;
    }

    /**
     * 设置加粗字体
     * @param boldfont Font对象
     */
    public PosterBuilder setBoldFont(Font font) {
        this.boldFont = font;
        return this;
    }

    /**
     * 设置加粗字体
     * @param String 字体文件路径，支持classpath:前缀
     * @throws IOException 
     * @throws FontFormatException 
     */
    public PosterBuilder setBoldFontPath(String path) throws FontFormatException, IOException {
        this.boldFont = Font.createFont(Font.TRUETYPE_FONT, resolover.getResource(path).getInputStream());
        return this;
    }

    public Font getBoldFont() {
        if(boldFont != null) {
            logger.debug("使用加粗字体: {}", boldFont.getFontName());
            return boldFont;
        } else {
            logger.debug("未设置加粗字体，使用标准字体: {}", regularFont.getFontName());
            return regularFont;
        }
    }

    /**
     * 开始设置插图
     * @return IllustBuilder 插图构造器
     */
    public IllustBuilder illust() {
        return new IllustBuilder(this);
    }

    /**
     * 开始设置文本
     * @return ArticleBuilder 文本构造器
     */
    public ArticleBuilder article() {
        return new ArticleBuilder(this);
    }
    
    /**
     * 构造海报
     * @return BufferedImage 海报图片
     * @throws IOException
     */
    public BufferedImage buildImage() throws IOException {
        // 初始高度
        this.height = 112 + 359;
        
        // 构造插图
        if(this.illust != null) {
            this.illust.build(this);
            this.height += this.illust.getHeight();
        }
        
        // 构造文本
        if(this.article != null) {
            this.article.build(this);
            this.height += this.article.getHeight();
        }

        // 插图和文本同时存在，额外增加33像素的高度
        if(this.illust != null && this.article != null) {
            this.height += 33;
        }

        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        // 消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 消除画图锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, this.width, this.height);

        // 绘制插图
        if(this.illust != null) {
            graphics.drawImage(this.illust.getImage(), 72, 112, null);
        }
        
        // 绘制文章
        if(this.article != null) {
            graphics.drawImage(this.article.getImage(), 72, this.illust != null ? (112 + this.illust.getHeight() + 33) : 112, null);
        }
        
        // 构造并绘制圆角边框和双引号
        graphics.drawImage(new Border().build(this), 0, 0, null);
        
        // 构造并绘制昵称和日期
        graphics.drawImage(new Nickname().build(this), 0, 0, null);
        
        // 构造并绘制小程序码和slogan
        graphics.drawImage(new Qrcode().build(this), 0, 0, null);

        graphics.dispose();
        return image;
    }
    
    /**
     * 构造海报
     * @return String 海报图片Base64编码
     * @throws IOException
     */
    public String buildBase64() throws IOException {
        BufferedImage image = buildImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        String imageString = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        return imageString;
    }

    static public class IllustBuilder {
        private PosterBuilder parent;
        private Illust illust;
        public IllustBuilder(PosterBuilder parent) {
            this.parent = parent;
            this.illust = this.parent.illust == null ? new Illust() : this.parent.illust;
            parent.illust = this.illust;
        }
        public PosterBuilder and() {
            return this.parent;
        }
        public IllustBuilder path(String path) {
            this.illust.setPath(path);
            return this;
        }
    }
    
    static public class ArticleBuilder {
        private PosterBuilder parent;
        private Article article;
        public ArticleBuilder(PosterBuilder parent) {
            this.parent = parent;
            this.article = this.parent.article == null ? new Article() : this.parent.article;
            parent.article = this.article;
        }
        public PosterBuilder and() {
            return this.parent;
        }
        public ArticleBuilder text(String text) {
            this.article.setText(text);
            return this;
        }
    }
}

/**
 * 海报元素抽象类
 */
abstract class PosterElement {
    private BufferedImage image;
    private int width;
    private int height;
    
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * 将当前元素构造为BufferedImage
     * @param parent PosterBuilder
     * @return BufferedImage
     * @throws IOException
     */
    public BufferedImage build(PosterBuilder parent) throws IOException {
        this.internalBuild(parent);
        return this.image;
    }

    /**
     * 将当前元素构造为BufferedImage
     * @param parent PosterBuilder
     * @throws IOException 
     */
    abstract protected void internalBuild(PosterBuilder parent) throws IOException;
}

/**
 * 插图
 */
class Illust extends PosterElement {
    final private Logger logger = LoggerFactory.getLogger(getClass());
    private String path;
    
    public void setPath(String path) {
        logger.debug("使用插图: {}", path);
        this.path = path;
    }

    @Override
    public void internalBuild(PosterBuilder parent) throws IOException {
        if(StringUtils.isEmpty(this.path)) {
            throw new IllegalStateException("插图路径为空");
        }
        
        // 插图宽度为海报总宽度减去两侧各72像素的边距
        this.setWidth(parent.getWidth() - 144);
        
        // 加载插图，为防止尺寸不符，做一次保持横纵比例的resize
        this.setImage(Utils.resize(ImageIO.read(new ClassPathResource(path).getInputStream()), this.getWidth()));
        
        this.setHeight(this.getImage().getHeight());
    }
}

/**
 * 文本
 */
class Article extends PosterElement {
    final private Logger logger = LoggerFactory.getLogger(getClass());
    private String text;
    
    public void setText(String text) {
        logger.debug("使用文本: {}", text);
        this.text = text;
    }

    @Override
    public void internalBuild(PosterBuilder parent) throws IOException {
        if(StringUtils.isEmpty(this.text)) {
            throw new IllegalStateException("文本内容为空");
        }
        
        // 插图宽度为海报总宽度减去两侧各72像素的边距
        this.setWidth(parent.getWidth() - 144);
        
        // 暂时将图片高度设为9999，待计算完折行后再根据实际高度进行裁切
        this.setHeight(9999);
        
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        // 消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 消除画图锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Font font = parent.getRegularFont().deriveFont(Font.PLAIN, 32);
        graphics.setFont(font);
        Color color = new Color(51, 51, 51);
        graphics.setColor(color);
        
        // 根据宽度将文本折行，每行的字符串保存在list里
        List<String> lines = new LinkedList<String>();
        int idx = 0;
        while (idx < this.text.length()) {
            int count = 0;
            int row_width = 0;
            while (true) {
                count++;
                row_width = graphics.getFontMetrics().stringWidth(this.text.substring(idx, idx + count));
                if (row_width > this.getWidth()) {
                    // 超出宽度，count-1并开始新一行
                    count--;
                    lines.add(this.text.substring(idx, idx + count));
                    break;
                }
                if (idx + count >= this.text.length()) {
                    // text遍历完成
                    lines.add(this.text.substring(idx, idx + count));
                    break;
                }
            }
            idx += count;
        }
        // 遍历list，绘制每一行文本
        for (int i = 0; i < lines.size(); i++) {
            graphics.drawString(lines.get(i), 6, 32 + 52 * i);
        }
        graphics.dispose();
        
        //根据折行重新计算实际高度
        this.setHeight(36 + 52 * (lines.size() - 1));

        this.setImage(image.getSubimage(0, 0, this.getWidth(), this.getHeight()));
    }
}

/**
 * 框线
 */
class Border extends PosterElement {
    @Override
    public void internalBuild(PosterBuilder parent) throws IOException {
        this.setWidth(parent.getWidth());
        this.setHeight(parent.getHeight());
        
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        // 消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 消除画图锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color color = new Color(229, 229, 229);
        graphics.setColor(color);
        
        // 线宽4像素
        graphics.setStroke(new BasicStroke(4));
        
        // 左右边距40像素，上边距54像素，下边距232像素，带45像素圆角
        graphics.drawRoundRect(40, 54, this.getWidth() - 40 * 2, this.getHeight() - 232, 45, 45);
        
        // 去掉左上和右下的圆角，以便绘制双引号
        graphics.setColor(Color.WHITE);
        graphics.drawRect(40, 40, 63, 48);
        graphics.fillRect(40, 40, 63, 48);
        graphics.drawRect(this.getWidth() - 103, this.getHeight() - 205, 63, 48);
        graphics.fillRect(this.getWidth() - 103, this.getHeight() - 205, 63, 48);
        
        // 绘制左上和右下的双引号
        BufferedImage quotemark_left = Utils.resize(ImageIO.read(new ClassPathResource("image/quotemark_left.png").getInputStream()), 59);
        BufferedImage quotemark_right = Utils.resize(ImageIO.read(new ClassPathResource("image/quotemark_right.png").getInputStream()), 59);
        graphics.drawImage(quotemark_left, 40, 40, null);
        graphics.drawImage(quotemark_right, this.getWidth() - 97, this.getHeight() - 208, null);

        graphics.dispose();
        this.setImage(image);
    }
}

/**
 * 昵称和日期
 */
class Nickname extends PosterElement {
    @Override
    public void internalBuild(PosterBuilder parent) throws IOException {
        this.setWidth(parent.getWidth());
        this.setHeight(parent.getHeight());
        
        BufferedImage image = new BufferedImage(parent.getWidth(), parent.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        // 消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 消除画图锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Font nickname_font = parent.getBoldFont().deriveFont(Font.PLAIN, 28);
        Font date_font = parent.getRegularFont().deriveFont(Font.PLAIN, 24);

        graphics.setFont(nickname_font);
        Color nickname_color = new Color(51, 51, 51);
        graphics.setColor(nickname_color);
        int nickname_width = graphics.getFontMetrics().stringWidth(parent.getNickname());
        graphics.drawString(parent.getNickname(), parent.getWidth() - nickname_width - 72, parent.getHeight() - 274);

        graphics.setFont(date_font);
        Color nickname_date = new Color(153, 153, 153);
        graphics.setColor(nickname_date);
        String date = new SimpleDateFormat("M 月 d 日").format(new Date());
        int date_width = graphics.getFontMetrics().stringWidth(date);
        graphics.drawString(date, parent.getWidth() - date_width - 72, parent.getHeight() - 235);

        graphics.dispose();
        
        this.setImage(image);
    }
}

/**
 * 小程序码和slogan
 */
class Qrcode extends PosterElement {
    // 小程序码图片
    final private String QRCODE_PATH = "image/qrcode.png";
    // slogan图片
    final private String SLOGAN_PATH = "image/qrcode_slogan.png";
            
    @Override
    public void internalBuild(PosterBuilder parent) throws IOException {
        this.setWidth(parent.getWidth());
        this.setHeight(parent.getHeight());
        
        BufferedImage image = new BufferedImage(parent.getWidth(), parent.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        // 消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 消除画图锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        BufferedImage qrcode = Utils.resize(ImageIO.read(new ClassPathResource(QRCODE_PATH).getInputStream()), 100);
        graphics.drawImage(qrcode, 40, parent.getHeight() - 40 - 100, null);
        BufferedImage slogan = Utils.resize(ImageIO.read(new ClassPathResource(SLOGAN_PATH).getInputStream()), 331);
        graphics.drawImage(slogan, 165, parent.getHeight() - 94 - 24, null);

        graphics.dispose();
        
        this.setImage(image);
    }
}

class Utils {
    /**
     * 根据指定的宽度，保持横纵比例地调整BufferedImage大小
     * @param source BufferedImage 原始image
     * @param targetW int 目标宽
     * @return BufferedImage 返回新image
     */
    protected static BufferedImage resize(BufferedImage source, int targetW) {
        int type = source.getType();
        BufferedImage target = null;
        double ratio = (double) targetW / source.getWidth();
        int targetH = (int) Math.round(source.getHeight() * ratio);
        if (type == BufferedImage.TYPE_CUSTOM) {
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }
        Graphics2D g = target.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(ratio, ratio));
        g.dispose();
        return target;
    }
}

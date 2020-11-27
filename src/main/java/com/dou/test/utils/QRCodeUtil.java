package com.dou.test.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author dsp
 * @date 2019-09-10
 */
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 默认是黑色
    private static final int QR_COLOR = 0xFF000000;
    // 背景颜色
    private static final int BG_WHITE = 0xFFFFFFFF;
    // 二维码尺寸
    private static final int QR_CODE_WIDTH = 400;
    private static final int QR_CODE_HEIGHT = 400;
    // LOGO宽度
    private static final int LOGO_WIDTH = 60;
    // LOGO高度
    private static final int LOGO_HEIGHT = 60;


    public static class BufferedImageLuminanceSource extends LuminanceSource {

        private final BufferedImage image;
        private final int left;
        private final int top;

        public BufferedImageLuminanceSource(BufferedImage image) {
            this(image, 0, 0, image.getWidth(), image.getHeight());
        }

        public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width, int height) {
            super(width, height);

            int sourceWidth = image.getWidth();
            int sourceHeight = image.getHeight();
            if (left + width > sourceWidth || top + height > sourceHeight) {
                throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
            }

            for (int y = top; y < top + height; y++) {
                for (int x = left; x < left + width; x++) {
                    if ((image.getRGB(x, y) & 0xFF000000) == 0) {
                        image.setRGB(x, y, 0xFFFFFFFF); // = white
                    }
                }
            }

            this.image = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);
            this.image.getGraphics().drawImage(image, 0, 0, null);
            this.left = left;
            this.top = top;
        }

        public byte[] getRow(int y, byte[] row) {
            if (y < 0 || y >= getHeight()) {
                throw new IllegalArgumentException("Requested row is outside the image: " + y);
            }
            int width = getWidth();
            if (row == null || row.length < width) {
                row = new byte[width];
            }
            image.getRaster().getDataElements(left, top + y, width, 1, row);
            return row;
        }

        public byte[] getMatrix() {
            int width = getWidth();
            int height = getHeight();
            int area = width * height;
            byte[] matrix = new byte[area];
            image.getRaster().getDataElements(left, top, width, height, matrix);
            return matrix;
        }

        public boolean isCropSupported() {
            return true;
        }

        public LuminanceSource crop(int left, int top, int width, int height) {
            return new QRCodeUtil.BufferedImageLuminanceSource(image, this.left + left, this.top + top, width, height);
        }

        public boolean isRotateSupported() {
            return true;
        }

        public LuminanceSource rotateCounterClockwise() {
            int sourceWidth = image.getWidth();
            int sourceHeight = image.getHeight();
            AffineTransform transform = new AffineTransform(0.0, -1.0, 1.0, 0.0, 0.0, sourceWidth);
            BufferedImage rotatedImage = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g = rotatedImage.createGraphics();
            g.drawImage(image, transform, null);
            g.dispose();
            int width = getWidth();
            return new QRCodeUtil.BufferedImageLuminanceSource(rotatedImage, top, sourceWidth - (left + width), getHeight(), width);
        }

    }

    // 用于设置QR二维码参数
    private static Map<EncodeHintType, Object> encodeHints = new HashMap<EncodeHintType, Object>() {
        {
            // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            // 设置编码方式
            put(EncodeHintType.CHARACTER_SET, CHARSET);
            put(EncodeHintType.MARGIN, 1);
        }
    };

    private static Map<DecodeHintType, Object> decodeHints = new HashMap<DecodeHintType, Object>() {
        {
            put(DecodeHintType.CHARACTER_SET, CHARSET);
        }
    };

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_WIDTH, encodeHints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (needCompress) {
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QR_CODE_WIDTH - width) / 2;
        int y = (QR_CODE_WIDTH - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    public static BufferedImage encode(String content, String imgPath, boolean needCompress) throws Exception {
        return QRCodeUtil.createImage(content, imgPath, needCompress);
    }

    public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    public static void encode(String content, String imgPath, String destPath) throws Exception {
        QRCodeUtil.encode(content, imgPath, destPath, false);
    }

    public static void encode(boolean needCompress, String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, needCompress);
    }

    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, false);
    }

    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new QRCodeUtil.BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        return new MultiFormatReader().decode(bitmap, decodeHints).getText();
    }

    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

    /*
        ==============================================================================
     */

    public static String GenerateGUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase().replaceAll("-", "");
    }

    public static StringBuilder getMD5(String dataStr, String slat) {
        byte[] secretBytes = null;
        dataStr = dataStr + slat;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(dataStr.getBytes(StandardCharsets.UTF_8));
            secretBytes = md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert secretBytes != null;
        //将加密后的数据转换为16进制数字
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code.insert(0, "0");
        }
        return md5code;
    }

    public static void buildSmartBoxCode(String content, String note, String imageType, OutputStream outputStream) {
        try {
            BufferedImage image = qrCodeWithNoteImage(content, note);
            ImageIO.write(image, imageType, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void buildSmartBoxCode(String content, String note, String imageType, ImageOutputStream imageOutputStream) {
        try {
            BufferedImage image = qrCodeWithNoteImage(content, note);
            ImageIO.write(image, imageType, imageOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage qrCodeWithNoteImage(String content, String note) {
        BufferedImage qrCode = qrCodeImage(content, null);
        BufferedImage noteImage = noteImage(note);
        BufferedImage image = new BufferedImage(QR_CODE_WIDTH * 2, QR_CODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D imageGraphics = image.createGraphics();
        imageGraphics.drawImage(qrCode, 0, 0, QR_CODE_WIDTH, QR_CODE_HEIGHT, null);
        imageGraphics.drawImage(noteImage, QR_CODE_WIDTH, 0, QR_CODE_WIDTH, QR_CODE_HEIGHT, null);
        imageGraphics.dispose();
        image.flush();
        return image;
    }

    private static BufferedImage qrCodeImage(String content, BufferedImage logo) {
        BufferedImage qrImage = new BufferedImage(QR_CODE_WIDTH, QR_CODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
        BitMatrix bm;
        try {
            bm = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT, encodeHints);
            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < QR_CODE_WIDTH; x++) {
                for (int y = 0; y < QR_CODE_HEIGHT; y++) {
                    qrImage.setRGB(x, y, bm.get(x, y) ? QR_COLOR : BG_WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

        if (null != logo) {
            Graphics2D logoGraphics2D = qrImage.createGraphics();
            logoGraphics2D.drawImage(
                    logo, QR_CODE_WIDTH * 2 / 5, QR_CODE_HEIGHT * 2 / 5,
                    QR_CODE_WIDTH * 2 / 10, QR_CODE_HEIGHT * 2 / 10, null);
            logoGraphics2D.dispose();
            logo.flush();
        }
        qrImage.flush();
        return qrImage;
    }

    private static BufferedImage noteImage(String note) {
        BufferedImage noteImage = new BufferedImage(QR_CODE_WIDTH, QR_CODE_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        if (null == note || "".equals(note)) {
            return noteImage;
        }
        Graphics2D noteImageGraphics = noteImage.createGraphics();
        noteImageGraphics.setBackground(Color.WHITE);
        noteImageGraphics.clearRect(0, 0, QR_CODE_WIDTH, QR_CODE_HEIGHT);
        // noteImageGraphics.setPaint(Color.BLUE);
        noteImageGraphics.setColor(Color.BLACK);
        noteImageGraphics.setFont(new Font("微软雅黑", Font.BOLD, 30));
        int gap = 5;
        noteImageGraphics = autoNote(noteImageGraphics, note, QR_CODE_WIDTH, gap, 0);
        noteImageGraphics.dispose();
        noteImage.flush();
        return noteImage;
    }

    private static Graphics2D autoNote(Graphics2D graphics2D, String note, int imageWidth, int gap, int n) {
        int strWidth = graphics2D.getFontMetrics().stringWidth(note);
        int strHeight = graphics2D.getFontMetrics().getHeight();
        n = n + 1;
        int y = strHeight * n + gap * (n - 1);
        int x = imageWidth / graphics2D.getFont().getSize() - 1;
        int indentation = graphics2D.getFont().getSize() / 2;
        if (strWidth > (imageWidth - 1)) {
            String note1 = note.substring(0, x);
            String note2 = note.substring(x);
            graphics2D.drawString(note1, indentation, y);
            return autoNote(graphics2D, note2, imageWidth, gap, n);
        } else {
            graphics2D.drawString(note, indentation, y);
            return graphics2D;
        }
    }


    @Deprecated
    public static void main(String[] args) throws Exception {
        // System.out.println(getMD5());

        // System.out.println(QRCodeUtil.GenerateGUID());

        // SmartCode();

        // File qrCodeFile = new File("C:\\Users\\Administrator\\Desktop\\note.png");//生成后图片的输出地址
        // ImageIO.write(new QRCodeUtil().buildNoteImage(
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈" +
        //         "本码检完之前可多次使用哈哈"), "png", qrCodeFile);

        // FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\note1.png");
        // QRCodeUtil.buildSmartBoxCode("qwert","12312312313123131","png",fileOutputStream);

        // String text = DigestUtils.md5DigestAsHex(("12345678"+"18513107567").getBytes());
        String text = DigestUtils.md5DigestAsHex(("12345678" + "18888888888").getBytes());
        System.out.println(text);
    }

    @Deprecated
    private static void SmartCode() throws Exception {
        // 存放在二维码中的内容
        String text = DigestUtils.md5DigestAsHex("18513107567".getBytes());
        // 嵌入二维码的图片路径
        String imgPath = "C:\\Users\\Administrator\\Desktop\\timg.jpg";
        // 生成的二维码的路径及名称
        String destPath = "C:\\Users\\Administrator\\Desktop\\test.jpg";
        //生成二维码
        // QRCodeUtil.encode(true, text, destPath);
        QRCodeUtil.encode("2482cf62feb6503c6e13659a563b8917", imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }

}

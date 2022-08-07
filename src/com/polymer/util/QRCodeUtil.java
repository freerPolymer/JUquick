package com.polymer.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.polymer.ibase.IConstant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类
 *  二维码生成|解析
 * @author polymer
 * @since 2022.08.07
 */
public final class QRCodeUtil {

	/**
	 * 生成简单二维码
	 *  默认 png 图片格式
	 * @param content 内容
	 * @param side  正方形边长
	 *              side取值范围： 200<=side<=1500
	 * @param pathFile 输出文件
	 *                 例如：D://xx/15268.png
	 * @return
	 * @throws WriterException
	 */
	public static String QREncode( String content,int side,File pathFile) {
		if(content==null||content.length()<1)return null;
		try {
			if(side<200&&side>1500)side=200;
			BitMatrix bitMatrix=defaultMatrix(content,side,side);
			MatrixToImageWriter.writeToPath(bitMatrix, "png", pathFile.toPath());// 输出原图片
		} catch (Exception e){}
		return pathFile.isFile()&& pathFile.canRead()?pathFile.getAbsolutePath():null;
	}

	/**
	 * 生成带LOGO图片的二维码
	 *  问题：生成二维码正常,生成带logo的二维码logo变成黑白
	 *  原因：MatrixToImageConfig默认黑白，需要设置BLACK、WHITE
	 * @param content
	 * @param side
	 *              side取值范围： 200<=side<=1500
	 * @param logoFile
	 * @param pathFile
	 * @return
	 * @throws Exception
	 */
	public static String QREncode( String content ,int side,File logoFile,  File pathFile){
		if(content==null||content.length()<1)return null;
		if(!(logoFile.exists()&&logoFile.canRead()))return null;
		BitMatrix bitMatrix=null;
		String result=null;
		try {
			if(side<200&&side>1500)side=200;
			bitMatrix=defaultMatrix(content,side,side);
			//------- 生成logo的黑白色二维码
			int onColor=0xFF000001;//前景色
			int offColor=0xFFFFFFFF;//背景色
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(onColor,offColor );
			BufferedImage bufferedImage = LogoMatrix(MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig), logoFile);
			result=ImageIO.write(bufferedImage, "png", pathFile)?pathFile.getAbsolutePath():null;//输出带logo图片
		} catch (Exception e) {}
		return result;
	}

	/**
	 * 扫描二维码
	 *  bug : 如果出现二维码logo本身就是一张二维码图片，则返回null
	 * @param qrcode
	 * @return
	 *
	 */
	public static Result QRScanner( File qrcode) {
		if(qrcode==null|| qrcode.canRead()==false)return null;
		final MultiFormatReader formatReader = new MultiFormatReader();
		//定义二维码参数
		Map  hints= new HashMap<>();
		Result result =null;
		try {
			//读取指定的二维码文件
			BufferedImage bufferedImage =ImageIO.read(qrcode);
			BinaryBitmap binaryBitmap= new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
			hints.put(EncodeHintType.CHARACTER_SET, IConstant.CHAR_SET);
			result=formatReader.decode(binaryBitmap, hints);
			bufferedImage.flush();
		} catch (Exception e) {}
		return result;
	}

	/**
	 * 二维码添加logo图片
	 * @param matrixImage 源二维码图片
	 * @param logoFile logo图片
	 * @return 返回带有logo的二维码图片
	 */
	private static BufferedImage LogoMatrix(BufferedImage matrixImage, File logoFile) throws Exception{
		/**
		 * 读取二维码图片，并构建绘图对象
		 */
		Graphics2D g2 = matrixImage.createGraphics();

		int matrixWidth = matrixImage.getWidth();
		int matrixHeigh = matrixImage.getHeight();

		/**
		 * 读取Logo图片
		 */
		BufferedImage logo = ImageIO.read(logoFile);

		//开始绘制图片
		g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制
		BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke);// 设置笔画对象
		//指定弧度的圆角矩形
		RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,10,10);
		g2.setColor(Color.white);
		g2.draw(round);// 绘制圆弧矩形

		//设置logo 有一道灰色边框
		BasicStroke stroke2 = new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setStroke(stroke2);// 设置笔画对象
		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+1, matrixHeigh/5*2+1, matrixWidth/5-3, matrixHeigh/5-3,10,10);
		g2.setColor(new Color(128,128,128));
		g2.draw(round2);// 绘制圆弧矩形

		g2.dispose();
		matrixImage.flush() ;
		return matrixImage ;
	}

	/**
	 * 设置 参数
	 * @param content
	 * @param width
	 * @param height
	 * @return
	 */
	private static BitMatrix defaultMatrix( String content, int width, int height)throws Exception{
		Map<EncodeHintType, Object> hints = new HashMap<>();
		//内容编码格式
		hints.put(EncodeHintType.CHARACTER_SET,IConstant.CHAR_SET);
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		//设置二维码边的空度，非负数
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		return bitMatrix;
	}

}

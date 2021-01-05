package com.yc.tickets.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateCodeServlet/*")
public class CreateCodeServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	private Random random = new Random();
	private static int LENGTH = 4;

	public void image(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

//		处理缓存
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		// 立即清除
		resp.setDateHeader("Expires", 0);

//		生成验证码
		String code = getRandomCode();

//		将验证码保存到session中
		req.getSession().setAttribute(SessionKeyConstant.VERIFICATIONCODE, code);

//		创建验证码图片并返回 
		BufferedImage image = getCodeImage(code, 110, 38);
		// 要输出的图片 图片格式 输出流
		ImageIO.write(image, "JPEG", resp.getOutputStream());
	}

	/**
	 * 得到验证码图片
	 * 
	 * @param code   验证码字符串
	 * @param width  图片宽度
	 * @param height 图片高度
	 * @return
	 */
	private BufferedImage getCodeImage(String code, int width, int height) {
		// 1.定义一张图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

		// 2.得到画笔
		Graphics g = image.getGraphics();

		// 3.设置画笔的颜色
		g.setColor(this.getRandomColor(210, 45));

		// 4. 绘制背景，因为画图必须是按照从底层到表层的顺序画，否则会重叠
		g.fillRect(0, 0, width, height);

		// 5. 绘制干扰线
		int x1, x2, y1, y2;
		for (int i = 0; i < code.length() * 10; i++) {
			// 给每条干扰线都换种颜色
			g.setColor(getRandomColor(140, 50));

			x1 = random.nextInt(width);
			x2 = random.nextInt(width);
			y1 = random.nextInt(height);
			y2 = random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}

		// 6. 绘制验证码
		// 6.1 绘制验证码的字体
		g.setFont(new Font("Couriew New", Font.ITALIC, 24));
		for (int i = 0; i < code.length(); i++) {
			// 同样的给每个字符一种颜色
			g.setColor(getRandomColor(40, 80));
			g.drawString(code.charAt(i) + "", 10 + 24 * i, 28);
		}

		return image;
	}

	/**
	 * 随机获得画笔颜色
	 * 
	 * @param start 开始值
	 * @param bound 范围
	 * @return
	 */
	private Color getRandomColor(int start, int bound) {

		int r = start + random.nextInt(bound);
		int g = start + random.nextInt(bound);
		int b = start + random.nextInt(bound);

		Color color = new Color(r, g, b);
		return color;
	}

	/**
	 * 生成随机字符串
	 * 
	 * @return
	 */
	private String getRandomCode() {

		StringBuffer stringBuffer = new StringBuffer();
		int flag = 0;
		for (int i = 0; i < LENGTH; i++) {
			flag = random.nextInt(3);
			switch (flag) {
			case 0:
				stringBuffer.append(random.nextInt(10));
				break;
			case 1:
				stringBuffer.append((char) (random.nextInt(26) + 65));
				break;
			default:
				stringBuffer.append((char) (random.nextInt(26) + 97));
				break;
			}
		}

		return stringBuffer.toString();
	}
}

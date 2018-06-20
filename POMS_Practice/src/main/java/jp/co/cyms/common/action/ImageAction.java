package jp.co.cyms.common.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.struts2.interceptor.ServletRequestAware;

import jp.co.cyms.base.BaseAction;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.ConfigBean;

/**
 * return Images. example: /POMS/fac001/ImageAction?imgName=DELLLP02.JPG
 * 
 * @author longnd
 *
 */
public class ImageAction extends BaseAction implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	byte[] imageInByte = null;
	String imgName;

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public ImageAction() {
		LOG.info("ImageAction");
	}

	public String execute() {
		return SUCCESS;
	}

	public byte[] getCustomImageInBytes() {
		BufferedImage originalImage;
		try {
			if (!StringUtil.isNullOrEmpty(imgName) && !imgName.equals("null")) {
				originalImage = ImageIO.read(getImageFile(this.imgName));
				// convert BufferedImage to byte array
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				String type = "jpg";
				if (this.imgName.matches("^.*(\\.png|\\.PNG)$")) {
					type = "png";
				}
				ImageIO.write(originalImage, type, baos);
				baos.flush();
				imageInByte = baos.toByteArray();
				baos.close();
			}

		} catch (IOException e) {
			LOG.error("Error can not find image");
		}

		return imageInByte;
	}

	private File getImageFile(String imageId) {
		ConfigBean configBean = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		File file = new File(configBean.getImageDir() + "\\" + this.imgName);
		LOG.info(file.toString());
		return file;
	}

	public String getCustomContentType() {
		return "image/jpeg";
	}

	public String getCustomContentDisposition() {
		return "anyname.jpg";
	}
}
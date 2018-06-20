/*
 * COPYRIGHT Mitsui Zosen Systems Research Inc. All right Reserved
 */
package jp.co.cyms.common;

//import gai.util.CommonDefineIF;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 *
 * PDF形式にオフィスファイルを変換するクラス
 *
 * @since 2014/09/29
 * @author TienVT
 */
public class FileOfficeConverter {
	private OfficeManager officeManager = null;
	private OfficeDocumentConverter converter = null;

	/**
	 * コンストラクタ クラス
	 * 
	 * @param libreOffice
	 *            : libreOffice インストール フォルダ(PDF変換)
	 * @since 2014/09/30
	 * @throws Exception
	 */
	public FileOfficeConverter(String libreOffice) {
		// 2015.12.25 Modified by Saito.Y
		// 作成待機時間１０分に設定。
		officeManager = new DefaultOfficeManagerConfiguration().setTaskExecutionTimeout(60000L * 10)
				.setOfficeHome(libreOffice).buildOfficeManager();
		officeManager.start();
		converter = new OfficeDocumentConverter(officeManager);
	}

	/**
	 * PDF形式にオフィスファイルを変換する処理
	 *
	 * @since 2014/09/30
	 * @param srcFile
	 *            : 元ファイル
	 * @param descFile
	 *            : 先ファイル
	 * @return インサートする処理結果 正常: true、異常: false
	 */
	public boolean isSuccessCreatePDF(String srcFile, String descFile) {
		boolean isSucceced = false;
		try {
			converter.convert(new File(srcFile), new File(descFile));
			isSucceced = true;
			return isSucceced;
		} catch (Throwable e) {
			e.printStackTrace();
			officeManager.stop();
			return false;
		}
	}

	/**
	 * libreofficeにコネクション終了する処理
	 *
	 * @since 2014/09/30
	 */
	public void stopOfficeManager() {
		officeManager.stop();
	}
}

/*
 * CLASS   : FileUtil
 * PACKAGE : jp.co.cyms.common
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;

/**
 * FileUtil
 * 
 * @author tichnv
 * @since 2014/08/01
 */
public class FileUtil {

    /**
     * FileUtil's constructor
     */
    public FileUtil() {
    }

    /**
     * pathCheck
     * 
     * @param path String path
     */
    public static void pathCheck(String path) {
        File pfile = null;
        pfile = new File(path);
        if (!pfile.exists()) {
            pfile.mkdirs();
        }
    }

    /**
     * deleteDir
     * 
     * @param dir File
     * @return result
     */
    public static boolean deleteDir(File dir) {

        if (dir.exists()) {
            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        System.out.println(dir.getPath() + ":" + dir.getName() + "[deleteDir]");
                        return false;
                    }
                }
            }

            if (!dir.delete()) {
                System.out.println(dir.getPath() + ":" + dir.getName() + "[deleteDir]");
                return false;
            }
        }
        return true;
    }

    /**
     * deleteFiles
     * 
     * @param dir File
     * @return result
     */
    public static boolean deleteFiles(File dir) {

        if (dir.exists()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    System.out.println(dir.getPath() + ":" + dir.getName() + "[deleteFiles]");
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * copyFile
     * 
     * @param sourceFile File
     * @param targetFile File
     * @throws IOException IOException
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {

        if (!sourceFile.exists() || sourceFile.isDirectory()) {
            return;
        }

        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }

            outBuff.flush();
        } finally {
            if (inBuff != null) {
                inBuff.close();
            }
            if (outBuff != null) {
                outBuff.close();
            }
        }
    }

    /**
     * copyDirectiory
     * 
     * @param sourceDir String
     * @param targetDir String
     * @throws IOException IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {

        (new File(targetDir)).mkdirs();
        File[] file = (new File(sourceDir)).listFiles();
        if (file == null) {
            return;
        }
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                File sourceFile = file[i];
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                String dir1 = sourceDir + "/" + file[i].getName();
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * フォルダ及びファイルZIP<br>
     * 
     * @param zipFileName ZIP先ファイル名
     * @param inputFile ZIP元ファイル名
     * @throws IOException IOException
     */
   /* public static void zip(String zipFileName, String inputFile) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, new File(inputFile), "");
        out.close();
    }*/

    /**
     * フォルダ及びファイルZIP<br>
     * 
     * @param out ZIP先ファイル
     * @param file ZIP元ファイル
     * @param base ZIPパス
     * @throws IOException IOException
     */
   /* public static void zip(ZipOutputStream out, File file, String base) throws IOException {
        if (file.isDirectory()) {
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (File child : file.listFiles()) {
                zip(out, child, base + child.getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(file);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }*/

    /**
     * Doc保存<br>
     * 
     * @param doc Documentファイル
     * @param filename 保存先
     */
    public static void docSave(Document doc, String filename) {

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//Apple//DTD PLIST 1.0//EN");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.apple.com/DTDs/PropertyList-1.0.dtd");

            transformer.transform(new DOMSource(doc), new StreamResult(filename));

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Docロード<br>
     * 
     * @param filename ロードファイル名
     * @return Document
     */
   /* public static Document docLoad(String filename) {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filename));
            document.normalize();
        } catch (ParserConfigurationException | IOException | SAXException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return document;
    }*/

    /**
     * ファイルを取得<br>
     * 
     * @param dir フォルダ
     * @return List<File>
     */
    public static List<File> getFiles(File dir) {

        File[] files = dir.listFiles();
        List<File> listFiles = new ArrayList<File>();

        if (files != null) {
            for (File file : files) {
                listFiles.add(file);
            }
        }
        return listFiles;
    }
    
    /**
	 * Remove multiple file by regex
	 * 
	 * @param strDir
	 * @param prefix
	 */
	public static void removeFileRegex(String strDir, String prefix) {
		File directory = new File(strDir);
		try {
			for (File f : directory.listFiles())
				if (f.getName().startsWith(prefix))
					f.delete();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Copy a file from srcFile to desFile
	 * 
	 * @param srcFile
	 * @param desFile
	 */
	public static void copyFile(String srcFile, String desFile) {
		try {
			File source = new File(srcFile);
			File dest = new File(desFile);
			FileUtils.copyFile(source, dest);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * ファイルの存在をチェック
	 * 
	 * @param strFilePath
	 *            : String
	 * @return boolean （true:　ファイルは存在する、 false:　ファイルは存在しない）
	 */
	public static boolean isFileExist(String strFilePath) {
		if (strFilePath == null)
			return false;

		try {
			File fileCheck = new File(strFilePath);

			if (fileCheck.exists())
				return true;
			else
				return false;
		}
		catch (Exception ex) {
			return false;
		}
	}
}

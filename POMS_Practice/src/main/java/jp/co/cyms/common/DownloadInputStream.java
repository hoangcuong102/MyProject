/**
 * 
 */
package jp.co.cyms.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author TienVT
 *
 */
public class DownloadInputStream extends FileInputStream{

	/** File download **/
	File file = null;
	public DownloadInputStream(File file) throws FileNotFoundException {
		super(file);
		// set file download
		this.file = file;
	}
	
	@Override
	public void close() throws IOException {
		super.close();
		// delete after close
		file.delete();
	}
}

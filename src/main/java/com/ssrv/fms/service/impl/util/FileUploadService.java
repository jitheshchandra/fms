package com.ssrv.fms.service.impl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
@Lazy(true)
public class FileUploadService {
	
	public void writeFile(byte[] content, String fileName) throws IOException {
		
		
		File file = new File(fileName);

 
		if (!file.exists()) {
			file.createNewFile();
		}
 
		FileOutputStream fop = new FileOutputStream(file);
 
		fop.write(content);
		fop.flush();
		fop.close();
 
	}
	
	public String getSplittedFileName(String fileStr){
		
		String strArr[] = fileStr.split("\\\\");
		return strArr[strArr.length -1];
	}

}

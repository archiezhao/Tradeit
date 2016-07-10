package com.tradeit.utility.image;

import java.io.*;

import com.tradeit.actions.bussiness.PostInfo;
import com.tradeit.utility.database.PostOperate;

public class ImageDelete {
	
	static String basePathAWS = "/webapps";
	//filecontent: request.getPart("filename").getgetInputStream
	public static void processRequest(PostInfo targetPost) throws NumberFormatException, Exception{

		File file1 = null;
		File file2 = null;
		File file3 = null;
		if(!targetPost.imageid1.equals("0")) {
			file1 = new File(basePathAWS + "/tmp/" + targetPost.imageid1);
			file1.delete();
		}
		if(!targetPost.imageid2.equals("0")) {
			file2 = new File(basePathAWS + "/tmp/" + targetPost.imageid2);
			file2.delete();
		}
		if(!targetPost.imageid3.equals("0")) {
			file3 = new File(basePathAWS + "/tmp/" + targetPost.imageid3);
			file3.delete();
		}
	}
}

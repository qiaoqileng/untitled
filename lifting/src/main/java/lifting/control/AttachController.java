package lifting.control;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lifting.model.Attaches;
import lifting.utils.AttachExcelUtil;
import lifting.utils.ExcelUtils;
import lifting.utils.Utils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.upload.UploadFile;

public class AttachController extends Controller{
	
	public void form(){
		render("attach_form.jsp");
	}
	
	public void submit() {
		try {
	        // 开时时间  
	        Long begin = new Date().getTime();  
			List<UploadFile> files = getFiles();
			String sql = Attaches.dao.getSql("attach.insert");
            // 保存sql后缀  
            StringBuffer suffix = new StringBuffer();  
            for(UploadFile file:files){
				File localFile = file.getFile();
				if (localFile != null && localFile.exists() && !localFile.isDirectory()) {
					ExcelUtils rUtils = new AttachExcelUtil(localFile.getAbsolutePath());
//						Attaches.dao.put(map)
					final LinkedList<Attaches> list= rUtils.readExcelContentForT();
					if (!Utils.emptyList(list)) {
						Db.tx(new IAtom() {
							
							@Override
							public boolean run() throws SQLException {
								int[] arrays = Db.batchSave(list, list.size());
								System.out.println(Arrays.toString(arrays));
								return true;
							}
						});
					}
				}
			}
	        // 结束时间  
	        Long end = new Date().getTime();  
	        // 耗时  
	        System.out.println("cast : " + (end - begin) / 1000 + " ms");  
			renderText("cast : " + (end - begin) / 1000 + " ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void download() {
		String sql = Attaches.dao.getSql("attach.download");
		List<Attaches> aList = Attaches.dao.find(sql);
		AttachExcelUtil rUtils;
		try {
			rUtils = new AttachExcelUtil(ExcelUtils.mkFilePath());
			File file = rUtils.exportExcel(aList);
			if (file == null || !file.exists()) {
				renderText("failed");
			} else {
				renderFile(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderNull();
		}
		
	}
}

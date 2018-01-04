package lifting.control;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lifting.model.Attaches;
import lifting.utils.AttachExcelUtil;
import lifting.utils.ExcelUtils;
import lifting.utils.Utils;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.upload.UploadFile;
import com.mysql.jdbc.StringUtils;

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
					LinkedList<Attaches> list= rUtils.readExcelContentForT();
					if (!Utils.emptyList(list)) {
						for(Attaches attaches:list){
							suffix.append("(" + attaches.getId() + ", " + attaches.getName() + ", " + attaches.getContent() + ", " + attaches.getFiles() + "),");
						}
					}
				}
			}
            if (!StringUtils.isNullOrEmpty(suffix.toString())) {
            	sql = sql + suffix.substring(0, suffix.length() - 1);
            	System.out.println(Charset.defaultCharset().name());
            	final String finalSql = new String(sql.getBytes(),"GBK");
            	System.out.println(finalSql);
                boolean success = Db.tx(new IAtom() {
    				
    				@Override
    				public boolean run() throws SQLException {
    					int count = Db.update(finalSql);
    					return count == 1;
    				}
    			});
                System.out.println(success);
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
}

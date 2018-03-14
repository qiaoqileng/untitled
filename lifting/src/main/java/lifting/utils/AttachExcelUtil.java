package lifting.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import lifting.model.Attaches;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFSheet;

public class AttachExcelUtil extends ExcelUtils<Attaches> {

	public AttachExcelUtil(String filepath) {
		super(filepath);
	}
	
	@Override
	public LinkedList<Attaches> readExcelContentForT() throws Exception {
		if(wb==null){  
            throw new Exception("Workbook对象为空！");  
        }  
        LinkedList<Attaches> list = new LinkedList<Attaches>();  
        sheet = wb.getSheetAt(0);  
        // 得到总行数  
        int rowNum = sheet.getLastRowNum();  
        row = sheet.getRow(0);  
        int colNum = row.getPhysicalNumberOfCells();  
        // 正文内容应该从第二行开始,第一行为表头的标题  
        for (int i = 1; i <= rowNum; i++) {  
            row = sheet.getRow(i);  
            int j = 0;  
            Attaches cellValue = new Attaches();  
            while (j < colNum) {  
                Object obj = getCellFormatValue(row.getCell(j));  
                switch (j) {
				case 0:
					int id = (int) row.getCell(j).getNumericCellValue();
					cellValue.setId(BigInteger.valueOf(id));
					break;
				case 1:
					cellValue.setName((String) obj);
					break;
				case 2:
					cellValue.setContent((String) obj);
					break;
				case 3:
					cellValue.setFiles((String) obj);		
					break;
				}
                j++;  
            }  
            list.add(cellValue);
        }  
        return list;  
	}
	
	public File exportExcel(List<Attaches> attaches) throws Exception{
		if (Utils.emptyList(attaches)) {
			return null;
		} else {
			FileOutputStream out = new FileOutputStream(getFile());
			sheet = wb.createSheet();
			for (int i = 0; i < attaches.size(); i++) {
				row = sheet.createRow(i);
				Attaches attach = attaches.get(i);
				for (int j = 0; j < 4; j++) {
					switch (j) {
					case 0:
						row.createCell(j, CellType.NUMERIC).setCellValue(attach.getId().doubleValue());
						break;
					case 1:
						row.createCell(j, CellType.STRING).setCellValue(attach.getName());
						break;
					case 2:
						row.createCell(j, CellType.STRING).setCellValue(attach.getContent());
						break;
					case 3:
						row.createCell(j, CellType.STRING).setCellValue(attach.getFiles());
						break;
					}
				}
				if (i % 10000 == 0) {// 一万行向磁盘写一次
	                //第六步：将内容写入磁盘  由于-1设置关闭自动刷新 需要人工主动刷新  调用：
	                ((SXSSFSheet) sheet).flushRows(100); // retain 100 last rows and flush all others
	                // Thread.sleep(1000);
	                System.out.println("写入....");
	                // ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
	                // this method flushes all rows
	             }
			}
			wb.write(out);
			out.close();
			return getFile();
		}
	}

}

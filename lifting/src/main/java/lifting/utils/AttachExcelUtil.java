package lifting.utils;

import java.math.BigInteger;
import java.util.LinkedList;

import lifting.model.Attaches;

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

}

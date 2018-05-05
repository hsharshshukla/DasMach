import java.io.*;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class exceltest {
	public static void main (String args[]) throws Exception{
	File f = new File("/development/test.xls");
	WritableWorkbook testfile = Workbook.createWorkbook(f);
	WritableSheet sheet = testfile.createSheet("sheet",0);
	Label l = new Label(0, 0, "data 1");
	sheet.addCell(l);
	testfile.write();
	testfile.close();
	}
}

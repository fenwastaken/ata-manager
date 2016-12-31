package ata;


import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import jxl.Cell;
import jxl.CellType;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.Font;
import jxl.format.Format;
import jxl.format.Orientation;
import jxl.format.Pattern;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Excel {

	private static int column = 0;
	private static int row = 0;
	private static String content = "";
	private static String file = "";
	
	public static String create(){
		
		int[] iTable = new int[11] ;
		
		//get desktop
		String desktopPath = System.getProperty("user.home") + "\\Desktop";
		
		try {
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");
			String result = df.format(date);
			
			String fileName = "Ata(" + result + ")";
			String type = ".xls";
			file = desktopPath + "\\" + fileName + type;
			
			

			
			//check wether file exists
			File f = new File(file);
			while(f.exists() && !f.isDirectory()) {
				//System.out.println("FILE " + file + " EXISTS");
			    fileName += "(copie)";
			    file = desktopPath + "\\" + fileName + type;
			  //  System.out.println("NEW FILE: " + file);
			    f = new File(file);
			}
			
			WritableWorkbook workbook = Workbook.createWorkbook(new File(file));
			workbook.createSheet("Sheet1", 0);
			
			WritableSheet sheet = workbook.getSheet(0); 


			
			Vector<String> vecHeader = new Vector<String>();
			vecHeader.add("Date");
			vecHeader.add("Type A/C");
			vecHeader.add("Immatriculation");
			vecHeader.add("ATA");
			vecHeader.add("Tache");
			vecHeader.add("Formation");
			vecHeader.add("Execution");
			vecHeader.add("Controles");
			vecHeader.add("Encadrement");
			vecHeader.add("APRS");
			vecHeader.add("Nom");
			
			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			wcf.setBackground(Colour.PALE_BLUE);
			
			for(String str : vecHeader){
				System.out.println("OUTPUT: " + str);
				Label label = new Label(column, row, str, wcf);
				sheet.addCell(label);
				sheet.setColumnView(column, str.length() + 3);
				column++;
			}
			
			try {
				Vector<Line> vLine = Manager.getAllLinesToObject();
				
				
				for(Line ln : vLine){
					column = 0;
					row++;
					
					Vector<String> lnContent = new Vector<String>();
					lnContent.add(ln.getDate());
					lnContent.add(ln.getType());
					lnContent.add(ln.getImmat());
					lnContent.add(ln.getAta());
					lnContent.add(ln.getTache());
					
					if(ln.isFormation()){
						lnContent.add("Oui");
					}
					else{
						lnContent.add("Non");
					}
					
					if(ln.isExecution()){
						lnContent.add("Oui");
					}
					else{
						lnContent.add("Non");
					}
					
					if(ln.isControles()){
						lnContent.add("Oui");
					}
					else{
						lnContent.add("Non");
					}
					
					if(ln.isEncadrement()){
						lnContent.add("Oui");
					}
					else{
						lnContent.add("Non");
					}
					
					if(ln.isAprs()){
						lnContent.add("Oui");
					}
					else{
						lnContent.add("Non");
					}
					
					lnContent.add(ln.getNom());
					
					//records the longest string in each column
					int i = 0;
					WritableCellFormat wcf2 = new WritableCellFormat();
					wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
					for(String str : lnContent){
						Label label = new Label(column, row, str, wcf2);
						int width = str.length() + 3;
						if( iTable[i] < width){
							iTable[i] = width;
						}
						sheet.addCell(label);
						column++;
						i++;
					}
					
				}
				
				//change column width if column title < longest string in it
				column = 0;
				row = 0;
				for(int w : iTable){
					Cell cell = sheet.getCell(column, row);
					String str = cell.getContents();
					if(str.length() < w){
						sheet.setColumnView(column, w);
					}
					column++;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			workbook.write();
			workbook.close();

		} catch (WriteException | IOException e) {
			System.out.println("excel create error");
			e.printStackTrace();
		}
		return file;
	}
	
	
	
}

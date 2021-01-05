package com.yc.tickets.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yc.tickets.dao.DBHelper_Mysql;

import jxl.Workbook;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月21日 下午12:00:03
*/
public class DBToExcel {
	/**
	 * 将数据库数据导出成excel表
	 * @param sql	要执行的sql语句
	 * @param filePath	文件路径
	 * @param sheetName		表名
	 * @param sheetID		第几张表，表的页号
	 */
	public static int exportExcel ( String sql , String filePath , String sheetName , int sheetID) {
		JdbcTemplate template = new JdbcTemplate( DBHelper_Mysql.getDs());
		int result = 0 ;
		try {
			//得到工作簿
			File file ;
			
			filePath = filePath.endsWith(".xls") ? filePath : filePath + ".xls" ;
			
			file = new File( filePath ) ;
			
			if ( !file.exists() ) {
				file.createNewFile() ;
			} 
			
			//创建这个工作簿的副本
			WritableWorkbook book = Workbook.createWorkbook( file ) ;
			//创建一张新表
			WritableSheet sheet = book.createSheet(sheetName, sheetID) ;
			
			//设置单元格的格式							字体				大小			加粗
			WritableFont font1 = new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD) ;
			WritableCellFormat format1 = new WritableCellFormat(font1) ;
			//设置单元格内容水平居中
			format1.setAlignment( jxl.format.Alignment.CENTRE);
			//设置单元格内容垂直居中
			format1.setVerticalAlignment( jxl.format.VerticalAlignment.CENTRE);
			//设置单元格自动换行
			format1.setWrap(true);
			
			WritableFont font2 = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD) ;
			WritableCellFormat format2 = new WritableCellFormat(font2) ;
			//设置单元格内容水平居中
			format2.setAlignment( jxl.format.Alignment.CENTRE);
			//设置单元格内容垂直居中
			format2.setVerticalAlignment( jxl.format.VerticalAlignment.CENTRE);
			//设置单元格自动换行
			format2.setWrap(true);
			
			
			//读取数据库的数据
			//List<Map<String, Object>> list = db.findAll(sql) ;
			List<Map<String, Object>> list = template.queryForList(sql);
			
			if ( list.size() <= 0 ) {
				return result;
			}
			
			/*将读取的数据写入excel表中
			 * 	1.将列名写入表
			 * 	2.将内容写入表
			 */
			//将键写入excel表
			Set<String> set = list.get(0).keySet() ;
			Iterator<String> iterator = set.iterator() ;
			sheet.setRowView(0, 500);
			for (int i = 0; i < set.size() ; i++) {
				sheet.setColumnView(i, 30);
				sheet.addCell( new jxl.write.Label(i, 0, iterator.next(),format1 ) );
			}
			
			
			//将值写入excel表
			//小于行数
			Iterator<String> iterator2 ;
			for (int i = 1; i <= list.size() ; i++) {
				iterator2 = set.iterator() ;
				//小于列数
				for (int j = 0; j < list.get(i-1).size() ; j++) {
					sheet.addCell( new jxl.write.Label(j,i,list.get(i-1).get(iterator2.next())+"",format2) );
				}
			}
			
			//将数据写入工作簿中
			book.write();
			book.close();
			
			result = 1 ;
		} catch (RowsExceededException e) {
			e.printStackTrace();
		}  catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
	} 
}

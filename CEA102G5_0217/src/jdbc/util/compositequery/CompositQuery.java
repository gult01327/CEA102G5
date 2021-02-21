package jdbc.util.compositequery;

import java.util.Map;
import java.util.Set;

public class CompositQuery {
	
	public static String get_aCondition(String columnName,String value) {
		
		String aCondition = null;
		
		if("COM_ID".equals(columnName) || ("COMC_ID".equals(columnName))) {
			aCondition = columnName + "=" + value;
		}else if("COM_NAME".equals(columnName) || "COMC_NAME".equals(columnName)|| 
				"COM_CONTENT".equals(columnName)){
			aCondition = columnName + " LIKE '%" + value + "%'";
		}else if("end".equals(columnName)) {
			aCondition = " date_format(COM_TIME,'%Y-%m-%d')<='" + value + "'";  
//			aCondition = "'" + value + "'";
		}else if("COM_TIME".equals(columnName) || "COM_PRICE".equals(columnName)) {
			aCondition = columnName + ">= '" + value + "'";
//			aCondition = columnName + " BETWEEN '" + value + "'"; 
		}else if("COM_PRICE2".equals(columnName)) {
			aCondition = "COM_PRICE <=" + value;
		}
		
		return aCondition + " ";
	}
	
	public static String get_WhereCondition(Map<String,String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		String datetime = null;
		int count = 0;
		for(String key : keys) {
			
			String value = map.get(key)[0];
			
			if(value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition(key, value.trim());
				
				if(count == 1) {
					whereCondition.append("WHERE " + aCondition);
				}else {
					whereCondition.append("AND " + aCondition);
				}
				
				System.out.println("用了" + count +"個欄位數做了查詢");
			}		
		}
			
		
		return whereCondition.toString();
	}
	
	
}

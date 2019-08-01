package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;



public class Test1 {
	
	private static Connection conn;
	
	private static String sql = "SELECT execution_plan_id, automation_id FROM execution_result "+
	"WHERE row_created >= '2019-01-01 00:00:00' AND row_created <= '2019-02-28 23:59:59'";
	
	private static String[] testCaseIdsTest = new String[]{"AT-98178","AT-98179","AT-98180","AT-98181","AT-98182","AT-98183","AT-98184","AT-98185","AT-98186","AT-98187","AT-98188","AT-98189","AT-98162","AT-98163","AT-98164","AT-98165","AT-98166","AT-98167","AT-98168","AT-98169","AT-98170","AT-98171","AT-98172","AT-98173","AT-98174","AT-98175","AT-98176","AT-98177"};
	
	private static HashSet<Integer> expIds = new HashSet<>();
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.56.169/reporting_dashboard","ajain5","ajain5");
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()){
				for(String tcId:testCaseIdsTest){
					if(rs.getString(2).contains(tcId)){
						System.out.println(rs.getInt(1)+" - "+rs.getString(2)); 
						expIds.add(rs.getInt(1));
					}
				}
			}
			System.out.println(expIds);
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	

}

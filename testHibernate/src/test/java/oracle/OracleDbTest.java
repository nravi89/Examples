package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class OracleDbTest {
	 
    public static void main(String[] args) {
 
        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
 
        // Step 1: Loading or registering Oracle JDBC driver class
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading Oracle JDBC driver");
            cnfex.printStackTrace();
        }
 
        // Step 2: Opening database connection
        try {
 
            // Step 2.A: Create and get connection using DriverManager class
            //String db = "jdbc:oracle:thin:@blr-devoda-scan.corp.yodlee.com:1521/test";
            String db = "jdbc:oracle:thin:@blr-devoda-scan.corp.yodlee.com:1521/ibtsbld";
        	connection = DriverManager.getConnection(db,"read", "read"); 
            // Step 2.B: Creating JDBC Statement 
            statement = connection.createStatement();
            // Step 2.C: Executing SQL &amp;amp; retrieve data into ResultSet
            /*
               String tableName = "Param_Acl#Cobrand_Acl_value";
               String query = "Select Param_acl.Acl_name, Param_acl.Acl_value as Default_value, Cobrand_Acl_Value.acl_value, Cobrand_Acl_Value.Row_Last_Updated "+
                          "from Param_Acl,Cobrand_Acl_value "+ 
                          "where Param_acl.Param_Acl_Id=Cobrand_Acl_Value.Param_Acl_Id and cobrand_id=10005176";
           
           /* ;String tableName = "Sum_Info_Param_Key#Sum_info_param_value"
            String query = "Select Sum_Info_Param_Key.Sum_Info_Param_Key_Name, Sum_Info_Param_Key.Default_Value, Sum_info_param_value.param_value, Sum_Info_Param_Key.Row_Last_Updated, Sum_info_param_value.sum_info_id "+
                           "from Sum_Info_Param_Key, Sum_info_param_value "+
                           "where Sum_Info_Param_Key.Sum_Info_Param_Key_Id=Sum_info_param_value.Sum_Info_Param_Key_Id";
          
            */
            
            String tableName = "cobrand_Non_Param_Acl#Non_Param_Acl";
            String query = "Select cobrand_Non_Param_Acl.cob_Value, Non_Param_Acl.Default_Value, Non_Param_Acl.Acl_name, cobrand_Non_Param_Acl.row_Last_Updated "+
                           "from cobrand_Non_Param_Acl,Non_Param_Acl "+
                           "where cobrand_Non_Param_Acl.Non_param_Acl_Id=cobrand_Non_Param_Acl.Non_param_Acl_Id and cobrand_id=10000004 and ROWNUM <= 100 limit 1";
          
            //  String query = "select * from mem_item where mem_id = 13522364";
            resultSet = statement.executeQuery(query);
            
            
           // Util.serialize(resultSet);
            //resultSet = (ResultSet) Util.deserialize();
 
            /*System.out.println("ID\tName\t\t\tAge\tMatches");
            System.out.println("==\t================\t===\t=======");*/
            
           
            
            
            
            
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int metaDataCount = rsmd.getColumnCount();
            
            String[] columnNames = new String[metaDataCount];
            List<String[]> columnValues = new ArrayList<>();
            
            for(int i=1; i<=metaDataCount;i++){
            	System.out.print(rsmd.getColumnName(i) + "\t");
            	columnNames[i-1] = rsmd.getColumnName(i);
            }
                
            System.out.println();
            // processing returned data and printing into console
            String[] row = null;
            while(resultSet.next()) {
            	row = new String[metaDataCount];
            	for(int i=1; i<=metaDataCount; i++){
            		System.out.print(resultSet.getString(i) + "\t");
            		row[i-1] = resultSet.getString(i);
            	}
            	columnValues.add(row);
            	System.out.println();
                /*System.out.println(resultSet.getInt(1) + "\t" + 
                        resultSet.getString(2) + "\t" + 
                        resultSet.getInt(3) + "\t" +
                        resultSet.getInt(4));*/
            }
            JSONObject json = Util.createJsonObject(tableName,columnNames,columnValues);
           // System.out.println(json);
            Util.exportAsHTML(json);
            Util.exportInFile(json);
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {
 
            // Step 3: Closing database connection
            try {
                if(null != connection) {
 
                    // cleanup resources, once after processing
                    resultSet.close();
                    statement.close();
 
                    // and then finally close connection
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
}

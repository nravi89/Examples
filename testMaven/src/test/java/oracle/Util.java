package oracle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
	
	public static void serialize(Object obj){
		 try{    
	            //Saving of object in a file 
	            FileOutputStream file = new FileOutputStream("D:/temp/config_poc/obj.obj"); 
	            ObjectOutputStream out = new ObjectOutputStream(file); 
	              
	            // Method for serialization of object 
	            out.writeObject(obj); 
	              
	            out.close(); 
	            file.close(); 
	              
	            System.out.println("Object has been serialized"); 
	  
	        } 
	          
	        catch(IOException ex){ 
	            System.out.println("IOException is caught"); 
	            ex.printStackTrace();
	        } 
	}
	
	public static Object deserialize(){
		Object obj = null;
		try{    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream("D:/temp/config_poc/obj.obj"); 
            ObjectInputStream in = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            obj = in.readObject(); 
              
            in.close(); 
            file.close(); 
              
            System.out.println("Object has been deserialized "); 
        } 
          
        catch(IOException ex){ 
            System.out.println("IOException is caught"); 
            ex.printStackTrace();
        } 
          
        catch(ClassNotFoundException ex){ 
            System.out.println("ClassNotFoundException is caught"); 
            ex.printStackTrace();
        } 
		return obj;
	}
	
	public static JSONObject createJsonObject(String tableName,String[] columnNames,List<String[]> columnsValue){
		JSONObject  jsonObject = new JSONObject();
		jsonObject.put("tableName", tableName);
		jsonObject.put("columnNames", columnNames);
		jsonObject.put("columnsValue", columnsValue);
		
		return jsonObject;
	}
	
	public static void exportAsHTML(JSONObject json){
		try {
			FileWriter fwriter = new FileWriter(new File("D:/temp/exported_Html/"+json.getString("tableName")+".html"));
			String bt = "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>"+
			"<script src='https://code.jquery.com/jquery-3.2.1.slim.min.js'></script>"+
			"<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js'></script>"+
			"<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js'></script>";
			
			fwriter.write(bt);
			
			String[] cn = (String[])json.get("columnNames");
			
			fwriter.write("<table class='table table-sm table-striped'><head><tr>");
			for(String name:cn){
				fwriter.write("<th>"+name+"</th>");
			}
			fwriter.write("</tr></head><body>");
			
			JSONArray columnsValue = json.getJSONArray("columnsValue");
			JSONArray cvArray = null;
			for(int i=0;i< columnsValue.length(); i++){
				cvArray = columnsValue.getJSONArray(i);
				fwriter.write("<tr>");
				String value = null;
				Object jo = null;
				for(int j=0;j<cvArray.length();j++){
					jo = cvArray.get(j);
					/*if(jo!=null){
						value = (String)jo;
					}else{
						value = "null";
					}
					*/
					fwriter.write("<td>"+jo+"</td>");
				}
				fwriter.write("</tr>");
				
				if(i%100 == 0)
					fwriter.flush();
			}
			fwriter.write("</body></table>");
			fwriter.flush();
			fwriter.close();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void exportInFile(JSONObject json){
		try {
			FileWriter fwriter = new FileWriter(new File("D:/temp/exported_Html/"+json.getString("tableName")+".json"));
		    fwriter.write(json.toString());
		    fwriter.flush();
		    fwriter.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

package quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Test2{
	public static void main(String[] args) throws ParseException {
	 
		String exp  = "(accountName==TESTDATA1) && (CONTAINER==bank) ||  (CONTAINER==bank) || ((amount>2000) && (amount<10000))) ";	
	     
		HashMap<String, Object> map= new HashMap<>();
		map.put("accountName", "TESTDATA1");
		map.put("CONTAINER", "bank");
		map.put("amount", 5000);
		
		
		LinkedList state = new LinkedList<>();
		
		
		String sDate1="02/08/2018";  
	    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		
	     System.out.println(date1);
	     
	     
	     
	     //String str = " null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null";
	     String str = "NETWORTH, ABUSEOFSERVICE, TRANSACTIONS, SDG, DATAEXTRACTS, COBRAND, SANITY, GET ACCOUNTS, SUBSCRIBE EVENT (API NAME), SANITY (SUITE NAME), UPDATE SUBSCRIBED EVENT (API NAME), COBRAND (ENTITY NAME), GET SUBSCRIBED EVENTS (API NAME), DEL SUBSCRIBED EVENTS(API NAME), NAMEDAPI, YSOREGRESSION, ADD PROVIDER ACCOUNT, TRANSACTION, FIREMEM, HELD/HELD AWAY CONFIG, REGRESSION, WEBHOOKS, MFA, DELETETRANSACTIONCATEGORIES, CREATETRANSACTIONCATEGORIES, SMB, SMB_REGRESSION, ADD-EDIT- REALESTATEACCOUNT -DEEPLINKING., ADD-EDIT-MANUALACCOUNT., ADD-EDIT-MANUALACCOUNT-DEEPLINKING., ADD-EDIT-REALESTATEACCOUNT., ADD-EDIT-MANUALACCOUNT, ASSETTRANSFER, GETUSERDATA, SMB_DATAEXTARCTS, WEALTHFUNCTIONAL, WEALTHREGRESSION, GRANULARCATEGORY FEATURE, SIMPLEDESC VERSION2.0, GET PROVIDER ACCOUNTS, PROVIDER ACCOUNTS, DATAEXTRACTS_V1.0, DATAEXTRACTS_V1.1, DEPDENDENTDATASETATTRIBUTES, REQUEST COMPARISION, COMMON MODULE, TRACEABILITY, RE ADD, PRSPR, DOCUMENT DOWNLOAD, CACHE REFRESH, COMMONMODULE, COMMONMODULETEMPLATE, NOTIMEGAP, DEPENDENT DATASET ATTRIBUTES, ENROLLEBILLS, SMOKE, PSD2_ERROR_CODE, YAD, OAUTH_BLOCKING_IN_USER_MIGRATION, MAINTENANCE_SCHEDULE_URL_FIELD, TWITTER_HANDLE, YCC, EMAILALERTS, SERVICEREQUEST, SANITY-PSDFL, SITE_SEARCH_VISIBILITY, CREATE_EDIT_SITE, R-20571, META_DATA_BASE_AGGREGATION, PSD_YSL_NONOAUTH_NONSDG, BULK_UPLOAD, PSD_YSL_OAUTH_SDG, SUBBRAND, TIMEFILTER, SLAM_BANG, DUPLICATE_SITE_OAUTH, FL_GETPROVIDER_COUNTAPI_V1.1, R-20858, GLOBALMESSAGE, GLOBALMESSAGES, FRAMEWORK, SITE_CREATION, LOGINUI, REG-PSDFL, ACCOUNTSHARING, REMEMBERME, ALERTSETTINGS, ACCOUNT SHARING, BUDGET_SANITY, BUDGET_REGRESSION, SANITY, REGRESSION, ACCOUNTSHARING, REGRESSION, SANITY, ACCOUNTSHARING, REGRRESSION, REGRESION, REGESSION, ACCUNTSHARING, SANITY, REGRESSION, REGRESSSION, RGERESSION, REGRERSSION, GRANULAR CATEGORY ENABLED, PII, REGRESS, REGRESSION, SANITY, GRANULAR CATEGORY DISABLED, GRANULARCATMERGER DISABLED, GRANULARCATMERGER ENABLED, GRANULARCATENABLED MERGERDISABLED, ABUSEOFSERVICEINTEGRATION, REST, REGRESSION_V1.1, SDG_CONTEXTUALDATA, SDG_SANITY, SDG_REGRESSION";
	     
	     List<String> dubFeats = Arrays.asList("COBRAND LOGIN","COMMAND","FIREMEM","MFA","PARSER","SAMLREGISTER","WEBHOOKS");
	     
	     String strA[] = str.split(",");
	     
	     for(String d:dubFeats){
	    	 for(String f:strA){
	    		 if(d.equals(f.trim())){
	    			 System.out.println(d);
	    		 }
	    	 }
	     }
	     
	     for(int i=0;i<strA.length;i++){
	    	 for(int j=i+1;j<strA.length;j++){
	    		 
	    		 if(strA[i].trim().equals(strA[j].trim())){
	    			 System.out.println("dulicate::"+strA[i]);
	    		 }
	    	 }
	     }
	     
	     
	     
	     System.out.println(strA.length);
	     
	     for(String s:strA)
	     System.out.print("'"+s.trim()+"',");
	     
	     System.out.println();
	     System.out.println("=======================");
	     List<String> dubFeatsa = Arrays.asList("COBRAND LOGIN","COMMAND","FIREMEM","MFA","PARSER","SAMLREGISTER , WEBHOOKS");
	     System.out.println(dubFeatsa);
	}

}

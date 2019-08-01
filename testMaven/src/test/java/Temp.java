import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class Temp {
	
	public static String createBodyParamsForUpdateOBAccount(List<String> field) throws JSONException {

		JSONObject obUpdateAccountObj = new JSONObject();
		if (field != null && !field.isEmpty())
			obUpdateAccountObj.put("scopeId", field);

		String obUpdateActBodyParams = obUpdateAccountObj.toString();
		System.out.println("Open Banking body params for UDPATE_OB_ACCOUNT is:: " + obUpdateActBodyParams);

		return obUpdateActBodyParams;
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("ravi");
		list.add("narayan");
		createBodyParamsForUpdateOBAccount(list);
	}

}

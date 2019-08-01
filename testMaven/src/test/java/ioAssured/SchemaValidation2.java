package ioAssured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ioAssured.schemaValidation.AccountStatus;
import ioAssured.schemaValidation.AccountType;
import ioAssured.schemaValidation.AuthType;
import ioAssured.schemaValidation.CurrencyValidator;
import ioAssured.schemaValidation.DateOnlyValidator;
import ioAssured.schemaValidation.DateTimeValidator;
import ioAssured.schemaValidation.EnumContainerTypes;
import ioAssured.schemaValidation.EnumEventName;
import ioAssured.schemaValidation.EnumLocale;
import ioAssured.schemaValidation.EnumMerchantSource;
import ioAssured.schemaValidation.EnumProviderAccountAggSource;
import ioAssured.schemaValidation.EnumProviderAccountDetailsStatus;
import ioAssured.schemaValidation.EnumProviderAccountStatus;
import ioAssured.schemaValidation.EnumRefreshStatus;
import ioAssured.schemaValidation.EnumStatusCode;
import ioAssured.schemaValidation.EnumTransactionBaseTypes;
import ioAssured.schemaValidation.EnumTransactionStatus;
import ioAssured.schemaValidation.EnumTransactionTypes;
import ioAssured.schemaValidation.LifeInsuranceType;
import ioAssured.schemaValidation.MFAType;
import ioAssured.schemaValidation.ProviderStatus;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SchemaValidation2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Response resp =  RestAssured.given()
				.baseUri("http://localhost:8181")
				.basePath("/web/user.json")
			    .get();
		JSONObject jsonSubject = new JSONObject(new JSONTokener(resp.getBody().asString()));
		
		System.out.println(jsonSubject);
        FileInputStream inputStream = new FileInputStream("D:/testWorkspace/testMaven/src/main/resources/user.json");
		JSONObject jsonSchema = new JSONObject(new JSONTokener(inputStream));
		
		SchemaLoader schemaLoader = SchemaLoader.builder().schemaJson(jsonSchema)
				.addFormatValidator("money", new CurrencyValidator())// rawSchema
																		// is
																		// the
																		// JSON
																		// representation
																		// of
																		// the
																		// schema
																		// utilizing
																		// the
																		// "evenlength"
																		// non-standard
																		// format
				.addFormatValidator("datetime", new DateTimeValidator()).addFormatValidator("locale", new EnumLocale())
				.addFormatValidator("eventName", new EnumEventName())
				.addFormatValidator("date", new DateOnlyValidator())
				.addFormatValidator("container", new EnumContainerTypes())
				.addFormatValidator("baseType", new EnumTransactionBaseTypes())
				.addFormatValidator("transactionStatus", new EnumTransactionStatus())
				.addFormatValidator("transactionType", new EnumTransactionTypes())
				.addFormatValidator("merchantSource", new EnumMerchantSource())
				.addFormatValidator("aggSource", new EnumProviderAccountAggSource())
				.addFormatValidator("providerAccStatus", new EnumProviderAccountStatus())
				.addFormatValidator("providerAccDetailsStatus", new EnumProviderAccountDetailsStatus())
				.addFormatValidator("statusCode", new EnumStatusCode())
				.addFormatValidator("refreshStatus", new EnumRefreshStatus())
				.addFormatValidator("providerStatus", new ProviderStatus()).addFormatValidator("MFAType", new MFAType())
				.addFormatValidator("AuthType", new AuthType())
				.addFormatValidator("lifeInsuranceType", new LifeInsuranceType())
				.addFormatValidator("accountStatus", new AccountStatus())
				.addFormatValidator("accountType", new AccountType()).build();

		try {
			Schema schema = schemaLoader.load().build();
			schema.validate(jsonSubject);
			System.out.println("dine");
		} catch (ValidationException e) {

			System.out.println("Error:" + e.getMessage() + "::" + e.toString() + "LL::" + e.getCausingExceptions());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

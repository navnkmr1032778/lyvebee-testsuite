package web.test.lyvebee_testsuite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import web.test.lyvebee_testsuite.basetest.TestMain;

public class SMS_Test extends TestMain {
	public static final String ACCOUNT_SID = "AC9105edbd02043a9dc92fe9a8d6b3240f";
	public static final String AUTH_TOKEN = "1109b3b363f20b8ce3961a6fa084bd78";

	public static String testOTPForInstructor() throws Exception {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		ResourceSet<Message> messages = Message.reader().limit(20).read();
		String finalvalue = null;
		for (Message record : messages) {
			String sID = record.getSid();
			System.out.println(sID);
			String messageBody = record.getBody();

			Pattern p = Pattern.compile("(.*) is your verification code for(.*).lyvebee.com");
			Matcher m = p.matcher(messageBody);

			finalvalue = null;
			while (m.find()) {
				finalvalue = m.group(1);
			}
//			Message.deleter(record.getSid()).delete();
		}

		return finalvalue;
	}

}

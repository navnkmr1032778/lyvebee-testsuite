package web.test.lyvebee_testsuite;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.mashape.unirest.http.Unirest;
import com.solutionstar.swaftee.utils.EmailMessage;
import com.solutionstar.swaftee.utils.InboxEmail;

import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class User_SignIn_Test extends TestMain {

	String mail = null;
	UserPOJO user;

	@Test(description = "Verify SignIn Customer using mail id", priority = 0)
	public void testSignInConsumer() {
		try {
			loadMainPage();
			mainPage.goToSignInPageFromHeader();
			if (userEmailListCreated.size() > 0) {
				user = userEmailListCreated.get(0);
			} else {
				user = getDefaultCustomerUser();
			}

			mail = user.getUserEmail();
			log("USER INSTRUCTOR SIGN IN MAIL::" + user.getUserEmail());
			signInPage.signInUsingEmail(mail);
			log("SIGN IN USING MAIL IS ACCEPTED AND MAIL SENT FOR LOGIIN.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify SignIn email message for Customer", dependsOnMethods = {
			"testSignInConsumer" }, priority = 1)
	public void testSignInInvitationEmailConsumer() throws UnsupportedEncodingException {
		try {
			System.out.println(mail);
			int i = 24;
			List<InboxEmail> inbox = nadaMailService.getInboxByEMail(mail);
			while (inbox.size() == 0 && i > 0) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				inbox = nadaMailService.getInboxByEMail(mail);
				i--;
			}
			EmailMessage mailMessage = nadaMailService.getMessageWithSubjectContainsWith(inbox,
					TestConstant.MAIL_USER_SIGNIN_SUBJECT_STRING);
			String html = mailMessage.getHtml();
			String htmltri = html.trim();
			System.out.println("\n\n\n");
			System.out.println("HTML STRING OF ENROLLMENT MAIL::" + htmltri);
			System.out.println("\n\n\n");

			Pattern p = Pattern.compile("<a href='(.*)'>Sign in to LyveBee Inc");
			Matcher m = p.matcher(html);

			String finalvalue = null;
			while (m.find()) {
				finalvalue = m.group(1);
			}
			finalvalue = URLDecoder.decode(finalvalue, "UTF-18");
			finalvalue = finalvalue.replace("&amp;", "&");
			getLogger().info("Link to Sign In: " + finalvalue);

			getDriver().get(finalvalue);
			Unirest.get(finalvalue);

			mainPage.waitForPageLoadComplete();
			userSearchClassesPage.validate();
			log("LOGIN MAIL RECEIVED SUCCESSFULLY AND LINK PRESENT FOR LOGIN.");
			log("USER CUSTOMER SUCCESSFULLY LOGGED IN.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInvitationEmailInstructor::"
					+ ExceptionUtils.getFullStackTrace(e));
		}
	}
}

package web.test.lyvebee_testsuite;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.solutionstar.swaftee.utils.EmailMessage;
import com.solutionstar.swaftee.utils.InboxEmail;

import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class Instructor_SignIn_Test extends TestMain {

	UserPOJO user;

	@Test(description = "Verify SignIn Instructor using mail id", priority = 0)
	public void testSignInInstructor() {
		try {
			loadMainPage();
			mainPage.goToSignInPageFromHeader();
			user = getDefaultInstructorUser();
			log("USER INSTRUCTOR SIGN IN MAIL::" + user.getUserEmail());
			signInPage.signInUsingEmail(user.getUserEmail());
			log("SIGN IN USING MAIL IS ACCEPTED AND MAIL SENT FOR LOGIIN.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify SignIn email message for Instructor", dependsOnMethods = {
			"testSignInInstructor" }, priority = 1)
	public void testSignInInvitationEmailInstructor() throws UnsupportedEncodingException {
		try {
			String mail = user.getUserEmail();
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
			userProfilePage.validate();
			log("LOGIN MAIL RECEIVED SUCCESSFULLY AND LINK PRESENT FOR LOGIN.");
			log("USER INSTRUCTOR SUCCESSFULLY LOGGED IN.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInvitationEmailInstructor::"
					+ ExceptionUtils.getFullStackTrace(e));
		}

	}
}

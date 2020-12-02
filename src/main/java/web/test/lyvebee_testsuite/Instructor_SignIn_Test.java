package web.test.lyvebee_testsuite;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;

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
			"testSignInInstructor" }, priority = 1, retryAnalyzer = RetryAnalyzer.class)
	public void testSignInInvitationEmailInstructor() throws UnsupportedEncodingException {
		try {
			String mail = user.getUserEmail();
			System.out.println(mail);
			getDriver().get(Email_Test.testUserSignInMail(mail));
			int i = 0;
			while (mainPage.isInvalidCodePresent()) {
				getDriver().get(Email_Test.testUserSignInMail(mail));
				i++;
				if (i == 5) {
					break;
				}
			}
			mainPage.waitForPageLoadComplete();
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

package web.test.lyvebee_testsuite;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;

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
			log("USER CUSTOMER SIGN IN MAIL::" + user.getUserEmail() + " USER NAME::" + user.getUserName());
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

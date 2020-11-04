package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee_testsuite.contants.TestConstant;

public class User_AllSignIn_Test extends User_SignIn_Test {

	@Test(description = "Verify SignIn Using Facebook Customer", priority = 2)
	public void testFacebookSignInConsumer() {
		try {
			mainPage.logOut();
			loadMainPage();
			mainPage.goToSignInPageFromHeader();
			signInPage.signInUsingFacebook(TestConstant.FACEBOOK_USERNAME, TestConstant.FACEBOOK_PASSWORD);
			userSearchClassesPage.validate();
			log("CUSTOMER LOGIN via FACEBOOK IS SUCCESSFUL");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify SignIn Using Google For Customer", priority = 3, enabled = false)
	public void testGoogleSignInConsumer() {
		try {
			mainPage.logOut();
			loadMainPage();
			mainPage.goToSignInPageFromHeader();
			signInPage.signInUsingGMail(TestConstant.GMAIL_USERNAME, TestConstant.GMAIL_PASSWORD);
			userSearchClassesPage.validate();
			log("CUSTOMER LOGIN via GOOGLE IS SUCCESSFUL");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

}

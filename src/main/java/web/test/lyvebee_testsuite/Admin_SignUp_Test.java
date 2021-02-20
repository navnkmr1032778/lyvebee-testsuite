package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class Admin_SignUp_Test extends TestMain {

	UserPOJO user;

	@Test(description = "Verify Instructor account deletion", priority = 0)
	public void testDeleteAccountFromApi() {
		try {
			loadDeleteAccountFromAPIMainPage();
			if (!instructorDeleteAcccountFromAPI.deleteInstructorAccount(TestConstant.DEFAULT_ADMIN_USER_USERNAME))
				Assert.fail("ADMIN ACCOUNT FAILED TO DELETE");
			loadDeleteAccountFromAPIMainPage();
			if (!instructorDeleteAcccountFromAPI.deleteInstructorAccount(TestConstant.DEFAULT_INSTRUCTOR_USER_USERNAME))
				Assert.fail("INSTRUCTOR ACCOUNT FAILED TO DELETE");
			loadDeleteAccountFromAPIMainPage();
			if (!instructorDeleteAcccountFromAPI.deleteInstructorAccount(TestConstant.DEFAULT_CUSTOMER_USER_EMAIL))
				Assert.fail("CUSTOMER ACCOUNT FAILED TO DELETE");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpPage::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Account Signup", dependsOnMethods = { "testDeleteAccountFromApi" }, priority = 1)
	public void testAdminSignup() {
		try {
			user = getDefaultAdminUser();
			loadMainPage();
			mainPage.goToSignUpPageFromHeader();
			log("ADMIN SIGNUP MAIL::" + user.getUserEmail());
			signUpPage.signUpUsingEmail(user.getUserName(), user.getUserEmail());
			log("ADMIN SIGNUP MAIL SENT SUCCUESSFULLY" + user.getUserEmail());
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpPage::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Check SignUp Mail Consumer", dependsOnMethods = { "testAdminSignup" }, priority = 2)
	public void testSignUpInvitationEmail() {
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
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

//	@Test(description = "Check Landing Page Configuration", dependsOnMethods = {
//			"testSignUpInvitationEmail" }, priority = 2)
//	public void testInstructorMobileOTP() {
//		try {
//			instructorPhoneNumberValidationPage
//					.enterMobileNumber(TestConstant.DEFAULT_INSTRUCTOR_SIGNUP_USER_PHONENUMBER);
//			instructorPhoneNumberValidationPage.clickVerify();
//
//			String otp = SMS_Test.testOTPForInstructor();
//			instructorPhoneNumberValidationPage.submitOTP(otp);
//		} catch (Exception e) {
//			e.printStackTrace();
//			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
//		}
//	}

	@Test(description = "Check Landing Page Configuration", dependsOnMethods = {
			"testSignUpInvitationEmail" }, priority = 4)
	public void testCompleteLandingPage() {
		try {
			int length = 10;
			boolean useLetters = true;
			boolean useNumbers = false;
			String siteDomain = "test" + RandomStringUtils.random(length, useLetters, useNumbers);
			log("Admin URL :" + siteDomain);
			adminLandingPage.completeFirstStep();
			adminLandingPage.completeSecondStep(siteDomain);
			adminLandingPage.completeThirdStep();
			adminLandingPage.completeFourthStep();
			adminLandingPage.completeFifthStep();
			adminLandingPage.completeSixthStep();

			adminLandingPage.completeHeaderTab();
			adminLandingPage.completeSocialTab();
			adminLandingPage.completeContactInfoTab();
			mainPage.waitForPageLoadComplete();
			log("ADMIN SIGNUP MAIL RECEIVED SUCCUESSFULLY");
			log("ADMIN SIGNUP MAIL HAS THE LINK FOR LOGIN");
			log("ADMIN SIGNUP SUCCESSFUL");
			TestConstant.WHITELABEL_BASE_PAGE_URL_CURRENT = "https://" + siteDomain + ".lyvebee.com";
			TestConstant.ADMIN_MAIL_CURRENT = user.getUserEmail();
			adminUser.add(user);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
		}
	}
}

package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;

public class Instructor_SignUp_Test extends TestMain {

	int invalidImageCount = 0;
	UserPOJO user;

	@Test(description = "Verify Instructor account deletion", priority = 1)
	public void testAcceptInvitation() {
		try {
//			user = invitedUsers.get(0);
			user = getDefaultInstructorUser();
			loadMainPage();
			mainPage.get(teamMemberInvitationSignUpLink);
			signUpPage.signUpThroughInvitation("Instructor1", null);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpPage::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Check SignUp Mail Consumer", dependsOnMethods = { "testAcceptInvitation" }, priority = 2)
	public void testSignUpMailFromInvitation() {
		try {
			String mail = user.getUserEmail();
			System.out.println(mail);
			mainPage.get(Email_Test.testUserSignInMail(mail));
			mainPage.sleep(8000);
			mainPage.waitForPageLoadComplete();
			int i = 0;
			while (mainPage.isInvalidCodePresent()) {
				getDriver().get(Email_Test.testUserSignInMail(mail));
				i++;
				if (i == 5) {
					break;
				}
			}
			instructorUserEmailListCreated.add(user);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

//	@Test(description = "Verify SignUp Consumer", dependsOnMethods = { "testDeleteAccountFromApi" }, priority = 1)
//	public void testSignUpPage() {
//		try {
//			loadMainPage();
//			mainPage.goToSignUpPageFromHeader();
//			log("INSTRUCTOR SIGNUP MAIL::" + user.getUserEmail());
//			signUpPage.signUpUsingEmail(user.getUserName(), user.getUserEmail());
//			log("INSTRUCTOR SIGNUP MAIL SENT SUCCUESSFULLY" + user.getUserEmail());
//		} catch (Exception e) {
//			e.printStackTrace();
//			AssertJUnit.fail("Exception happened in testSignUpPage::" + ExceptionUtils.getFullStackTrace(e));
//		}
//	}
//
//	@Test(description = "Check SignUp Mail Consumer", dependsOnMethods = { "testSignUpPage" }, priority = 2)
//	public void testSignUpInvitationEmail() {
//		try {
//			String mail = user.getUserEmail();
//			System.out.println(mail);
//			getDriver().get(Email_Test.testUserSignInMail(mail));
//			int i = 0;
//			while (mainPage.isInvalidCodePresent()) {
//				getDriver().get(Email_Test.testUserSignInMail(mail));
//				i++;
//				if (i == 5) {
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
//		}
//	}

//	@Test(description = "Check Landing Page Configuration", dependsOnMethods = {
//			"testSignUpInvitationEmail" }, priority = 3)
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

//	@Test(description = "Check Landing Page Configuration", dependsOnMethods = {
//			"testSignUpInvitationEmail" }, priority = 4)
//	public void testCompleteLandingPage() {
//		try {
//			adminLandingPage.completeFirstStep();
//			adminLandingPage.completeSecondStep("testauto-" + CommonUtils.getCurrentTimeString());
//			adminLandingPage.completeThirdStep();
//			adminLandingPage.completeFourthStep();
//			adminLandingPage.completeFifthStep();
//			adminLandingPage.completeSixthStep();
//
//			adminLandingPage.completeHeaderTab();
//			adminLandingPage.completeSocialTab();
//			adminLandingPage.completeContactInfoTab();
//			mainPage.waitForPageLoadComplete();
//			mainPage.goToMyProfilePageFromHeader();
//			userProfilePage.validate();
//			log("INSTRUCTOR SIGNUP MAIL RECEIVED SUCCUESSFULLY");
//			log("INSTRUCTOR SIGNUP MAIL HAS THE LINK FOR LOGIN");
//			log("INSTRUCTOR SIGNUP SUCCESSFUL");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
//		}
//	}

}

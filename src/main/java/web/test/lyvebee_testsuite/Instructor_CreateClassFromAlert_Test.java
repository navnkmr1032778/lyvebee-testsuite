package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class Instructor_CreateClassFromAlert_Test extends Instructor_SignIn_Test {

//	@Test(description = "Check SignUp Mail Consumer", dependsOnMethods = {
//			"testSignInInvitationEmailInstructor" }, priority = 4)
	public void testCreateFirstClassFromAlert() {
		try {
			ClassPOJO classPojo = getClassPojo();
			mainPage.goToMyProfilePageFromMobileView();
//			instructorPartnerOnBoardigPage.doCreateClassLater();
			userProfilePage.goToCreateClassPageFromAlert();
			instructorCreateClassesPage.createClass(classPojo,
					utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);
			if (!instructorCreateClassesPage.isClassCardPresent(classPojo))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Check SignUp Mail Consumer", priority = 5)

	public void testLyveBeeAccountFromAlerts() {
		try {
			mainPage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCalendarPermissionPageFromAlert();
			userProfilePage.createGMeetAccount();
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
		}
	}
}

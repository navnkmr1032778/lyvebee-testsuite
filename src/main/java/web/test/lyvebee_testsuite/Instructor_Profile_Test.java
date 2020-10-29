package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.solutionstar.swaftee.utils.CommonUtils;

public class Instructor_Profile_Test extends Instructor_SignIn_Test {

	@Test(description = "Verify reset password of Video account", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 2)
	public void testResetVideoAccountPassword() {
		try {
			userProfilePage.goToMyProfilePageFromMobileView();
			log("CUSTOMER VIEW MYPROFILE PAGE LOADED SUCCESSFUL");

			userProfilePage.resetPasswordOnVideoAccount(CommonUtils.getCurrentTimeString());
			if (!userProfilePage.isPasswordResetSuccess())
				Assert.fail("Password reset is not successful");

			log("PASSWORD RESET IS SUCCESSFUL");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify reset password of Video account", priority = 3)
	public void testAddSpecialityFeature() {
		try {
			loadMainPage();
			mainPage.goToMyProfilePageFromHeader();
			log("CUSTOMER VIEW MYPROFILE PAGE LOADED SUCCESSFUL");
			userProfilePage.goToAddNewSpecialityPage();
			String speciality = CommonUtils.getCurrentTimeString();
			instructorAddNewSpecialityPage.addSpeciality(speciality);

			if (!instructorAddNewSpecialityPage.isSpecialityAdded())
				Assert.fail("Speciality not added successful");
			log("SPECIALITY ADDED SUCCESSFUL");

			if (!instructorAddNewSpecialityPage.isSpecialityVideoURLPresent(speciality))
				Assert.fail("Speciality Video URL is present");

			log("SPECIALITY VIDEO URL IS PRESENT");

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify reset password of Video account", dependsOnMethods = {
			"testAddSpecialityFeature" }, priority = 4)
	public void testRemoveSpecialityFeature() {
		try {
			instructorAddNewSpecialityPage.deleteSpeciality();
			if (!instructorAddNewSpecialityPage.isSpecialityRemovedSuccessfully())
				Assert.fail("Speciality not added successful");
			log("SPECIALITY DELETE SUCCESSFUL");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

}

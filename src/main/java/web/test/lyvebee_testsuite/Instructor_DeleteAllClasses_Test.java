package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;

public class Instructor_DeleteAllClasses_Test extends Instructor_SignIn_Test {
	ClassPOJO classPojo;

	@Test(description = "Verify delete class feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 2)
	public void testDeleteClass() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			if (!instructorCreateClassesPage.deleteAllClasses())
				Assert.fail("All classes delete unsuccessful");
			log("ALL CLASSES DELETED SUCCESSFULLY.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in deleteClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

}

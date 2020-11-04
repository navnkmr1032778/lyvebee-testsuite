package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class Instructor_ClassCRUD_Test extends Instructor_SignIn_Test {
	ClassPOJO classPojo;

	@Test(description = "Verify Create Class feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 2)
	public void testCreateClass() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			log("CREATE CLASS PAGE LOADED SUCCESSFUL");

			for (int i = 0; i < 1; i++) {
				classPojo = getClassPojo();
				log("CLASS NAME TO BE CREATED::" + classPojo.getClassName());
				instructorCreateClassesPage.createClass(classPojo,
						utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);
			}
			if (!instructorCreateClassesPage.isClassCardPresent(classPojo))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");

			instructorCreateClassesPage.goToViewMyClassesPageFromCreateClassPage();
			instructorViewMyClassesPage.validate();
			if (!instructorViewMyClassesPage.isClassSessionPresentInUnBooked(classPojo.getClassName()))
				Assert.fail("Created class session not present in the UnBooked section of ViewMyClasses page.");
			classListCreated.add(classPojo);
			log("CLASS CREATED SUCCESSFULLY CLASS NAME::" + classPojo.getClassName());
			log("CLASS CARD PRESENT IN THE VIEW MY CLASS PAGE::" + classPojo.getClassName());
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify class duplication feature", dependsOnMethods = { "testCreateClass" }, priority = 3)
	public void testDuplicateClass() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			ClassPOJO classPojoTemp = (ClassPOJO) this.classPojo.clone();
			classPojoTemp = instructorCreateClassesPage.duplicateClass(classPojoTemp);
			if (!instructorCreateClassesPage.isClassCardPresent(classPojoTemp)) {
				Assert.fail(
						"Created class card not present in the classes section of CreateClass page after duplicate.");
			}
			classListCreated.add(classPojoTemp);
			log("CLASS " + classPojo.getClassName() + " IS DUPLICATED. DUPLICATE CLASS NAME IS::"
					+ classPojoTemp.getClassName());
			log("CLASS CARD PRESENT IN THE VIEW MY CLASS PAGE::" + classPojoTemp.getClassName());
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in duplicateClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify delete class feature", dependsOnMethods = { "testCreateClass" }, priority = 4)
	public void testDeleteClass() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			instructorCreateClassesPage.deleteClass(classListCreated.get(1).getClassName());
			if (instructorCreateClassesPage.isClassCardPresent(classListCreated.get(1)))
				Assert.fail("Created class card present in the classes section of CreateClass page after deletion.");
			log("CLASS DELETED SUCCESSFULLY. DELETED CLASS NAME::" + classListCreated.get(1).getClassName());
			log("CLASS CARD NOT PRESENT IN THE VIEW MY CLASS PAGE::" + classListCreated.get(1).getClassName());
			classListCreated.remove(1);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in deleteClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Add another Class feature", dependsOnMethods = { "testCreateClass" }, priority = 5)
	public void testAddAnotherClass() {
		try {
			ClassPOJO classPojoTemp = getClassPojo();
			instructorCreateClassesPage.createClass(classPojoTemp,
					utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);
			classListCreated.add(classPojoTemp);
			if (!instructorCreateClassesPage.isClassCardPresent(classPojoTemp))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");
			log("CLASS ADDED SUCCESSFULLY. CLASS NAME::" + classPojoTemp.getClassName());
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in addAnotherClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

//	@Test(description = "Check Add another Class feature", dependsOnMethods = { "testCreateClass" }, priority = 4)
//	public void testCreateLyveBeeVideoAccount() {
//		try {
////			instructorCreateClassesPage.createLyveBeeVideoAccount();
//		} catch (Exception e) {
//			e.printStackTrace();
//			AssertJUnit.fail("Exception happened in createLyveBeeVideoAccount::" + ExceptionUtils.getFullStackTrace(e));
//		}
//	}

}

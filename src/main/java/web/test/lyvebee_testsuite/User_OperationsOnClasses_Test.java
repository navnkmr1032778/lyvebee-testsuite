package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;

public class User_OperationsOnClasses_Test extends User_SignIn_Test {

	static String className = "";
	static ClassPOJO classPojo = null;

	@Test(description = "Verify search all classes feature and check for broken images.", dependsOnMethods = {
			"testSignInInvitationEmailConsumer" }, priority = 2)
	public void testSearchAllClassFeature() {
		try {
			mainPage.goToFindClassFromHeader();
			log("CUSTOMER FIND CLASSES PAGE LOADED SUCCESSFUL");

			userSearchClassesPage.searchAllClass();
			log("ALL CLASS SEARCHING SUCCESSFULL");

			if (!mainPage.checkBrokenImages(test))
				Assert.fail("Class search has the broken images. Please check for search all");
			log("NO BROKEN IMAGES PRESENT IN THE FIND CLASSES PAGE.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSearchClassFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Check Search specific class on Search Classes Page", dependsOnMethods = {
			"testSearchAllClassFeature" }, priority = 3)
	public void testSearchClassFeature() {
		try {
			classPojo = classListCreated.get(1);
			className = classPojo.getClassName();

			mainPage.goToFindClassFromHeader();
			if (!userSearchClassesPage.searchClass(className)) {
				AssertJUnit.fail("Search is not working/ not returned appropirate results");
			}
			log("SEARCHING CLASS NAME::" + className + " SUCCESSFUL");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSearchClassFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Check Class detail page of the selected class", dependsOnMethods = {
			"testSearchClassFeature" }, priority = 4)
	public void testClassDetailFeature() {
		try {
			userSearchClassesPage.goToClassDetailPage(className);
			if (!userClassDetailPage.getClassName().contains(className))
				fail("testClassDetailFeature");
			log("CLASS DETAIL PAGE LOADED SUCCESSFULLY FOR CLASS::" + className);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testClassDetailFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Check Class detail page of the selected class", dependsOnMethods = {
			"testClassDetailFeature" }, priority = 5)
	public void testOtherClassesByThisInstructorFeature() {
		try {
			if (!userClassDetailPage.isOtherClassPresentInClassDetailPage(classPojo))
				Assert.fail("Other classes of the same instructor not present ");

			log("Other classes of the same instructor present in the class detail page.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testClassDetailFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify the class enrollment", dependsOnMethods = { "testClassDetailFeature" }, priority = 6)
	public void testClassEnrollFeature() {
		try {
			userClassDetailPage.enrollClass();
			log("ENROLLING FOR THE CLASS SESSION SUCCESSFULL");

			userPaymentPage.validate();
			userPaymentPage.addNewCardFirstTime("CardName", "4242424242424242", "1023", "123", "33176");
			userPaymentPage.proceedToPayment();
			userViewMySessionsPage.validate();
			log("PAYMENT CARD DETAILS ADDED SUCCESSFULLY");

			if (!userViewMySessionsPage.isEnrolledClassCardPresent())
				Assert.fail("Class Enrollment card not present or having errors in the View My Classes page.");
			log("CLASS SESSION ENROLLED SUCCESSFULLY::" + classPojo.getClassName());
			bookedClassListByCustomer.add(classPojo);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testClassEnrollFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify class enrollement email sent to customer", dependsOnMethods = {
			"testClassDetailFeature" }, priority = 7)
	public void testClassEnrollEmailToCustomerFeature() {
		try {
			ClassPOJO classs = new ClassPOJO();
			classs.setClassName(className);
			log("ENROLLED CLASS NAME TO BE VERIFIED IN THE MAIL::" + className);
			log("CUSTOMER USER EMAIL ID::" + user.getUserEmail());

			if (!Email_Test.testEnrollMailForCustomer(user, classs))
				Assert.fail(
						"Class enrolled mail sent to customer has errors. Its not possible to take screenshot. Please have a look at it");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testClassEnrollFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify class enrollement email sent to Instructor", dependsOnMethods = {
			"testClassDetailFeature" }, priority = 8)
	public void testClassEnrollEmailToInstructorFeature() {
		try {
			ClassPOJO classs = new ClassPOJO();
			classs.setClassName(className);
			log("CLASS NAME TO BE VERIFIED IN THE MAIL OF INSTRUCTOR::" + className);
			log("INSTRUCTOR USER EMAIL ID::" + getDefaultInstructorUser().getUserEmail());

			if (!Email_Test.testEnrollMailForInstructor(getDefaultInstructorUser(), classs))
				Assert.fail(
						"Class enrolled mail sent to Instructor has errors. Its not possible to take screenshot. Please have a look at it");

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testClassEnrollFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify class session join page by customer", dependsOnMethods = {
			"testClassEnrollFeature" }, priority = 9)
	public void testSessionJoinFeature() {
		try {
			log("CLASS TO BE JOINED::" + className);
			userViewMySessionsPage.joinSessionWithName(className);

//			if (!userViewMySessionsPage.isClassWillOpen15MinutesBeforeTextPresent())
//				Assert.fail("Class meeting will open 15 minutes before the class text not present");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSessionJoinFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify class session cancel by Customer", dependsOnMethods = {
			"testClassEnrollFeature" }, priority = 10)
	public void testSessionCancelFeature() {
		try {
			log("CLASS TO BE CANCELLED::" + classListCreated.get(0).getClassName());
			loadMainPage();
			mainPage.goToFindClassFromHeader();
			userSearchClassesPage.searchClass(classListCreated.get(0).getClassName());
			userSearchClassesPage.goToClassDetailPage(classListCreated.get(0).getClassName());
			userClassDetailPage.enrollNextClass();
			userPaymentPage.addNewCardFirstTime("CardName", "4242424242424242", "1020", "123", "33176");
			userPaymentPage.proceedToPayment();

			// Cancel a session
			userViewMySessionsPage.cancelSessionWithName(classListCreated.get(0).getClassName());
			if (!userViewMySessionsPage.isEnrolledClassCardPresent())
				Assert.fail(
						"Class Enrollment card present after cancelling or having errors in the View My Classes page.");
			bookedAndCancelledClassListByCustomer.add(classListCreated.get(0));
			log("CLASS CANCELLED SUCCESSFULLY::" + classListCreated.get(0).getClassName());
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSessionCancelFeature::" + ExceptionUtils.getFullStackTrace(e));
		}
	}
}

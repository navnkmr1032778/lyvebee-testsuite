package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;

public class Instructor_OperationsOnClasses_Test extends Instructor_SignIn_Test {

	static ClassPOJO bookedClass = null;
	static ClassPOJO cancelledClass = null;

	@Test(description = "Verify deletion of unbooked Class session", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 2)
	public void testDeleteUnbookedClassSession() {
		try {
			userProfilePage.goToViewMyClassess();
			log("INSTRUCTOR VIEW MYSTUDIO PAGE LOADED SUCCESSFUL");

			instructorViewMyClassesPage.deleteUnbookedClass();
			log("UNBOOKED CLASS SESSION IS DELETED SUCCESSFULLY.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail(
					"Exception happened in testDeleteUnbookedClassSession::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify booked class from customer on Instructor account.", dependsOnMethods = {
			"testDeleteUnbookedClassSession" }, priority = 3)
	public void testBookedClassFromCustomer() {
		try {
			bookedClass = bookedClassListByCustomer.get(0);
			log("BOOKED CLASS TO TEST::" + bookedClass.getClassName());
			if (!instructorViewMyClassesPage.isClassCardPresentOnBookedClassList(bookedClass)) {
				Assert.fail("Booked session not present in booked tab.");
			}
			log("BOOKED CLASS CARD IS PRESENT IN THE VIEW MY CLASS PAGE -> AS INSTRUCTOR -> BOOKED PAGE.");
			String participantCountPresent = instructorViewMyClassesPage.getBookedClassParticipantCount(bookedClass);
			if (!participantCountPresent.contains("1 Participant")) {
				Assert.fail("Actual Participant Count - " + participantCountPresent
						+ "::Booked Class session or Participant count is not correctly displayed, after booking the class");
			}
			log("BOOKED CLASS PARTICIPANT COUNT IS 1.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testMeetUpCompletion::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify cancelled class from the customer on Instructor account.", dependsOnMethods = {
			"testDeleteUnbookedClassSession" }, priority = 4)
	public void testBookedAndCancelledClassFromCustomer() {
		try {
			cancelledClass = bookedAndCancelledClassListByCustomer.get(0);
			log("CANCELLED CLASS TO TEST::" + cancelledClass.getClassName());
			if (instructorViewMyClassesPage.isClassCardPresentOnBookedClassList(cancelledClass)) {
				Assert.fail("Cancelled session from customer, present in booked tab of instructor.");
			}
			log("CANCELLED CLASS CARD FROM CUSTOMER IS NOT PRESENT IN THE VIEW MY CLASS PAGE -> AS INSTRUCTOR -> BOOKED PAGE.");

			String participantCountPresent = instructorViewMyClassesPage
					.getUnBookedClassParticipantCount(cancelledClass);
			if (!participantCountPresent.contains("0 Participants")) {
				Assert.fail("Actual Participant Count - " + participantCountPresent
						+ "::Booked Class session or Participant count is not correctly displayed, after cancelling the class from customer");
			}
			log("CANCELLED CLASS PARTICIPANT COUNT IS 0.");

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testMeetUpCompletion::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify starting booked class session on Instructor account.", dependsOnMethods = {
			"testDeleteUnbookedClassSession" }, priority = 5)
	public void testClassStartFeature() {
		try {
			instructorViewMyClassesPage.commpleteMeetSetUp();
			mainPage.goToViewMyClassesPageFromMobileView();
			log("INSTRUCTOR CAN START THE BOOKED CLASS SESSION AND SEE THE INSTRUCTION FOR GOOGLE MEET.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testMeetUpCompletion::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify cancelling booked class session on Instructor account.", dependsOnMethods = {
			"testClassStartFeature" }, priority = 6)
	public void testCancelBookedClassSession() {
		try {
			instructorViewMyClassesPage.cancelBookedSession();
			log("BOOKED SESSION CAN BE CANCELLED BY THE INSTRUCTOR.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testCancelClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}
}

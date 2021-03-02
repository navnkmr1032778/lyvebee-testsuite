package web.test.lyvebee_testsuite;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class Instructor_CreateClassRegression_Test extends Instructor_SignIn_Test {
	ClassPOJO classPojo;

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 2)
	public void testCreateClassForThisWeekPeriod() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			mainPage.changeTimeZone(TestConstant.TIMEZONE_CHENNAITIME);
			userProfilePage.goToCreateClassPage();
			log("CREATE CLASS PAGE LOADED SUCCESSFUL");

			classPojo = getClassPojo();
			log("CLASS NAME TO BE CREATED::" + classPojo.getClassName());
			instructorCreateClassesPage.createClass(classPojo,
					utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);

			if (!instructorCreateClassesPage.isClassCardPresent(classPojo))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");
			log("CLASS CARD PRESENT");

			instructorCreateClassesPage.goToViewMyClassesPageFromCreateClassPage();
			instructorViewMyClassesPage.validate();
			if (!instructorViewMyClassesPage.isClassSessionPresentInUnBooked(classPojo.getClassName()))
				Assert.fail("Created class session not present in the UnBooked section of ViewMyClasses page.");
			log("CLASS CARD PRESENT IN UNBOOKED SECTION");
			List<String> expectedDateList = getDatesList(7);

			int numberOfActualSessions = instructorViewMyClassesPage
					.getNumberOfSessionPresent(classPojo.getClassName());
			if (!(numberOfActualSessions == expectedDateList.size()))
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT ARE :: " + numberOfActualSessions + "But expecting"
						+ expectedDateList.size() + " " + expectedDateList);

			List<String> sessionDateList = instructorViewMyClassesPage.getSessionDateList(classPojo.getClassName());
			Collections.sort(sessionDateList);

			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates are::" + sessionDateList + "but expecting " + expectedDateList);
			log("CLASS CARD DATES ARE CORRECTLY PRESENT IN UNBOOKED SECTION");

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClassForThisWeekPeriod" }, priority = 3)
	public void testCreateClassForThisWeekPeriodInClassProfilePage() {
		try {
			loadSearchClassPage();
			userSearchClassesPage.searchClass(classPojo.getClassName());
			userSearchClassesPage.goToClassDetailPage(classPojo.getClassName());
			int numberOfActualSessions = userClassDetailPage.getNumberOfSessionPresent();

			if (numberOfActualSessions != 5)
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT IN CLASS PROFILE ARE :: " + numberOfActualSessions
						+ "But expecting 5");

			List<String> sessionDateList = userClassDetailPage.getSessionDateList();
			Collections.sort(sessionDateList);

			List<String> expectedDateList = getDatesListForClassProfile(7);

			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates present in class profile page are::" + sessionDateList
						+ "but expecting " + expectedDateList);
			log("CLASS CARD DATES ARE CORRECTLY PRESENT IN CLASS PROFILE PAGE");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week And Next Week period feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 4)
	public void testCreateClassForThisWeekAndNextWeekPeriod() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			log("CREATE CLASS PAGE LOADED SUCCESSFUL");

			classPojo = getClassPojo();
			log("CLASS NAME TO BE CREATED::" + classPojo.getClassName());
			classPojo.setClassPeriod("This Week and Next Week");
			instructorCreateClassesPage.createClass(classPojo,
					utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);

			if (!instructorCreateClassesPage.isClassCardPresent(classPojo))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");

			instructorCreateClassesPage.goToViewMyClassesPageFromCreateClassPage();
			instructorViewMyClassesPage.validate();
			if (!instructorViewMyClassesPage.isClassSessionPresentInUnBooked(classPojo.getClassName()))
				Assert.fail("Created class session not present in the UnBooked section of ViewMyClasses page.");

			List<String> expectedDateList = getDatesList(14);

			int numberOfActualSessions = instructorViewMyClassesPage
					.getNumberOfSessionPresent(classPojo.getClassName());
			if (!(numberOfActualSessions == expectedDateList.size()))
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT ARE :: " + numberOfActualSessions + "But expecting"
						+ expectedDateList.size());

			List<String> sessionDateList = instructorViewMyClassesPage.getSessionDateList(classPojo.getClassName());
			Collections.sort(sessionDateList);

			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates are::" + sessionDateList + "but expecting " + expectedDateList);

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClassForThisWeekAndNextWeekPeriod" }, priority = 5)
	public void testCreateClassForThisWeekAndNextWeekPeriodInClassProfilePage() {
		try {
			loadSearchClassPage();
			userSearchClassesPage.searchClass(classPojo.getClassName());
			userSearchClassesPage.goToClassDetailPage(classPojo.getClassName());
			int numberOfActualSessions = userClassDetailPage.getNumberOfSessionPresent();

			if (numberOfActualSessions != 10)
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT IN CLASS PROFILE ARE :: " + numberOfActualSessions
						+ "But expecting 5");

			List<String> sessionDateList = userClassDetailPage.getSessionDateList();
			Collections.sort(sessionDateList);

			List<String> expectedDateList = getDatesListForClassProfile(14);

			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates present in class profile page are::" + sessionDateList
						+ "but expecting " + expectedDateList);
			log("CLASS CARD DATES ARE CORRECTLY PRESENT IN CLASS PROFILE PAGE");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week And Next 2 Week period feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 6)
	public void testCreateClassForThisWeekAndNext2WeekPeriod() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			log("CREATE CLASS PAGE LOADED SUCCESSFUL");

			classPojo = getClassPojo();
			log("CLASS NAME TO BE CREATED::" + classPojo.getClassName());
			classPojo.setClassPeriod("This Week and Next 2 Weeks");
			instructorCreateClassesPage.createClass(classPojo,
					utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);

			if (!instructorCreateClassesPage.isClassCardPresent(classPojo))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");

			instructorCreateClassesPage.goToViewMyClassesPageFromCreateClassPage();
			instructorViewMyClassesPage.validate();
			if (!instructorViewMyClassesPage.isClassSessionPresentInUnBooked(classPojo.getClassName()))
				Assert.fail("Created class session not present in the UnBooked section of ViewMyClasses page.");

			List<String> expectedDateList = getDatesList(21);

			int numberOfActualSessions = instructorViewMyClassesPage
					.getNumberOfSessionPresent(classPojo.getClassName());
			if (!(numberOfActualSessions == expectedDateList.size()))
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT ARE :: " + numberOfActualSessions + "But expecting"
						+ expectedDateList.size());

			List<String> sessionDateList = instructorViewMyClassesPage.getSessionDateList(classPojo.getClassName());
			Collections.sort(sessionDateList);
			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates are::" + sessionDateList + "but expecting " + expectedDateList);

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClassForThisWeekAndNext2WeekPeriod" }, priority = 7)
	public void testCreateClassForThisWeekAndNext2WeekPeriodInClassProfilePage() {
		try {
			loadSearchClassPage();
			userSearchClassesPage.searchClass(classPojo.getClassName());
			userSearchClassesPage.goToClassDetailPage(classPojo.getClassName());
			int numberOfActualSessions = userClassDetailPage.getNumberOfSessionPresent();

			if (numberOfActualSessions != 15)
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT IN CLASS PROFILE ARE :: " + numberOfActualSessions
						+ "But expecting 5");

			List<String> sessionDateList = userClassDetailPage.getSessionDateList();
			Collections.sort(sessionDateList);

			List<String> expectedDateList = getDatesListForClassProfile(21);

			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates present in class profile page are::" + sessionDateList
						+ "but expecting " + expectedDateList);
			log("CLASS CARD DATES ARE CORRECTLY PRESENT IN CLASS PROFILE PAGE");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week And Next 3 Week period feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 8)
	public void testCreateClassForThisWeekAndNext3WeekPeriod() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			log("CREATE CLASS PAGE LOADED SUCCESSFUL");

			classPojo = getClassPojo();
			log("CLASS NAME TO BE CREATED::" + classPojo.getClassName());
			classPojo.setClassPeriod("This Week and Next 3 Weeks");
			instructorCreateClassesPage.createClass(classPojo,
					utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);

			if (!instructorCreateClassesPage.isClassCardPresent(classPojo))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");

			instructorCreateClassesPage.goToViewMyClassesPageFromCreateClassPage();
			instructorViewMyClassesPage.validate();
			if (!instructorViewMyClassesPage.isClassSessionPresentInUnBooked(classPojo.getClassName()))
				Assert.fail("Created class session not present in the UnBooked section of ViewMyClasses page.");

			List<String> expectedDateList = getDatesList(28);

			int numberOfActualSessions = instructorViewMyClassesPage
					.getNumberOfSessionPresent(classPojo.getClassName());
			if (!(numberOfActualSessions == expectedDateList.size()))
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT ARE :: " + numberOfActualSessions + "But expecting"
						+ expectedDateList.size());

			List<String> sessionDateList = instructorViewMyClassesPage.getSessionDateList(classPojo.getClassName());
			Collections.sort(sessionDateList);
			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates are::" + sessionDateList + "but expecting " + expectedDateList);

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClassForThisWeekAndNext3WeekPeriod" }, priority = 8)
	public void testCreateClassForThisWeekAndNext3WeekPeriodInClassProfilePage() {
		try {
			loadSearchClassPage();
			userSearchClassesPage.searchClass(classPojo.getClassName());
			userSearchClassesPage.goToClassDetailPage(classPojo.getClassName());
			int numberOfActualSessions = userClassDetailPage.getNumberOfSessionPresent();

			if (numberOfActualSessions != 20)
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT IN CLASS PROFILE ARE :: " + numberOfActualSessions
						+ "But expecting 5");

			List<String> sessionDateList = userClassDetailPage.getSessionDateList();
			Collections.sort(sessionDateList);

			List<String> expectedDateList = getDatesListForClassProfile(28);

			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates present in class profile page are::" + sessionDateList
						+ "but expecting " + expectedDateList);
			log("CLASS CARD DATES ARE CORRECTLY PRESENT IN CLASS PROFILE PAGE");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week And Next 4 Week period feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 9)
	public void testCreateClassForThisWeekAndNext4WeekPeriod() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			log("CREATE CLASS PAGE LOADED SUCCESSFUL");

			classPojo = getClassPojo();
			log("CLASS NAME TO BE CREATED::" + classPojo.getClassName());
			classPojo.setClassPeriod("This Week and Next 4 Weeks");
			instructorCreateClassesPage.createClass(classPojo,
					utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);

			if (!instructorCreateClassesPage.isClassCardPresent(classPojo))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");

			instructorCreateClassesPage.goToViewMyClassesPageFromCreateClassPage();
			instructorViewMyClassesPage.validate();
			if (!instructorViewMyClassesPage.isClassSessionPresentInUnBooked(classPojo.getClassName()))
				Assert.fail("Created class session not present in the UnBooked section of ViewMyClasses page.");

			List<String> expectedDateList = getDatesList(35);

			int numberOfActualSessions = instructorViewMyClassesPage
					.getNumberOfSessionPresent(classPojo.getClassName());
			if (!(numberOfActualSessions == expectedDateList.size()))
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT ARE :: " + numberOfActualSessions + "But expecting"
						+ expectedDateList.size());

			List<String> sessionDateList = instructorViewMyClassesPage.getSessionDateList(classPojo.getClassName());
			Collections.sort(sessionDateList);
			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates are::" + sessionDateList + "but expecting " + expectedDateList);

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClassForThisWeekAndNext4WeekPeriod" }, priority = 10)
	public void testCreateClassForThisWeekAndNext4WeekPeriodInClassProfilePage() {
		try {
			loadSearchClassPage();
			userSearchClassesPage.searchClass(classPojo.getClassName());
			userSearchClassesPage.goToClassDetailPage(classPojo.getClassName());
			int numberOfActualSessions = userClassDetailPage.getNumberOfSessionPresent();

			if (numberOfActualSessions != 25)
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT IN CLASS PROFILE ARE :: " + numberOfActualSessions
						+ "But expecting 5");

			List<String> sessionDateList = userClassDetailPage.getSessionDateList();
			Collections.sort(sessionDateList);

			List<String> expectedDateList = getDatesListForClassProfile(35);

			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates present in class profile page are::" + sessionDateList
						+ "but expecting " + expectedDateList);
			log("CLASS CARD DATES ARE CORRECTLY PRESENT IN CLASS PROFILE PAGE");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week And Next 4 Week period feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 11)
	public void testCreateClassForDateRangePeriod() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			log("CREATE CLASS PAGE LOADED SUCCESSFUL");

			classPojo = getClassPojo();
			log("CLASS NAME TO BE CREATED::" + classPojo.getClassName());
			classPojo.setClassPeriod("Date Range");
			instructorCreateClassesPage.createClass(classPojo,
					utils.getCurrentWorkingDirectory() + TestConstant.FILE_PATH);

			if (!instructorCreateClassesPage.isClassCardPresent(classPojo))
				Assert.fail("Created class card not present in the classes section of CreateClass page after adding.");

			instructorCreateClassesPage.goToViewMyClassesPageFromCreateClassPage();
			instructorViewMyClassesPage.validate();
			if (!instructorViewMyClassesPage.isClassSessionPresentInUnBooked(classPojo.getClassName()))
				Assert.fail("Created class session not present in the UnBooked section of ViewMyClasses page.");

			List<String> expectedDateList = getDatesList(1);

			int numberOfActualSessions = instructorViewMyClassesPage
					.getNumberOfSessionPresent(classPojo.getClassName());
			if (!(numberOfActualSessions == expectedDateList.size()))
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT ARE :: " + numberOfActualSessions + "But expecting"
						+ expectedDateList.size());

			List<String> sessionDateList = instructorViewMyClassesPage.getSessionDateList(classPojo.getClassName());
			Collections.sort(sessionDateList);
			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates are::" + sessionDateList + "but expecting " + expectedDateList);

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClassForDateRangePeriod" }, priority = 12)
	public void testCreateClassForDateRangePeriodInClassProfilePage() {
		try {
			loadSearchClassPage();
			userSearchClassesPage.searchClass(classPojo.getClassName());
			userSearchClassesPage.goToClassDetailPage(classPojo.getClassName());
			int numberOfActualSessions = userClassDetailPage.getNumberOfSessionPresent();

			if (numberOfActualSessions != 1)
				Assert.fail("NUMBER OF ACTUAL SESSIONS PRESENT IN CLASS PROFILE ARE :: " + numberOfActualSessions
						+ "But expecting 5");

			List<String> sessionDateList = userClassDetailPage.getSessionDateList();
			Collections.sort(sessionDateList);

			List<String> expectedDateList = getDatesListForClassProfile(1);

			if (!sessionDateList.equals(expectedDateList))
				Assert.fail("Actual class session dates present in class profile page are::" + sessionDateList
						+ "but expecting " + expectedDateList);
			log("CLASS CARD DATES ARE CORRECTLY PRESENT IN CLASS PROFILE PAGE");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	public List<String> getDatesList(int numberOfDays) {
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		List<String> dates = new ArrayList<String>();
		LocalDate localDate2;

		for (int i = 0; i < numberOfDays; i++) {
			localDate2 = localDate.plusDays(i);
			if (localDate2.getDayOfWeek().equals(DayOfWeek.SUNDAY)
					|| localDate2.getDayOfWeek().equals(DayOfWeek.SATURDAY))
				break;
			dates.add(localDate2.format(formatter));
		}
		Collections.sort(dates);
		return dates;
	}

	public List<String> getDatesListForClassProfile(int numberOfDays) {
		LocalDate localDate = LocalDate.now();// For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy (EEEE)");
		List<String> dates = new ArrayList<String>();
		LocalDate localDate2;

		for (int i = 0; i < numberOfDays; i++) {
			localDate2 = localDate.plusDays(i);
			if (localDate2.getDayOfWeek().equals(DayOfWeek.SUNDAY)
					|| localDate2.getDayOfWeek().equals(DayOfWeek.SATURDAY))
				break;
			dates.add(localDate2.format(formatter));
		}
		Collections.sort(dates);
		return dates;
	}
}

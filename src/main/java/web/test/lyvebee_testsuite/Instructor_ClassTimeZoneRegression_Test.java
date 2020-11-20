package web.test.lyvebee_testsuite;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class Instructor_ClassTimeZoneRegression_Test extends Instructor_SignIn_Test {
	ClassPOJO classPojo;
	String classURL;
	String sessionTime;
	LocalDateTime localDate;

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 2)
	public void testCreateClass() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToCreateClassPage();
			log("CREATE CLASS PAGE LOADED SUCCESSFUL");

			classPojo = getClassPojo();
			log("CLASS NAME TO BE CREATED::" + classPojo.getClassName());

			mainPage.changeTimeZone(TestConstant.TIMEZONE_CHENNAITIME);
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

			loadSearchClassPage();
			userSearchClassesPage.searchClass(classPojo.getClassName());
			userSearchClassesPage.goToClassDetailPage(classPojo.getClassName());
			classURL = mainPage.getCurrentUrl();

			sessionTime = userClassDetailPage.getSesssionTiming();
			log("SESSION TIME:: " + sessionTime);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy (EEEE) hh:mm a");
			localDate = LocalDateTime.parse(sessionTime, formatter);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 3)
	public void testClassTimingForEasterTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_EASTERNTIME);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_EASTERNTIME);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_EASTERNTIME);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_EASTERNTIME);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_EASTERNTIME);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 4)
	public void testClassTimingForCentralTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_CENTRALTIME);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_CENTRALTIME);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_CENTRALTIME);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_CENTRALTIME);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_CENTRALTIME);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 5)
	public void testClassTimingForPacificTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_PACIFICTIME);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_PACIFICTIME);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_PACIFICTIME);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_PACIFICTIME);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_PACIFICTIME);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 6)
	public void testClassTimingForSingaporeTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_SINGAPORE);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_SINGAPORE);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_SINGAPORE);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_SINGAPORE);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_SINGAPORE);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 7)
	public void testClassTimingForMelbourneTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_MELBOURNETIME);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_MELBOURNETIME);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_MELBOURNETIME);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_MELBOURNETIME);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_MELBOURNETIME);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 8)
	public void testClassTimingForHawaiiTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_HAWAII);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_HAWAII);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_HAWAII);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_HAWAII);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_HAWAII);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 9)
	public void testClassTimingForBangkokTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_BANGKOKTIME);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_BANGKOKTIME);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_BANGKOKTIME);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BANGKOKTIME);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BANGKOKTIME);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 10)
	public void testClassTimingForGMTTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_GMT);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_GMT);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_GMT);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_GMT);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_GMT);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 11)
	public void testClassTimingForAlaskaTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_ALASKA);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_ALASKA);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_ALASKA);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_ALASKA);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_ALASKA);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 12)
	public void testClassTimingForBrasiliaTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_BRASILIA);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_BRASILIA);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_BRASILIA);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BRASILIA);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BRASILIA);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 13)
	public void testClassTimingForBerlinTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_BERLIN);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_BERLIN);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_BERLIN);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BERLIN);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BERLIN);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 14)
	public void testClassTimingForBeirutTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_BEIRUT);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_BEIRUT);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_BEIRUT);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BEIRUT);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BEIRUT);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	// cool
	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 15)
	public void testClassTimingForKuwaitTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_KUWAIT);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_KUWAIT);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_KUWAIT);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_KUWAIT);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_KUWAIT);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 16)
	public void testClassTimingForAbudhabiTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_ABUDHABI);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_ABUDHABI);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_ABUDHABI);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_ABUDHABI);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_ABUDHABI);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 17)
	public void testClassTimingForTokyoTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_TOKYO);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_TOKYO);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_TOKYO);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_TOKYO);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_TOKYO);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Create Class with This Week period feature", dependsOnMethods = {
			"testCreateClass" }, priority = 18)
	public void testClassTimingForBrisbannTimeZone() {
		try {
			mainPage.gotoURL(classURL);
			userClassDetailPage.changeTimeZone(TestConstant.TIMEZONE_BRISBANE);
			log("TIMEZONE CHANGED TO ::" + TestConstant.TIMEZONE_BRISBANE);

			String actualTiming = userClassDetailPage.getSesssionTiming();
			String expectedTiming = getTimingForClassProfile(TestConstant.TIMEZONE_BRISBANE);

			log("Actual Timing::" + actualTiming);
			log("Expected Timing::" + expectedTiming);

			if (!expectedTiming.equals(actualTiming))
				Assert.fail("CLASS TIMING IS NOT CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BRISBANE);
			log("CLASS TIMING IS CORRECT FOR TIMEZONE" + TestConstant.TIMEZONE_BRISBANE);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	public String getTimingForClassProfile(String timeZone) {
		String a = timeZone;
		Pattern p = Pattern.compile("\\((.*)\\) (.*)");
		Matcher m = p.matcher(a);

		String finalvalue = null;
		while (m.find()) {
			finalvalue = m.group(1);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy (EEEE) hh:mm a");

		ZoneId oldZone = ZoneId.of("GMT+05:30");
		ZoneId newZone = ZoneId.of(finalvalue);
		LocalDateTime newDateTime = localDate.atZone(oldZone).withZoneSameInstant(newZone).toLocalDateTime();

		return newDateTime.format(formatter);
	}
}

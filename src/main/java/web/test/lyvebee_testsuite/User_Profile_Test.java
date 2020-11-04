package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee_testsuite.contants.TestConstant;

public class User_Profile_Test extends User_SignIn_Test {

	@Test(description = "Verify profile name update by customer", dependsOnMethods = {
			"testSignInInvitationEmailConsumer" }, priority = 0)
	public void testUpdateNameInEditProfileConsumer() {
		try {
			mainPage.goToMyProfilePageFromHeader();
			log("CUSTOMER VIEW MYPROFILE PAGE LOADED SUCCESSFUL");

			userProfilePage.goToEditProfilePage();
			log("CUSTOMER EDIT MYPROFILE PAGE LOADED SUCCESSFUL");

			userProfilePage.updateName("AutoNameUpdated");
			userProfilePage.saveProfile();
			log("CUSTOMER PROFILE NAME UPDATED SUCCESSFULLY.");
			userProfilePage.validate();
			if (!userProfilePage.isProfileTitlePresent("AutoNameUpdated"))
				Assert.fail("Updated profile Name is not presetn in the Profile page");
			log("UPDATED CUSTOMER PROFILE NAME SHOWED IN THE MYPROFILE PAGE.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Add Country alert on MyProfile page", dependsOnMethods = {
			"testUpdateNameInEditProfileConsumer" }, priority = 1)
	public void testAddCountryAlertOnMyProfilePage() {
		try {

			mainPage.goToMyProfilePageFromMobileView();
			log("CUSTOMER VIEW MYPROFILE PAGE LOADED SUCCESSFUL FROM THE MOBILE VIEW");
			userProfilePage.goToEditProfilePage();
			if (!userProfilePage.isAddCountryAlertPresent()) {
				Assert.fail("Alert for AddCountry is not present when the country is not added");
			}
			log("ALERT FOR ADD COUNTRY PRESENT IN THE MY PROFILE PAGE");

			userProfilePage.selectCountry();

			userProfilePage.saveProfile();
			userProfilePage.validate();
			log("COUNTRY ADDED SUCCESSFULLY");

			if (userProfilePage.isAddCountryAlertPresent()) {
				Assert.fail("Alert for AddCountry is present when the country is added");
			}
			log("ALERT FOR ADD COUNTRY NOT PRESENT IN THE MY PROFILE PAGE");

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify add state in the profile", dependsOnMethods = {
			"testUpdateNameInEditProfileConsumer" }, priority = 2)
	public void testAddStateInEditProfileConsumer() {
		try {
			mainPage.goToMyProfilePageFromMobileView();
			userProfilePage.goToEditProfilePage();
			userProfilePage.selectState();
			userProfilePage.saveProfile();
			userProfilePage.validate();
			log("CUSTOMER STATE UPDATED SUCCESSFULLY AND VISIBLE IN THE MYPROFILE PAGE");

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));

		}
	}

	@Test(description = "Verify add languages in the profile", dependsOnMethods = {
			"testUpdateNameInEditProfileConsumer" }, priority = 3)
	public void testAddLanguageInEditProfileConsumer() {
		try {
			mainPage.goToMyProfilePageFromMobileView();
			userProfilePage.goToEditProfilePage();
			userProfilePage.removeLanguage();
			log("CUSTOMER LANGUAGE ADDED SUCCESSFULLY.");

			userProfilePage.selectLanguages("English");
			log("CUSTOMER LANGUAGE REMOVED SUCCESSFULLY.");

			userProfilePage.removeLanguage();
			if (!userProfilePage.isAllLanguageCantRemovedPresent()) {
				Assert.fail("Alert for AllLanguageCantBeRemoved is not present when the all language is removed");
			}
			log("ALL LANGUAGES CANT BE REMOVED ALERT PRESENT WHILE REMOVING ALL LANGUAGES.");
			userProfilePage.selectLanguages("Hindi");
			userProfilePage.saveProfile();
			userProfilePage.validate();
			log("HINDI LANGUGAGE ADDED SUCCESSFULLY AND PRESENT IN THE MY PROFILE PAGE");

		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify Add Payment alert on MyProfile page", dependsOnMethods = {
			"testUpdateNameInEditProfileConsumer" }, priority = 4)

	public void testAddPaymentAlertOnMyProfilePage() {
		try {
			if (TestConstant.ENVIRONMENT.equals("stage")) {
				mainPage.goToMyProfilePageFromMobileView();

				if (!userProfilePage.isAddPaymentAlertPresent()) {
					Assert.fail("Alert for PaymentCardAdd is not present when the Payment card is not added");
				}
				log("ALERT FOR ADD PAYMENT PRESENT IN THE MY PROFILE PAGE");

				userProfilePage.goToEditProfilePage();
				userProfilePage.goToPaymentPageFromAlert();
				log("CUSTOMER ADD PAYMENT PAGE LOADED SUCCESSFUL");

				userPaymentPage.addNewCardFirstTime("AutoCardName", "4242424242424242", "1023", "342", "33176");
				userPaymentPage.clickSaveButton();
				log("PAYMENT CARD ADDED SUCCESSFULLY");

				loadMainPage();
				mainPage.goToMyProfilePageFromHeader();
				if (userProfilePage.isAddPaymentAlertPresent()) {
					Assert.fail("Alert for PaymentCardAdd is present when the Payment card is added");
				}
				log("ALERT FOR ADD PAYMENT NOT PRESENT IN THE MY PROFILE PAGE");
			} else {
				log("In Prod environment we cant set up the test payment cards");
			}
		} catch (Exception e) {
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
			e.printStackTrace();
		}
	}

	@Test(description = "Verify become consultant page on Customer profile", dependsOnMethods = {
			"testSignInInvitationEmailConsumer" }, priority = 4)
	public void testBecomeConsultantOnMyProfilePageConsumer() {
		try {
			mainPage.goToMyProfilePageFromMobileView();
			userProfilePage.clickBecomeConsultantButton();
			log("BECOME CUSULTANT PAGE LOADED SUCCESSFULLY WITH PHONENUMBER TEXTBOX");

			instructorPhoneNumberValidationPage.selectCountryCode("India");
			instructorPhoneNumberValidationPage.enterMobileNumber("8825972990");
//			instructorPhoneNumberValidationPage.clickVerify();
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInstructor::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

}

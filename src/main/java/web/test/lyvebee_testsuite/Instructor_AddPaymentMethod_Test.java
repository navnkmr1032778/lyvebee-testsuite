package web.test.lyvebee_testsuite;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;

public class Instructor_AddPaymentMethod_Test extends Instructor_SignIn_Test {
	ClassPOJO classPojo;

	@Test(description = "Verify add payment method in Instructor profile", dependsOnMethods = {
			"testSignInInvitationEmailInstructor" }, priority = 2)
	public void testPaymentMethod() {
		try {
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToAddPaymentPage();
			instructorAddPaymentMethodPage.validate();

			log("ADD PAYMENT METHOD PAGE LOADED SUCCESSFULLY");

			instructorAddPaymentMethodPage.addPaypalAccount();
			if (!instructorAddPaymentMethodPage.isPaymentAddedSuccessfully()) {
				Assert.fail("Paypal payment is not added");
			}
			log("PAYPAL PAYMENT METHOD ADDED SUCCESSFULLY");

			instructorAddPaymentMethodPage.addPaytmAccount();
			if (!instructorAddPaymentMethodPage.isPaymentAddedSuccessfully()) {
				Assert.fail("Paytm payment is not added");
			}
			log("PAYTM PAYMENT METHOD ADDED SUCCESSFULLY");

			instructorAddPaymentMethodPage.removePayPalAccount();
			log("PAYPAL PAYMENT METHOD REMOVED SUCCESSFULLY");

			instructorAddPaymentMethodPage.removePaytmAccount();
			log("PAYTM PAYMENT METHOD REMOVED SUCCESSFULLY");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

}

package web.test.lyvebee_testsuite;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.solutionstar.swaftee.utils.EmailMessage;
import com.solutionstar.swaftee.utils.InboxEmail;

import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;
import web.test.lyvebee_testsuite.contants.TestConstant;
import web.test.pom.instructor.Instructor_DeleteAccountFromAPI_Page;

public class Instructor_SignUp_Test extends TestMain {

	int invalidImageCount = 0;
	UserPOJO user;

	@BeforeClass
	public void beforeClass() {
		instructorDeleteAcccountFromAPI = new Instructor_DeleteAccountFromAPI_Page(getDriver());
	}

	@Test(description = "Verify Instructor account deletion", priority = 0)
	public void testDeleteAccountFromApi() {
		try {
			user = getAutoInstructorUser();
			loadDeleteAccountFromAPIMainPage();
			if (!instructorDeleteAcccountFromAPI
					.deleteInstructorAccount(TestConstant.DEFAULT_INSTRUCTOR_SIGNUP_USER_PHONENUMBER))
				Assert.fail("INSTRUCTOR ACCOUNT FAILED TO DELETE");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpPage::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify SignUp Consumer")
	public void testSignUpPage() {
		try {
			loadMainPage();
			mainPage.goToSignUpPageFromHeader();
			log("INSTRUCTOR SIGNUP MAIL::" + user.getUserEmail());
			signUpPage.signUpUsingEmail(user.getUserName(), user.getUserEmail());
			log("INSTRUCTOR SIGNUP MAIL SENT SUCCUESSFULLY" + user.getUserEmail());
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpPage::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Check SignUp Mail Consumer", dependsOnMethods = { "testSignUpPage" })
	public void testSignUpInvitationEmail() throws UnsupportedEncodingException {
		try {
			String mail = user.getUserEmail();
			System.out.println(mail);
			int i = 24;
			List<InboxEmail> inbox = nadaMailService.getInboxByEMail(mail);
			while (inbox.size() == 0 && i > 0) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				inbox = nadaMailService.getInboxByEMail(mail);
				i--;
			}
			EmailMessage mailMessage = nadaMailService.getMessageWithSubjectContainsWith(inbox,
					TestConstant.MAIL_USER_SIGNIN_SUBJECT_STRING);
			String html = mailMessage.getHtml();
			Pattern p = Pattern.compile("<a href='(.*)'>Sign in to LyveBee Inc");
			Matcher m = p.matcher(html);

			String finalvalue = null;
			while (m.find()) {
				finalvalue = m.group(1);
			}
			finalvalue = URLDecoder.decode(finalvalue, "UTF-18");
			finalvalue = finalvalue.replace("&amp;", "&");
			getLogger().info("Link to Sign In: " + finalvalue);

			getDriver().get(finalvalue);
			instructorUserEmailListCreated.add(user);

			instructorPhoneNumberValidationPage.validate();
			log("INSTRUCTOR SIGNUP MAIL RECEIVED SUCCUESSFULLY");
			log("INSTRUCTOR SIGNUP MAIL HAS THE LINK FOR LOGIN");
			log("INSTRUCTOR SIGNUP SUCCESSFUL");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify SignUp Consumer")
	public void testInstructorMobileOTP() {
		try {
			instructorPhoneNumberValidationPage
					.enterMobileNumber(TestConstant.DEFAULT_INSTRUCTOR_SIGNUP_USER_PHONENUMBER);
			instructorPhoneNumberValidationPage.clickVerify();

			String otp = SMS_Test.testOTPForInstructor();
			instructorPhoneNumberValidationPage.submitOTP(otp);
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

}

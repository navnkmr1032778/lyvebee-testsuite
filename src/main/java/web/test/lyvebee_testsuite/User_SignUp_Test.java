package web.test.lyvebee_testsuite;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.mashape.unirest.http.Unirest;
import com.solutionstar.swaftee.utils.EmailMessage;
import com.solutionstar.swaftee.utils.InboxEmail;

import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class User_SignUp_Test extends TestMain {

	int invalidImageCount = 0;
	UserPOJO user;

	@Test(description = "Verify SignUp Consumer")
	public void testSignUpPage() {
		try {
			loadWhiteLabelMainPage();
			mainPage.goToSignUpPageFromHeader();
			user = getAutoConsumerUser();
			log("CUSOTMER SIGNUP MAIL::" + user.getUserEmail());
			signUpPage.signUpUsingEmail(user.getUserName(), user.getUserEmail());
			log("CUSOTMER SIGNUP MAIL SENT SUCCUESSFULLY" + user.getUserEmail());
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
			Pattern p = Pattern.compile("<a href=\"(.*)\" target(.*)\">Sign in to LyveBee Inc");
			Matcher m = p.matcher(html);

			String finalvalue = null;
			while (m.find()) {
				finalvalue = m.group(1);
			}
			finalvalue = URLDecoder.decode(finalvalue, "UTF-8");
			finalvalue = finalvalue.replace("&amp;", "&");
			getLogger().info("Link to Sign In: " + finalvalue);

			getDriver().get(finalvalue);
			Unirest.get(finalvalue);
			userEmailListCreated.add(user);
			System.out.println(user.getUserEmail());
			userSearchClassesPage.validate();
			log("CUSOTMER SIGNUP MAIL RECEIVED SUCCUESSFULLY");
			log("CUSOTMER SIGNUP MAIL HAS THE LINK FOR LOGIN");
			log("CUSTOMER SIGNUP SUCCESSFUL");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignUpInvitationEmail::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

}

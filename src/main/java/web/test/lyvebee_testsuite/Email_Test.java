package web.test.lyvebee_testsuite;

import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.solutionstar.swaftee.utils.EmailMessage;
import com.solutionstar.swaftee.utils.InboxEmail;

import web.test.lyvebee.POJO.ClassPOJO;
import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.basetest.TestMain;
import web.test.lyvebee_testsuite.contants.TestConstant;

public class Email_Test extends TestMain {

	public static boolean testEnrollMailForCustomer(UserPOJO user, ClassPOJO classPojo) throws Exception {
		Thread.sleep(15000);
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
				TestConstant.MAIL_USER_ENROLL_SUBJECT_STRING);
		String html = mailMessage.getHtml();

		if (!html.contains("Hi " + user.getUserName()))
			return false;
		if (!html.contains(classPojo.getClassName()))
			return false;
		if (!html.contains("CHAT WITH INSTRUCTOR"))
			return false;

		return true;
	}

	public static boolean testEnrollMailForInstructor(UserPOJO user, ClassPOJO classPojo) throws Exception {
		Thread.sleep(15000);
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
				TestConstant.MAIL_INSTRUCTOR_ENROLL_SUBJECT_STRING);
		String html = mailMessage.getHtml();
		if (!html.contains("Hi " + user.getUserName())) {
			log("User " + user.getUserName() + " Not present in the mail");
			return false;
		}
		if (!html.contains(classPojo.getClassName())) {
			log("Class " + classPojo.getClassName() + "Not present in the mail");
			return false;
		}
		if (!html.contains("CHAT WITH PARTICIPANT")) {
			return false;
		}
		return true;
	}

	public static String testUserSignInMail(String mail) throws Exception {
		Thread.sleep(15000);
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
		return finalvalue;
	}
}

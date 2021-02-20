package web.test.lyvebee_testsuite;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import web.test.lyvebee.POJO.ClassPOJO;
import web.test.lyvebee.POJO.UserPOJO;

public class Admin_InviteMyTeamMembers_Test extends Admin_SignIn_Test {
	ClassPOJO classPojo;
	UserPOJO user;

	@Test(description = "Verify add payment method in Instructor profile", dependsOnMethods = {
			"testSignInInvitationEmailAdmin" }, priority = 2)
	public void testSendTeamMemberInvitation() {
		try {
			user = getDefaultInstructorUser();
			userHomePage.goToMyProfilePageFromMobileView();
			userProfilePage.goToInviteTeamMemberPage();

			log("INVITE TEAM MEMBER PAGE LOADED SUCCESSFULLY");

			adminInviteTeamMembersPage.sendInvitaion(user.getUserEmail(), user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in createClass::" + ExceptionUtils.getFullStackTrace(e));
		}
	}

	@Test(description = "Verify SignIn email message for Instructor", dependsOnMethods = {
			"testSendTeamMemberInvitation" }, priority = 3)
	public void testTeamMemberInvitationEmailAdmin() throws UnsupportedEncodingException {
		try {
			String mail = user.getUserEmail();
			System.out.println(mail);
			teamMemberInvitationSignUpLink = Email_Test.testUserInvitationMail(mail);
			invitedUsers.add(user);
			mainPage.waitForPageLoadComplete();
			log("INVITATION MAIL RECEIVED SUCCESSFULLY AND LINK PRESENT FOR SINGUP.");
		} catch (Exception e) {
			e.printStackTrace();
			AssertJUnit.fail("Exception happened in testSignInInvitationEmailInstructor::"
					+ ExceptionUtils.getFullStackTrace(e));
		}

	}

}

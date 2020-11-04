package web.test.lyvebee_testsuite.contants;

public class TestConstant {
	public static String LYVEBEE_BASE_PAGE_URL = "https://staging-ssr.lyvebee.com/";

	// Mail Subject
	public static final String MAIL_USER_SIGNIN_SUBJECT_STRING = "Sign in to LyveBee Inc";
	public static final String MAIL_USER_ENROLL_SUBJECT_STRING = "Thank you for enrolling for a class!";
	public static final String MAIL_INSTRUCTOR_ENROLL_SUBJECT_STRING = "Congrats! A participant just enrolled in your class!";

	public static final String REPORTS_DIRECTORY = "Reports";
	public static final String FILE_PATH = "/resources/testdata/kedarkanth.jpg";

	public static final String FACEBOOK_USERNAME = "naveenkumar@lyvebee.com";
	public static final String FACEBOOK_PASSWORD = "Password@123";

	public static final String GMAIL_USERNAME = "lyveebeetester@gmail.com";
	public static final String GMAIL_PASSWORD = "Password@123";

	public static final String LINKEDIN_USERNAME = "navnkmr08@gmail.com";
	public static final String LINKEDIN_PASSWORD = "Password@123";

	public static String DEFAULT_CUSTOMER_USER_EMAIL = "automail20201017180010@clrmail.com";
	public static String DEFAULT_INSTRUCTOR_USER_USERNAME = "heputid@getnada.com";
	public static String DEFAULT_INSTRUCTOR_SIGNUP_USER_USERNAME = "zetyoc@getnada.com";
	public static String DEFAULT_INSTRUCTOR_SIGNUP_USER_PHONENUMBER = "2058812777";

	public static final String ENVIRONMENT = System.getProperty("testenv", "stage");

	static {

		if (ENVIRONMENT.equals("stage")) {

		} else if (ENVIRONMENT.equals("prod")) {
			LYVEBEE_BASE_PAGE_URL = "https://www.lyvebee.com/";
			DEFAULT_CUSTOMER_USER_EMAIL = "ripipe@getnada.com";
			DEFAULT_INSTRUCTOR_USER_USERNAME = "xidyw@getnada.com";
		}
	}
}

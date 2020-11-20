package web.test.lyvebee_testsuite.contants;

public class TestConstant {
	public static String LYVEBEE_BASE_PAGE_URL = "https://staging-ssr.lyvebee.com/";
	public static String CLASSPAGE_URL = "https://www.lyvebee.com/classes";

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
	public static String DEFAULT_INSTRUCTOR_SIGNUP_USER_PHONENUMBER = "+12058812777";
	public static final String ENVIRONMENT = System.getProperty("testenv", "stage");

	// Time Zone
	public static String TIMEZONE_HAWAII = "(GMT-10:00) Hawaii";
	public static String TIMEZONE_PACIFICTIME = "(GMT-08:00) Pacific Time (US & Canada)";
	public static String TIMEZONE_CENTRALTIME = "(GMT-06:00) Central Time (US & Canada)";
	public static String TIMEZONE_EASTERNTIME = "(GMT-05:00) Eastern Time (US & Canada)";
	public static String TIMEZONE_ATLANTICTIME = "(GMT-04:00) Atlantic Time (Canada)";
	public static String TIMEZONE_GMT = "(GMT+00:00) Greenwich Mean Time : Dublin, Edinburgh, Lisbon, London";
	public static String TIMEZONE_CHENNAITIME = "(GMT+05:30) Chennai, Kolkata, Mumbai, New Delhi";
	public static String TIMEZONE_BANGKOKTIME = "(GMT+07:00) Bangkok, Hanoi, Jakarta";
	public static String TIMEZONE_SINGAPORE = "(GMT+08:00) Kuala Lumpur, Singapore";
	public static String TIMEZONE_MELBOURNETIME = "(GMT+11:00) Canberra, Melbourne, Sydney";
	// AdditionalTZ
	public static String TIMEZONE_ALASKA = "(GMT-09:00) Alaska";
	public static String TIMEZONE_BRASILIA = "(GMT-03:00) Brasilia";
	public static String TIMEZONE_BERLIN = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
	public static String TIMEZONE_BEIRUT = "(GMT+02:00) Beirut";
	public static String TIMEZONE_KUWAIT = "(GMT+03:00) Kuwait, Riyadh, Baghdad";
	public static String TIMEZONE_ABUDHABI = "(GMT+04:00) Abu Dhabi, Muscat";
	public static String TIMEZONE_TOKYO = "(GMT+09:00) Osaka, Sapporo, Tokyo";
	public static String TIMEZONE_BRISBANE = "(GMT+10:00) Brisbane";

	static {
		if (ENVIRONMENT.equals("stage")) {
			CLASSPAGE_URL = "https://staging-ssr.lyvebee.com/classes";
		} else if (ENVIRONMENT.equals("prod")) {
			LYVEBEE_BASE_PAGE_URL = "https://www.lyvebee.com/";
			DEFAULT_CUSTOMER_USER_EMAIL = "ripipe@getnada.com";
			DEFAULT_INSTRUCTOR_USER_USERNAME = "xidyw@getnada.com";
		}
	}
}

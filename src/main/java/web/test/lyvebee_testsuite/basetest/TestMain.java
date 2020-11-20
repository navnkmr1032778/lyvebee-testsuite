package web.test.lyvebee_testsuite.basetest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.Status;
import com.solutionstar.swaftee.utils.CSVParserUtils;
import com.solutionstar.swaftee.utils.CommonUtils;
import com.solutionstar.swaftee.utils.NadaEMailService;
import com.solutionstar.swaftee.utils.ImageComparison.TakeScreenshot;
import com.solutionstar.swaftee.utils.ImageComparison.TakeScreenshotUtils;
import com.solutionstar.swaftee.webdriverbase.AppTest;

import web.test.lyvebee.POJO.ClassPOJO;
import web.test.lyvebee.POJO.UserPOJO;
import web.test.lyvebee_testsuite.contants.TestConstant;
import web.test.pom.instructor.Instructor_AddNewSpeciality_Page;
import web.test.pom.instructor.Instructor_AddPaymentMethod_Page;
import web.test.pom.instructor.Instructor_CreateClass_Page;
import web.test.pom.instructor.Instructor_DeleteAccountFromAPI_Page;
import web.test.pom.instructor.Instructor_ParterOnBoarding_Page;
import web.test.pom.instructor.Instructor_PhoneNumber_Validation_Page;
import web.test.pom.instructor.Instructor_ViewMyClasses_Page;
import web.test.pom.main.Main_Page;
import web.test.pom.main.SignIn_Page;
import web.test.pom.main.SignUp_Page;
import web.test.pom.main.User_ClassDetail_Page;
import web.test.pom.main.User_Home_Page;
import web.test.pom.main.User_Notifications_Page;
import web.test.pom.main.User_Payment_Page;
import web.test.pom.main.User_Profile_Page;
import web.test.pom.main.User_SearchClasses_Page;
import web.test.pom.main.User_ViewMySessions_Page;

public class TestMain extends AppTest {

	public static Logger logger = LoggerFactory.getLogger(TestMain.class);

	public CommonUtils utils = new CommonUtils();
	public CSVParserUtils csvParser = new CSVParserUtils();
	public static NadaEMailService nadaMailService = new NadaEMailService();

	// Data
	public static List<ClassPOJO> classListCreated = new ArrayList<ClassPOJO>();
	public static List<ClassPOJO> bookedClassListByCustomer = new ArrayList<ClassPOJO>();
	public static List<ClassPOJO> bookedAndCancelledClassListByCustomer = new ArrayList<ClassPOJO>();
	public static List<UserPOJO> userEmailListCreated = new ArrayList<UserPOJO>();
	public static List<UserPOJO> instructorUserEmailListCreated = new ArrayList<UserPOJO>();
	public static List<UserPOJO> userSessionURL = new ArrayList<UserPOJO>();
	public static ClassPOJO timeZoneClass;
	// Main Pages
	public Main_Page mainPage;

	// SignUp/SignIn Pages
	public SignUp_Page signUpPage;
	public SignIn_Page signInPage;

	// User's Pages
	public User_Home_Page userHomePage;
	public User_Profile_Page userProfilePage;
	public User_SearchClasses_Page userSearchClassesPage;
	public User_ClassDetail_Page userClassDetailPage;
	public User_Payment_Page userPaymentPage;
	public User_Notifications_Page userNotificationPage;
	public User_ViewMySessions_Page userViewMySessionsPage;

	// Instructor Pages
	public Instructor_CreateClass_Page instructorCreateClassesPage;
	public Instructor_ViewMyClasses_Page instructorViewMyClassesPage;
	public Instructor_PhoneNumber_Validation_Page instructorPhoneNumberValidationPage;
	public Instructor_AddPaymentMethod_Page instructorAddPaymentMethodPage;
	public Instructor_AddNewSpeciality_Page instructorAddNewSpecialityPage;
	public Instructor_DeleteAccountFromAPI_Page instructorDeleteAcccountFromAPI;
	public Instructor_ParterOnBoarding_Page instructorPartnerOnBoardigPage;

	@BeforeTest
	public void beforeTest() {
		spark.config().enableTimeline(true);
		spark.config().setDocumentTitle("LyveBee Test Report");
		spark.config().setReportName("LyveBee Automation Test Report");

		extent.setSystemInfo("Environment", TestConstant.ENVIRONMENT);
		extent.setSystemInfo("URL", TestConstant.LYVEBEE_BASE_PAGE_URL);
		extent.setSystemInfo("Browser", "Chrome");
	}

	@BeforeClass
	public void beforeClass() {
		signUpPage = new SignUp_Page(getDriver());
		signInPage = new SignIn_Page(getDriver());

		userHomePage = new User_Home_Page(getDriver());
		userProfilePage = new User_Profile_Page(getDriver());
		userSearchClassesPage = new User_SearchClasses_Page(getDriver());
		userClassDetailPage = new User_ClassDetail_Page(getDriver());
		userPaymentPage = new User_Payment_Page(getDriver());
		userNotificationPage = new User_Notifications_Page(getDriver());
		userViewMySessionsPage = new User_ViewMySessions_Page(getDriver());

		instructorCreateClassesPage = new Instructor_CreateClass_Page(getDriver());
		instructorViewMyClassesPage = new Instructor_ViewMyClasses_Page(getDriver());
		instructorPhoneNumberValidationPage = new Instructor_PhoneNumber_Validation_Page(getDriver());
		instructorAddPaymentMethodPage = new Instructor_AddPaymentMethod_Page(getDriver());
		instructorAddNewSpecialityPage = new Instructor_AddNewSpeciality_Page(getDriver());
		instructorDeleteAcccountFromAPI = new Instructor_DeleteAccountFromAPI_Page(getDriver());
		instructorPartnerOnBoardigPage = new Instructor_ParterOnBoarding_Page(getDriver());
	}

	public void loadSearchClassPage() {
		mainPage.gotoURL(TestConstant.CLASSPAGE_URL);
	}

	public void loadMainPage() {
		if (hasDriver() && mainPage != null) {
			mainPage = mainPage.loadMainPage(TestConstant.LYVEBEE_BASE_PAGE_URL);
		}
		if (!hasDriver() || mainPage == null) {
			getDriver().get(TestConstant.LYVEBEE_BASE_PAGE_URL);
			mainPage = new Main_Page(getDriver());
		}
		getDriver().manage().window().maximize();
	}

	public void loadDeleteAccountFromAPIMainPage() {
		if (hasDriver() && mainPage != null) {
			mainPage = mainPage.loadMainPage(TestConstant.LYVEBEE_BASE_PAGE_URL);
		}
		if (!hasDriver() || mainPage == null) {
			getDriver().get(TestConstant.LYVEBEE_BASE_PAGE_URL);
			mainPage = new Main_Page(getDriver());
		}
		getDriver().manage().window().maximize();
	}

	public int getIndexOfClassWithName(String className) {
		int i = 0;
		for (ClassPOJO classes : classListCreated) {
			if (classes.getClassName().equals(className))
				return i;
			else
				i++;
		}
		return -1;
	}

	public ClassPOJO getClassPojo() {
		ClassPOJO classPojo = new ClassPOJO("AutoClass" + CommonUtils.getCurrentTimeString(), "AutoClassDescription",
				"11:00PM", "11:30PM", "AutoInstructorName", "12", "This Week", "20");
		if (TestConstant.ENVIRONMENT.equals("prod"))
			classPojo.setClassCost("0");
		return classPojo;
	}

	public UserPOJO getDefaultInstructorUser() {
		UserPOJO user = new UserPOJO();
		user.setUserEmail(TestConstant.DEFAULT_INSTRUCTOR_USER_USERNAME);
		user.setUserName("Naveen");
		return user;
	}

	public UserPOJO getDefaultInstructorSignUpUser() {
		UserPOJO user = new UserPOJO();
		user.setUserEmail(TestConstant.DEFAULT_INSTRUCTOR_SIGNUP_USER_USERNAME);
		user.setUserName("Naveen");
		return user;
	}

	public UserPOJO getAutoInstructorUser() {
		UserPOJO user = new UserPOJO();
		user.setUserName("AutoName");
		user.setUserEmail("synibular@dropjar.com");
		return user;
	}

	public UserPOJO getDefaultCustomerUser() {
		UserPOJO user = new UserPOJO();
		user.setUserEmail(TestConstant.DEFAULT_CUSTOMER_USER_EMAIL);
		user.setUserName("AutoName");
		return user;
	}

	public UserPOJO getAutoConsumerUser() {
		UserPOJO user = new UserPOJO();
		user.setUserName("AutoName");
		user.setUserEmail("auto" + CommonUtils.getCurrentTimeString() + NadaEMailService.NADA_EMAIL_DOMAIN);
		return user;
	}

	public static void log(String message) {
		test.log(Status.INFO, message);
		logger.info(message);
	}

	public void takeScreenShot(String fileName) {
		WebDriver driver = getDriver();
		if (driver != null) {
			TakeScreenshot ts = new TakeScreenshotUtils(false, "", "", false);
			ts.captureScreenShot(driver, fileName);
			utils.captureFullBrowserScreenShot(fileName, driver);
		} else {
			logger.info("Couldn't take screenshot.. No driver found.");
		}

	}

	public void takeScreenShot() {
		WebDriver driver = getDriver();
		if (driver != null) {
			String fileName = null;
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			fileName = fileName + stackTraceElements[2].getMethodName() + ".png";
			TakeScreenshot ts = new TakeScreenshotUtils(false, "", "", false);
			ts.captureScreenShot(driver, fileName);
			fileName = fileName.replace(".png", "");
			utils.captureFullBrowserScreenShot(fileName, driver);
		} else {
			logger.info("Couldn't take screenshot.. No driver found.");
		}

	}

	public void afterTest() {
		stopDriver();
	}

	public void fail(String message) {
		String additionalDetails = "test failed";
		Assert.fail(message + additionalDetails);
		// takeScreenShot(WebDriverConstants.PATH_TO_BROWSER_SCREENSHOT + message +
		// ".png");
	}

	public void fail(String message, Exception e) {
		if (e instanceof SkipException)
			throw new SkipException(e.getMessage());
		if (e instanceof UnhandledAlertException) {
			try {
				Alert a = getDriver().switchTo().alert();
				String alertMessage = a.getText();
				a.accept();
				message += " \nMessage from unhandled alert box: \n" + alertMessage;
			} catch (NoAlertPresentException ex) {
			}
		}
		fail(message + "\nMessage from Exception:\n" + ExceptionUtils.getRootCauseStackTrace(e));
	}
}

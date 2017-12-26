package kz.epam.atm.gmailtestPF.property;

public class GlobalConstants {

    static final String CONFIG_PROPERTIES_PATH = "src\\main\\resources\\config.properties";
    public static final int EXPLICIT_WAIT_TIMEOUT = 5;
    public static final int PAGE_LOAD_TIMEOUT = 15;
    public static final int IMPLICIT_WAIT_TIMEOUT = 5;
    public static final int GENERATOR_BOUND = 1000;
    public static final String LOGIN_FAIL_ERR_MSG = "Login failed.";
    public static final String LOGOUT_FAIL_ERR_MSG = "Logout failed.";
    public static final String DRAFT_EMAIL_ABSENCE_ERR_MSG = "Draft email not found.";
    public static final String INCORRECT_RECIPIENT_ERR_MSG = "Email recipient is not equal.";
    public static final String INCORRECT_SUBJECT_ERR_MSG = "Email Subject is not equal.";
    public static final String INCORRECT_BODY_ERR_MSG = "Email body is not equal.";
    public static final String DRAFT_EMAIL_PRESENCE_ERR_MSG = "Found draft email in the draft folder.";
    public static final String EMPTY_SENT_FOLDER_ERR_MSG = "Sent folder is empty.";
    public static final String SENT_EMAIL_PRESENCE_ERR_MSG = "Found sent email in the sent folder";
    public static final String EMAIL_DELETION_ERR_MSG = "Email deletion failed.";
    public static final String SCREENSHOTS_PATH_AND_NAME = "./target/screenshots/screenshot-";
    public static final String PNG = ".png";
    public static int scrCount = 0;
}


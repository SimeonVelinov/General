package test.cases.trello;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.restUtils.restUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;
import pages.trello.LoginPage;

public class BaseTest {

    static UserActions actions = new UserActions();
    protected static BoardsPage boardsPage;
    protected static BoardPage boardPage;

    @BeforeClass
    public static void setUp() {
        restUtils.create_Workspace();
        restUtils.setup_Board();
        UserActions.loadBrowser("trello.homePage");
        login();
        boardsPage = new BoardsPage(actions.getDriver());
        boardPage = new BoardPage(actions.getDriver());

    }

    @AfterClass
    public static void tearDown() {
        UserActions.quitDriver();
        restUtils.delete_Workspace();
    }

    public static void login() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser("user");
    }

}

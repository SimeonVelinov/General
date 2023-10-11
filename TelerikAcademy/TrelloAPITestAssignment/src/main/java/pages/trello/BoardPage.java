package pages.trello;

import com.telerikacademy.testframework.Utils;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }

    public void addCardToList(String cardName) {

        actions.waitForElementClickable("trello.boardPage.textArea");
        actions.typeValueInField(cardName, "trello.boardPage.textArea");
        actions.clickElement("trello.boardPage.submitCard");
        actions.assertElementPresent("trello.boardPage.cardContainer");

    }

    public void moveCardToList(String cardName, String listName) {
        String card = String.format(getUIMappingByKey("trello.boardPage.cardByName"), cardName);
        String list = String.format(getUIMappingByKey("trello.boardPage.listByName"), listName);
        actions.clickAndDragElement(card, list);
    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertAddedCard() {
        String cardName = Utils.getUIMappingByKey("trello.cardName");

        actions.waitForElementClickable("trello.boardPage.addedCardTitle");
        actions.assertElementAttribute("trello.boardPage.addedCardTitle", "innerText", cardName);
    }

    public void assertCardMoved() {
        String cardName = Utils.getUIMappingByKey("trello.cardName");
        String locator = String.format(Utils.getUIMappingByKey("trello.boardPage.listByCard"), cardName);
        String location = Utils.getUIMappingByKey("trello.boardPage.secondList");

        actions.waitForElementVisible("trello.boardPage.cardByName", cardName);
        actions.assertElementAttribute(locator, "innerText", location);
    }

    public void createList(String listName) {
        actions.waitForElementVisible("trello.boardPage.listNameInput");
        actions.typeValueInField(listName, "trello.boardPage.listNameInput");
        actions.clickElement("trello.boardPage.addListButton");
    }

    public void deleteBoard(){
        actions.waitForElementClickable("trello.menuButton");
        actions.clickElement("trello.menuButton");
        actions.waitForElementClickable("trello.closeBoard");
        actions.clickElement("trello.closeBoard");
        actions.waitForElementClickable("trello.confirmClose");
        actions.clickElement("trello.confirmClose");
        actions.waitForElementClickable("trello.permaConfirmClose");
        actions.clickElement("trello.permaConfirmClose");
        actions.waitForElementPresent("trello.deleteMessage");
        actions.waitForElementClickable("trello.confrimPermaDelete");
        actions.clickElement("trello.confrimPermaDelete");
    }
}

package test.cases.trello;

import com.telerikacademy.testframework.Utils;
import org.junit.Ignore;
import org.junit.Test;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;


public class BoardTest extends BaseTest {


    @Test
    public void createBoardWhenCreateBoardClicked() {
        actions.clickElement("trello.homeButton");
        actions.waitForElementClickable("trello.create.boardButton");
        boardsPage.createBoard();
        try {
            boardPage.assertListExists("To Do");
        } catch (AssertionError e) {
            boardPage.createList("To Do");
            boardPage.createList("Doing");
        }
    }

    @Test
    public void createNewCardInExistingBoardWhenCreateCardClicked() {
        try {
            actions.assertElementPresent("trello.sendCard");
        } catch (Exception e) {
            try {
                actions.assertElementPresent("trello.boardNameDsp");
            } catch (Exception a) {
                actions.clickElement("trello.preBoard");
            }
            try {
                actions.clickElement("trello.boardPage.addCardButton");
            }
            catch (Exception b) {
                actions.clickElement("trello.preBoard");
                actions.waitForElementClickable("trello.boardPage.addCardButton");
                actions.clickElement("trello.boardPage.addCardButton");
            }
        }
        boardPage.addCardToList(getUIMappingByKey("trello.cardName"));
    }

    @Test
    public void moveCardBetweenStatesWhenDragAndDropIsUsed() {
        String listName = Utils.getUIMappingByKey("trello.boardPage.firstList");
        String cardName = Utils.getUIMappingByKey("trello.cardName");
        String location = Utils.getUIMappingByKey("trello.boardPage.secondList");

        try {
            actions.assertElementPresent("trello.boardNameDsp");
        } catch (Exception a) {
            actions.clickElement("trello.preBoard");
        }
        try {
            boardPage.assertListExists(listName);
        } catch (AssertionError e) {
            actions.clickElement("trello.preBoard");
            boardPage.assertListExists(listName);
        }
        try {
            boardPage.addCardToList(cardName);
        } catch (AssertionError e) {
            actions.clickElement("trello.boardPage.addCardButton");
            boardPage.addCardToList(cardName);
        }
        boardPage.assertAddedCard();

        boardPage.moveCardToList(cardName, location);
        boardPage.assertCardMoved();
    }


    @Test
    public void deleteBoardWhenDeleteButtonIsClicked() {
        boardsPage.createBoard();
        actions.waitForElementPresent("trello.boardNameDsp");
        boardPage.deleteBoard();
        actions.waitForElementPresent("trello.boardsList");
        actions.assertElementPresent("trello.boardsList");
    }
}

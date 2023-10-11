package com.telerikacademy.testframework.restUtils;

public class Endpoints {
    public static final String VERIFY_CONN =
            String.format("%s/members/me?key=%s&token=%s",
                    Constants.BASE_URL,
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
    public static final String CREATE_WORKSPACE =
            String.format("%s/organizations?displayName=%s&key=%s&token=%s",
                    Constants.BASE_URL,
                    Constants.WORKSPACE_NAME,
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
    public static final String DELETE_WORKSPACE =
            String.format("%s/organizations/%s?key=%s&token=%s",
                    Constants.BASE_URL,
                    "%s",
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
    public static final String CREATE_BOARD =
            String.format("%s/boards/?name=%s&key=%s&token=%s&defaultLists=false",
                    Constants.BASE_URL,
                    Constants.BOARD_NAME,
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
    public static final String CREATE_LIST =
            String.format("%s/lists?name=%s&idBoard=%s&key=%s&token=%s",
                    Constants.BASE_URL,
                    "%s",
                    "%s",
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
    public static final String CREATE_CARD =
            String.format("%s/cards?name=%s&idList=%s&key=%s&token=%s",
                    Constants.BASE_URL,
                    Constants.CARD_NAME,
                    Constants.LIST_ID,
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
    public static final String UPDATE_CARD =
            String.format("%s/cards/%s?key=%s&token=%s",
                    Constants.BASE_URL,
                    Constants.CARD_ID,
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
    public static final String DELETE_BOARD =
            String.format("%s/boards/%s?key=%s&token=%s",
                    Constants.BASE_URL,
                    Constants.BOARD_ID,
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
    public static final String DELETE_ALL_BOARDS =
            String.format("%s/boards/%s?key=%s&token=%s",
                    Constants.BASE_URL,
                    "%s",
                    Constants.TRELLO_KEY,
                    Constants.TRELLO_TOKEN);
}


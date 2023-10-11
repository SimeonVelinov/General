package com.telerikacademy.testframework.restUtils;

import io.restassured.response.Response;

import static com.telerikacademy.testframework.restUtils.Constants.WORKSPACE_ID;
import static com.telerikacademy.testframework.restUtils.Endpoints.*;
import static io.restassured.RestAssured.given;

public class restUtils {

    public static void create_Workspace() {
        Response response = given()
                .body("")
                .post(CREATE_WORKSPACE);
        WORKSPACE_ID = response.jsonPath().get("id");
    }

    public static void delete_Workspace() {
        given()
                .body("")
                .delete(String.format(DELETE_WORKSPACE, WORKSPACE_ID));
    }

    public static void setup_Board () {
        String boardId;
        Response response = given()
                .body("")
                .post(CREATE_BOARD);
        boardId = response.jsonPath().get("id");
        given()
                .body("")
                .post(String.format(CREATE_LIST, "To Do", boardId));
        given()
                .body("")
                .post(String.format(CREATE_LIST, "Doing", boardId));
    }

}

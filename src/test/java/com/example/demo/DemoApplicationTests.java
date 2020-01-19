package com.example.demo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import io.restassured.RestAssured;

public class DemoApplicationTests {

    @BeforeClass
    public static void beforeClass() throws Exception {
        // WebDriverのパスを設定
//        System.setProperty("webdriver.ie.driver", "C:\\SeleniumDriver\\IEDriverServer.exe");
//        System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDriver\\geckodriver.exe");
//        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDriver\\chromedriver.exe");

        // テスト結果の出力先
        Configuration.reportsFolder = "test-result/reports";

        // 使用するブラウザの指定
//        Configuration.browser = WebDriverRunner.INTERNET_EXPLORER;
//        Configuration.browser = WebDriverRunner.FIREFOX;
        Configuration.browser = WebDriverRunner.CHROME;
    }

    @Test
    public void test() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com/";
        given()
                .get("/posts/2")
                .then()
                .body("userId", equalTo(1))
                .body("id", equalTo(2))
                .body("title", equalTo("qui est esse"))
        ;

        given().get("/posts/2").prettyPrint();

    }
/*
    @Test
    public void test2 () {
    	 Configuration.browser = WebDriverRunner.CHROME;

    	    Selenide.open("http://www.google.com/");

    	    // Google検索
    	    SelenideElement element = Selenide.$(By.name("q"));
    	    element.val("selenide").pressEnter();
    }
    */

}
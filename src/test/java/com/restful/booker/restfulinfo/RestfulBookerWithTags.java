package com.restful.booker.restfulinfo;

import com.restful.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RestfulBookerWithTags extends TestBase {

    @WithTag("restfulbooker:NEGATIVE")
    @Title("This method gets error code 404 when a method is used without resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given().log().all()
                .when()
                .patch()
                .then()
                .statusCode(404).log().all();
    }

    @WithTags({@WithTag("restfulbooker:NEGATIVE"),
            @WithTag("restfulbooker:REGRESSION")})
    @Title("This test provides an error code 403 when user doesn't give authentication")
    @Test
    public void authenticationMissing() {
        SerenityRest.rest().
                given().log().all()
                .when().delete("/id")
                .then().log().all().statusCode(403);
    }

    @WithTags({@WithTag("restfulbooker:POSITIVE"),
            @WithTag("restfulbooker:REGRESSION")})
    @Title("This test returns a valid response code 200 when GET request is used")
    @Test
    public void verifingStatusCodeIsCorrect() {
        SerenityRest.given()
                .when()
                .get()
                .then().log().all().statusCode(200);
    }
}

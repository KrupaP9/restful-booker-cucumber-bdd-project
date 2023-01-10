package com.restful.booker.cucumber.steps;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.restfulinfo.RestfulSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyCURDSteps {

    static ValidatableResponse response;
    static int id;
    static int totalprice;

    static String token;

    @Steps
    RestfulSteps restfulSteps;


    @When("^I create a new booking with firstName\"([^\"]*)\", lastName\"([^\"]*)\", totalprice \"([^\"]*)\", depositpaid \"([^\"]*)\", checkin \"([^\"]*)\", checkout\"([^\"]*)\" additionalneeds \"([^\"]*)\"$")
    public void iSendPOSTRequestToCreateANewBookingWithFirstNameLastNameTotalpriceDepositpaidCheckinCheckoutAdditionalneeds(String firstName, String lastName, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        totalprice = 400;
        response = restfulSteps.createBooking(firstName, lastName, totalprice, depositpaid, bookingdates, additionalneeds);
        id = response.extract().path("bookingid");

    }

    @And("^I update request with firstName\"([^\"]*)\", lastName\"([^\"]*)\", totalprice \"([^\"]*)\", depositpaid \"([^\"]*)\", checkin \"([^\"]*)\", checkout\"([^\"]*)\" additionalneeds \"([^\"]*)\"$")
    public void iSendAPutRequestWithFirstNameLastNameTotalpriceDepositpaidCheckinCheckoutAdditionalneeds(String firstName, String lastName, String _totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        response = restfulSteps.getTokken();
        token = response.extract().path("token");
        totalprice = 200;
        BookingPojo.BookingDates bookingdates = new BookingPojo.BookingDates();
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        response = restfulSteps.updateBookingWithID(id, token, firstName, lastName, totalprice, depositpaid, bookingdates, additionalneeds);
    }

    @And("^The price \"([^\"]*)\" is successfully updated$")
    public void thePriceIsSuccessfullyUpdated(String _id) {
        response = restfulSteps.getSingleBookingIDs(id);
        HashMap<String, ?> booking = response.extract().path("");
        Assert.assertThat(booking, hasValue(totalprice));
    }


    @Then("^The booking is successfully deleted$")
    public void theBookingIsSuccessfullyDeleted() {
        restfulSteps.getSingleBookingIDs(id).statusCode(404);
    }

    @Then("^I verify that new booking is created$")
    public void iVerfiyThatNewBookingIsCreatedById() {
        restfulSteps.getSingleBookingIDs(id);
    }

    @And("^I send delete booking request$")
    public void iSendDeleteBookingById() {
        restfulSteps.deleteABookingID(id, token).statusCode(201);
    }
}

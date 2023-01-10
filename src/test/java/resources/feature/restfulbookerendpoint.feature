Feature: Create Read Update Delete Booking Endpoints
  As I user I want to GET, POST, PUT, and Delete booking requests

  Scenario: Verify if user can access the booking
    When I send a GET request
    Then I must get a valid response code 200

  Scenario Outline: Verify if user can create a new booking
    When I send a request to create a new booking with firstName"<firstName>", lastName"<lastName>", totalprice "<totalprice>", depositpaid "<depositpaid>", checkin "<checkin>", checkout"<checkout>" additionalneeds "<additionalneeds>"
    Then I must get back a valid response code 201
    And I verify that new booking is created by "id"
    Examples:
      | firstName | lastName | totalprice | depositpaid | checkin    | checkout   | additionalneeds     |
      | Kajal     | Kajal    | 100        | true        | 2023-11-01 | 2023-11-05 | Breakfast and Lunch |

  Scenario: Verify if user can update a booking
    When I update a request with firstName"<firstName>", lastName"<lastName>", totalprice "<totalprice>", depositpaid "<depositpaid>", checkin "<checkin>", checkout"<checkout>" additionalneeds "<additionalneeds>"
    Then I must get a valid response code 200

  Scenario: Verify if user can delete an existing record
    When I send delete request
    Then I should see the response code 201

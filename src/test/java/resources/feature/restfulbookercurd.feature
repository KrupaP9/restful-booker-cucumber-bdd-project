Feature: Restful Booker CRUD Test
  As a user I want to test create a booking, read details, update and delete the booking

  Scenario Outline: Create, Read, Update, Delete the Booking
    When I create a new booking with firstName"<firstName>", lastName"<lastName>", totalprice "<totalprice>", depositpaid "<depositpaid>", checkin "<checkin>", checkout"<checkout>" additionalneeds "<additionalneeds>"
    Then I verify that new booking is created
    And I update request with firstName"<firstName>", lastName"<lastName>", totalprice "<totalprice>", depositpaid "<depositpaid>", checkin "<checkin>", checkout"<checkout>" additionalneeds "<additionalneeds>"
    And The price "<totalprice>" is successfully updated
    And I send delete booking request
    Then The booking is successfully deleted
    Examples:
      | firstName | lastName | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Kajal     | Patel    | 200        | true        | 2023-11-01 | 2023-11-05 | Lunch           |
      | Jiyal     | Patel    | 100        | false       | 2023-10-11 | 2023-10-21 | Dinner          |




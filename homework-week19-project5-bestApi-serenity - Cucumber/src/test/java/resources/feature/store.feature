@store
Feature: Testing different request on the store

  Scenario: I get all stores from application
    Given I am on homepage of application of store
    When I send Get request to list endpoint of store
    Then I must get back a valid status code 200 of store

  Scenario: I create store from application
    Given I am on homepage of application of store
    When I send Post request to list endpoint of store
    Then I must get back a valid status code 201 of store

  Scenario: I update store from application
    Given I am on homepage of application of store
    When I send Put request to list endpoint of store
    Then I must get back a valid status code 200 of store

  Scenario: I delete store from application
    Given I am on homepage of application of store
    When I send Delete request to list endpoint of store
    Then I must get back a valid status code 200 of store
    And I validate if store is deleted

  @Test
  Scenario: I verify following data response
    Given I am on homepage of application of store
    When I send Get request to list endpoint of store
  Then Verify the if the total is equal to 1561
  And Verify the if the stores of limit is equal to 10
  And Check the single Name in the Array list (Inver Grove Heights)
  And Check the multiple Names in the ArrayList (Roseville, Burnsville, Maplewood)
  And Verify the storied=8 inside storeservices of the third store of second services
  And Check hash map values createdAt inside storeservices map where store name = Roseville
  And Verify the state = MN of forth store
  And Verify the store name = "Oakdale" of nineth store
  And Verify the storeId = 11 for the sixth store
  And  Verify the serviceId = 4 for the seventh store of forth service

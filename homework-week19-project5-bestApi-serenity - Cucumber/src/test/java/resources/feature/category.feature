@category
Feature: Testing different request on the product

  Scenario: I get all categories from application
    Given I am on homepage of application of category
    When I send Get request to list endpoint of category
    Then I must get back a valid status code 200 of category

  Scenario: I create categories from application
    Given I am on homepage of application of category
    When I send Post request to list endpoint of category
    Then I must get back a valid status code 201 of category

  Scenario: I update categories from application
    Given I am on homepage of application of category
    When I send Put request to list endpoint of category
    Then I must get back a valid status code 200 of category

  Scenario: I delete categories from application
    Given I am on homepage of application of category
    When I send Delete request to list endpoint of category
    Then I must get back a valid status code 200 of category
    And I validate if category is deleted


@product
Feature: Testing different request on the product

  Scenario: I get all products from application
    Given I am on homepage of application of product
    When I send Get request to list endpoint of product
    Then I must get back a valid status code 200 of product

  Scenario: I create product from application
    Given I am on homepage of application of product
    When I send Post request to list endpoint of product
    Then I must get back a valid status code 201 of product

  Scenario: I update product from application
    Given I am on homepage of application of product
    When I send Put request to list endpoint of product
    Then I must get back a valid status code 200 of product

  Scenario: I delete product from application
    Given I am on homepage of application of product
    When I send Delete request to list endpoint of product
    Then I must get back a valid status code 200 of product
    And I validate if product is deleted


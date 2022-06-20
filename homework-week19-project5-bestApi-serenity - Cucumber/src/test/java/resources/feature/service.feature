@service
Feature: Testing different request on the service

  Scenario: I get all services from application
    Given I am on homepage of application of service
    When I send Get request to list endpoint of service
    Then I must get back a valid status code 200 of service

  Scenario: I create service from application
    Given I am on homepage of application of service
    When I send Post request to list endpoint of service
    Then I must get back a valid status code 201 of service

  Scenario: I update service from application
    Given I am on homepage of application of service
    When I send Put request to list endpoint of service
    Then I must get back a valid status code 200 of service

  Scenario: I delete service from application
    Given I am on homepage of application of service
    When I send Delete request to list endpoint of service
    Then I must get back a valid status code 200 of service
    And I validate if service is deleted


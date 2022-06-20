@utility
Feature: Testing different request on the utility

  Scenario: I get  heathCheck of application
    Given I am on homepage of application of utility
    When I send Get request to list endpoint of heathCheck

  Scenario: I get version of application
    Given I am on homepage of application of utility
    When I send Get request to list endpoint of version




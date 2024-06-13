@pet @regression
Feature: User create update delete pet to execute end to end test

  Scenario: User create update delete pet
    Given User create a pet using the Json file
    When User create a pet using Pojo pet class structure
    And User gets Created previous test's pet calling details
    And User update previous test's created pet
    And User check previous test's pet calling details Schema validation
    And User delete first created test pet
    Then User check deleted pet information

@pet
Feature: User create update delete pet to execute end to end test

  Scenario: User create update delete pet
    Given User create a pet using the Json file
    When User create a pet using Pojo pet class structure
    Then User gets Created previous test's pet calling details
    Then User update previous test's created pet
    Then User check previous test's pet calling details Schema validation
    Then User delete first created test pet
    Then User check deleted pet information

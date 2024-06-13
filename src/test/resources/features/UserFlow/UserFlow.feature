@user @smoke @regression
Feature: User create update delete pet to execute end to end test

  Scenario: User create update delete user
    Given User create a user on the page
    When User get created user credentials
    And User update the created user
    And User get updated user
    And User delete the updated user
    And User delete first created user
    And User get deleted user
    Then User check user is deleted

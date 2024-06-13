@user
Feature: User create update delete pet to execute end to end test

  Scenario: User create update delete user
    Given User create a user on the page
    When User get created user credentials
    Then User update the created user
    Then User get updated user
    Then User delete the updated user
    Then User delete first created user
    Then User get deleted user
    Then User check user is deleted

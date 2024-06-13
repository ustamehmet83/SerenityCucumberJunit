@DDTPet
Feature: User create update delete pet to execute end to end test

  Scenario Outline: User create update delete user
    Given User get multiple pet with <id> and "<name>"

    Examples:
    |id|name|
    |222|tex|
    |333|doggie|
    |444|karabas|
    |555|kral|
    |666|Aboba|



@Login
Feature: Login feature
  I want to use this template for my feature file

  @LoginInvalidCredentials
  Scenario Outline: Login with invalid credentials
    Given I am on Langing page
    When I click on Login
    And I enter "<user_name>" and "<password>" 
    Then I am successfully logged "false"

    Examples:
    |user_name |password|
    |John      |Dow     |
    |Anne      |Ddfg    |

@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorVaidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with username <userid> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | userid  									| password 	|
      | frozenfreebee@gmail.com 	| Sourav@1 |
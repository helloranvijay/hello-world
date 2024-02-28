
@tag
Feature: Purchase the Order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <userid> and password <password>
    When I add product <productName> to the Cart
    And Checkout <productName> and submit the Order
    Then "THANKYOU FOR THE ORDER." Message is displayed on Confirmation Page

    Examples: 
      | userid  									| password 	| productName |
      | frozenfreebee@gmail.com 	| Sourav@18 | ZARA COAT 3 |

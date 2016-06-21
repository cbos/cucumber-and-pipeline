Feature: Welcome page options
  Welcome page gives the choice to go to a new request or open the list for the status of the entered requests.
  
  Scenario: User opens new request
    Given A user opens the welcome page
    When the user clicks on the choice for new request
    Then the new request page is opened

  Scenario: User opens credit status
    Given A user opens the welcome page
    When the user clicks on the choice for credit status
    Then the credit status page is opened 
Feature: New credit request
  On the credit request page new requests can be entered

  Scenario: User enters a new request
    Given A user opens the welcome page
    And the user clicks on the choice for new request
    When the user enters a new request with:
      | Naam    | Adres          | Postcode | Plaats     | Bedrag |
      | Quintor | Maanlander 14m | 3824 MP  | Amersfoort | 10000  |
    Then the message "Credit request ontvangen" is shown
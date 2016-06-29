Feature: Status of credit requests
  The status page shows the status of the requests and gets refreshed periodically

  Scenario: User can see the status of his new request
    Given A user opens the welcome page
    And the user clicks on the choice for new request
    When the user enters a new request with:
      | Naam    | Adres          | Postcode | Plaats     | Bedrag |
      | Quintor | Maanlander 14m | 3824 MP  | Amersfoort | 10000  |
    And the user opens the status page in the menu
    Then the newest request has name "Quintor" and has status "ONTVANGEN"
    And after some waiting the status become "IN_BEHANDELING"
    And after some waiting the status become "AKKOORD"


  Scenario: User can see the status of his new request
    Given A user opens the welcome page
    And the user clicks on the choice for new request
    When the user enters a new request with:
      | Naam    | Adres                 | Postcode | Plaats    | Bedrag  |
      | Quintor | Ubbo Emmiussingel 112 | 9711 BK  | Groningen | 2000000 |
    And the user opens the status page in the menu
    Then the newest request has name "Quintor" and has status "ONTVANGEN"
    And after some waiting the status become "IN_BEHANDELING"
    And after some waiting the status become "AFGEWEZEN"


    
    
    
    
    
    
    
    
    
    
    
    
    
  Scenario Outline: User can see the status of his new request
    Given A user opens the welcome page
    And the user clicks on the choice for new request
    When the user enters a new request with:
      | Naam   | Adres   | Postcode   | Plaats   | Bedrag   |
      | <Naam> | <Adres> | <Postcode> | <Plaats> | <Bedrag> |
    And the user opens the status page in the menu
    Then the newest request has name "<Naam>" and has status "ONTVANGEN"
    And after some waiting the status become "IN_BEHANDELING"
    And after some waiting the status become "<EindStatus>"

    Examples:
      | Naam               | Adres                 | Postcode | Plaats     | Bedrag  | EindStatus |
      | Quintor Amersfoort | Maanlander 14m        | 3824 MP  | Amersfoort | 10000   | AKKOORD    |
      | Quintor Groningen  | Ubbo Emmiussingel 112 | 9711 BK  | Groningen  | 2000000 | AFGEWEZEN  |
      | Quintor Den Haag   | Koninginnegracht 19   | 2514 AB  | Den Haag   | 12345   | AKKOORD    |
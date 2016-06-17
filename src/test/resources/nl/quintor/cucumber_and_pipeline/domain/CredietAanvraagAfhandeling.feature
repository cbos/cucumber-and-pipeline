# language: nl

Functionaliteit: Afhandelen crediet aanvraag

  Crediet aanvragen worden automatisch afgehandeld met behulp van een aantal regels.
  De belangrijkste controle is de hoogte van het bedrag.

  Scenario:
    Stel klant Bos doet nieuwe aanvraag doet voor 10000
    Als de aanvraag in behandeling wordt genomen
    Dan moet de status "IN_BEHANDELING" zijn

  Scenario:
    Stel klant Bos doet nieuwe aanvraag doet voor 10000
    Als de aanvraag wordt verwerkt
    Dan moet de status "AKKOORD" zijn

  Scenario:
    Stel klant De Vette doet nieuwe aanvraag doet voor 1000000
    Als de aanvraag wordt verwerkt
    Dan moet de status "AFGEWEZEN" zijn

  Abstract Scenario:
    Stel klant <klant> doet nieuwe aanvraag doet voor <bedrag>
    Als de aanvraag wordt verwerkt
    Dan moet de status "<status>" zijn
    Voorbeelden:
      | klant    | bedrag  | status    |
      | Bos      | 1000000 | AFGEWEZEN |
      | De Vette | 10000   | AKKOORD   |
Feature: To verify sensibull scenario

  Scenario: Sensibull Testing Scenario
    Given User is on "https://web.sensibull.com/" homepage
    Then Click on strategies wizard
    Then Add stock as "NIFTY" and View as "ABOVE"
    When User clicks on Go button
    Then User need to click on first "1" Trade button from displayed result
    Then User need to fill lots as "22" and Click on AnalyseOption Button
    Then Verify option details are same for lots as "22" on both pages and confirm quantity matches


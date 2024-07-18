Feature: Login feature

  Scenario: Login Success
Given I open Browser
And I open Login page
When I enter email"taqimed99@gmaill.com"
And I enter password"Med-20115-010499@"
And I click submit
Then I am logged in
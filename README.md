# SauceDemoAndJiraAPIRepo
This this Automation Framework Include both API and Selenium Automation code

### Step to Clone the code and Execution
git clone https://github.com/SushmaTesting/SauceDemoAndJiraAPIRepo.git

#### To Run Selenium Test
mvn test -Pchrome,sauce-demo-test

#### To Run API Test
mvn test -Pchrome,jira-test

mvn clean test will run both selenium and API Test

Note: I have added Dummy Credential so while Executing replace with your Jira Login And Browserstack credential

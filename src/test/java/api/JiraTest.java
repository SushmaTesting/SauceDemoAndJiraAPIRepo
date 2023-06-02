package api;

import api.jiraApiMethod.JiraAPIPage;
import common.util.TestUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class JiraTest {
    String sheetName = "API";

    @DataProvider
    public Object[][] JiraTestData() {
        TestUtils testUtils = new TestUtils();
        Object data[][] = testUtils.getTestData(sheetName);
        return data;
    }

    @Test(priority = 1, dataProvider = "JiraTestData")
    public void jiraTest(String UserName, String Password, String Description, String Summary, String IssueType, String ProjectName, String Type, String value, String AddComment) throws IOException {
        JiraAPIPage jira = new JiraAPIPage();
        jira.createSession(UserName, Password);
        jira.addTicket(Description, Summary, IssueType, ProjectName);
        jira.addComment(Type, value, AddComment);
        jira.deleteTicket();
    }
}


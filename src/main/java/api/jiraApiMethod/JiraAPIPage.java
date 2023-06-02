package api.jiraApiMethod;

import static io.restassured.RestAssured.given;

import api.pojo.*;
import org.testng.log4testng.Logger;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class JiraAPIPage {
    Logger log = Logger.getLogger(JiraAPIPage.class);
    RequestSpecification request;
    SessionFilter session;
    String id;

    public void createSession(String userName, String password) {
        request = new RequestSpecBuilder().setBaseUri("http://localhost:9090").setContentType(ContentType.JSON).build();
        session = new SessionFilter();
        LoginJira loginRequest = new LoginJira();
        loginRequest.setUsername(userName);
        loginRequest.setPassword(password);
        RequestSpecification reqLogin = given().log().all().filter(session).spec(request).body(loginRequest);
        LoginresponseJira Response = reqLogin.when().post("/rest/auth/1/session").then().log().all().extract()
                .response().as(LoginresponseJira.class);
        log.info(Response.getSession().getValue());
        log.info(Response.getSession().getName());

    }

    public void addTicket(String description, String Summary, String issueType, String project) {
        Fields field = new Fields();
        field.setDescription(description);
        field.setSummary(Summary);
        Issuetype isType = new Issuetype();
        isType.setName(issueType);
        Project projectName = new Project();
        projectName.setKey(project);
        field.setIssuetype(isType);
        field.setProject(projectName);
        AddJiraField addticket = new AddJiraField();
        addticket.setFields(field);

        RequestSpecification createTicketReq = given().log().all().filter(session).spec(request).body(addticket);
        AddTicketResponse ticketRequestResponse = createTicketReq.when().post("/rest/api/2/issue").then().log().all()
                .extract().response().as(AddTicketResponse.class);
        System.out.println(ticketRequestResponse.getId());
        id = ticketRequestResponse.getId();
    }

    public void addComment(String Type, String value, String body) {
        AddComment comment = new AddComment();
        Visibility vs = new Visibility();
        vs.setType(Type);
        vs.setValue(value);
        comment.setBody(body);
        comment.setVisibility(vs);

        RequestSpecification createCommentReq = given().log().all().filter(session).spec(request).body(comment);
        String commentResp = createCommentReq.when().post("/rest/api/2/issue/" + id + "/comment").then().log().all()
                .extract().response().asString();
        System.out.println(commentResp);
    }

    public void deleteTicket() {
        RequestSpecification deleteReq = given().log().all().filter(session).spec(request);
        deleteReq.when().delete("/rest/api/2/issue/" + id + "").then().log().all().extract().response().asString();

    }
}

package com.javacourse.test.topic.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateTopicCommandTest {

    private CreateTopicCommand command;

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        command = new CreateTopicCommand();
    }

    @Test
    public void getPageDependingOnWhetherInsertIsSuccessful_deleteSuccessful_getResultingPage() {
        TopicService service = mock(TopicService.class);
        when(service.create(anyObject())).thenReturn(true);

        WebPage expected = new WebPage(WebPage.WebPageBase.TOPICS_ACTION)
                .setDispatchType(WebPage.DispatchType.REDIRECT);
        WebPage actual = command.getPageDependingOnWhetherInsertIsSuccessful(request, new Topic(), "", service);

        assertEquals(expected, actual);
    }

    @Test
    public void getPageDependingOnWhetherInsertIsSuccessful_deleteUnsuccessful_getErrorPage() {
        TopicService service = mock(TopicService.class);
        when(service.create(anyObject())).thenThrow(UnsuccessfulQueryException.class);

        WebPage expected = new WebPage(WebPage.WebPageBase.TOPICS_ADMIN_CREATE);
        WebPage actual = command.getPageDependingOnWhetherInsertIsSuccessful(request, new Topic(), "", service);

        assertEquals(expected, actual);
    }

    @Test
    public void constructTopic_returnsWellFormedTopic(){
        when(request.getParameter("name")).thenReturn("topic");

        Topic expected = new Topic(0, "topic");
        Topic actual = command.constructTopic(request);

        assertEquals(expected, actual);
    }
}
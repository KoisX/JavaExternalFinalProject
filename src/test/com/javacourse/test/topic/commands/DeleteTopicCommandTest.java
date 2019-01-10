package com.javacourse.test.topic.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.WebPage;
import com.javacourse.test.topic.TopicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTopicCommandTest {

    DeleteTopicCommand command;

    @Mock
    HttpServletRequest request;


    @Before
    public void setUp() throws Exception {
        command = new DeleteTopicCommand();
    }

    @Test
    public void getPageDependingOnWhetherDeleteIsSuccessful_deleteSuccessful_returnsProperWebpage() {
        TopicService service = mock(TopicService.class);
        when(service.delete(anyString())).thenReturn(true);
        when(request.getParameter(anyString())).thenReturn("param");

        WebPage expected = new WebPage(WebPage.WebPageBase.TOPICS_ADMIN_PAGE);
        WebPage actual = command.getPageDependingOnWhetherDeleteIsSuccessful(request, service);

        assertEquals(expected, actual);
    }

    @Test
    public void getPageDependingOnWhetherDeleteIsSuccessful_deleteUnsuccessful_returnsErrorWebpage() {
        TopicService service = mock(TopicService.class);
        when(service.delete(anyString())).thenThrow(UnsuccessfulQueryException.class);
        when(request.getParameter(anyString())).thenReturn("param");

        WebPage expected = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        WebPage actual = command.getPageDependingOnWhetherDeleteIsSuccessful(request, service);

        assertEquals(expected, actual);
    }
}
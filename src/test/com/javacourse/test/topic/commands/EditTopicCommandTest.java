package com.javacourse.test.topic.commands;

import com.javacourse.ApplicationResources;
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
import javax.servlet.http.HttpSession;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditTopicCommandTest {

    private EditTopicCommand command;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession session;

    @Before
    public void setUp() throws Exception {
        command = new EditTopicCommand();
    }

    @Test
    public void getPageBasedOnWhetherEditIsSuccessful_editSuccessful_returnsProperPage() {
        TopicService service = mock(TopicService.class);
        when(service.update(anyObject())).thenReturn(true);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn("");

        WebPage expected = new WebPage(WebPage.WebPageBase.TOPICS_ACTION)
                .setDispatchType(WebPage.DispatchType.REDIRECT);
        WebPage actual = command.getPageBasedOnWhetherEditIsSuccessful(request, new Topic(), service,ResourceBundle.getBundle(ApplicationResources.getErrorBundle(), new Locale("ukr")));

        assertEquals(expected, actual);
    }

    @Test
    public void getPageBasedOnWhetherEditIsSuccessful_editUnsuccessful_returnsErrorPage() {
        TopicService service = mock(TopicService.class);
        when(service.update(anyObject())).thenThrow(UnsuccessfulQueryException.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn("");
        WebPage expected = new WebPage(WebPage.WebPageBase.TOPICS_ADMIN_EDIT);
        WebPage actual = command.getPageBasedOnWhetherEditIsSuccessful(request, new Topic(), service, ResourceBundle.getBundle(ApplicationResources.getErrorBundle(), new Locale("ukr")));

        assertEquals(expected, actual);
    }
}
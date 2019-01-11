package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.test.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowTestByTopicCommandTest {

    private ShowTestByTopicCommand command;
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        command = new ShowTestByTopicCommand();
        request = mock(HttpServletRequest.class);

    }

    @Test
    public void setTestsAttribute_topicIdNull_returnsFalse() {
        TestService service = new TestService();
        assertFalse(command.setTestsAttribute(request, service));
    }

    @Test
    public void setTestsAttribute_unsuccessfulDbInteraction_returnsFalse(){
        TestService service = mock(TestService.class);
        when(service.findByTopicId(anyString())).thenThrow(UnsuccessfulQueryException.class);
        assertFalse(command.setTestsAttribute(request, service));
    }

    @Test
    public void setTestsAttribute_successfulDbInteraction_returnsTrue(){
        TestService service = mock(TestService.class);
        when(request.getParameter(anyString())).thenReturn("param");
        assertTrue(command.setTestsAttribute(request, service));
    }
}
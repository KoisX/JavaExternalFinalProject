package com.javacourse.test.commands;

import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.answer.AnswerService;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
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
public class ShowEditAnswerCommandTest {

    @Mock
    HttpServletRequest request;

    private ShowEditAnswerCommand command;

    @Before
    public void setUp() throws Exception {
        command = new ShowEditAnswerCommand();
    }

    @Test
    public void getPageBasedOnWhetherOperationIsSuccessful() {
        AnswerService service = mock(AnswerService.class);
        when(service.findById(anyString())).thenReturn(new Answer());
        WebPage expected = new WebPage(WebPage.WebPageBase.ANSWER_ADMIN_EDIT_PAGE);
        WebPage actual = command.getPageBasedOnWhetherOperationIsSuccessful(request, service);
        assertEquals(expected, actual);
    }
}
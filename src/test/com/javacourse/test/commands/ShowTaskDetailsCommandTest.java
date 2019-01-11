package com.javacourse.test.commands;

import com.javacourse.shared.WebPage;
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
public class ShowTaskDetailsCommandTest {

    @Mock
    HttpServletRequest request;

    private ShowTaskDetailsCommand command;

    @Before
    public void setUp() throws Exception {
        command = new ShowTaskDetailsCommand();
    }

    @Test
    public void getPageDependingOnWhetherOperationIsSuccessful_getsRequestWithCorrectParams_returnsProperWebpage() {
        TaskService service = mock(TaskService.class);
        when(service.findById(anyString())).thenReturn(new Task());
        WebPage expected = new WebPage(WebPage.WebPageBase.TASK_ADMIN_EDIT_PAGE);
        WebPage actual = command.getPageDependingOnWhetherOperationIsSuccessful(request, service);
        assertEquals(expected, actual);
    }
}
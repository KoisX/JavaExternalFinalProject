package com.javacourse.test.commands;

import com.javacourse.shared.WebPage;
import com.javacourse.test.TestService;
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
public class ShowEditTestDescriptionCommandTest {

    @Mock
    HttpServletRequest request;

    private ShowEditTestDescriptionCommand command;

    @Before
    public void setUp() throws Exception {
        command = new ShowEditTestDescriptionCommand();
    }

    @Test
    public void getPageDependingOnWhetherOperationIsSuccessful_getsRequestWithCorrectParams_returnsProperWebpage() {
        TestService service = mock(TestService.class);
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findById(anyString())).thenReturn(new com.javacourse.test.Test());

        WebPage expected = new WebPage(WebPage.WebPageBase.TEST_ADMIN_EDIT_DESCRIPTION);
        WebPage actual = command.getPageDependingOnWhetherOperationIsSuccessful(request, service);

        assertEquals(expected, actual);
    }
}
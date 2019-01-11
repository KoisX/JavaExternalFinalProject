package com.javacourse.test.commands;

import com.javacourse.shared.WebPage;
import com.javacourse.test.TestService;
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
public class ShowEditTestHeaderCommandTest {

    @Mock
    HttpServletRequest request;

    private ShowEditTestHeaderCommand command;

    @Before
    public void setUp() throws Exception {
        command = new ShowEditTestHeaderCommand();
    }

    @Test
    public void getPageDependingOnWhetherOperationIsSuccessful_getsRequestWithCorrectParams_returnsProperWebpage() {
        TestService service = mock(TestService.class);
        when(request.getParameter(anyString())).thenReturn("1");
        when(service.findById(anyString())).thenReturn(new com.javacourse.test.Test());

        WebPage expected = new WebPage(WebPage.WebPageBase.TEST_ADMIN_EDIT_HEADER);
        WebPage actual = command.getPageDependingOnWhetherOperationIsSuccessful(request, service);

        assertEquals(expected, actual);
    }
}
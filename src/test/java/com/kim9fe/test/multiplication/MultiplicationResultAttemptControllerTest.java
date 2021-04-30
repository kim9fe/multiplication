package com.kim9fe.test.multiplication;

import com.google.gson.Gson;
import com.kim9fe.test.multiplication.controller.MultiplicationResultAttemptController;
import com.kim9fe.test.multiplication.controller.MultiplicationResultAttemptController.ResultResponse;
import com.kim9fe.test.multiplication.domain.Multiplication;
import com.kim9fe.test.multiplication.domain.MultiplicationResultAttempt;
import com.kim9fe.test.multiplication.domain.User;
import com.kim9fe.test.multiplication.service.MultiplicationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import org.slf4j.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MultiplicationResultAttemptController.class)
public class MultiplicationResultAttemptControllerTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    private Gson gson;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeEach
    public void setUp(){
        gson = new Gson();
    }

    @Test
    public void postResultReturnCorrect() throws Exception{
        genericParameterizedTest(true);
    }

    @Test
    public void postResultReturnWrong() throws Exception{
        genericParameterizedTest(false);
    }
    
    public void genericParameterizedTest(final boolean correct) throws Exception{

        // given
        given(multiplicationService.checkAttempt(any(MultiplicationResultAttempt.class)))
            .willReturn(correct);

        User user = new User("kim9fe");
        Multiplication multiplication = new Multiplication(50, 70);
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3500, correct);

        logger.info("Attempt JSON : {}", gson.toJson(attempt));

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/results")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(attempt))
        ).andReturn().getResponse();

        // then
        ResultResponse result = new ResultResponse(correct);

        logger.info("RESULT JSON : {}", gson.toJson(result));

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString()
                    , gson.toJson(new MultiplicationResultAttempt(
                                                attempt.getUser()
                                                , attempt.getMultiplication()
                                                , attempt.getResultAttemp()
                                                , correct)
                                )
                    );
    }
}

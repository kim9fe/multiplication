package com.kim9fe.test.multiplication;

import com.google.gson.Gson;
import com.kim9fe.test.multiplication.controller.MultiplicationController;
import com.kim9fe.test.multiplication.domain.Multiplication;
import com.kim9fe.test.multiplication.service.MultiplicationService;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {
    
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
    public void getRandomMultiplicationTest() throws Exception {
        //given
        given(multiplicationService.createRandomMultiplication())
                .willReturn(new Multiplication(70, 20));

        //when
        MockHttpServletResponse response = mvc.perform(get("/multiplications/random").accept(MediaType.APPLICATION_JSON))  
            .andReturn().getResponse();
        
        //then
        String gsonJson = gson.toJson(new Multiplication(70, 20));
        logger.info("JSON : {}", gsonJson);

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), gsonJson);
    }
}

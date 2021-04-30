package com.kim9fe.test.multiplication;

import com.kim9fe.test.multiplication.domain.Multiplication;
import com.kim9fe.test.multiplication.service.MultiplicationService;
import com.kim9fe.test.multiplication.service.RandomGeneratorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MultiplicationServiceTest {
    @MockBean
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*
    @BeforeEach
    public void setUp(){
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }
    */

    @Test
    void createRandomMultiplicationTest(){

        // randomGeneratorService.generateRandomFactor 가 정해진 값으로 return하게 설정
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // multiplication instance생성
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        // assert
        assertEquals(multiplication.getFactorA(), 50);
        assertEquals(multiplication.getFactorB(), 30);
        //assertEquals(multiplication.getResult(), 1500);

        logger.info("TEST>>>>>>>>>>>>>>>");

        //assertThat(multiplication.getFactorA()).isEqualTo(50);
        //assertThat(multiplication.getFactorB()).isEqualTo(30);
    }
}

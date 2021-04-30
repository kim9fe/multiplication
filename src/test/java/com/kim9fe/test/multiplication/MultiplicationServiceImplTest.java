package com.kim9fe.test.multiplication;

import com.kim9fe.test.multiplication.domain.Multiplication;
import com.kim9fe.test.multiplication.domain.MultiplicationResultAttempt;
import com.kim9fe.test.multiplication.domain.User;
import com.kim9fe.test.multiplication.repository.MultiplicationResultAttemptRepository;
import com.kim9fe.test.multiplication.repository.UserRepository;
import com.kim9fe.test.multiplication.service.MultiplicationService;
import com.kim9fe.test.multiplication.service.MultiplicationServiceImpl;
import com.kim9fe.test.multiplication.service.RandomGeneratorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiplicationServiceImplTest {
    
    private MultiplicationService multiplicationService;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService /*
                                                    , attemptRepository
                                                    , userRepository*/);
        //multiplicationService = new MultiplicationServiceImpl();
    }

    @Test
    public void createRandomMultiplicationTest(){
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        assertEquals(multiplication.getFactorA(), 50);
        assertEquals(multiplication.getFactorB(), 30);
        //assertEquals(multiplication.getResult(), 1500);
    }

    @Test
    public void checkCorrectAttemptTest(){
        //given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("kim9fe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);

        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
        given(userRepository.findByAlias("kim9fe")).willReturn(Optional.empty());

        //when
        boolean attempResult = multiplicationService.checkAttempt(attempt);

        //assert
        assertTrue(attempResult);
        verify(attemptRepository).save(verifiedAttempt);
    }

    @Test
    public void checkWrongAttemptTest(){
        //given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("kim9fe");
        MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3100, false);

        //when
        boolean attempResult = multiplicationService.checkAttempt(attempt);

        //assert
        assertFalse(attempResult);
    }
}

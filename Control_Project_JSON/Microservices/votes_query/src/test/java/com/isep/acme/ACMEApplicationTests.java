package com.isep.acme;

import com.isep.acme.consumer.VoteConsumer;
import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.model.Review;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.IVoteService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class ACMEApplicationTests {

}

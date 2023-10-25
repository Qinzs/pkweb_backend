package com.pkweb.backend1.Controllers;
import com.pkweb.backend1.Repositories.community.AnswerMapper;
import com.pkweb.backend1.Repositories.community.PublishMapper;
import com.pkweb.backend1.pojo.Answer;
import com.pkweb.backend1.pojo.Publish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommunityTest {

    @InjectMocks
    private Community community;

    @Mock
    private PublishMapper publishMapper;

    @Mock
    private AnswerMapper answerMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testReturnPublish() {
        List<Publish> dummyPublishList = new ArrayList<>();
        dummyPublishList.add(new Publish(1, "Title1", 1, "Content1", 0, 0, new Timestamp(System.currentTimeMillis()), "Author1"));

        Mockito.when(publishMapper.returnAll()).thenReturn(dummyPublishList);

        List<Publish> publishes = community.returnPublish();

        assertNotNull(publishes);
        assertEquals(1, publishes.size());
        assertEquals("Title1", publishes.get(0).getTitle());
    }

    @Test
    public void testCreatePublish() {
        Publish newPublish = new Publish(1, "New Title", 1, "New Content", 0, 0, new Timestamp(System.currentTimeMillis()), "New Author");

        Mockito.when(publishMapper.findNameByID(newPublish.getUserID())).thenReturn("New Author");
        community.createPublish(newPublish);

        Mockito.verify(publishMapper).createPublish(newPublish);
        assertEquals("New Author", newPublish.getAuthorName());
    }

    @Test
    public void testAddAnswer() {
        Publish publish = new Publish(1, "Title1", 1, "Content1", 0, 0, new Timestamp(System.currentTimeMillis()), "Author1");
        Answer newAnswer = new Answer(1, 1, new Timestamp(System.currentTimeMillis()), "New Author", "New Content", 1);

        Mockito.when(publishMapper.findPublishByID(1)).thenReturn(publish);
        Mockito.when(publishMapper.findNameByID(1)).thenReturn("Author1");
        community.addAnswer(1, newAnswer);
    }

    @Test
    public void testCheckAnswers() {
        int publishID = 1;

        Answer answer1 = new Answer(1, 1, null, "Author1", "Answer1", publishID);
        Answer answer2 = new Answer(2, 2, null, "Author2", "Answer2", publishID);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);

        Mockito.when(answerMapper.findAnswerByPublishID(publishID)).thenReturn(answers);
        List<Answer> result = community.checkAnswers(publishID);
        assertEquals(answers, result);
    }
}

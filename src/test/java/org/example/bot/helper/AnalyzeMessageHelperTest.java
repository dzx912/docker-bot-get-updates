package org.example.bot.helper;

import org.example.bot.response.MessageResponse;
import org.example.bot.response.UpdateResultResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * Проверяем AnalyzeMessageHelper
 */
public class AnalyzeMessageHelperTest {

    private static final int CORRECT_LAST_UPDATE_ID = 3;

    private MessageResponse correctMessage;
    private MessageResponse uncorrectedMessage;

    @Before
    public void setUp() {
        UpdateResultResponse updateResultResponse = new UpdateResultResponse();
        updateResultResponse.setUpdateId(CORRECT_LAST_UPDATE_ID);

        correctMessage = new MessageResponse();
        correctMessage.setOk(true);
        correctMessage.setResult(Collections.singletonList(updateResultResponse));

        uncorrectedMessage = new MessageResponse();
        uncorrectedMessage.setOk(false);
        uncorrectedMessage.setResult(Collections.emptyList());
    }

    @Test
    public void getLastUpdateIdCorrect() throws Exception {
        int lastUpdateId = AnalyzeMessageHelper.getLastUpdateId(correctMessage);
        Assert.assertEquals(CORRECT_LAST_UPDATE_ID, lastUpdateId);
    }

    @Test
    public void getLastUpdateIdUncorrected() throws Exception {
        int lastUpdateId = AnalyzeMessageHelper.getLastUpdateId(uncorrectedMessage);
        Assert.assertEquals(-1, lastUpdateId);
    }

}
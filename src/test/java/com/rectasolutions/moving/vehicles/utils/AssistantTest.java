package com.rectasolutions.moving.vehicles.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AssistantTest {

    @Test
    void getImagesStorePath() {
        assertNotNull(Assistant.getImagesStorePath());
        assertNotNull(Assistant.getFolderSeperator());
    }
}
package com.ll.SSG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WiseSayTableTest {
    private WiseSayTable wiseSayTable;

    public WiseSayTableTest() {
        wiseSayTable = new WiseSayTable("test_data");
    }

    @BeforeEach
    public void beforeEach() {
        Util.file.deleteDir("test_data");

        wiseSayTable.save("나에게 불가능이란 없다.", "나폴레옹");
        wiseSayTable.save("나의 죽음을 적들에게 알리지 마라.", "이순신");
    }

    @Test
    public void 저장() {
        int newId = wiseSayTable.getLastId() + 1;
        wiseSayTable.save("자유가 아니면 죽음을 달라!", "패트릭 헨리");

        assertTrue(new File("test_data/wise_say/%d.json".formatted(newId)).exists());
    }
}

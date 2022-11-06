package com.ll.SSG;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WiseSayTableTest {
    private WiseSayTable wiseSayTable;

    @BeforeAll
    public void beforeAll() {
        App.setMode("test");
        wiseSayTable = new WiseSayTable(App.getDataBaseDir());
    }

    @BeforeEach
    public void beforeEach() {
        List<WiseSay> wiseSays = wiseSayTable.findAll();
        Util.file.deleteDir(App.getDataBaseDir());

        wiseSayTable.save("나에게 불가능이란 없다.", "나폴레옹");
        wiseSayTable.save("나의 죽음을 적들에게 알리지 마라.", "이순신");
    }

    @Test
    public void 저장() {
        int newId = wiseSayTable.getLastId() + 1;
        wiseSayTable.save("자유가 아니면 죽음을 달라!", "패트릭 헨리");

        assertTrue(new File("%s/wise_say/%d.json".formatted(App.getDataBaseDir(), newId)).exists());
    }


    @Test
    public void 조회() {
        WiseSay wiseSaying = wiseSayTable.findById(1);

        assertEquals(1, wiseSaying.id);
        assertEquals("나에게 불가능이란 없다.", wiseSaying.content);
        assertEquals("나폴레옹", wiseSaying.author);
    }

    @Test
    public void 전체조회() {
        List<WiseSay> wiseSays = wiseSayTable.findAll();

        assertEquals(2, wiseSays.size());
        assertEquals(1, wiseSays.get(0).id);
        assertEquals("나에게 불가능이란 없다.", wiseSays.get(0).content);
        assertEquals("나폴레옹", wiseSays.get(0).author);

        assertEquals(2, wiseSays.get(1).id);
        assertEquals("나의 죽음을 적들에게 알리지 마라.", wiseSays.get(1).content);
        assertEquals("이순신", wiseSays.get(1).author);
    }
}

package com.ll.SSG;

import java.util.ArrayList;
import java.util.List;

public class WiseSayService {
    List<WiseSay> wiseSays = new ArrayList<>();
    WiseSayRepository wiseSayRepository = new WiseSayRepository(wiseSays);
    int id = 0;

    public List<WiseSay> findAll() {
        return wiseSayRepository.findAll();
    }

    public WiseSay findById(int id) {
        return wiseSayRepository.findById(id);
    }

    public WiseSay write(String content, String author) {
        int wiseSayId = ++id;

        WiseSay wiseSay = new WiseSay(wiseSayId, content, author);

        wiseSays.add(wiseSay);
        return wiseSay;
    }

    public void remove(WiseSay wiseSay) {
        wiseSays.remove(wiseSay);
    }

    public void modify(WiseSay wiseSay, String content, String author) {
        wiseSay.content = content;
        wiseSay.author = author;
    }
}

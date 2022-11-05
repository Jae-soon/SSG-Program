package com.ll.SSG;

import java.util.ArrayList;
import java.util.List;

public class WiseSayService {
    List<WiseSay> wiseSays = new ArrayList<>();
    int id = 0;

    public List<WiseSay> findAll() {
        return wiseSays;
    }

    public WiseSay findById(int id) {
        for(WiseSay wiseSay : wiseSays) {
            if(wiseSay.id == id) {
                return wiseSay;
            }
        }
        return null;
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

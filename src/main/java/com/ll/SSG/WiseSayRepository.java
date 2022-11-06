package com.ll.SSG;

import java.util.List;

public class WiseSayRepository {
    List<WiseSay> wiseSays;
    WiseSayTable wiseSayTable = new WiseSayTable();

    public WiseSayRepository(List<WiseSay> wiseSays) {
        this.wiseSays = wiseSays;
    }
    public List<WiseSay> findAll() {
        return wiseSayTable.findAll();
    }

    public WiseSay findById(int id) {
        for(WiseSay wiseSay : wiseSays) {
            if(wiseSay.id == id) {
                return wiseSay;
            }
        }
        return null;
    }
}

package com.ll.SSG;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WiseSayService {
    List<WiseSay> wiseSays = new ArrayList<>();
    WiseSayRepository wiseSayRepository = new WiseSayRepository(wiseSays);
    int id = 0;
    WiseSayTable wiseSayTable = new WiseSayTable(App.getDataBaseDir());

    public List<WiseSay> findAll() {
        return wiseSayTable.findAll();
    }

    public WiseSay findById(int id) {
        return wiseSayRepository.findById(id);
    }

    public WiseSay write(String content, String author) {
        return wiseSayTable.save(content, author);
    }

    public void remove(int id) {
        wiseSayTable.removeById(id);
    }

    public WiseSay modify(int id, String content, String author) {
        return wiseSayTable.save(id, content, author);
    }

    public void dumpToJson() {
        List<WiseSay> wiseSays = wiseSayRepository.findAll();

        String json = "[" + wiseSays
                .stream()
                .map(wiseSay -> wiseSay.toJson())
                .collect(Collectors.joining(",")) + "]";

        Util.file.saveToFile("%s/data.json".formatted(App.getDataBaseDir()), json);
    }
}

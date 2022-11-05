package com.ll.SSG;

import java.io.File;
import java.util.Map;

public class WiseSayTable {
    private String baseDir;

    public WiseSayTable(String baseDir) {
        this.baseDir = baseDir;
    }

    public void save(WiseSay wiseSay) {
        Util.file.mkdir("%s/wise_say".formatted(baseDir));
        String body = wiseSay.toJson();
        Util.file.saveToFile("%s/wise_say/%d.json".formatted(baseDir, wiseSay.id), body);
    }

    public void save(String content, String author) {
        int id = getLastId() + 1;

        WiseSay wiseSay = new WiseSay(id, content, author);
        save(wiseSay);

        saveLastId(id);
    }

    private void saveLastId(int id) {
        Util.file.saveToFile("%s/wise_say/last_id.txt".formatted(baseDir), id + "");
    }

    public  int getLastId() {
        String lastId = Util.file.readFromFile("%s/wise_say/last_id.txt".formatted(baseDir), "");

        if (lastId.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(lastId);
    }

    public WiseSay findById(int id) {
        String path = "%s/wise_say/%d.json".formatted(baseDir, id);

        if (new File(path).exists() == false) {
            return null;
        }

        Map<String, Object> map = Util.json.jsonToMapFromFile(path);

        if (map == null) {
            return null;
        }

        return new WiseSay((int) map.get("id"), (String) map.get("content"), (String) map.get("author"));
    }
}

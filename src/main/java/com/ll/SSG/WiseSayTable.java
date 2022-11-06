package com.ll.SSG;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WiseSayTable {
    private String baseDir;

    public WiseSayTable() {
    }

    public static String getTableDataFilePath(int id) {
        return getTableDirPath() + "/" + id + ".json";
    }

    public static String getTableLastIdFilePath() {
        return getTableDirPath() + "/last_id.txt";
    }

    public static String getTableDataDumpFilePath() {
        return getTableDirPath() + "/data.json";
    }

    public static String getTableDirPath() {
        return App.getDataBaseDir() + "/wise_say";
    }

    public void save(WiseSay wiseSay) {
        Util.file.mkdir(getTableDirPath());
        String body = wiseSay.toJson();
        Util.file.saveToFile(getTableDataFilePath(wiseSay.id), body);
    }

    public WiseSay save(String content, String author) {
        int id = getLastId() + 1;

        WiseSay wiseSay = new WiseSay(id, content, author);
        save(wiseSay);

        saveLastId(id);

        return wiseSay;
    }

    public WiseSay save(int id, String content, String author) {
        WiseSay wiseSay = new WiseSay(id, content, author);
        save(wiseSay);

        return wiseSay;
    }

    private void saveLastId(int id) {
        Util.file.saveToFile(getTableLastIdFilePath(), id + "");
    }

    public  int getLastId() {
        String lastId = Util.file.readFromFile(getTableLastIdFilePath(), "");

        if (lastId.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(lastId);
    }

    public WiseSay findById(int id) {
        String path = getTableDataFilePath(id);

        if (new File(path).exists() == false) {
            return null;
        }

        Map<String, Object> map = Util.json.jsonToMapFromFile(path);

        if (map == null) {
            return null;
        }

        return new WiseSay((int) map.get("id"), (String) map.get("content"), (String) map.get("author"));
    }

    public List<WiseSay> findAll() {
        List<Integer> fileIds = getFileIds();

        return fileIds
                .stream()
                .map(id -> findById(id))
                .collect(Collectors.toList());
    }

    private List<Integer> getFileIds() {
        String path = getTableDirPath();
        List<String> fileNames = Util.file.getFileNamesFromDir(path);

        return fileNames
                .stream()
                .filter(fileName -> !fileName.equals("last_id.txt"))
                .filter(fileName -> !fileName.equals("data.json")) // 영상에는 이 부분이 빠져있습니다. 꼭 추가해주세요.
                .filter(fileName -> fileName.endsWith(".json"))
                .map(fileName -> fileName.replace(".json", ""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public void removeById(int id) {
        String deleteFilePath = getTableDataFilePath(id);

        File file = new File(deleteFilePath);

        if(!file.exists()) {
            System.out.println("해당 ID가 존재하지 않습니다.");
            return;
        }

        new File(deleteFilePath).delete();
    }
}

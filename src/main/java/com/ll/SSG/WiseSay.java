package com.ll.SSG;

public class WiseSay {
    int id;
    String content;
    String author;

    public WiseSay(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    // WiseSay(id = id, content = content, author = author)형식으로 변경
    @Override
    public String toString() {
        return """
                id = %d
                명언 = %s
                작가명 = %s""".formatted(id, content, author);
    }
}

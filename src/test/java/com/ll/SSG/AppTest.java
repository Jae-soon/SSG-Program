package com.ll.SSG;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void scanner() throws IOException {
        String input = """
                등록
                명언
                작가""".stripIndent();

        InputStream in = new ByteArrayInputStream(input.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String cmd = br.readLine().trim();
        String content = br.readLine().trim();
        String author = br.readLine().trim();

        assertEquals("등록", cmd);
        assertEquals("명언", content);
        assertEquals("작가", author);

        br.close();
    }
}

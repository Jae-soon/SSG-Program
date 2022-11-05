package com.ll.SSG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public void run() throws IOException {
        System.out.println("===== 명언 SSG =====");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        outer:
        while (true) {
            System.out.println("Command Example : '종료', '등록'");
            System.out.print("Please enter a command  : ");


            String cmd = br.readLine();

            int id = 0;

            switch(cmd) {
                case "등록":
                    System.out.print("명언 : ");
                    String content = br.readLine().trim();

                    System.out.print("작가 : ");
                    String author = br.readLine().trim();

                    int wiseSayId = ++id;

                    WiseSay wiseSay = new WiseSay(wiseSayId, content, author);
                    System.out.println(wiseSay);

                    System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSayId);
                    break;

                case "종료":
                    break outer;
            }
        }

        br.close();
    }
}

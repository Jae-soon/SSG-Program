package com.ll.SSG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void run() throws IOException {
        System.out.println("===== 명언 SSG =====");

        WiseSayController wiseSayController = new WiseSayController(br);

        outer:
        while (true) {
            System.out.println("Command Example : '종료', '등록', '삭제', '찾기', '목록', '수정'");
            System.out.print("Please enter a command  : ");

            String cmd = br.readLine();
            Rq rq = new Rq(cmd);

            switch(rq.getCmd()) {
                case "찾기":
                    wiseSayController.getWiseSayById(rq, cmd);
                    continue;

                case "등록":
                    wiseSayController.write(rq);
                    continue;

                case "목록":
                    wiseSayController.list(rq);
                    continue;

                case "삭제":
                    wiseSayController.delete(rq, cmd);
                    continue;

                case "수정":
                    wiseSayController.modify(rq, cmd);
                    continue;

                case "종료":
                    break outer;
            }
        }

        br.close();
    }
}

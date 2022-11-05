package com.ll.SSG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public void run() throws IOException {
        System.out.println("===== 명언 SSG =====");

        List<WiseSay> wiseSays = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int id = 0;

        outer:
        while (true) {
            System.out.println("Command Example : '종료', '등록'");
            System.out.print("Please enter a command  : ");

            String cmd = br.readLine();
            Rq rq = new Rq(cmd);

            switch(rq.getCmd()) {
                case "등록":
                    System.out.print("명언 : ");
                    String content = br.readLine().trim();

                    System.out.print("작가 : ");
                    String author = br.readLine().trim();

                    int wiseSayId = ++id;

                    WiseSay wiseSay = new WiseSay(wiseSayId, content, author);
                    System.out.println(wiseSay);

                    System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSayId);
                    wiseSays.add(wiseSay);
                    break;

                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("-------------------");
                    for (int i = wiseSays.size() - 1; i >= 0; i--) {
                        WiseSay getWiseSay = wiseSays.get(i);
                        System.out.printf("%d / %s / %s\n", getWiseSay.id, getWiseSay.content, getWiseSay.author);
                    }
                    break;

                case "삭제":
                    int deleteId = 0;
                    if(cmd.equals("삭제")) {
                        System.out.println("삭제할 ID를 입력해주세요.");
                        deleteId = Integer.parseInt(br.readLine().trim());
                    } else {
                        deleteId = rq.getIntParam("id", 0);
                    }

                    if(deleteId == 0) {
                        System.out.println("삭제할 ID를 입력해주세요.");
                        continue;
                    }

                    WiseSay wiseSay_ = null;

                    for (WiseSay getWiseSay : wiseSays) {
                        if(getWiseSay.id == deleteId) {
                            wiseSay_ = getWiseSay;
                        }
                    }

                    if(wiseSay_ == null) {
                        System.out.println("해당 ID의 명언이 존재하지 않습니다.");
                        continue;
                    }

                    wiseSays.remove(wiseSay_);

                    System.out.printf("%d번 명언이 삭제되었습니다.", deleteId);
                    continue;

                case "종료":
                    break outer;
            }
        }

        br.close();
    }
}

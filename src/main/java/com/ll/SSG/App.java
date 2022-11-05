package com.ll.SSG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    List<WiseSay> wiseSays = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int id = 0;

    public void run() throws IOException {
        System.out.println("===== 명언 SSG =====");

        outer:
        while (true) {
            System.out.println("Command Example : '종료', '등록', '삭제', '찾기', '목록', '수정'");
            System.out.print("Please enter a command  : ");

            String cmd = br.readLine();
            Rq rq = new Rq(cmd);

            switch(rq.getCmd()) {
                case "찾기":
                    getWiseSayById(rq, cmd);
                    continue;

                case "등록":
                    write(rq);
                    continue;

                case "목록":
                    list(rq);
                    continue;

                case "삭제":
                    delete(rq, cmd);
                    continue;

                case "수정":
                    modify(rq, cmd);
                    continue;

                case "종료":
                    break outer;
            }
        }

        br.close();
    }

    private void modify(Rq rq, String cmd) throws IOException {
        int wiseSayId;

        if(cmd.equals("수정")) {
            System.out.print("수정할 명언의 ID를 입력해주세요. : ");
            wiseSayId = Integer.parseInt(br.readLine().trim());
        } else {
            wiseSayId = rq.getIntParam("id", 0);
        }

        if(wiseSayId == 0) {
            System.out.println("정확한 명언 ID를 입력해주세요.");
            return;
        }

        WiseSay wiseSay = rq.findById(wiseSayId, wiseSays);

        if(wiseSay == null) {
            System.out.println("해당 ID의 명언이 존재하지 않습니다.");
            return;
        }

        System.out.printf("기존 명언 : %s\n", wiseSay.content);
        System.out.print("변경 명언 : ");
        wiseSay.content = br.readLine().trim();

        System.out.printf("기존 작가 : %s\n", wiseSay.author);
        System.out.print("변경 작가 : ");
        wiseSay.author = br.readLine().trim();

        System.out.printf("%d번 명언이 수정되었습니다.", wiseSayId);
    }

    private void getWiseSayById(Rq rq, String cmd) throws IOException {
        int wiseSayId;

        if(cmd.equals("찾기")) {
            System.out.print("찾을 명언의 ID를 입력해주세요. : ");
            wiseSayId = Integer.parseInt(br.readLine().trim());
        } else {
            wiseSayId = rq.getIntParam("id", 0);
        }

        if(wiseSayId == 0) {
            System.out.println("정확한 명언 ID를 입력해주세요.");
            return;
        }

        WiseSay wiseSay = rq.findById(wiseSayId, wiseSays);

        if(wiseSay == null) {
            System.out.println("해당 ID의 명언이 존재하지 않습니다.");
            return;
        }

        System.out.println(wiseSay);
    }

    private void delete(Rq rq, String cmd) throws IOException {
        int deleteId = 0;
        if(cmd.equals("삭제")) {
            System.out.print("삭제할 ID를 입력해주세요. : ");
            deleteId = Integer.parseInt(br.readLine().trim());
        } else {
            deleteId = rq.getIntParam("id", 0);
        }

        if(deleteId == 0) {
            System.out.println("삭제할 ID를 입력해주세요.");
            return;
        }

        WiseSay wiseSay_ = null;

        for (WiseSay getWiseSay : wiseSays) {
            if(getWiseSay.id == deleteId) {
                wiseSay_ = getWiseSay;
            }
        }

        if(wiseSay_ == null) {
            System.out.println("해당 ID의 명언이 존재하지 않습니다.");
            return;
        }

        wiseSays.remove(wiseSay_);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
    }

    private void write(Rq rq) throws IOException {
        System.out.print("명언 : ");
        String content = br.readLine().trim();

        System.out.print("작가 : ");
        String author = br.readLine().trim();

        int wiseSayId = ++id;

        WiseSay wiseSay = new WiseSay(wiseSayId, content, author);
        System.out.println(wiseSay);

        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSayId);
        wiseSays.add(wiseSay);
    }

    private void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSays.size() - 1; i >= 0; i--) {
            WiseSay getWiseSay = wiseSays.get(i);
            System.out.printf("%d / %s / %s\n", getWiseSay.id, getWiseSay.content, getWiseSay.author);
        }
    }
}

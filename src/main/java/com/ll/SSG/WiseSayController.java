package com.ll.SSG;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WiseSayController {
    BufferedReader br;
    WiseSayService wiseSayService = new WiseSayService();
    WiseSayTable wiseSayTable = new WiseSayTable(App.getBaseDir());

    public WiseSayController(BufferedReader br) {
        this.br = br;
    }

    public void modify(Rq rq, String cmd) throws IOException {
        int wiseSayId;

        if(cmd.equals("수정")) {
            try {
                System.out.print("수정할 명언의 ID를 입력해주세요. : ");
                wiseSayId = Integer.parseInt(br.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
                return;
            }
        } else {
            wiseSayId = rq.getIntParam("id", 0);
        }

        if(wiseSayId == 0) {
            System.out.println("정확한 명언 ID를 입력해주세요.");
            return;
        }

        WiseSay wiseSay = wiseSayTable.findById(wiseSayId);

        if(wiseSay == null) {
            System.out.println("해당 ID의 명언이 존재하지 않습니다.");
            return;
        }

        System.out.printf("기존 명언 : %s\n", wiseSay.content);
        System.out.print("변경 명언 : ");
        String content = br.readLine().trim();

        System.out.printf("기존 작가 : %s\n", wiseSay.author);
        System.out.print("변경 작가 : ");
        String author = br.readLine().trim();

        wiseSayService.modify(wiseSayId, content, author);

        System.out.printf("%d번 명언이 수정되었습니다.", wiseSayId);
    }

    public void getWiseSayById(Rq rq, String cmd) throws IOException {
        int wiseSayId;

        if(cmd.equals("찾기")) {
            try {
                System.out.print("찾을 명언의 ID를 입력해주세요. : ");
                wiseSayId = Integer.parseInt(br.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
                return;
            }
        } else {
            wiseSayId = rq.getIntParam("id", 0);
        }

        if(wiseSayId == 0) {
            System.out.println("정확한 명언 ID를 입력해주세요.");
            return;
        }

        WiseSay wiseSay = wiseSayTable.findById(wiseSayId);

        if(wiseSay == null) {
            System.out.println("해당 ID의 명언이 존재하지 않습니다.");
            return;
        }

        System.out.println(wiseSay);
    }

    public void delete(Rq rq, String cmd) throws IOException {
        int deleteId;
        if(cmd.equals("삭제")) {
            try {
                System.out.print("삭제할 ID를 입력해주세요. : ");
                deleteId = Integer.parseInt(br.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
                return;
            }
        } else {
            deleteId = rq.getIntParam("id", 0);
        }

        if(deleteId == 0) {
            System.out.println("삭제할 ID를 입력해주세요.");
            return;
        }

        wiseSayService.remove(deleteId);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", deleteId);
    }

    public void write(Rq rq) throws IOException {
        System.out.print("명언 : ");
        String content = br.readLine().trim();

        System.out.print("작가 : ");
        String author = br.readLine().trim();

        WiseSay wiseSay = wiseSayService.write(content, author);
        System.out.println(wiseSay);

        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSay.id);
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");

        List<WiseSay> wiseSays = wiseSayService.findAll();

        for (int i = wiseSays.size() - 1; i >= 0; i--) {
            WiseSay getWiseSay = wiseSays.get(i);
            System.out.printf("%d / %s / %s\n", getWiseSay.id, getWiseSay.content, getWiseSay.author);
        }
    }

    public void build(Rq rq) {
        wiseSayService.dumpToJson();
    }
}

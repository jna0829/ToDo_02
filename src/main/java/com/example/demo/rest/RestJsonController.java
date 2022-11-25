package com.example.demo.rest;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/json") //공통 URL
@Slf4j //로깅 라이브러리 (로깅찍을때 잡다한 정보제공, 파일 입출력 제공)
public class RestJsonController {


    /**
     * 로그 레벨
     *  - TRACE : 잡다한 내용을 로깅
     *  - DEBUG : 개발 당시 확인할 내용들 로깅
     *  - INFO : 운영시 필요한 정보들을 로깅
     *  - WARN : 에러까지는 아닌데 좀 경고상황 로깅
     *  - ERROR : 심각한 에러 로깅
     */

    //@RequestBody 는 클라이언트가 JSON으로 받아야 할때 사용함
    @PostMapping("/major")
    public List<String> major(@RequestBody List<String> majorList){

        //System.out.println("/json/major POST request!"); //기초배울때만 사용
        log.info("/json/major POST request! - {}", majorList);
        return null;
    }

    @PutMapping("/bbs")
    public void bbs(@RequestBody Article article){

        log.info("/json/bbs PUT request! - {}",article);
    }

    @DeleteMapping("/bbs-list")
    public void bbsList(@RequestBody List<Article> list){
        log.info("/json/bbs-list DELETE request! - {}", list);
    }

    @Setter @Getter @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Article{
        private long id;
        private String title;
        private String content;
        private String writer;
    }
}

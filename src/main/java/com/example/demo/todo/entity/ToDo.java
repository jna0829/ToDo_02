package com.example.demo.todo.entity;

import lombok.*;

//lombok
@Setter @Getter @ToString
//@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 전체 필드 초기화 생성자
//역할 : 하나의 할 일 데이터의 집합 객체
public class ToDo {

    private long id; // 할 일들을 식별하는 번호
    private String userId; // 할 일을 등록한 회원의 식별자
    private String title; //할 일 내용
    private boolean done; // 할 일 완료 여부

    private static long seq; //일련번호

    public ToDo(){
        this.id = ++seq;
    }

    public ToDo(String title) {
        this(); //나의 기본생성자 호출 -> public ToDo()의 this.id = ++seq; 호출
        this.title = title;
        this.userId = "noname";
        //this.done = false; -> done의 기본값은 false이기 때문에 초기화 하지 않아도 된다.
    }

}

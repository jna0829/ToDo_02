package com.example.demo.rest;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/param")
public class RestParamController {

    @GetMapping("/user") //URL : /param/user
    public String userName(HttpServletRequest request){

        // /param/user?name=홍길동 -> 홍길동 을 읽음
        String name = request.getParameter("name");

        return String.format("당신의 이름은 %s입니다.", name);
    }

    //@RequestParam(defaultValue = "10") 옵션을 주면 age를 안쓸때 자동으로 10을 입력해줌
    //@RequestParam(value = "name", required = false) value옵션은 거의 지역변수 이름이 겹칠때 사용
    // value옵션은 지역변수 이름이 겹칠때 사용
    @GetMapping("/user2") //URL : /param/user2?name=홍길동&age=25
    public String userName(
            @RequestParam(value = "name", required = false) String nm,
            @RequestParam(defaultValue = "10") int age
            ){

        return String.format("당신의 이름은 %s님이고, 나이는 %d세 입니다.", nm, age);
    }


    //URL : /param/user3?name=홍길동&age=25&address=서울&height=170&hobby=축구&hobby=농구
    @GetMapping("/user3")
    public String user3(UserDTO userInfo){

        return String.format(
                "당신의 이름은 %s이고, 나이는 %d세이고, 주소는 %s이며, 키는 %.2f며, 취미는 %s들이다."
                ,userInfo.getName()
                ,userInfo.getAge()
                ,userInfo.getAddress()
                ,userInfo.getHeight()
                ,userInfo.getHobby()
        );

    }

    //@PathVariable 는 ?가 아닌 경로문자열 /로 주소표현
    @GetMapping("/user4/{userNum}/") //URL : /param/user4/22
    public String user4(@PathVariable int userNum) {

        return String.format("회원번호는 %d입니다." , userNum);
    }

    @Setter //필수
    @Getter @ToString
    @NoArgsConstructor //필수
    @AllArgsConstructor
    public static class UserDTO{
        private String name;
        private int age;
        private String address;
        private double height;
        private List<String> hobby;
    }

}

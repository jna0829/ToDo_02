package com.example.demo.todo.api;

import com.example.demo.todo.dro.FindAllDTO;
import com.example.demo.todo.dro.TodoDto;
import com.example.demo.todo.entity.ToDo;
import com.example.demo.todo.repository.TodoRepository;
import com.example.demo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/todos") //공통 url
@RequiredArgsConstructor //repository의 생성자와 @Autowired를 자동생성해준다
public class TodoApiController {

    private final TodoService service;


    // 컨트롤러 리턴은 무조건 ResponseEntity<?>!!! body에 리턴할 값을 넣으면 된다
    // 할 일 목록 전체조회 요청
    @GetMapping
    public ResponseEntity<?> todos() {
        log.info("/api/todos GET request!");
        return ResponseEntity.ok().body(service.findAllServ());
    }

    // 할 일 목록 등록 요청
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ToDo newTodo){

        newTodo.setUserId("noname"); //newTodo는 ToDo에서 기본생성자를 불러오기 때문에 따로 noname을 수동으로 설정해야 한다
        log.info("/api/todos POST request! - {}", newTodo);

        try {
            FindAllDTO dto = service.createServ(newTodo);

            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    /// 할 일 개별 조회 요청
    // URI : /api/todos/3 : GET  => 3번 할 일만 조회해서 클라이언트에게 리턴
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id){

        log.info("/api/todos/{} Get request!", id);

        //id가 null일때
        if(id == null || id <0) return ResponseEntity.badRequest().build();

        //핵심적으로 해야할 일
        TodoDto dto = service.findOneServ(id);

        //dto가 만약 null이면
        if(dto == null) return ResponseEntity.notFound().build();
        return  ResponseEntity.ok().body(dto);
    }


    // 할 일 삭제 요청
    // URI : /api/todos/3 : DELETE
    // => 3번 할 일을 삭제 후 삭제된 이후 갱신된 할일 목록 리턴
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){

        log.info("/api/todos/{} DELETE request!", id);
        try{
            FindAllDTO dtos = service.deleteServ(id);
            return  ResponseEntity.ok().body(dtos);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }




}
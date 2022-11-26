package com.example.demo.todo.service;

import com.example.demo.todo.dro.FindAllDTO;
import com.example.demo.todo.dro.TodoDto;
import com.example.demo.todo.entity.ToDo;
import com.example.demo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//역할 : 컨트롤러와 저장소 사이의 잡일 처리 역할
@Service //빈등록
@Slf4j //로그
@RequiredArgsConstructor //repository의 생성자와 @Autowired를 자동생성해준다
public class TodoService {

    private final TodoRepository repository;  //불변성을 위해서 final -> 안전하게

    /*
        - 할 일 목록조회 중간처리
        1. 컨트롤러에게 userId를 뺀 할일 리스트를 전달한다.
        2. 할 일 목록에 카운트를 세서 따로 추가해서 전달한다.
     */
    public FindAllDTO findAllServ(){
        List<ToDo> toDoList = repository.findAll(); //민감한 정보가 담긴 리스트 , 정보를 싹 가져옴

        FindAllDTO findAllDTO = new FindAllDTO(toDoList);
        //findAllDTO.setCount(toDoList.size());
        //findAllDTO.convertDtoList(toDoList);

        //findAllDTO.setTodos(dtos);

        return findAllDTO;
    }

    //할일 목록 등록 요청
    public FindAllDTO createServ(final ToDo newTodo) {

        //안전장치 설치
        if(newTodo == null){
            log.warn("newTodo cannot be null!");
            throw new RuntimeException("newTodo cannot be null!");
        }

        boolean flag = repository.save(newTodo);
        if (flag) log.info("새로운 할일 [Id: {}]이 저장되었습니다.", newTodo.getId());

        return flag ? findAllServ() : null;
    }


    public TodoDto findOneServ(Long id) {

        ToDo toDo = repository.findOne(id);
        log.info("findOneServ return data - {}", toDo);

        return toDo != null ? new TodoDto(toDo) : null;

    }


    public FindAllDTO deleteServ(long id) {

        boolean flag = repository.remove(id);

        //삭제 실패한 경우 (flag가 false)
        if(!flag){
            log.warn("delete fail not found id [{}]", id);
            throw new RuntimeException("delete fail");
        }

        return findAllServ();
    }

}

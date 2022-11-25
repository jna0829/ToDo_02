package com.example.demo.todo.dro;

import com.example.demo.todo.entity.ToDo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindAllDTO {

    private int count; //할 일 목록의 개수
    private List<TodoDto> todos; //userId가 빠진 할 일 목록

    public FindAllDTO(List<ToDo> toDoList) {
        this.count = toDoList.size();
        this.convertDtoList(toDoList);
    }

    //List<ToDo>를 받으면 List<TodoDto>로 변환하는 메서드
    public void convertDtoList(List<ToDo> toDoList){
        List<TodoDto> dtos = new ArrayList<>(); //민감한 정보를 뺀 리스트 생성 ,userId뺀

        for (ToDo toDo : toDoList) {
            // TodoDto dto = new TodoDto(toDo); //setter로 안불러와도 생성자로 toDo를 불러오면 됨
                                                // 변수를 한번만 쓰니 변수를 따로 선언할 필요가 없다
                                                // dtos.add(안에 넣음)
            //        dto.setId(toDo.getId());
            //       dto.setTitle(toDo.getTitle());
            //        dto.setDone(toDo.isDone());
            dtos.add(new TodoDto(toDo));
        }
        this.todos = dtos;
    }





}

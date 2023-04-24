package com.study.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Entity는 테이블을 의미, JPA에서 클래스가 DB에 있는 테이블을 의미




@Entity
@Data
public class Board {
    //JPA가 읽어서 처리.

    @Id //primary key를 의미함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
}

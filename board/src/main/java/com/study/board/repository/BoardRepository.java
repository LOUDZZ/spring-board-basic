package com.study.board.repository;
//레파지토리는, 레파지토리라고 import 해주어야 함.
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.study.board.entity.Board;

/*
 * BoardRepository 인터페이스가 JpaRepository<Board,Integer>를 상속받았기 때문에,
 * BoardRepository에서는 findAll(), findById(), save(), deleteById() 등의 메소드를 사용할 수 있음.
 * 이 메소드들은 JpaRepository에서 이미 구현되어 있으므로,
 * BoardRepository에서는 구현할 필요 없이 바로 사용할 수 있음.
 */

/*
* JPA는 Java Persistence API의 약자로, 자바에서 객체를 관계형 데이터베이스에 매핑하고 조작하기 위한 API.
* JPA를 사용하면 객체와 테이블 간의 매핑을 어노테이션 등을 이용해 간단히 설정할 수 있으며, 객체지향적인 코드를 작성할 수 있음.
* JPA를 사용하면 데이터베이스와의 연결, SQL 작성, 결과 매핑 등을 개발자가 직접 구현할 필요 없이, 자동으로 처리할 수 있습니다.
* 따라서 개발 생산성을 높일 수 있으며, 유지 보수도 쉽게 할 수 있음.
 */

@Repository
//Repository를 통해 빈 등록
public interface BoardRepository extends JpaRepository<Board,Integer> {
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}

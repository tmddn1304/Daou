package com.daou.repository;


import com.daou.entity.Band;
import com.daou.entity.BandAssign;
import com.daou.types.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Song
 */

@Repository 
public interface BandRepository extends JpaRepository<Band, Long> { //JpaRepository 상속 기본적인 DB CRUD 인터페이스 사용가능 

	//비워있어도 잘 작동함. 
	// long 이 아니라 Long으로 작성. ex) int => Integer 같이 primitive형식 사용못함
	// findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
	public Optional<Band> findBySerialNo(String serialNo);
	public List<Band> findByCategory(category category);
	public Optional<BandAssign> deleteBySerialNo(String serialNo);
	public List<Band> findByLocalNo(String localNo);
}
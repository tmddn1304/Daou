package com.daou.repository;


import com.daou.entity.BandLog;
import com.daou.types.BandLogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Song
 */

@Repository 
public interface BandLogRepository extends JpaRepository<BandLog, Long> {
	//비워있어도 잘 작동함. 
	// long 이 아니라 Long으로 작성. ex) int => Integer 같이 primitive형식 사용못함
	// findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
	
	public Optional<BandLog> findBySeqLogNo(Long seqLogNo);
	public Optional<BandLog> findBySerialNo(String serialNo);
	public List<BandLog> findByRevType(BandLogType revType);

}
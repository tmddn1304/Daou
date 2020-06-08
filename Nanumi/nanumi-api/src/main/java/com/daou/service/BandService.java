package com.daou.service;

import com.daou.entity.Band;
import com.daou.repository.BandRepository;
import com.daou.types.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Song
 */
@Service
public class BandService {
	@Autowired
	private BandRepository bandRepository;

	public List<Band> findAll() {
		List<Band> bands = new ArrayList<>();
		bandRepository.findAll().forEach(e -> bands.add(e));
		return bands;
	}

	public Optional<Band> findBySerialNo(String SerialNo) {
		Optional<Band> band = bandRepository.findBySerialNo(SerialNo);
		return band;
	}

	public List<Band> findByCategory(category category) {
		List<Band> categoryBands = new ArrayList<>();
		bandRepository.findByCategory(category).forEach(e -> categoryBands.add(e));
		return categoryBands;
	}

	@Transactional
	public Band save(Band band) {
		bandRepository.save(band);
		return band;
	}

	@Transactional
	public void deleteBand(String serialNo) {
		bandRepository.deleteBySerialNo(serialNo);
	}

	@Transactional
	public void updateByBandNumberRange(String serialNo, Band band) {
		Optional<Band> e = bandRepository.findBySerialNo(serialNo);
		if (e.isPresent()) {
			e.get().setStartNo(band.getStartNo());
			e.get().setEndNo(band.getEndNo());
			e.get().setCategory(band.getCategory());
			e.get().setStatus(band.getStatus());
			bandRepository.save(e.get());
		}
	}


	// 모든 지번 조회
	@Transactional
	public List<String> findLocalNumbers(){
		List<String> localNumbers = new ArrayList<>();
		for(Band band : bandRepository.findAll()){
			if(!localNumbers.contains(band.getLocalNo())){
				localNumbers.add(band.getLocalNo());
			}
		}
		return localNumbers;
	}

	// 지번에 따른 국번 조회
	@Transactional
	public List<String> findBaseNumbers(String localNumber){
		List<String> baseNumbers = new ArrayList<>();
		for(Band band : bandRepository.findByLocalNo(localNumber)){
			if(!baseNumbers.contains(band.getBaseNo())){
				baseNumbers.add(band.getBaseNo());
			}
		}
		return baseNumbers;
	}


//	public void deleteById(Long mbrNo) {
//		memberRepository.deleteById(mbrNo);
//	}
//

}

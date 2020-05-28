package com.daou.service;

import com.daou.entity.BandAssign;
import com.daou.entity.BandLog;
import com.daou.repository.BandAssignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Song
 */
@Service
public class BandAssignService {
	@Autowired
	private BandAssignRepository bandAssignRepository;

	public List<BandAssign> findAll() {
		List<BandAssign> bandAssigns = new ArrayList<>();
		bandAssignRepository.findAll().forEach(e -> bandAssigns.add(e));
		return bandAssigns;
	}

	public Optional<BandAssign> findBySeqNo(Long seqNo) {
		Optional<BandAssign> bandAssigns = bandAssignRepository.findBySeqNo(seqNo);
		return bandAssigns;
	}
	
	public Optional<BandAssign> findBySerialNo(String serialNo) {
		Optional<BandAssign> bandAssigns = bandAssignRepository.findBySerialNo(serialNo);
		return bandAssigns;
	}

	public List<BandAssign> findBySvcId(String serviceCode) {
		List<BandAssign> bandAssigns = new ArrayList<>();
		bandAssignRepository.findBySvcId(serviceCode).forEach(e -> bandAssigns.add(e));
		return bandAssigns;
	}

	public void updateByBandNumberRange(Long seqNo, BandAssign bandAssign) {
		Optional<BandAssign> e = bandAssignRepository.findById(seqNo);
		if (e.isPresent()) {
			e.get().setSeqNo(bandAssign.getSeqNo());
			e.get().setStartNo(bandAssign.getStartNo());
			e.get().setEndNo(bandAssign.getEndNo());
			bandAssignRepository.save(bandAssign);
		}
	}

}

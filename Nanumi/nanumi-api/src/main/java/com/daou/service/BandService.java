package com.daou.service;

import com.daou.entity.Band;
import com.daou.entity.BandLog;
import com.daou.repository.BandLogRepository;
import com.daou.repository.BandRepository;
import com.daou.types.BandLogType;
import com.daou.types.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private BandLogRepository bandLogRepository;

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

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Band save(Band band) {
		bandRepository.save(band);

		BandLogType enumLog = BandLogType.INSERT;

		StringBuilder str = new StringBuilder();
		str.append(band.getStartNo())
				.append("~")
				.append(band.getEndNo());

		BandLog bandLog = new BandLog();
		bandLog.setSerialNo(band.getSerialNo());
		bandLog.setRevType(enumLog);
		bandLog.setDescription(str.toString());

		bandLogRepository.save(bandLog);

		return band;
	}

	@Transactional
	public void deleteBand(String serialNo) {
		bandRepository.deleteBySerialNo(serialNo);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void updateByBandNumberRange(String serialNo, Band band) {
		Optional<Band> e = bandRepository.findBySerialNo(serialNo);

		BandLogType enumLog = BandLogType.UPDATE;

		StringBuilder str = new StringBuilder();
		str.append(e.get().getStartNo())
				.append("~")
				.append(e.get().getEndNo())
				.append(" -> ")
				.append(band.getStartNo())
				.append("~")
				.append(band.getEndNo());

		if (e.isPresent()) {
			e.get().setStartNo(band.getStartNo());
			e.get().setEndNo(band.getEndNo());
			e.get().setCategory(band.getCategory());
			e.get().setStatus(band.getStatus());
			bandRepository.save(e.get());
		}

		BandLog bandLog = new BandLog();
		bandLog.setSerialNo(e.get().getSerialNo());
		bandLog.setRevType(enumLog);
		bandLog.setDescription(str.toString());

		bandLogRepository.save(bandLog);
	}



}

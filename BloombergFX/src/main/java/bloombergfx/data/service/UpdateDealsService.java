package bloombergfx.data.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import bloombergfx.data.DealRepository;
import bloombergfx.model.Record;
import bloombergfx.model.ValidRecord;

@Service
public class UpdateDealsService {

	private static final Logger logger = LoggerFactory.getLogger(UpdateDealsService.class);

	@Autowired
	DealRepository dealRespository;

	@Async
	public synchronized void updateDeals(final List<ValidRecord> records) {
		logger.info("Processing deals started .. .");

		long tStart = System.currentTimeMillis();

		for (Record record : records) {
			dealRespository.update(record.getFromCurrency());
		}

		dealRespository.flush();

		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;

		logger.info("Processing deals completed (" + tDelta + "ms).");
	}
}

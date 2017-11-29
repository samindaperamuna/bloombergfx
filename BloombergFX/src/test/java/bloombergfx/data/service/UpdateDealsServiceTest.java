package bloombergfx.data.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import bloombergfx.model.Deal;
import bloombergfx.model.ValidRecord;

@RunWith(SpringRunner.class)
public class UpdateDealsServiceTest {

    @MockBean
    private UpdateDealsService updateDealsService;

    private List<ValidRecord> records;
    private Deal deal;

    @Before
    public void setup() {

	records = new ArrayList<ValidRecord>();

	deal = new Deal();
	deal.setCount(0);
	deal.setCurrencyCode("USD");

	doAnswer(new Answer<Void>() {

	    @Override
	    public Void answer(InvocationOnMock invocation) throws Throwable {
		deal.setCount(1);

		return null;
	    }
	}).when(updateDealsService).updateDeals(records);
    }

    @Test
    public void testUpdateDeals() {
	this.updateDealsService.updateDeals(records);

	assertThat(deal.getCount()).isEqualTo(1);
    }
}

package bloombergfx.data;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import bloombergfx.model.Deal;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@Transactional
public class DealRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DealRepository dealRpository;

    private Deal deal;

    @Before
    public void setup() {
	deal = new Deal();
	deal.setCount(3);
	deal.setCurrencyCode("USD");
    }

    @Test
    public void testFindByCurrencyCode() {
	// Using the entity manager not the repository method to save the entity.
	entityManager.persist(deal);
	entityManager.flush();

	Deal found = dealRpository.findByCurrencyCode("USD");
	assertThat(found.getCurrencyCode()).isEqualTo(deal.getCurrencyCode());
    }

    @Test
    public void testSaveS() {
	Deal save = dealRpository.save(deal);

	assertThat(save.getCurrencyCode()).isEqualTo(deal.getCurrencyCode());
    }

    @Test
    public void testUpdate() {
	// Using the entity manager not the repository method to save the entity.
	entityManager.persist(deal);
	entityManager.flush();

	int code = dealRpository.update(deal.getCurrencyCode());

	assertThat(code).isEqualTo(1);
    }
}

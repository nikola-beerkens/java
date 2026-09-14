package pair;

import static org.junit.Assert.*;

import org.junit.Test;

public class PairTest {

	@Test
	public void firstInOut() {
		Pair<String, Boolean> p = new Pair<>("foo", true);
		assertEquals("foo", p.getFirst());
		assertEquals(true, p.getSecond());
	}
}

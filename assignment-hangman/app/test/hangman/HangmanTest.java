package hangman;

import static org.junit.Assert.*;
import org.junit.Test;

public class HangmanTest {

	@Test
	public void test() {
		HangmanTester tester = new HangmanTester();
		tester.newGallows("baobab");
		assertEquals("......", tester.getWordSoFar());

		tester.guessLetter('a');
		assertEquals(".a..a.", tester.getWordSoFar());
		assertEquals("a", tester.getGuessedLetters());
		assertFalse(tester.isWordGuessed());

		tester.guessLetter('b');
		assertEquals("ba.bab", tester.getWordSoFar());
		assertEquals("ab", tester.getGuessedLetters());
		assertFalse(tester.isWordGuessed());

		tester.guessLetter('z');
		assertEquals("ba.bab", tester.getWordSoFar());
		assertEquals("abz", tester.getGuessedLetters());
		assertFalse(tester.isWordGuessed());
		
		tester.guessLetter('o');
		assertEquals("baobab", tester.getWordSoFar());
		assertEquals("abzo", tester.getGuessedLetters());
		assertTrue(tester.isWordGuessed());
	}

}

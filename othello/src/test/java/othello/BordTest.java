package othello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import othello.Bord.stone_type;

public class BordTest {

	static Bord t_bord;
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		t_bord = new Bord();
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testBord() {
		//fail("まだ実装されていません");
		assertNotNull(t_bord);
	}

	@Test
	public void testGetMaxSize() {
		//fail("まだ実装されていません");
		assertEquals( t_bord.getMaxSize(), 8);
	}

	@Test
	public void testIsPositionEmpty() {
		//fail("まだ実装されていません");
		Position pos = new Position(7, 7);
		t_bord.setPositionEmpty(pos);
		assertTrue( t_bord.isPositionEmpty(pos));
	}

	@Test
	public void testIsPositionWhite() {
		//fail("まだ実装されていません");
		Position pos = new Position(7, 7);
		t_bord.setPositionEmpty(pos);
		t_bord.setPositionWhite(pos);
		assertTrue( t_bord.isPositionWhite(pos));
	}

	@Test
	public void testIsPositionBlack() {
//		fail("まだ実装されていません");
		Position pos = new Position(7, 7);
		t_bord.setPositionEmpty(pos);
		t_bord.setPositionBlack(pos);
		assertTrue( t_bord.isPositionBlack(pos));
	}

	@Test
	public void testGetStone() {
//		fail("まだ実装されていません");
		Position pos = new Position(7, 7);
		t_bord.setPositionEmpty(pos);
		t_bord.setPositionBlack(pos);

		stone_type sttyp = t_bord.getStone(pos);
		assertEquals( sttyp, stone_type.BLACK);
	}

	@Test
	public void testCheckCanPutStone() {
//		fail("まだ実装されていません");

	}

	@Test
	public void testIsNumEmpty() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testIsCountBlack() {
//		fail("まだ実装されていません");
		int aa = t_bord.isCountBlack();
		assertEquals(aa,1);
	}

	@Test
	public void testIsCountWhite() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testSetPositionBlack() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testSetStoneBlack() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testSetPositionWhite() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testSetStoneWhite() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testSetPositionEmpty() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testInitGame() {
//		fail("まだ実装されていません");
		t_bord.initGame();
	}

	@Test
	public void testReturnPosition() {
//		fail("まだ実装されていません");
	}

}

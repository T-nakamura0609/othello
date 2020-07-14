package othello;

import java.util.ArrayList;
import java.util.Iterator;

public class Bord {
	private static stone_type m_bord[][]; // フィールド[x][y]
	private static final int MAX_MASU = 8; // マスの数

	/**
	 * マス要素
	 *
	 */
	public static enum stone_type {
		EMPTY, BLACK, WHITE
	};

	public Bord() {
		// TODO 自動生成されたコンストラクター・スタブ
		initialBord();
	}

	/**
	 * 盤面の初期化
	 */
	private void initialBord() {
		m_bord = new stone_type[MAX_MASU][MAX_MASU];
		for (int i = 0; i < m_bord.length; i++) {
			for (int j = 0; j < m_bord[i].length; j++) {
				m_bord[i][j] = stone_type.EMPTY;
			}
		}
	}

	public int getMaxSize() {
		return MAX_MASU;
	}

	// マスの範囲外判定
	private void checkOutOfBord(Position pos) throws OutOfBoardException {
		if ((0 > pos.getY()) || (pos.getY() >= m_bord.length)) {
			throw new OutOfBoardException();
		}
		else if ((0 > pos.getX()) || (pos.getX() >= m_bord[pos.getY()].length)) {
			throw new OutOfBoardException();
		}
	}

	// 指定マスの種別判定
	private boolean checkPosition(stone_type target, Position pos) throws OutOfBoardException {

		checkOutOfBord(pos);
		if (target == m_bord[pos.getY()][pos.getX()]) {
			return true;
		}
		return false;
	}

	// 指定マスが空白か判定
	public boolean isPositionEmpty(Position pos) {
		try {
			return checkPosition(stone_type.EMPTY, pos);
		} catch (OutOfBoardException e) {
			// TODO 自動生成された catch ブロック
			//e.printStackTrace();
			return false;
		}
	}

	// 指定マスが白か判定
	public boolean isPositionWhite(Position pos) {
		try {
			return checkPosition(stone_type.WHITE, pos);
		} catch (OutOfBoardException e) {
			// TODO 自動生成された catch ブロック
			//			e.printStackTrace();
			return false;
		}
	}

	// 指定マスが黒か判定
	public boolean isPositionBlack(Position pos) {
		try {
			return checkPosition(stone_type.BLACK, pos);
		} catch (OutOfBoardException e) {
			// TODO 自動生成された catch ブロック
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param pos Position オブジェクト
	 * @return posのマス情報
	 */
	public stone_type getStone(Position pos) {
		return m_bord[pos.getY()][pos.getX()];
	}

	/**
	 * 指定マスで幾つ石を裏返せるか数える
	 * @param stoneColar
	 * @param pos
	 * @return 裏返せる石の総数
	 */
	public ArrayList<Position> checkCanPutStone(stone_type stoneColar, Position pos) {
		int num = 0;
		int total = 0;

		ArrayList<Position> arrayAll = new ArrayList<Position>();
		// 左上を起点に時計回りに1~8]

		for (int vec = 1; vec <= 8; vec++) {
			// チェック方向の座標を取得
			ArrayList<Position> arrayPos = new ArrayList<Position>();
			Position wk_Pos = new Position(pos.getY(), pos.getX());
			switch (vec) {
			case 1:
				wk_Pos.LeftUp();
				break;
			case 2:
				wk_Pos.Up();
				break;
			case 3:
				wk_Pos.RightUp();
				break;
			case 4:
				wk_Pos.Right();
				break;
			case 5:
				wk_Pos.RightDown();
				break;
			case 6:
				wk_Pos.Down();
				break;
			case 7:
				wk_Pos.LeftDown();
				break;
			case 8:
				wk_Pos.Left();
				break;
			}
			num = checkSetPosition(arrayPos, stoneColar, wk_Pos, vec, 0);
			total += num;
			if (0 != num) {
				arrayAll.addAll(arrayPos);
			}
			//System.out.println("vec:" + vec + "cnt:" + num);
		}
		//System.out.println("total:" + total);
		return arrayAll;
	}

	/**
	 * 指定方向辺りの裏返せる石の数を数える
	 * @param stoneColar チェック対照の石の色
	 * @param pos チェック対照の座標
	 * @param vector チェック対照方向、左上を起点に時計回りに1~8
	 * @return 裏返せる石の数
	 */
	private int checkSetPosition(ArrayList<Position> arrayPos, stone_type stoneColar, Position pos, int vector,
			int count) {
		Position wk_Pos = new Position(pos.getY(), pos.getX());

		boolean ret;
		try {
			ret = checkPosition(stoneColar, pos);
		} catch (OutOfBoardException e) {
			// TODO 自動生成された catch ブロック
			//e.printStackTrace();
			return 0;
		}
		if (ret) {
			// 次の座標を指定
			switch (vector) {
			case 1:
				wk_Pos.LeftUp();
				break;
			case 2:
				wk_Pos.Up();
				break;
			case 3:
				wk_Pos.RightUp();
				break;
			case 4:
				wk_Pos.Right();
				break;
			case 5:
				wk_Pos.RightDown();
				break;
			case 6:
				wk_Pos.Down();
				break;
			case 7:
				wk_Pos.LeftDown();
				break;
			case 8:
				wk_Pos.Left();
				break;
			}
			count++;
			arrayPos.add(pos);
			return checkSetPosition(arrayPos, stoneColar, wk_Pos, vector, count);
		} else if (isPositionEmpty(wk_Pos)) {
			return 0;
		} else {
			return count;
		}
	}

	// 盤面上の指定石の数を返す
	private int getObj(stone_type target) {
		int num = 0;

		for (int i = 0; i < m_bord.length; i++) {
			for (int j = 0; j < m_bord[i].length; j++) {
				if (target == m_bord[i][j]) {
					num++;
				}
			}
		}
		return (num);
	}

	// 盤面上の空のマスの数を返す
	public int isNumEmpty() {
		return getObj(stone_type.EMPTY);
	}

	// 盤面上の黒のマスの数を返す
	public int isCountBlack() {
		return getObj(stone_type.BLACK);
	}

	// 盤面上の白のマスの数を返す
	public int isCountWhite() {
		return getObj(stone_type.WHITE);
	}

	/**
	 * 黒の石を置く
	 * @param pos
	 * @return
	 * @throws OutOfBoardException
	 */
	public boolean setPositionBlack(Position pos) {

		if (isPositionEmpty(pos)) {
			m_bord[pos.getY()][pos.getX()] = stone_type.BLACK;
			return true;
		}
		System.out.println("おけません");
		return false;
	}

	public boolean setStoneBlack(Position pos) {
		if (isPositionEmpty(pos)) {
			ArrayList<Position> arryPos = checkCanPutStone(stone_type.WHITE, pos);
			if(arryPos.isEmpty()){
				// 返せる石なし
				return false;
			}
			m_bord[pos.getY()][pos.getX()] = stone_type.BLACK;

			Iterator<Position> ite = arryPos.iterator();
			while (ite.hasNext()) {
				Position pripos = ite.next();
				m_bord[pripos.getY()][pripos.getX()] = stone_type.BLACK;
			}

			return true;
		}
		System.out.println("おけません");
		return false;
	}

	/**
	 * 白の石を置く
	 * @param pos
	 * @return
	 * @throws OutOfBoardException
	 */
	public boolean setPositionWhite(Position pos) {
		if (isPositionEmpty(pos)) {
			m_bord[pos.getY()][pos.getX()] = stone_type.WHITE;
			return true;
		}
		System.out.println("おけません");
		return false;
	}

	public boolean setStoneWhite(Position pos) {
		if (isPositionEmpty(pos)) {
			ArrayList<Position> arryPos = checkCanPutStone(stone_type.BLACK, pos);
			if(arryPos.isEmpty()){
				// 返せる石なし
				return false;
			}
			m_bord[pos.getY()][pos.getX()] = stone_type.WHITE;

			Iterator<Position> ite = arryPos.iterator();
			while (ite.hasNext()) {
				Position pripos = ite.next();
				m_bord[pripos.getY()][pripos.getX()] = stone_type.WHITE;
			}

			return true;
		}
		System.out.println("おけません");
		return false;
	}


	/**
	 * マスを空にする
	 * @param pos
	 * @throws OutOfBoardException
	 */
	public void setPositionEmpty(Position pos) {
		m_bord[pos.getY()][pos.getX()] = stone_type.EMPTY;
	}

	//
	/**
	 * 盤面上の石を初期状態にする
	 */
	public void initGame() {
		m_bord[3][3]=stone_type.WHITE;
		m_bord[4][4]=stone_type.WHITE;

		m_bord[3][4]=stone_type.BLACK;
		m_bord[4][3]=stone_type.BLACK;
	}
	// 以下CPU用メソッド

	// 裏返せる石判定
	private boolean retStone(Position pos, stone_type stone) throws OutOfBoardException, CannotPutException {
		//ArrayList<Position> arryPos;

		// 枠外判定
		checkOutOfBord(pos);
		// 空枠判定
		if (stone_type.EMPTY == m_bord[pos.getY()][pos.getX()]) {
			throw new CannotPutException();
		}
		// 同色判定
		if (stone != m_bord[pos.getY()][pos.getX()]) {
			// stone
			return false;
		}
		return true;

	}

	// 裏返せる石の位置を返す
	public void returnPosition(int ishi, Position pos) {

	}

}

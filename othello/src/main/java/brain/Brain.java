/**
 *
 */
package brain;

import java.util.ArrayList;

import othello.Bord;
import othello.Bord.stone_type;
import othello.Position;

/**
 * @author user2
 *
 */
public final class Brain {
	// 自分の色

	// 盤面オブジェクト
	private Bord m_gameBord;
	public stone_type m_stone;

	public Brain( Bord bord, stone_type stone) {
		// TODO 自動生成されたコンストラクター・スタブ
		m_gameBord = bord;
		m_stone = stone;
	}

	// 置ける枠の検索
	// 置ける枠の評価
	// 置く枠の決定
	public Position getNextStone(){
		int len = m_gameBord.getMaxSize();
		stone_type chk_stone = (m_stone == stone_type.BLACK)? stone_type.WHITE: stone_type.BLACK;

		for(int i=0;i<len;i++){
			for(int j=0;j<len;j++){
				Position pos = new Position(i, j);
				if (m_gameBord.isPositionEmpty(pos)) {
					ArrayList<Position> arryPos = m_gameBord.checkCanPutStone(chk_stone, pos);
					if(arryPos.isEmpty()){
						// 返せる石なし
						continue;
					}
					System.out.println("next stone x:"+pos.getX() + " y:"+pos.getY());
					return pos;
				}

			}
		}
		return null;

	}
}

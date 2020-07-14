package othello;

public class Position {
	private int m_tate;
	private int m_yoko;

	public static enum position_move {UP,DOWN,RIGHT,LEFT,UPRIGHT,UPLEFT,DOWNRIGHT,DOWNLEFT};

	public Position() {
		// TODO 自動生成されたコンストラクター・スタブ
		m_tate = 0;
		m_yoko = 0;
	}
	public Position (int tate, int yoko) {
		m_tate = tate;
		m_yoko = yoko;
	}

	// getX
	public int getX() {
		return this.m_yoko;
	}
	// GetY
	public int getY() {
		return this.m_tate;
	}

	//move
	public Position Move(position_move p_mv) {
		switch(p_mv){
		case UP:
			this.m_tate -= 1;
			break;
		case DOWN:
			this.m_tate += 1;
			break;
		case LEFT:
			this.m_yoko -= 1;
			break;
		case RIGHT:
			this.m_yoko += 1;
			break;
		case UPLEFT:
			this.m_tate -= 1;
			this.m_yoko -= 1;
			break;
		case UPRIGHT:
			this.m_tate -= 1;
			this.m_yoko += 1;
			break;
		case DOWNLEFT:
			this.m_tate += 1;
			this.m_yoko -= 1;
			break;
		case DOWNRIGHT:
			this.m_tate += 1;
			this.m_yoko += 1;
			break;
			default:
		}
		return this;

	}

	// Up
	public Position Up() {
		this.m_tate -= 1;
		return this;
	}
	// Down
	public Position Down() {
		this.m_tate += 1;
		return this;
	}
	// Right
	public Position Right() {
		this.m_yoko += 1;
		return this;
	}
	// Left
	public Position Left() {
		this.m_yoko -= 1;
		return this;
	}
	// Rightup
	public Position RightUp() {
		this.m_tate -= 1;
		this.m_yoko += 1;
		return this;
	}
	// RightDown
	public Position RightDown() {
		this.m_tate += 1;
		this.m_yoko += 1;
		return this;
	}
	// LeftDown
	public Position LeftDown() {
		this.m_tate += 1;
		this.m_yoko -= 1;
		return this;
	}
	// LeftUp
	public Position LeftUp() {
		this.m_tate -= 1;
		this.m_yoko -= 1;
		return this;
	}
}

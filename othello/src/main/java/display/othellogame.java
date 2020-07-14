package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import brain.Brain;
import othello.Bord;
import othello.Bord.stone_type;
import othello.OutOfBoardException;
import othello.Position;

/**
 * @author user2
 *
 */
public class othellogame extends JFrame {

	private JPanel contentPane;
	/**
	 * 盤面画像情報
	 */
	private BufferedImage m_Bord;
	/**
	 * 黒石画像情報
	 */
	private BufferedImage m_Black;
	/**
	 * 白石画像情報
	 */
	private BufferedImage m_White;
	private int m_BordSize;
	private JEditorPane editorPane;
	/**
	 * 盤面情報
	 */
	private Bord bord;

	/**
	 * 先攻後攻フラグ
	 * 黒:true
	 * 白:false
	 */
	private boolean m_stoneFlg = true;
	private int bairitu = 2; // マスの倍率
	/**
	 * 状況フラグ
	 * プレイ中：true
	 * 待機中：false
	 */
	private boolean m_playFlg = false;

	/**
	 * 設置権
	 * 人間：true
	 * 機械：false
	 */
	private boolean m_player = true;

	/**
	 * パス回数
	 */
	private int m_pass_count;
	/**
	 * パス上限
	 */
	private int m_pass_max;

	private Brain m_brain;

	private JPanel panel_stone_inf;
	private JPanel panel_setting;
	private JRadioButton rdbtn_black;
	private JRadioButton rdbtn_white;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextPane infoPane;
	private JComboBox<?> comboBox;
	private JButton btnPass;
	private JTextField textField_white_stone_num;
	private JTextField textField_black_stone_num;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					othellogame frame = new othellogame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		super.paint(g);
		try {
			printImage();
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
	/**
	 * Create the frame.
	 */
	public othellogame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

				JPanel inf_panel = new JPanel();
				contentPane.add(inf_panel, BorderLayout.EAST);
				inf_panel.setLayout(new BoxLayout(inf_panel, BoxLayout.Y_AXIS));

				panel_stone_inf = new JPanel();
				inf_panel.add(panel_stone_inf);
				panel_stone_inf.setLayout(new BoxLayout(panel_stone_inf, BoxLayout.Y_AXIS));

				JPanel panel = new JPanel();
				panel_stone_inf.add(panel);
				panel.setLayout(new GridLayout(0, 1, 0, 0));

				JTextField textField = new JTextField();
				textField.setEditable(false);
				textField.setText("\u9ED2\u306E\u6570");
				panel.add(textField);
				textField.setColumns(10);

				textField_black_stone_num = new JTextField();
				textField_black_stone_num.setText("0");
				textField_black_stone_num.setBackground(Color.WHITE);
				textField_black_stone_num.setEditable(false);
				panel.add(textField_black_stone_num);
				textField_black_stone_num.setColumns(10);

				JPanel panel_1 = new JPanel();
				panel_stone_inf.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 1, 0, 0));

				JTextField textField_2 = new JTextField();
				textField_2.setEditable(false);
				textField_2.setText("\u767D\u306E\u6570");
				panel_1.add(textField_2);
				textField_2.setColumns(10);

				textField_white_stone_num = new JTextField();
				textField_white_stone_num.setText("0");
				textField_white_stone_num.setBackground(Color.WHITE);
				textField_white_stone_num.setEditable(false);
				panel_1.add(textField_white_stone_num);
				textField_white_stone_num.setColumns(10);

				JPanel panel_2 = new JPanel();
				panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_stone_inf.add(panel_2);
				panel_2.setLayout(new GridLayout(0, 1, 0, 0));

								JPanel panel_3 = new JPanel();
								panel_2.add(panel_3);
								panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

								JTextField textField_1 = new JTextField();
								textField_1.setText("\u30D1\u30B9\u56DE\u6570");
								textField_1.setBackground(Color.WHITE);
								textField_1.setEditable(false);
								panel_3.add(textField_1);
								textField_1.setColumns(10);

								comboBox = new JComboBox();
								comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
								panel_3.add(comboBox);

								infoPane = new JTextPane();
								infoPane.setToolTipText("\u60C5\u5831\u30D1\u30CD\u30EB");
								infoPane.setBackground(Color.WHITE);
								infoPane.setEditable(false);
								panel_2.add(infoPane);

				panel_setting = new JPanel();
				inf_panel.add(panel_setting);
				panel_setting.setLayout(new BoxLayout(panel_setting, BoxLayout.Y_AXIS));

				rdbtn_black = new JRadioButton("\u9ED2\uFF1A\u5148\u653B");
				buttonGroup.add(rdbtn_black);
				rdbtn_black.setSelected(true);
				rdbtn_black.setToolTipText("\u9ED2\uFF1A\u5148\u653B");
				panel_setting.add(rdbtn_black);

				rdbtn_white = new JRadioButton("\u767D\uFF1A\u5F8C\u653B");
				buttonGroup.add(rdbtn_white);
				rdbtn_white.setToolTipText("\u767D\uFF1A\u5F8C\u653B");
				panel_setting.add(rdbtn_white);

		JPanel btn_panel = new JPanel();
		contentPane.add(btn_panel, BorderLayout.SOUTH);

		final JButton btnGamePlay = new JButton("\u958B\u59CB");
		btnGamePlay.setToolTipText("\u958B\u59CB/\u6295\u4E86\u30DC\u30BF\u30F3");
		btnGamePlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(btnGamePlay.getText().equals("投了")){
					// ゲーム終了処理
					itemSetEnable(true);

					// playフラグ切り替え
					m_playFlg = false;
					// 開始ボタン表示変更
					btnGamePlay.setText("開始");

					m_brain = null;
				}else{
					// ゲーム開始処理
					// 設定ボタンの有効無効切り替え
					itemSetEnable(false);

					System.out.println("pass:"+ (String)comboBox.getSelectedItem() );
					m_pass_max = Integer.parseInt((String)comboBox.getSelectedItem());

					// 盤面の初期化
					createBord();
					bord.initGame();
					// CPUの初期化
					// playフラグ切り替え
					m_playFlg = true;
					// 開始ボタン表示変更
					btnGamePlay.setText("投了");

					// CPUへの初期情報設定
					stone_type stone = m_stoneFlg ? stone_type.WHITE: stone_type.BLACK;
					m_brain = new Brain(bord, stone);
					// 設置権の初期化
					m_player = m_stoneFlg ? true: false;

				}
				m_pass_count = 0;
				infoPane.setText("");

				try {
					m_stoneFlg = true;
					printImage();
				} catch (InterruptedException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
		});
		btn_panel.add(btnGamePlay);

		btnPass = new JButton("\u30D1\u30B9");
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(m_playFlg){
					if( m_pass_count < m_pass_max){
						m_pass_count++;
					}else{
						//System.out.println("もうパスできません");
						infoPane.setText("もうパスできません");
					}
				}
			}
		});
		btnPass.setEnabled(false);
		btn_panel.add(btnPass);

		editorPane = new JEditorPane();
		editorPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Position pos = getPosition(e.getY(), e.getX());

				try {
					setStone(pos);
					printImage();

					Thread.sleep(500);
					setStoneCpu();
					printImage();
				} catch (InterruptedException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
		});
		editorPane.setEditable(false);
		contentPane.add(editorPane, BorderLayout.CENTER);

		// add
		loadImage();
		createBord();
	}

	/**
	 * クリック座標から対照マスを返す
	 * @param y 縦座標
	 * @param x 横座標
	 * @return Position型オブジェクト
	 */
	private Position getPosition(int y, int x) {
		int size = 16 * bairitu;
		int max_x = bord.getMaxSize();
		int max_y = bord.getMaxSize();

		if ((max_x * size > x) && (max_y * size > y)) {
			Position pos = new Position((y / size), (x / size));
			return pos;
		}
		return null;
	}

	/**
	 * 画像イメージの読込
	 */
	private void loadImage() {
		try {
			m_Bord = ImageIO.read(new File("src/main/resources/img/bord_base.png"));
			m_Black = ImageIO.read(new File("src/main/resources/img/bord_black.png"));
			m_White = ImageIO.read(new File("src/main/resources/img/bord_white.png"));
			m_BordSize = m_Bord.getWidth();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * 盤面情報の初期化
	 */
	private void createBord() {
		bord = new Bord();
	}

	/**
	 * 石設定
	 * @param pos 指定座標
	 * @throws InterruptedException
	 * @throws OutOfBoardException
	 */
	private void setStone(Position pos)  {
		if (null == pos) {
			return;
		}

		if(m_player){
			if(m_stoneFlg){
				// 黒石設定
				if(bord.setStoneBlack(pos)){
					m_player = false;
				}
			}else{
				// 白石設定
				if(bord.setStoneWhite(pos)){
					m_player = false;
				}
			}
		}
	}

	private void setStoneCpu() {
		if(!m_player){

			// CPU
			Position cpu_pos = m_brain.getNextStone();
			if(null != cpu_pos){
				if( stone_type.BLACK == m_brain.m_stone){
					// 黒石設定
					bord.setStoneBlack(cpu_pos);
				}else{
					// 白石設定
					bord.setStoneWhite(cpu_pos);
				}
			}
			m_player = true;
		}
	}

	// お試しランダム設置
	private void setImage() throws OutOfBoardException {
		Random rnd = new Random();
		int masu_size = bord.getMaxSize();

		for (int i = 0; i < masu_size; i++) {
			for (int j = 0; j < masu_size; j++) {
				int ran = rnd.nextInt(3);
				switch (ran) {
				case 0:
					bord.setPositionEmpty(new Position(i, j));
					break;
				case 1:
					bord.setPositionBlack(new Position(i, j));
					break;
				case 2:
					bord.setPositionWhite(new Position(i, j));
					break;
				}
			}
		}
	}

	/**
	 * 描画処理
	 */
	private void printImage() throws InterruptedException {

		int size = m_BordSize * bairitu;
		int masu_size = bord.getMaxSize();
		BufferedImage out_img = null;

		for (int y = 0; y < masu_size; y++) {//横
			for (int x = 0; x < masu_size; x++) {//縦

				switch (bord.getStone(new Position(y, x))) {
				case EMPTY:
					out_img = m_Bord;
					break;
				case BLACK:
					out_img = m_Black;
					break;
				case WHITE:
					out_img = m_White;
					break;
				}
				//Thread.sleep(25);

				editorPane.getGraphics().drawImage(out_img,
						x * size, y * size, (x + 1) * size, (y + 1) * size,
						0, 0, out_img.getHeight(), out_img.getWidth(),
						null);
			}
		}

		// 石の数集計

		textField_black_stone_num.setText(Integer.toString( bord.isCountBlack() ));

		textField_white_stone_num.setText(Integer.toString( bord.isCountWhite() ));
	}

	private void itemSetEnable( boolean stat) {
		// 設定ボタンの有効無効切り替え
			// パス回数
			comboBox.setEnabled(stat);
			// 先攻後攻
			rdbtn_black.setEnabled(stat);
			rdbtn_white.setEnabled(stat);

			// 反転設定
			// パスボタン
			btnPass.setEnabled(!stat);
	}
}

package kitri.foodCourt.user.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.*;

import kitri.foodCourt.dto.FoodDto;
import kitri.foodCourt.user.BasketDetail;
import kitri.foodCourt.user.User;
import kitri.foodCourt.user.basket.BasketMain;
import kitri.foodCourt.user.menu.*;
import kitri.foodCourt.user.swing.FLabel;

public class FoodMain extends JFrame {
	String currentPanelName;
	public JPanel contentPane;
	public JTextField searchField;
	public JPanel panel = new JPanel();
	public JPanel panel_5 = new JPanel();
	public JPanel panel_3 = new JPanel();
	public JPanel panel_2 = new JPanel();
	public UserMenuView userMenuView = new UserMenuView();
	public UserMenuDetailView UserMenuDetailView = new UserMenuDetailView();
	public FoodMainView foodMainView = new FoodMainView();
	public NoSearchMenuImg noSearchMenuImg = new NoSearchMenuImg();

	public JLabel labSearch = new JLabel("\uAC80\uC0C9");
	public JPanel panel_6 = new JPanel();
	public JPanel panChangePanel = new JPanel();
	public CardLayout card = new CardLayout();

	public JButton btnMainMenu = new MainButton(
			new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/mainbutton.jpg")));

	public JButton btnSearch = new JButton(
			new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/search.PNG")));
	public JPanel panMainButton = new JPanel();
	public JLabel labMenu = new JLabel(
			new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/menu.PNG")));
	public JButton btnUserInfo = new JButton(
			new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/mypage.PNG")));
	public JLabel labName = new JLabel();
	public JButton btnOrderList = new JButton(
			new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/basket.png")));
	public JLabel labBasket = new JLabel("\uC8FC\uBB38\uBAA9\uB85D");
	public JPanel panBarVerRight = new JPanel();
	public Cursor csorHandCursor = new Cursor(Cursor.HAND_CURSOR);
	boolean check = false;
	// ��ٱ���, �����κ� ����
	public User user;
	public BasketMain basketMain;
	public FLabel lbOrderCircle = new FLabel();
	public FLabel lbOrderCount = new FLabel(Font.BOLD, 20);

	public JLabel labHansic = new JLabel("\uD55C\uC2DD");
	public JLabel labJoongsic = new JLabel("\uC911\uC2DD");
	public JLabel labIlsic = new JLabel("\uC77C\uC2DD");
	public JLabel labYangsic = new JLabel("\uC591\uC2DD");
	public JButton btnHansic = new MainButton(new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/HansicMin1.jpg")));
	public JButton btnJoongsic = new MainButton(new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/joongsicMin.jpg")));
	public JButton btnIlsic = new MainButton(new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/ilsicMin.jpg")));
	public JButton btnYangsic = new MainButton(new ImageIcon(FoodMain.class.getResource("/kitri/foodCourt/user/menu/mainImage/yangsicMin.jpg")));
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodMain frame = new FoodMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void test() {
		FoodDto food1 = new FoodDto("1", "�����", 1, "�ѽ�", 5000, "/kitri/foodCourt/user/basket/image/����1.jpg");
		FoodDto food2 = new FoodDto("2", "������ũ", 2, "���", 8000, "/kitri/foodCourt/user/basket/image/����1.jpg");
		FoodDto food3 = new FoodDto("3", "�����", 3, "�߽�", 7000, "/kitri/foodCourt/user/basket/image/����1.jpg");
		FoodDto food4 = new FoodDto("4", "������Ű«��", 4, "�Ͻ�", 6000, "/kitri/foodCourt/user/basket/image/����1.jpg");
		FoodDto food5 = new FoodDto("5", "������Ű«��", 4, "�Ͻ�", 6000, "/kitri/foodCourt/user/basket/image/����1.jpg");
		BasketDetail detail1 = new BasketDetail(food1, 1);
		BasketDetail detail2 = new BasketDetail(food2, 2);
		BasketDetail detail3 = new BasketDetail(food3, 3);
		BasketDetail detail4 = new BasketDetail(food4, 4);
		BasketDetail detail5 = new BasketDetail(food5, 5);

		user.getBasket().setOrderCount(lbOrderCount);
		user.getBasket().add(detail1);
		user.getBasket().add(detail2);
		user.getBasket().add(detail3);
		user.getBasket().add(detail4);
		user.getBasket().add(detail5);
	}
	public void setPanelName(String panelName) {
		this.currentPanelName = panelName;
	}
	public String getPanelName() {
		return this.currentPanelName;
	}

	public FoodMain() {
		// �׽�Ʈ��
		user = new User("calubang", "�Ⱥ���", 5000);
		labName.setText(user.getName() + "��");
		test();
		basketMain = new BasketMain(this);

		labName.setHorizontalAlignment(SwingConstants.CENTER);
		labName.setVerticalTextPosition(0);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		panel.setBorder(null);
		btnSearch.setToolTipText("\uAC80\uC0C9");
		// Ŀ�� �������
		btnSearch.setCursor(csorHandCursor);
		btnMainMenu.setCursor(csorHandCursor);
		btnUserInfo.setToolTipText("\uD68C\uC6D0\uC815\uBCF4");
		btnUserInfo.setCursor(csorHandCursor);
		lbOrderCount.setHorizontalAlignment(SwingConstants.CENTER);
		lbOrderCount.setBackground(Color.WHITE);
		lbOrderCount.setCursor(csorHandCursor);
		btnHansic.setCursor(csorHandCursor);
		btnJoongsic.setCursor(csorHandCursor);
		btnIlsic.setCursor(csorHandCursor);
		btnYangsic.setCursor(csorHandCursor);
		
		btnOrderList.setFocusPainted(false);
		btnOrderList.setContentAreaFilled(false);
		btnOrderList.setToolTipText("\uC8FC\uBB38\uBAA9\uB85D");
		btnOrderList.setCursor(csorHandCursor);
		labName.setCursor(csorHandCursor);
		labBasket.setCursor(csorHandCursor);

		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel_5.setBackground(Color.DARK_GRAY);

		panel_5.setBounds(12, 10, 1160, 6);
		panel.add(panel_5);
		panel_3.setBackground(Color.DARK_GRAY);

		panel_3.setBounds(12, 102, 1160, 6);
		panel.add(panel_3);

		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(16, 21, 115, 75);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		btnMainMenu.setToolTipText("\uBA54\uC778");
		btnMainMenu.setBorder(null);
		btnMainMenu.setPreferredSize(new Dimension(115, 75));

		btnMainMenu.setVerticalAlignment(SwingConstants.BOTTOM);
		btnMainMenu.setBorderPainted(false);
		panel_2.add(btnMainMenu);
		btnMainMenu.setBackground(Color.WHITE);
		panChangePanel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(16, 210, 115, 116);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		labHansic.setOpaque(true);
		labHansic.setBackground(Color.WHITE);
		labHansic.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(labHansic, BorderLayout.SOUTH);
		panel_1.add(btnHansic, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(16, 337, 115, 116);
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		
		labJoongsic.setBackground(Color.WHITE);
		labJoongsic.setOpaque(true);
		labJoongsic.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(labJoongsic, BorderLayout.SOUTH);
		panel_4.add(btnJoongsic, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(16, 463, 115, 116);
		panel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		labIlsic.setBackground(Color.WHITE);
		labIlsic.setOpaque(true);
		labIlsic.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(labIlsic, BorderLayout.SOUTH);
		panel_7.add(btnIlsic, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(16, 589, 115, 116);
		panel.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		labYangsic.setBackground(Color.WHITE);
		labYangsic.setOpaque(true);
		labYangsic.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(labYangsic, BorderLayout.SOUTH);
		panel_8.add(btnYangsic, BorderLayout.CENTER);

//		--------------------���ϴ� �г� �κ� ī�巹�̾ƿ� ����
		panChangePanel.setLayout(card);
		panChangePanel.add(UserMenuDetailView, "UserMenuDetailView");
		panChangePanel.add(userMenuView, "userMenuView");
		panChangePanel.add(foodMainView, "foodMainView");
		panChangePanel.add(noSearchMenuImg, "noSearchMenuImg");
		panChangePanel.add(basketMain, "basketMain");
		panChangePanel.setBounds(160, 118, 1012, 634);
		panel.add(panChangePanel);
		currentPanelName = "foodMainView";
		card.show(panChangePanel, currentPanelName);

		searchField = new JTextField();
		searchField.setFont(new Font("���� ���", Font.BOLD, 16));
		searchField.setBorder(new MatteBorder(2, 2, 4, 4, (Color) Color.LIGHT_GRAY));
		searchField.setBounds(240, 42, 553, 38);
		panel.add(searchField);
		searchField.setColumns(10);
		panel_6.setBackground(Color.DARK_GRAY);

		panel_6.setBounds(145, 118, 4, 633);
		panel.add(panel_6);
		btnSearch.setBorder(null);
		btnSearch.setBounds(826, 42, 40, 38);

		panel.add(btnSearch);
		panMainButton.setBounds(145, 26, 4, 66);
		panel.add(panMainButton);
		panMainButton.setBackground(Color.DARK_GRAY);
		labMenu.setToolTipText("\uBA54\uB274");
		labMenu.setOpaque(true);
		labMenu.setBackground(Color.WHITE);
		labMenu.setHorizontalAlignment(SwingConstants.CENTER);
		labMenu.setBounds(12, 118, 123, 82);

		panel.add(labMenu);
		btnUserInfo.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		btnUserInfo.setBounds(965, 26, 79, 54);

		panel.add(btnUserInfo);
		labName.setBounds(965, 77, 79, 22);

		panel.add(labName);

		// �ֹ� ���� ǥ��
		lbOrderCount.setBounds(1129, 15, 35, 35);
		panel.add(lbOrderCount);
		user.getBasket().setOrderCount(lbOrderCount);

		lbOrderCircle.setBounds(1129, 15, 35, 35);
		URL url = FoodMain.class.getResource("/kitri/foodCourt/user/basket/image/red_circle.png");
		try {
			BufferedImage image = ImageIO.read(url);
			ImageIcon icon = new ImageIcon(
					image.getScaledInstance(lbOrderCircle.getWidth(), lbOrderCircle.getHeight(), Image.SCALE_FAST));
			lbOrderCircle.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel.add(lbOrderCircle);

		btnOrderList.setBorder(new LineBorder(Color.GRAY, 2, true));
		btnOrderList.setBounds(1074, 26, 79, 54);

		panel.add(btnOrderList);
		labBasket.setVerticalTextPosition(SwingConstants.CENTER);
		labBasket.setHorizontalAlignment(SwingConstants.CENTER);
		labBasket.setBounds(1074, 77, 79, 22);

		panel.add(labBasket);
		panBarVerRight.setBackground(Color.DARK_GRAY);
		panBarVerRight.setBounds(916, 26, 4, 66);

		panel.add(panBarVerRight);
		labSearch.setBounds(170, 26, 60, 66);
		panel.add(labSearch);
		// ---------------------
		labSearch.setFont(new Font("����", Font.PLAIN, 30));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);

		btnHansic.setName("1");
		btnJoongsic.setName("2");
		btnIlsic.setName("3");
		btnYangsic.setName("4");
		
//		--------------------------�̺�Ʈ ��Ϻ�
		FoodMainController foodMainController = new FoodMainController(this);
		btnMainMenu.addActionListener(foodMainController);
		btnSearch.addActionListener(foodMainController);
		btnUserInfo.addActionListener(foodMainController);
		btnOrderList.addActionListener(foodMainController);
		searchField.addActionListener(foodMainController);

		btnHansic.addActionListener(foodMainController);
		btnJoongsic.addActionListener(foodMainController);
		btnIlsic.addActionListener(foodMainController);
		btnYangsic.addActionListener(foodMainController);
		
		foodMainView.labHansic.addActionListener(foodMainController);
		foodMainView.labJoongsic.addActionListener(foodMainController);
		foodMainView.labIlsic.addActionListener(foodMainController);
		foodMainView.labYangsic.addActionListener(foodMainController);
	}
}
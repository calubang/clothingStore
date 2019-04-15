package kitri.foodCourt.user.view;

import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import javax.swing.*;
import javax.swing.border.*;

import kitri.foodCourt.dto.FoodDto;
import kitri.foodCourt.user.BasketDetail;
import kitri.foodCourt.user.User;
import kitri.foodCourt.user.controller.FoodMainController;
import kitri.foodCourt.user.swing.*;

public class FoodMain extends JFrame {
	public String currentPanelName;
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

	public FLabel labSearch = new FLabel("\uAC80\uC0C9");
	public JPanel panel_6 = new JPanel();
	public JPanel panChangePanel = new JPanel();
	public CardLayout card = new CardLayout();

	public JButton btnMainMenu = new MainButton();

	public JButton btnSearch = new JButton(
			new ImageIcon(FoodMain.class.getResource("/img/user/search.PNG")));
	public JPanel panMainButton = new JPanel();
	public JButton btnUserInfo = new JButton();
	public JLabel labName = new JLabel();
	public JButton btnOrderList = new JButton(
			new ImageIcon(FoodMain.class.getResource("/img/user/basket.png")));
	public JLabel labBasket = new JLabel("\uC8FC\uBB38\uBAA9\uB85D");
	public JPanel panBarVerRight = new JPanel();
	public Cursor csorHandCursor = new Cursor(Cursor.HAND_CURSOR);
	boolean check = false;
	// ��ٱ���, �����κ� ����
	public User user;
	public BasketMain basketMain;
	public FLabel lbOrderCircle = new FLabel();
	public FLabel lbOrderCount = new FLabel(Font.BOLD, 20);
	public FoodMainController foodMainController;
	
	//�������� ����
	public UserInfo userInfo;
	public JPasswordField pfPassword;
	public JPanel pPassword;
	public FOptionPane fOptionPane;
	public JDialog dgPassword;

	public JLabel labHansic = new JLabel("\uD55C  \uC2DD");
	public JLabel labJoongsic = new JLabel("\uC911  \uC2DD");
	public JLabel labIlsic = new JLabel("\uC77C  \uC2DD");
	public JLabel labYangsic = new JLabel("\uC591  \uC2DD");
	public JButton btnHansic = new MainButton(new ImageIcon(FoodMain.class.getResource("/img/user/koreanFoodImageSmall.png")));
	public JButton btnJoongsic = new MainButton(new ImageIcon(FoodMain.class.getResource("/img/user/chineseFoodImageSmall.png")));
	public JButton btnIlsic = new MainButton(new ImageIcon(FoodMain.class.getResource("/img/user/JapaneseFoodImageSmall.png")));
	public JButton btnYangsic = new MainButton(new ImageIcon(FoodMain.class.getResource("/img/user/americanFoodImageSmall.png")));
	
	
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

	public void setPanelName(String panelName) {
		this.currentPanelName = panelName;
	}
	public String getPanelName() {
		return this.currentPanelName;
	}

	public FoodMain() {
		// �׽�Ʈ��
		user = new User("calubang", "�Ⱥ���", 5000);
		user.setPhoneNumberFirst("010");
		user.setPhoneNumberMiddle("7163");
		user.setPhoneNumberlast("3863");
		user.setPassword("123456");
		user.getBasket().setOrderCount(lbOrderCount);
		labName.setFont(new Font("���� ���", Font.BOLD, 15));
		labName.setText(user.getName() + "��");
		
		//���ڵ�
		setTitle("ǱŰ�� ���� ���� ȯ���մϴ�.");
		basketMain = new BasketMain(this);
		userInfo = new UserInfo(this);

		labName.setHorizontalAlignment(SwingConstants.CENTER);
		labName.setVerticalTextPosition(0);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		panel.setBorder(null);
		btnSearch.setToolTipText("\uAC80\uC0C9");
		
		//�н����� Ȯ�ο� �г�
		fOptionPane = new FOptionPane();
		dgPassword = fOptionPane.createDialog(this, "��ι�ȣ Ȯ��");
		dgPassword.setBounds(600, 400, dgPassword.getWidth(), dgPassword.getHeight());
		pPassword = new JPanel();
		pPassword.setLayout(new BorderLayout());
		FLabel label = new FLabel(Font.PLAIN, 15);
		pfPassword = new JPasswordField();
		label.setText("��й�ȣ�� �Է��ϼ���.");
		pPassword.add(label, BorderLayout.NORTH);
		pPassword.add(pfPassword, BorderLayout.CENTER);
		
		
		// Ŀ�� �������
		btnSearch.setCursor(csorHandCursor);
		btnMainMenu.setIcon(null);
		btnMainMenu.setBounds(0, 0, 120, 75);
		btnMainMenu.setCursor(csorHandCursor);
		btnUserInfo.setIcon(new ImageIcon(FoodMain.class.getResource("/img/user/myInfo.png")));
		btnUserInfo.setBackground(Color.WHITE);
		btnUserInfo.setToolTipText("\uD68C\uC6D0\uC815\uBCF4");
		btnUserInfo.setCursor(csorHandCursor);
		//btnUserInfo
		lbOrderCount.setHorizontalAlignment(SwingConstants.CENTER);
		lbOrderCount.setBackground(Color.WHITE);
		lbOrderCount.setCursor(csorHandCursor);
		
		btnOrderList.setFocusPainted(false);
		btnOrderList.setContentAreaFilled(false);
		btnOrderList.setToolTipText("\uC8FC\uBB38\uBAA9\uB85D");
		btnOrderList.setCursor(csorHandCursor);
		labName.setCursor(csorHandCursor);
		labBasket.setFont(new Font("���� ���", Font.BOLD, 15));
		labBasket.setCursor(csorHandCursor);

		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel_5.setBackground(SystemColor.activeCaption);

		panel_5.setBounds(12, 10, 1160, 6);
		panel.add(panel_5);
		panel_3.setBackground(SystemColor.activeCaption);

		panel_3.setBounds(12, 102, 1160, 6);
		panel.add(panel_3);

		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(16, 21, 120, 75);
		panel.add(panel_2);
		panel_2.setLayout(null);
		btnMainMenu.setToolTipText("\uBA54\uC778");
		btnMainMenu.setPreferredSize(new Dimension(115, 75));
		btnMainMenu.setIcon(new ImageIcon(FoodMain.class.getResource("/img/user/home2.png")));
		btnMainMenu.setBorderPainted(false);
		panel_2.add(btnMainMenu);
		btnMainMenu.setBackground(Color.WHITE);
		panChangePanel.setBackground(Color.WHITE);
		
		//koreanFood
		RoundPanel pKoreanMenu = new RoundPanel(2, 40);
		pKoreanMenu.setName("1");
		pKoreanMenu.setBackground(Color.WHITE);
		pKoreanMenu.setBounds(16, 120, 115, 100);
		panel.add(pKoreanMenu);
		pKoreanMenu.setLayout(null);
		
		labHansic.setFont(new Font("���� ���", Font.BOLD, 15));
		labHansic.setBounds(10, 70, 95, 20);
		labHansic.setOpaque(true);
		labHansic.setBackground(Color.WHITE);
		labHansic.setHorizontalAlignment(SwingConstants.CENTER);
		pKoreanMenu.add(labHansic);		
		btnHansic.setBounds(10, 10, 95, 70);
		btnHansic.setCursor(csorHandCursor);
		pKoreanMenu.add(btnHansic);
		btnHansic.setName("1");
		btnHansic.setFocusable(false);
		btnHansic.setContentAreaFilled(false);
		btnHansic.setBorderPainted(false);
	
		//chineseMenu
		RoundPanel pChineseMenu = new RoundPanel(2, 40);
		pChineseMenu.setName("2");
		pChineseMenu.setBackground(Color.WHITE);
		pChineseMenu.setBounds(16, 230, 115, 100);
		panel.add(pChineseMenu);
		pChineseMenu.setLayout(null);
		
		labJoongsic.setFont(new Font("���� ���", Font.BOLD, 15));
		labJoongsic.setSize(95, 20);
		labJoongsic.setLocation(10, 70);
		labJoongsic.setBackground(Color.WHITE);
		labJoongsic.setOpaque(true);
		labJoongsic.setHorizontalAlignment(SwingConstants.CENTER);
		pChineseMenu.add(labJoongsic, BorderLayout.SOUTH);
		btnJoongsic.setSize(95, 60);
		btnJoongsic.setLocation(10, 10);
		btnJoongsic.setCursor(csorHandCursor);
		pChineseMenu.add(btnJoongsic, BorderLayout.CENTER);
		btnJoongsic.setName("2");
		btnJoongsic.setFocusable(false);
		btnJoongsic.setContentAreaFilled(false);
		btnJoongsic.setBorderPainted(false);
		
		//japaneseFood
		RoundPanel pJapaneseMenu = new RoundPanel(2, 40);
		pJapaneseMenu.setName("3");
		pJapaneseMenu.setBackground(Color.WHITE);
		pJapaneseMenu.setBounds(16, 340, 115, 100);
		panel.add(pJapaneseMenu);
		pJapaneseMenu.setLayout(null);
		
		labIlsic.setFont(new Font("���� ���", Font.BOLD, 15));
		labIlsic.setBounds(10, 70, 95, 20);
		
		labIlsic.setBackground(Color.WHITE);
		labIlsic.setOpaque(true);
		labIlsic.setHorizontalAlignment(SwingConstants.CENTER);
		pJapaneseMenu.add(labIlsic);
		btnIlsic.setBounds(10, 10, 95, 60);
		btnIlsic.setCursor(csorHandCursor);
		pJapaneseMenu.add(btnIlsic);
		btnIlsic.setName("3");
		btnIlsic.setFocusable(false);
		btnIlsic.setContentAreaFilled(false);
		btnIlsic.setBorderPainted(false);
		
		//americanFood
		RoundPanel pAmericanMenu = new RoundPanel(2, 40);
		pAmericanMenu.setName("4");
		pAmericanMenu.setBackground(Color.WHITE);
		pAmericanMenu.setBounds(16, 450, 115, 100);
		panel.add(pAmericanMenu);
		pAmericanMenu.setLayout(null);
		labYangsic.setFont(new Font("���� ���", Font.BOLD, 15));
		labYangsic.setBounds(10, 70, 95, 20);
		labYangsic.setBackground(Color.WHITE);
		labYangsic.setOpaque(true);
		labYangsic.setHorizontalAlignment(SwingConstants.CENTER);
		pAmericanMenu.add(labYangsic);
		btnYangsic.setBounds(10, 10, 95, 60);
		btnYangsic.setCursor(csorHandCursor);
		pAmericanMenu.add(btnYangsic);
		btnYangsic.setName("4");
		
		btnYangsic.setFocusable(false);
		btnYangsic.setContentAreaFilled(false);
		btnYangsic.setBorderPainted(false);

//		--------------------���ϴ� �г� �κ� ī�巹�̾ƿ� ����
		panChangePanel.setLayout(card);
		UserMenuDetailView.setBackground(Color.WHITE);
		panChangePanel.add(UserMenuDetailView, "UserMenuDetailView");
		panChangePanel.add(userMenuView, "userMenuView");
		panChangePanel.add(foodMainView, "foodMainView");
		panChangePanel.add(noSearchMenuImg, "noSearchMenuImg");
		panChangePanel.add(basketMain, "basketMain");
		panChangePanel.add(userInfo, "userInfo");
		panChangePanel.setBounds(160, 118, 1012, 634);
		panel.add(panChangePanel);
		currentPanelName = "foodMainView";
		card.show(panChangePanel, currentPanelName);

		searchField = new JTextField();
		searchField.setFont(new Font("���� ���", Font.BOLD, 16));
		searchField.setBorder(new MatteBorder(2, 2, 4, 4, (Color) Color.BLACK));
		searchField.setBounds(240, 42, 553, 38);
		panel.add(searchField);
		searchField.setColumns(10);
		panel_6.setBackground(SystemColor.activeCaption);

		panel_6.setBounds(145, 118, 4, 633);
		panel.add(panel_6);
		btnSearch.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		btnSearch.setBounds(826, 42, 40, 38);

		panel.add(btnSearch);
		panMainButton.setBounds(145, 26, 4, 66);
		panel.add(panMainButton);
		panMainButton.setBackground(SystemColor.activeCaption);
		btnUserInfo.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		btnUserInfo.setBounds(965, 26, 79, 54);

		panel.add(btnUserInfo);
		labName.setBounds(965, 77, 79, 22);

		panel.add(labName);

		// �ֹ� ���� ǥ��
		lbOrderCount.setBounds(1129, 15, 35, 35);
		panel.add(lbOrderCount);
		user.getBasket().setOrderCount(lbOrderCount);

		lbOrderCircle.setBounds(1129, 15, 35, 35);
		lbOrderCircle.setIcon(new ImageIcon(FoodMain.class.getResource("/img/user/red_circle.png")));
		panel.add(lbOrderCircle);

		btnOrderList.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		btnOrderList.setBounds(1074, 26, 79, 54);

		panel.add(btnOrderList);
		labBasket.setVerticalTextPosition(SwingConstants.CENTER);
		labBasket.setHorizontalAlignment(SwingConstants.CENTER);
		labBasket.setBounds(1074, 77, 79, 22);

		panel.add(labBasket);
		panBarVerRight.setBackground(SystemColor.activeCaption);
		panBarVerRight.setBounds(916, 26, 4, 66);

		panel.add(panBarVerRight);
		labSearch.setBounds(170, 26, 60, 66);
		panel.add(labSearch);
		// ---------------------
		labSearch.setFont(new Font("���� ���", Font.BOLD, 30));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		
//		--------------------------�̺�Ʈ ��Ϻ�
		foodMainController = new FoodMainController(this);
		btnMainMenu.addActionListener(foodMainController);
		btnSearch.addActionListener(foodMainController);
		btnUserInfo.addActionListener(foodMainController);
		btnOrderList.addActionListener(foodMainController);
		searchField.addActionListener(foodMainController);
		
		//�н����� Ȯ�ο� �ؽ�Ʈ�ʵ�
		pfPassword.addActionListener(foodMainController);
		
		//���� �̺�Ʈ
		btnHansic.addActionListener(foodMainController);
		pKoreanMenu.addMouseListener(foodMainController);
		btnJoongsic.addActionListener(foodMainController);
		pChineseMenu.addMouseListener(foodMainController);
		btnYangsic.addActionListener(foodMainController);
		pAmericanMenu.addMouseListener(foodMainController);
		btnIlsic.addActionListener(foodMainController);
		pJapaneseMenu.addMouseListener(foodMainController);
		
		foodMainView.labHansic.addActionListener(foodMainController);
		foodMainView.labJoongsic.addActionListener(foodMainController);
		foodMainView.labIlsic.addActionListener(foodMainController);
		foodMainView.labYangsic.addActionListener(foodMainController);
	}
}
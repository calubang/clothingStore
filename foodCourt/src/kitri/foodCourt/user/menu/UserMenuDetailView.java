package kitri.foodCourt.user.menu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import kitri.foodCourt.dto.FoodDto;
import kitri.foodCourt.user.swing.*;
import kitri.foodCourt.util.Constance;

public class UserMenuDetailView extends JPanel {
	JTextField tfCount;
	String tfcountStr;
	JTextArea tfDescription = new JTextArea();
	FoodDto foodDto;
	JLabel image;
	FLabel foodName = new FLabel(Font.BOLD, 30);
	FLabel price = new FLabel(Font.PLAIN, 15);
	FLabel count = new FLabel(Font.PLAIN, 15);
	FLabel point = new FLabel(Font.PLAIN, 15);
	FLabel panel = new FLabel(Font.PLAIN, 15);
	JButton btnBack = new JButton("\uC774\uC804\uC73C\uB85C");
	AddOrderListButton btnBasket;
	public UserMenuDetailView(FoodDto foodDto, FoodMainService service) {
		tfDescription.setLineWrap(true);
		tfDescription.setFont(new Font("���� ���", Font.PLAIN, 15));
		BufferedImage bimg;
		try {
			bimg = ImageIO.read(new File(UserMenuView.class.getResource(foodDto.getImageAddress()).toURI()));
			ImageIcon properImg; // ����ũ�⿡ ������ �̹����� ���� ����
			properImg = new ImageIcon(bimg.getScaledInstance((int) (475), (int) (394), Image.SCALE_SMOOTH));
			
			image = new JLabel(properImg);
			foodName.setText(foodDto.getFoodName());
			price.setText("���� : " + Integer.toString(foodDto.getPrice()) + " ��");
			tfDescription.setText("\n"+foodDto.getDescription());
			point.setText("���� ����Ʈ : " + Integer.toString(foodDto.getPoint()));
			this.foodDto = foodDto;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnBasket = new AddOrderListButton("\uC7A5\uBC14\uAD6C\uB2C8 \uB2F4\uAE30", this);
		btnBack.setName("back");
		btnBasket.setName("basket");
		btnBasket.setFoodDto(foodDto);
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		image.setBorder(new MatteBorder(2, 2, 5, 5, (Color) new Color(128, 128, 128)));
		image.setToolTipText("\uC774\uBBF8\uC9C0");
		image.setBounds(35, 39, 475, 394);
		add(image);
		
		
		foodName.setBorder(new LineBorder(Color.GRAY, 3));
		foodName.setHorizontalAlignment(SwingConstants.CENTER);
		foodName.setToolTipText("\uBA54\uB274 \uC774\uB984");
		foodName.setBounds(542, 39, 419, 71);
		add(foodName);
		
		price.setBorder(new LineBorder(Color.LIGHT_GRAY, 3, true));
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setBounds(542, 131, 162, 52);
		add(price);
		
		tfDescription.setBorder(new LineBorder(Color.LIGHT_GRAY, 3, true));
		tfDescription.setBounds(542, 202, 419, 165);
		tfDescription.setEditable(false);
		add(tfDescription);
		count.setText("����");
		count.setHorizontalAlignment(SwingConstants.CENTER);
		count.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		
		count.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		count.setBounds(542, 385, 69, 35);
		add(count);
		
		
		point.setBorder(new LineBorder(Color.LIGHT_GRAY, 3, true));
		point.setHorizontalAlignment(SwingConstants.CENTER);
		point.setBounds(723, 131, 162, 52);
		add(point);
		
		panel.setBackground(Color.BLACK);
		panel.setBounds(631, 385, 3, 35);
		add(panel);
		
		tfCount = new JTextField("1");
		tfCount.setFont(new Font("���� ���", Font.PLAIN, 15));
		tfcountStr = tfCount.getText();
		tfCount.setHorizontalAlignment(SwingConstants.CENTER);
		tfCount.setBounds(653, 385, 308, 35);
		tfCount.addKeyListener(service.foodMainController);
		add(tfCount);
		tfCount.setColumns(10);
		
		RoundPanel pBack = new RoundPanel(3, 40);
		pBack.setBackground(Color.WHITE);
		pBack.setMouseListener();
		pBack.setName("back");
		pBack.setLayout(null);
		pBack.setBounds(199, 476, 200, 70);
		add(pBack);
		
		FLabel lbBack = new FLabel();
		//lbBack.setBackground(Color.BLACK);
		lbBack.setBounds(20, 10, 150, 50);
		lbBack.setHorizontalAlignment(SwingConstants.CENTER);
		lbBack.setFont(new Font("���� ���", Font.PLAIN, 20));
		lbBack.setText("��������");
		pBack.add(lbBack);
		
		btnBack.setFont(new Font("���� ���", Font.BOLD, 20));
		btnBack.setBounds(10, 10, 180, 70);
		btnBack.setBackground(Color.WHITE);
		btnBack.setFocusable(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		//pBack.add(btnBack);
		
		btnBasket.setFont(new Font("���� ���", Font.BOLD, 20));
		btnBasket.setBounds(585, 476, 200, 70);
		btnBasket.setBorder(new RoundBorder(30, 3));
		btnBasket.setBackground(Color.WHITE);
		add(btnBasket);
		
		//�̺�Ʈ ��Ϻ� ------------------------------------------
		pBack.setController(service.foodMainController);
		btnBack.addActionListener(service.foodMainController);
		btnBasket.addActionListener(service.foodMainController);
		btnBasket.addMouseListener(service.foodMainController);
		
	}
	public UserMenuDetailView() {
		// TODO Auto-generated constructor stub
	}
	public FoodDto getFoodDto() {
		return this.foodDto;
	}
	public int getCount() {
		if(tfCount.getText().isEmpty() || Integer.parseInt(tfCount.getText()) == 0)
			return 0;
		return Integer.parseInt(tfCount.getText()); // ��ٱ��� �߰���ư �׼��̺�Ʈ�� �߻������� ����
	}
}

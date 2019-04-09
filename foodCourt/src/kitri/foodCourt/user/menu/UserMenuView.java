package kitri.foodCourt.user.menu;

import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class UserMenuView extends JPanel {
	/**
	 * Create the panel.
	 */
	JScrollPane scrollPane = new JScrollPane();
	JPanel panel = new JPanel();
	GridBagLayout gbl_panel = new GridBagLayout();
	FoodMainService service;
								//db ���� 	select * from food;
//								select * from food where category_id = 1;
//								select * from food where food_name like '%�%';
//								������� ���� FoodDto���� List<FoodDto> �ȿ� �־�� ���̴�
//								�׷��� count = footList.size() �� �Ѱ����� ���´��� 
//								ȭ�鿡 �ѷ��ش� 
//								������ ���� �޴� ������ 17����� ����

//	�޴� �˻�ȭ���� ���� ����� ������ �� 2������
//	1. �˻� �Ǵ� ī�װ� ������ ���� ���� ���
//	2. �޴� �� ȭ�鿡�� �������� Ŭ������ ���� ���
	
//	�ܼ��� �������� Ŭ���̸� �̹� ī�װ� ���� Ȥ�� �˻��� ���� �޴�����Ʈ�� �̹� �������� ���̹Ƿ�
//	�޴��� �ٲ� �ʿ� ���� �ܼ��� setvisible true�� ���ָ� �ȴ�
// ī�װ� ���� Ȥ�� �˻��� ���� ���� ��쿡�� db���� �޴�����Ʈ�� ���ͼ� �ѷ���� �ϹǷ� �Ű������� �޴� list�� �޾ƿͼ� �ѷ���� �Ѵ�
	public UserMenuView(List<FoodDto> list, FoodMainService service) {
		this.service = service;
		setBackground(Color.WHITE);
		setLayout(null);
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		panel.setLayout(gbl_panel);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(null);
		
		scrollPane.setViewportView(panel);
		scrollPane.setBounds(12, 10, 988, 614);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setMenu(list);
		add(scrollPane);
//		panel.setPreferredSize(new Dimension(998, 614));
		
		
	}
	public void setMenu(List<FoodDto> list) { //���⼭ ����Ʈ�� �޾ƿ´�
		int listSize = list.size();
		try {
			int gridx = 0;
			int gridy = 0;
			for(FoodDto foodDto : list) {
				// �̹��� �ε�
				BufferedImage bimg = ImageIO.read(new File(UserMenuView.class.getResource(foodDto.getImageAddress()).toURI()));
				ImageIcon properImg; // ����ũ�⿡ ������ �̹����� ���� ����
				properImg = new ImageIcon(bimg.getScaledInstance((int) (185), (int) (170), Image.SCALE_SMOOTH));
				
				//��ũ�� �� ������Ʈ�� �°� �������ֱ� 190 190
				MenuPanel menuPanel = new MenuPanel(properImg, foodDto.getFoodName());
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 9, 9);
				gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnNewButton.gridx = gridx;
				gbc_btnNewButton.gridy = gridy;
				panel.add(menuPanel, gbc_btnNewButton);
				gridx++;
				if(gridx > 4) {
					gridx = 0;
					gridy++;
				}
				menuPanel.btnImg.setName(foodDto.getFoodId());
				menuPanel.btnImg.addActionListener(service.foodMainController);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
//		���� �޴��� 17�� �̾ƿ´ٰ� �ϸ�
//		0 1 2 3 4 
//		5���� ù �ٿ�
//		�����ٿ��� 
//		5 6 7 8 9 
//		5���� �;��Ѵ�
//
//		�׷��ٸ� ������ 17�� �����µ� 
//		���ΰ� 4�ΰ� ���� ������ 
//		���θ� �ٽ� 0���θ�����ְ�
//		���� 1 ������Ų��

}

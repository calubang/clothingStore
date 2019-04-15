package kitri.foodCourt.user.swing;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Form extends JFrame { // �̹��� ���̴� ���α׷� ����

	BufferedImage bimg; // �̹��� ������ �о���� ����
	int width, height; // �о���� �̹����� ���� ũ��
	JLabel imgLable; // �̹����� ����� ���̺�
	JPanel panel; // �г�

	public Form() {
		System.out.println("������");
		try{
			bimg = ImageIO.read(new File(Form.class.getResource("/img/user/junjoobibimbab.jpg").toURI())); //�̹��� �ε�
			System.out.println("�̹��� �ε� �Ϸ�");
			width = bimg.getWidth();
			height = bimg.getHeight();
		} catch(IOException ex) {
			System.out.println("IO�ͼ��� �߻�");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void initalize() {
		// JFrame
		setTitle("�̹��� ���� ���� ����");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		// Jpanel
		panel = new JPanel();
		panel.setBounds(0, 0, getWidth(), getHeight());
		panel.setBackground(Color.black);
		add(panel);
		// imgLable
		imgLable = new JLabel(new ImageIcon(bimg));
		imgLable.setBounds(10, 10, 300, 300);
		panel.add(imgLable);
		// JSlider
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 200, 100);
		slider.setMajorTickSpacing(20);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new imgChanger());
		slider.setBounds(10, 460, 480, 20);
		panel.add(slider);
		setVisible(true);
	}// initialize

	class imgChanger implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			double mag = ((JSlider) e.getSource()).getValue() / 100.0d;
			panel.remove(imgLable);
			imgLable = null;
			imgLable = new JLabel(new ImageIcon(bimg.getScaledInstance((int) (width * mag), (int) (height * mag), Image.SCALE_SMOOTH)));
			imgLable.setBounds(10, 10, 300, 300);
			panel.add(imgLable);
			panel.repaint();
		}// stateChanger
	}// imgChanger
	public static void main(String[] args) {
		Form form = new Form();
		form.initalize();
	}
}// Form

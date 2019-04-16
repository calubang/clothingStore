package kitri.foodCourt.user.swing;

import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.*;

import kitri.foodCourt.util.Constance;

public class RoundButton extends JButton implements MouseListener{
	
	//���������� ���� �β�
	public int arc;
	public int thickness;
	public Color enterColor;
	public Color exitColor;

	public RoundButton() {
		super();
		defaultSetting();
	}

	public RoundButton(Action a) {
		super(a);
		defaultSetting();
	}

	public RoundButton(Icon icon) {
		super(icon);
		defaultSetting();
	}

	public RoundButton(String text, Icon icon) {
		super(text, icon);
		defaultSetting();
	}

	public RoundButton(String text) {
		super(text);
		defaultSetting();
	}
	
	public RoundButton(int thickness, int arc) {
		this.arc = arc;
		this.thickness = thickness;
		defaultSetting();
	}
	
	public void defaultSetting() {
		setBackground(Color.WHITE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		//this.enterColor = SwingFactory.hexToRgb("#FFF8DC");
		this.enterColor = Constance.MAIN_COLOR;
		//������ ��ü���� �ٷ� ���콺�̺�Ʈ�� ��Ͻ��ѹ���. �� ��ü�� �����ϸ� �� ���� �׼��� �Ͼ
		//Ŭ���δ� �������� �ʰ� actionListener �� �̿��Ѵ�.
		addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//���� ĥ�ϱ� button�� ��� �̹����� ���� ���� �ֱ⿡ ���� ó��.
		if(getIcon() == null) {
			g.setColor(getBackground());
			g.fillRoundRect(thickness/2, thickness/2, getWidth()-thickness, getHeight()-thickness, arc, arc);
		}else {
			g.setColor(getBackground());
			g.fillRoundRect(thickness/2, thickness/2, getWidth()-thickness, getHeight()-thickness, arc, arc);
			super.paintComponent(g);
		}
		
		//��ư�ȿ� ���� ������.. ��ġ�°��� �Ը��� �°� �����Ұ�
		if(!getText().isEmpty()) {
			g.setColor(getForeground());
			g.setFont(getFont());
			int length = getText().length();
			int size = getFont().getSize();
			if(length % 2 == 1) {
				length += 1;
			}
			if(size % 2 == 1) {
				size += 1;
			}
			g.drawString(getText(), getWidth()/2-(length*size)/2, getHeight()/2+getFont().getSize()/2);
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		RoundButton button = (RoundButton)e.getComponent();
		if(enterColor != null) {
			button.setBackground(enterColor);
		}else {
			button.setBackground(SystemColor.inactiveCaptionBorder);
		}
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		RoundButton button = (RoundButton)e.getComponent();
		if(exitColor != null) {
			button.setBackground(exitColor);
		}else {
			button.setBackground(Color.WHITE);
		}
	}

	
}

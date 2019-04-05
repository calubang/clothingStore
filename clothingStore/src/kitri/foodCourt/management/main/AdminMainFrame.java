package kitri.foodCourt.management.main;

import java.awt.EventQueue;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import kitri.foodCourt.management.member.AdminMemberInfo;
import kitri.foodCourt.management.menu.AdminProductManagement;
import kitri.foodCourt.management.payment.AdminPayment;
import kitri.foodCourt.management.request.AdminRequest;
import kitri.foodCourt.management.statistics.AdminStatistics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AdminMainFrame extends JFrame {

	JPanel contentPanel = new JPanel();
	JPanel ampPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	JButton productManageBtn = new JButton("\uC81C\uD488\uAD00\uB9AC");
	JButton memberManageBtn = new JButton("\uD68C\uC6D0\uAD00\uB9AC");
	JButton requestManageBtn = new JButton("\uC694\uCCAD\uAD00\uB9AC");
	JButton paymentBtn = new JButton("\uACB0\uC81C");
	JButton statisticsBtn = new JButton("\uD1B5\uACC4");
	
	AdminProductManagement apm = new AdminProductManagement();
	AdminMemberInfo ami = new AdminMemberInfo();
	AdminRequest ar = new AdminRequest();
	AdminPayment ap = new AdminPayment();
	AdminStatistics as = new AdminStatistics();
	
	CardLayout cl = new CardLayout(0, 0);
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrame frame = new AdminMainFrame();
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
	public AdminMainFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setResizable(false);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		buttonPanel.setLayout(new GridLayout(5, 1, 0, 60));
		buttonPanel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uAD00\uB9AC\uC790", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(40, 20, 40, 20)));
		buttonPanel.setBounds(12, 25, 150, 722);
		contentPanel.add(buttonPanel);
		
		productManageBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								cl.show(ampPanel, "AdminProduct");
							}
						});
		buttonPanel.add(productManageBtn);
		
		memberManageBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								cl.show(ampPanel, "AdminMember");
							}
						});
		
		buttonPanel.add(memberManageBtn);
		
		requestManageBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								cl.show(ampPanel, "AdminRequest");
							}
						});
		buttonPanel.add(requestManageBtn);
		
		paymentBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cl.show(ampPanel, "AdminPayment");
						}
					});
		buttonPanel.add(paymentBtn);
		
		statisticsBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cl.show(ampPanel, "AdminStatistics");
						}
					});
		buttonPanel.add(statisticsBtn);
		
		ampPanel.setBounds(174, 25, 1008, 722);
		ampPanel.setLayout(cl);
		ampPanel.add("AdminProduct", apm);
		ampPanel.add("AdminMember", ami);
		ampPanel.add("AdminRequest", ar);
		ampPanel.add("AdminPayment", ap);
		ampPanel.add("AdminStatistics", as);
		cl.show(ampPanel, "AdminProduct");
		
		contentPanel.add(ampPanel);
	}
}
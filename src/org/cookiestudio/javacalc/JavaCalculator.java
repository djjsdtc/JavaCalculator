package org.cookiestudio.javacalc;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import org.cookiestudio.javacalc.Number;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class JavaCalculator {

	private JFrame frame;
	private JTextField textField;
	private Number num1=new Number();
	private Number num2=new Number();
	private Number numPoint;
	private char currentOperator=' ';
	private boolean isResult=false;
	
	class NumberActionListener implements ActionListener{
		private int number;
		NumberActionListener(int n){
			number=n;
		}
		public void actionPerformed(ActionEvent arg0) {
			if(numPoint==num1 && isResult) num1.clear();
			isResult=false;
			textField.setText(numPoint.push_back(number));
		}
	}
	
	class OperatorActionListener implements ActionListener{
		private char toOperate;
		private void perform(char operator){
			try{
				String s=new String();
				switch(operator){
				case ' ':
					return;
				case '+':
					s=num1.setNumber(num1.getNumber()+num2.getNumber());
					break;
				case '-':
					s=num1.setNumber(num1.getNumber()-num2.getNumber());
					break;
				case '*':
					s=num1.setNumber(num1.getNumber()*num2.getNumber());
					break;
				case '/':
					if(num2.getNumber()==0)throw new Exception("除数为0");
					s=num1.setNumber(num1.getNumber()/num2.getNumber());
					break;
				case '^':
					if(num1.getNumber()==0 && num2.getNumber()<0)throw new Exception("0的负指数幂不存在");
					s=num1.setNumber(Math.pow(num1.getNumber(),num2.getNumber()));
					break;
				}
				textField.setText(s);
			}catch(Exception e){
				num1.clear();
				num2.clear();
				textField.setText(e.getMessage());
			}
			numPoint=num1;
		}
		OperatorActionListener(char c){
			toOperate=c;
		}
		public void actionPerformed(ActionEvent arg0) {
			if(toOperate=='='){
				perform(currentOperator);
				isResult=true;
				return;
			}
			numPoint=num2;
			currentOperator=toOperate;
			textField.setText(num2.clear());
			isResult=false;
		}
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JavaCalculator window = new JavaCalculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCalculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		numPoint=num1;
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u6211\u7684\u8BA1\u7B97\u5668");
		frame.setBounds(100, 100, 479, 224);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, frame.getContentPane());
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0");
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel numPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -7, SpringLayout.NORTH, numPanel);
		springLayout.putConstraint(SpringLayout.NORTH, numPanel, 38, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, numPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, numPanel, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, numPanel, -308, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(numPanel);
		numPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton button7 = new JButton("7");
		button7.setFont(new Font("SimSun", Font.PLAIN, 12));
		button7.addActionListener(new NumberActionListener(7));
		numPanel.add(button7, "2, 2");
		
		JButton button8 = new JButton("8");
		button8.setFont(new Font("SimSun", Font.PLAIN, 12));
		button8.addActionListener(new NumberActionListener(8));
		numPanel.add(button8, "4, 2");
		
		JButton button9 = new JButton("9");
		button9.setFont(new Font("SimSun", Font.PLAIN, 12));
		button9.addActionListener(new NumberActionListener(9));
		numPanel.add(button9, "6, 2");
		
		JButton button4 = new JButton("4");
		button4.setFont(new Font("SimSun", Font.PLAIN, 12));
		button4.addActionListener(new NumberActionListener(4));
		numPanel.add(button4, "2, 4");
		
		JButton button5 = new JButton("5");
		button5.setFont(new Font("SimSun", Font.PLAIN, 12));
		button5.addActionListener(new NumberActionListener(5));
		numPanel.add(button5, "4, 4");
		
		JButton button6 = new JButton("6");
		button6.setFont(new Font("SimSun", Font.PLAIN, 12));
		button6.addActionListener(new NumberActionListener(6));
		numPanel.add(button6, "6, 4");
		
		JButton button1 = new JButton("1");
		button1.setFont(new Font("SimSun", Font.PLAIN, 12));
		button1.addActionListener(new NumberActionListener(1));
		numPanel.add(button1, "2, 6");
		
		JButton button2 = new JButton("2");
		button2.setFont(new Font("SimSun", Font.PLAIN, 12));
		button2.addActionListener(new NumberActionListener(2));
		numPanel.add(button2, "4, 6");
		
		JButton button3 = new JButton("3");
		button3.setFont(new Font("SimSun", Font.PLAIN, 12));
		button3.addActionListener(new NumberActionListener(3));
		numPanel.add(button3, "6, 6");
		
		JButton button0 = new JButton("0");
		button0.setFont(new Font("SimSun", Font.PLAIN, 12));
		button0.addActionListener(new NumberActionListener(0));
		
		JButton btnSymbol = new JButton("\u00B1");
		btnSymbol.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnSymbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(numPoint.symbolChange());
			}
		});
		numPanel.add(btnSymbol, "2, 8");
		numPanel.add(button0, "4, 8");
		
		JButton buttonDot = new JButton(".");
		buttonDot.setFont(new Font("SimSun", Font.PLAIN, 12));
		buttonDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(numPoint.push_dot());
			}
		});
		numPanel.add(buttonDot, "6, 8");
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 38, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 6, SpringLayout.EAST, numPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 0, SpringLayout.SOUTH, numPanel);
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnBack = new JButton("\u2190");
		btnBack.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(numPoint.pop_back());
			}
		});
		panel.add(btnBack, "2, 2");
		
		JButton btnC = new JButton("C");
		btnC.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(numPoint.clear());
			}
		});
		panel.add(btnC, "4, 2");
		
		JButton btnPlus = new JButton("+");
		btnPlus.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnPlus.addActionListener(new OperatorActionListener('+'));
		
		JButton btnCe = new JButton("CE");
		btnCe.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnCe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(num1.clear());
				num2.clear();
				numPoint=num1;
			}
		});
		panel.add(btnCe, "6, 2");
		
		JButton btnSin = new JButton("sin");
		btnSin.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(num1.setNumber(Math.sin(num1.getNumber())));
				isResult=true;
			}
		});
		panel.add(btnSin, "8, 2");
		
		JButton btnArcsin = new JButton("arcsin");
		btnArcsin.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnArcsin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(num1.getNumber()>1 || num1.getNumber()<-1) throw new Exception("arcsin和arccos的参数要在-1～1之间");
					textField.setText(num1.setNumber(Math.asin(num1.getNumber())));
				}catch(Exception e1){
					num1.clear();
					num2.clear();
					textField.setText(e1.getMessage());
				}
				isResult=true;
			}
		});
		panel.add(btnArcsin, "10, 2");
		panel.add(btnPlus, "2, 4");
		
		JButton btnMinus = new JButton("-");
		btnMinus.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnMinus.addActionListener(new OperatorActionListener('-'));
		panel.add(btnMinus, "4, 4, default, top");
		
		JButton btnMul = new JButton("*");
		btnMul.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnMul.addActionListener(new OperatorActionListener('*'));
		
		JButton btnx = new JButton("1/x");
		btnx.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(num1.getNumber()==0) throw new Exception("0的倒数不存在");
					textField.setText(num1.setNumber(1/num1.getNumber()));
				}catch(Exception e1){
					num1.clear();
					num2.clear();
					textField.setText(e1.getMessage());
				}
				isResult=true;
			}
		});
		panel.add(btnx, "6, 4");
		
		JButton btnCos = new JButton("cos");
		btnCos.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(num1.setNumber(Math.cos(num1.getNumber())));
				isResult=true;
			}
		});
		panel.add(btnCos, "8, 4");
		
		JButton btnArccos = new JButton("arccos");
		btnArccos.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnArccos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(num1.getNumber()>1 || num1.getNumber()<-1) throw new Exception("arcsin和arccos的参数要在0～1之间");
					textField.setText(num1.setNumber(Math.acos(num1.getNumber())));
				}catch(Exception e1){
					num1.clear();
					num2.clear();
					textField.setText(e1.getMessage());
				}
				isResult=true;
			}
		});
		panel.add(btnArccos, "10, 4");
		panel.add(btnMul, "2, 6");
		
		JButton btnDiv = new JButton("/");
		btnDiv.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnDiv.addActionListener(new OperatorActionListener('/'));
		panel.add(btnDiv, "4, 6");
		
		JButton btnEqual = new JButton("=");
		btnEqual.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnEqual.addActionListener(new OperatorActionListener('='));
		
		JButton btnSqrt = new JButton("sqrt");
		btnSqrt.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnSqrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(num1.getNumber()<0) throw new Exception("负数不能开方");
					textField.setText(num1.setNumber(Math.sqrt(num1.getNumber())));
				}catch(Exception e1){
					num1.clear();
					num2.clear();
					textField.setText(e1.getMessage());
				}
				isResult=true;
			}
		});
		panel.add(btnSqrt, "6, 6");
		
		JButton btnTan = new JButton("tan");
		btnTan.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(num1.setNumber(Math.tan(num1.getNumber())));
				isResult=true;
			}
		});
		panel.add(btnTan, "8, 6");
		
		JButton btnArctan = new JButton("arctan");
		btnArctan.setFont(new Font("SimSun", Font.PLAIN, 12));
		btnArctan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(num1.setNumber(Math.atan(num1.getNumber())));
				isResult=true;
			}
		});
		panel.add(btnArctan, "10, 6");
		panel.add(btnEqual, "2, 8, 3, 1");
		
		JButton button = new JButton("^");
		button.setFont(new Font("SimSun", Font.PLAIN, 12));
		button.addActionListener(new OperatorActionListener('^'));
		panel.add(button, "6, 8");
		
		JLabel label = new JLabel("\u4E09\u89D2\u51FD\u6570\u5747\u6309\u5F27\u5EA6\u5236");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		panel.add(label, "8, 8, 3, 1");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnf = new JMenu("\u6587\u4EF6(F)");
		mnf.setFont(new Font("宋体", Font.PLAIN, 12));
		mnf.setMnemonic('F');
		menuBar.add(mnf);
		
		JMenuItem mnuExit = new JMenuItem("\u9000\u51FA(X)");
		mnuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnuExit.setFont(new Font("宋体", Font.PLAIN, 12));
		mnuExit.setMnemonic('X');
		mnf.add(mnuExit);
		
		JMenu mnh = new JMenu("\u5E2E\u52A9(H)");
		mnh.setMnemonic('H');
		mnh.setFont(new Font("宋体", Font.PLAIN, 12));
		menuBar.add(mnh);
		
		JMenuItem mnuAbout = new JMenuItem("\u5173\u4E8E(A)");
		mnuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String str="我的计算器\n一个简易的计算器小程序\n版权所有(C) 2012 饼干工作室。保留所有权利。";
				JOptionPane.showMessageDialog(null,str,"关于",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnuAbout.setMnemonic('A');
		mnuAbout.setFont(new Font("宋体", Font.PLAIN, 12));
		mnh.add(mnuAbout);
	}
}

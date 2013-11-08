package com.andersonlfeitosa.jencode.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.andersonlfeitosa.jencode.command.Command;
import com.andersonlfeitosa.jencode.command.CommandRepository;
import com.andersonlfeitosa.jencode.command.Context;
import com.andersonlfeitosa.jencode.command.Result;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class MainFrame extends JFrame {
	private JPanel panel;
	private JLabel labelInput;
	private JTextField textInput;
	private JLabel labelOutput;
	private JTextField textOutput;
	private JLabel labelOperation;
	private JRadioButton radioEncode;
	private JRadioButton radioDecode;
	private JButton buttonExecute;
	private JLabel lblStatus;

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	public MainFrame() {
		initComponents();
	}

	private void buttonExecuteActionPerformed(ActionEvent e) {
		Command command = null;
		try {
			if (radioEncode.isSelected()) {
				command = CommandRepository.getInstance().getCommand("encode");
			} else {
				command = CommandRepository.getInstance().getCommand("decode");
			}
			
			Context ctx = new Context();
			ctx.setParameter(textInput.getText());
			Result result = command.execute(ctx);
			
			textOutput.setText(result.getResult().toString());
			
		} catch (Exception ex) {
			lblStatus.setText(ex.getMessage());
		}
	}

	private void initComponents() {
		panel = new JPanel();
		labelInput = new JLabel();
		textInput = new JTextField();
		labelOutput = new JLabel();
		textOutput = new JTextField();
		labelOperation = new JLabel();
		radioEncode = new JRadioButton();
		radioDecode = new JRadioButton();
		buttonExecute = new JButton();
		lblStatus = new JLabel();

		setTitle("jencode");
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout("default", "default"));
		
		panel.setLayout(new FormLayout("41dlu, $lcgap, 166dlu, $lcgap, right:17dlu", "10dlu, 5*($lgap, default), $lgap, 19dlu"));

		labelInput.setText("Input");
		labelInput.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(labelInput, CC.xy(1, 3));
		panel.add(textInput, CC.xy(3, 3));

		labelOutput.setText("Output");
		labelOutput.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(labelOutput, CC.xy(1, 5));
		panel.add(textOutput, CC.xy(3, 5));

		labelOperation.setText("Operation");
		labelOperation.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(labelOperation, CC.xy(1, 7));

		radioEncode.setText("Encode");
		radioEncode.setSelected(true);
		panel.add(radioEncode, CC.xy(3, 7));

		radioDecode.setText("Decode");
		panel.add(radioDecode, CC.xy(3, 9));

		buttonExecute.setText("Execute");
		buttonExecute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonExecuteActionPerformed(e);
			}
		});
		
		panel.add(buttonExecute, CC.xy(3, 11, CC.LEFT, CC.DEFAULT));
		panel.add(lblStatus, CC.xy(3, 13));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioEncode);
		buttonGroup.add(radioDecode);
		
		contentPane.add(panel, CC.xy(1, 1));
		setSize(465, 235);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

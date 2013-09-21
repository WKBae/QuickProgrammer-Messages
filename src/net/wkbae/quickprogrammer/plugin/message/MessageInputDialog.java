package net.wkbae.quickprogrammer.plugin.message;

import java.awt.GridBagLayout;
import javax.swing.JRadioButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class MessageInputDialog extends JDialog implements ActionListener, WindowListener {
	private static final long serialVersionUID = -5935494202953761873L;
	
	private JRadioButton useText;
	private JRadioButton useBlock;
	private JTextArea txtMessage;
	
	MessageInputDialog(MessageResult lastResult) {
		this();
		
		if(lastResult != null) {
			switch(lastResult.selected) {
			case MessageResult.TYPE_MESSAGE:
				useText.doClick();
				txtMessage.setText(lastResult.message);
				break;
			case MessageResult.USE_VARIABLE_BLOCK:
				useBlock.doClick();
				break;
			}
		}
	}
	
	MessageInputDialog() {
		super(null, "메시지 설정", ModalityType.DOCUMENT_MODAL);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(this);
		
		this.setSize(400, 200);
		this.setResizable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0, 0, 10};
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 0, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		useText = new JRadioButton("직접 입력");
		useText.setActionCommand("Type");
		useText.addActionListener(this);
		useText.setSelected(true);
		GridBagConstraints gbc_useText = new GridBagConstraints();
		gbc_useText.insets = new Insets(0, 0, 5, 5);
		gbc_useText.gridx = 1;
		gbc_useText.gridy = 1;
		getContentPane().add(useText, gbc_useText);
		
		txtMessage = new JTextArea();
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 5, 5);
		gbc_txtMessage.fill = GridBagConstraints.BOTH;
		gbc_txtMessage.gridheight = 2;
		gbc_txtMessage.gridx = 1;
		gbc_txtMessage.gridy = 2;
		getContentPane().add(txtMessage, gbc_txtMessage);
		
		
		useBlock = new JRadioButton("변수 블록");
		useBlock.setActionCommand("Block");
		useBlock.addActionListener(this);
		GridBagConstraints gbc_useBlock = new GridBagConstraints();
		gbc_useBlock.insets = new Insets(0, 0, 5, 5);
		gbc_useBlock.gridx = 2;
		gbc_useBlock.gridy = 1;
		getContentPane().add(useBlock, gbc_useBlock);
		
		JButton btnOk = new JButton("확인");
		btnOk.setActionCommand("Ok");
		btnOk.addActionListener(this);
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 4;
		gbc_btnOk.gridy = 1;
		getContentPane().add(btnOk, gbc_btnOk);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.setActionCommand("Cancel");
		btnCancel.addActionListener(this);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 2;
		getContentPane().add(btnCancel, gbc_btnCancel);
		
		ButtonGroup group = new ButtonGroup();
		group.add(useText);
		group.add(useBlock);
	}
	
	public int showDialog() {
		this.setVisible(true);
		return selected;
	}
	
	private int selected = MessageResult.TYPE_MESSAGE;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Type":
			txtMessage.setEnabled(true);
			this.selected = MessageResult.TYPE_MESSAGE;
			break;
		case "Block":
			txtMessage.setEnabled(false);
			this.selected = MessageResult.USE_VARIABLE_BLOCK;
			break;
		case "Ok":
			result = new MessageResult(selected, txtMessage.getText());
			this.setVisible(false);
			this.dispose();
			break;
		case "Cancel":
			this.selected = MessageResult.CANCELLED;
			result = new MessageResult(selected, null);
			this.setVisible(false);
			this.dispose();
			break;
		}
	}
	
	private MessageResult result;
	public MessageResult getResult() {
		return result;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(result == null) {
			this.selected = MessageResult.CANCELLED;
			result = new MessageResult(selected, null);
			this.setVisible(false);
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}

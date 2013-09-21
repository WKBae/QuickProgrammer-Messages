package net.wkbae.quickprogrammer.plugin.message;

import javax.swing.JOptionPane;

import net.wkbae.quickprogrammer.Program;

public class InformationMessageBlock extends MessageBlock {
	
	public InformationMessageBlock(Program program) {
		super(program);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(null, getMessage(), null, JOptionPane.INFORMATION_MESSAGE);
	}
}

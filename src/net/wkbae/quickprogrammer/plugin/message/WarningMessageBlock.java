package net.wkbae.quickprogrammer.plugin.message;

import javax.swing.JOptionPane;

import net.wkbae.quickprogrammer.Program;

public class WarningMessageBlock extends MessageBlock {
	
	public WarningMessageBlock(Program program) {
		super(program);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(null, getMessage(), null, JOptionPane.WARNING_MESSAGE);
	}
}

package net.wkbae.quickprogrammer.plugin.message;

import javax.swing.JOptionPane;

import net.wkbae.quickprogrammer.Program;

public class ErrorMessageBlock extends MessageBlock {
	
	public ErrorMessageBlock(Program program) {
		super(program);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(null, getMessage(), null, JOptionPane.ERROR_MESSAGE);
	}
}

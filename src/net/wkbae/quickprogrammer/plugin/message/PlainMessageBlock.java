package net.wkbae.quickprogrammer.plugin.message;

import javax.swing.JOptionPane;

import net.wkbae.quickprogrammer.Program;

public class PlainMessageBlock extends MessageBlock {
	
	public PlainMessageBlock(Program program) {
		super(program);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(null, getMessage(), null, JOptionPane.PLAIN_MESSAGE);
	}
}

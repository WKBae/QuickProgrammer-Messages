package net.wkbae.quickprogrammer.plugin.message;

import javax.swing.JOptionPane;

import net.wkbae.quickprogrammer.Program;

public class QuestionMessageBlock extends MessageBlock {
	
	public QuestionMessageBlock(Program program) {
		super(program);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(null, getMessage(), null, JOptionPane.QUESTION_MESSAGE);
	}
}

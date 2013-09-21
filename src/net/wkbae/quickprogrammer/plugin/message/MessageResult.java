package net.wkbae.quickprogrammer.plugin.message;

class MessageResult {

	public final static int TYPE_MESSAGE = 1;
	public final static int USE_VARIABLE_BLOCK = 2;
	public final static int CANCELLED = 4;
	
	public final int selected;
	public final String message;
	
	public MessageResult(int selected, String message) {
		this.selected = selected;
		this.message = message;
	}
}
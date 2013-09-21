package net.wkbae.quickprogrammer.plugin.message;

import java.util.Map;

import net.wkbae.quickprogrammer.Program;
import net.wkbae.quickprogrammer.VariableSocketBlockModel;
import net.wkbae.quickprogrammer.file.parser.ParseException;

public abstract class MessageBlock extends VariableSocketBlockModel {

	public MessageBlock(Program program) {
		super(program);
	}
	
	@Override
	public boolean initialize() {
		setResult(inputMessage());
		return result.selected != MessageResult.CANCELLED;
	}
	
	@Override
	public void onDoubleClick() {
		MessageResult res = inputMessage();
		if(res.selected != MessageResult.CANCELLED) {
			setResult(res);
		}
	}
	
	@Override
	public void saveState(Map<String, String> attrs) {
		super.saveState(attrs);
		
		switch(result.selected) {
		case MessageResult.TYPE_MESSAGE:
			attrs.put("msgtype", "message");
			attrs.put("message", result.message);
			break;
			
		case MessageResult.USE_VARIABLE_BLOCK:
			attrs.put("msgtype", "block");
			break;
		}
	}
	
	@Override
	public void restoreState(Map<String, String> attrs) throws ParseException {
		super.restoreState(attrs);
		switch(attrs.get("msgtype")) {
		case "message":
			setResult(new MessageResult(MessageResult.TYPE_MESSAGE, attrs.get("message")));
			break;
		case "block":
			setResult(new MessageResult(MessageResult.USE_VARIABLE_BLOCK, attrs.get("block")));
			break;
		}
	}
	
	private void setResult(MessageResult result) {
		this.result = result;
		
		if(result.selected == MessageResult.USE_VARIABLE_BLOCK) {
			setSocketsCount(1);
		} else {
			setSocketsCount(0);
		}
	}
	
	private MessageResult result;
	
	private final MessageResult inputMessage() {
		MessageInputDialog dialog = new MessageInputDialog(result);
		dialog.showDialog();
		return dialog.getResult();
	}
	
	protected final String getMessage() {
		switch(result.selected) {
		case MessageResult.TYPE_MESSAGE:
			return result.message;
		case MessageResult.USE_VARIABLE_BLOCK:
			return (String) getProgram().getVariableSockets().getSocket(getSockets().get(0)).getAttachedBlock().getModel().evaluate();
		default:
			return null;
		}
	}
}

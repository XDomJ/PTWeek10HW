package entity;

public class Guitar {

	private int guitarId;
	private String makeModel;
	private String style;
	private String nickname;
	
	public Guitar (int guitarId, String makeModel, String style, String nickname) {
		this.setGuitarId(guitarId);
		this.setMakeModel(makeModel);
		this.setStyle(style);
		this.setNickname(nickname);		
	}

	public int getGuitarId() {
		return guitarId;
	}

	public void setGuitarId(int guitarId) {
		this.guitarId = guitarId;
	}

	public String getMakeModel() {
		return makeModel;
	}
	
	public void setMakeModel(String makeModel) {
		this.makeModel = makeModel;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}

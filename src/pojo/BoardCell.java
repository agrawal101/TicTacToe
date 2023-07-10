package pojo;

import dao.PlayerSymbol;

public class BoardCell {
	private PlayerSymbol value;
	public BoardCell()
	{
		this.value = null;
	}

	public PlayerSymbol getValue() {
		return value;
	}

	public void setValue(PlayerSymbol value) {
		this.value = value;
	}


}

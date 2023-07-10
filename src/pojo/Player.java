package pojo;

import dao.PlayerSymbol;

public class Player {
	
	private String name;
	private PlayerSymbol symbol;
	
	public Player(String name, PlayerSymbol symbol) {
		super();
		this.name = name;
		this.symbol = symbol;
	}

	public PlayerSymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(PlayerSymbol symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
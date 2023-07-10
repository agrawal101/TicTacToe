package dao;

import pojo.BoardCell;

public class BoardDao {
private BoardCell[][] board;

public BoardDao()
{
	board = new BoardCell[3][3];
	for(int i = 0;i<3;i++)
	{
		for(int j = 0;j<3;j++)
		{
			board[i][j] = new BoardCell();
		}
	}
}

public boolean isValidMove(int r,int c)
{
	if(r<0 || c<0 || r>=3|| c>=3)
		return false;
	return board[r][c].getValue()==null;
}
public void setCell(int r,int c,PlayerSymbol symbol)
{
	board[r][c].setValue(symbol);
}
public PlayerSymbol getCell(int r,int c)
{
	return board[r][c].getValue();
}
public boolean checkWinner(PlayerSymbol symbol)
{
	return checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol);
}
public boolean checkRows(PlayerSymbol symbol)
{
	for(int i = 0;i<3;i++)
	{
		if(board[i][0].getValue()==symbol && board[i][1].getValue()==symbol 
				&& board[i][2].getValue()==symbol)
			return true;
	}
	return false;
}
public boolean checkColumns(PlayerSymbol symbol)
{
	for(int i = 0;i<3;i++)
	{
		if(board[0][i].getValue()==symbol && board[1][i].getValue()==symbol
				&& board[2][i].getValue()==symbol)
			return true;
	}
	return false;
}
public boolean checkDiagonals(PlayerSymbol symbol)
{
	return ((board[0][0].getValue()==symbol && board[1][1].getValue()==symbol && board[2][2].getValue()==symbol)||
			board[0][2].getValue()==symbol && board[1][1].getValue()==symbol && board[2][0].getValue()==symbol);
}
public boolean isBoardFull()
{
	for(int i = 0;i<3;i++)
	{
		for(int j = 0;j<3;j++)
		{
			if(board[i][j].getValue()==null)
				return false;
		}
	}
	return true;
}
}

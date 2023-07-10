package TicTacToe.src.service;

import java.util.Scanner;

import dao.BoardDao;
import dao.PlayerSymbol;
import pojo.GameStatus;
import pojo.Player;
import pojo.Move;

public class PlayerService {

	private BoardDao boardDao;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private GameStatus gameStatus;
	
	public PlayerService()
	{
		boardDao = new BoardDao();
		gameStatus = GameStatus.ONGOING;
	}
	public void playGame()
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter Player 1 Name");
		String player1Name = kb.nextLine();
		PlayerSymbol player1Symbol = getPlayerSymbolChoice(kb,player1Name);
		player1 = new Player(player1Name,player1Symbol);
		
		System.out.println("Enter Player 2 Name");
		String player2Name = kb.nextLine();
		PlayerSymbol player2Symbol = getPlayerSymbolChoice(kb,player1Name);
		player2 = new Player(player2Name,player2Symbol);
		
		currentPlayer = player1;

        System.out.println("\n--- Game Started ---");
        System.out.println(player1.getName() + " (" + player1.getSymbol() + ") vs " + player2.getName() + 
        		" (" + player2.getSymbol() + ")\n");
        while(gameStatus == GameStatus.ONGOING)
        {
        	System.out.println("Current Player: " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ")");
            System.out.print("Enter your move (row and column): ");
            int row = kb.nextInt();
            int col = kb.nextInt();
            Move move = new Move(row,col);
            if(isValidMove(move))
            {
            	makeMove(move);
            	printBoard();
            	checkGameOver();
            	if(gameStatus!=GameStatus.WIN)
                {
            	switchPlayer();
                }
            }
            else
            {
            	System.out.println("Invalid move!! Try agian");
            }
        }
        if(gameStatus==GameStatus.WIN)
        {
        	System.out.println("Game Over : "+currentPlayer.getName()+" "+currentPlayer.getSymbol() + " Wins");
        }
        else if(gameStatus==GameStatus.DRAW)
        {
        	System.out.println("Game Draw");
        }
	}
	public PlayerSymbol getPlayerSymbolChoice(Scanner kb,String playerName)
	{
		PlayerSymbol playerSymbol = null;
		
		while(playerSymbol==null)
		{
			System.out.println("Enter Symbol");
			String s = kb.nextLine().toUpperCase();
			if(s.equals("X"))
			{
				playerSymbol = PlayerSymbol.X;
			}
			else if(s.equals("O"))
			{
				playerSymbol = PlayerSymbol.O;
			}
			else
			{
				System.out.println("Invalid symbol");
			}
		}
		return playerSymbol;
	}
	private boolean isValidMove(Move move)
	{
		int r = move.getRow();
		int c = move.getCol();
		return boardDao.isValidMove(r, c);
	}
	private void makeMove(Move move)
	{
		int r = move.getRow();
		int c = move.getCol();
		PlayerSymbol symbol = currentPlayer.getSymbol();
		boardDao.setCell(r, c, symbol);
	}
	private void printBoard() {
		System.out.println();
		for(int r = 0;r<3;r++)
		{
			for(int c = 0;c<3;c++)
			{
				PlayerSymbol symbol = boardDao.getCell(r, c);
				if(symbol == null)
				{
					System.out.println("-");
				}
				else {
                System.out.print(symbol);
				}
				if (c < 2) {
                System.out.print(" | ");
				}
			}
			System.out.println();
			if (r < 2) {
            System.out.println("---------");
			}
		}
		System.out.println();
	}
	
	private void checkGameOver() {
		PlayerSymbol symbol = currentPlayer.getSymbol();
		if(boardDao.checkWinner(symbol))
			gameStatus = GameStatus.WIN;
		else if(boardDao.isBoardFull())
			gameStatus = GameStatus.DRAW;	
	}
	private void switchPlayer()
	{
		if(currentPlayer == player1)
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}
}
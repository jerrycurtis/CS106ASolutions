/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
/**
 * Karel krijgt nu het commando om te kijken waar hij dingen bij moet vullen
 */
	public void run() 
	{
		FillCurrentRow();
		while (frontIsClear()) 
		{
			JumpNextRow();
			FillCurrentRow();
		}
	}
/**
 * Commando om Karel de huidige rij volledig te vullen met stenen. Eerst kijkt hij of er een steen aanwezig is.
 * Zo niet, dan plaats Karel een steen. Dit om te voorkomen dat er twee stenen geplaatst worden.
 */
	private void FillCurrentRow() 
	{
		turnLeft();
		placeBeeperIfNotPresent();
		while (frontIsClear()) 
		{
			move();
			placeBeeperIfNotPresent();
		}
		returnToBottom();
	}
	
	private void returnToBottom() 
	{
		turnAround();
		while (frontIsClear()) 
		{
			move();
		}
		turnLeft();
	}
	
	private void placeBeeperIfNotPresent() 
	{
		if (noBeepersPresent()) 
		{
			putBeeper();
		}
	}	
	
	private void JumpNextRow()
	{
		move();
		move();
		move();
		move();
	}	
}

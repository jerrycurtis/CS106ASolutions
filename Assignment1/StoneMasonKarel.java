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

	public void run() 
	{
		FillCurrentRow();
		while (frontIsClear()) 
		{
			JumpNextRow();
			FillCurrentRow();
		}
	}

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

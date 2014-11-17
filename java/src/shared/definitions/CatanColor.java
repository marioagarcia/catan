package shared.definitions;

import java.awt.Color;

public enum CatanColor
{
	RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BRONZE;
	
	private Color color;
	
	static
	{
		RED.color = new Color(227, 66, 52);
		ORANGE.color = new Color(255, 165, 0);
		YELLOW.color = new Color(253, 224, 105);
		BLUE.color = new Color(111, 183, 246);
		GREEN.color = new Color(109, 192, 102);
		PURPLE.color = new Color(157, 140, 212);
		PUCE.color = new Color(204, 136, 153);
		WHITE.color = new Color(223, 223, 223);
		BRONZE.color = new Color(215, 163, 91);
	}
	
	public Color getJavaColor()
	{
		return color;
	}
}


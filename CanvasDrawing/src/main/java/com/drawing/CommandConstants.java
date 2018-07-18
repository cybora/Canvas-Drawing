package com.drawing;

public class CommandConstants {
	public static int EXPECTED_CREATE_COMMAND_LENGTH = 3;
	public static int EXPECTED_LINE_COMMAND_LENGTH = 5;
	public static int EXPECTED_RECTANGLE_COMMAND_LENGTH = 5;
	public static int EXPECTED_BUCKET_FILL_COMMAND_LENGTH = 4;
	
	public static String ERROR_EXPECTED_CREATE_COMMAND = "Create command syntax is C w h";
	public static String ERROR_EXPECTED_LINE_COMMAND = "Line command syntax is L x1 y1 x2 y2";
	public static String ERROR_EXPECTED_RECTANGLE_COMMAND = "Rectangle command syntax is R x1 y1 x2 y2";
	public static String ERROR_EXPECTED_BUCKET_FILL_COMMAND = "Bucket Fill command syntax is B x y c";
	
	public static String CANVAS_SHOULD_BE_CREATED = "Canvas should be created first";
	public static String ERROR_EXPECTED_BUCKET_FILL_COMMAND_WRONG_COLOR = "The color code must be one character long";
	
	public static String ERROR_POINT_CANNOT_BE_NEGATIVE = "The point cannot be negative";
	
}

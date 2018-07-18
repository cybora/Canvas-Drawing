package com.drawing;

import java.util.Scanner;

import com.drawing.domain.Canvas;
import com.drawing.domain.Line;
import com.drawing.domain.Point;
import com.drawing.domain.Rectangle;

/**
 * Hello world!
 *
 */
public class App {
	private static Canvas canvas;

	public static void main(String[] args) {
		usage();
		Scanner scan = new Scanner(System.in);
		String command = new String();

		while (!"Q".equals(command)) {
			System.out.print("enter command:");
			command = scan.nextLine();
			handleCommand(command);
		}

		scan.close();
	}
	
	public static void usage() {
		String usage = "Drawing program\n\n" +
			"C w h         -  Create a new canvas of width w and height h\n" +
			"L x1 y1 x2 y2 -  Create a new line from (x1,y1) to (x2,y2)\n" +
			"R x1 y1 x2 y2 -  Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2)\n" +
			"B x y c       -  Fill the entire area connected to (x,y) with colour 'c'\n" +
			"Q             -  Quit the program\n";
		
		System.out.println(usage);
	}
	

	public static void handleCommand(String command) {
		char operation = command.toUpperCase().charAt(0);
		
		try {

		switch (operation) {
		case 'C':
			handleCreateCommand(command);
			break;
		case 'L':
			handleLineCommand(command);
			break;
		case 'R':
			handleRectangleCommand(command);
			break;
		case 'B':
			handleBucketFillCommand(command);
			break;
		default:
			break;
		}
		
		} catch(IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
		} catch (IllegalStateException ise) {
			System.out.println(ise.getMessage());
		}
	}
	
	
	
	public static void handleCreateCommand(String command) {
		String[] ops = command.split(" ");

		if (ops.length != CommandConstants.EXPECTED_CREATE_COMMAND_LENGTH) {
			throw new IllegalArgumentException(CommandConstants.ERROR_EXPECTED_CREATE_COMMAND);
		}
		
		if (Integer.parseInt(ops[1]) <= 0 || Integer.parseInt(ops[2]) <= 0) {
			throw new IllegalArgumentException(CommandConstants.ERROR_POINT_CANNOT_BE_NEGATIVE);
		}

		canvas = new Canvas(Integer.parseInt(ops[1]), Integer.parseInt(ops[2]));
		canvas.render();
	}

	public static void handleLineCommand(String command) {
		String[] ops = command.split(" ");

		if (ops.length != CommandConstants.EXPECTED_LINE_COMMAND_LENGTH) {
			throw new IllegalArgumentException(CommandConstants.ERROR_EXPECTED_LINE_COMMAND);
		}

		if (!checkCanvas()) {
			throw new IllegalStateException(CommandConstants.CANVAS_SHOULD_BE_CREATED);
		} else {
			if (Integer.parseInt(ops[1]) <= 0 || Integer.parseInt(ops[2]) <= 0 ||
				Integer.parseInt(ops[3]) <= 0 || Integer.parseInt(ops[4]) <= 0 ) {
				throw new IllegalArgumentException(CommandConstants.ERROR_POINT_CANNOT_BE_NEGATIVE);
			}
			Line line = new Line(Integer.parseInt(ops[1]), Integer.parseInt(ops[2]), Integer.parseInt(ops[3]),
					Integer.parseInt(ops[4]));
			canvas.drawLine(line, Canvas.POINT_CHAR);
			canvas.render();
		}
	}

	public static void handleRectangleCommand(String command) {
		String[] ops = command.split(" ");

		if (ops.length != CommandConstants.EXPECTED_RECTANGLE_COMMAND_LENGTH) {
			throw new IllegalArgumentException(CommandConstants.ERROR_EXPECTED_RECTANGLE_COMMAND);
		}

		if (!checkCanvas()) {
			throw new IllegalStateException(CommandConstants.CANVAS_SHOULD_BE_CREATED);
		} else {
			if (Integer.parseInt(ops[1]) <= 0 || Integer.parseInt(ops[2]) <= 0 ||
				Integer.parseInt(ops[3]) <= 0 || Integer.parseInt(ops[4]) <= 0 ) {
					throw new IllegalArgumentException(CommandConstants.ERROR_POINT_CANNOT_BE_NEGATIVE);
				}
			// get the corners for the rectangle
			Point p1 = new Point(Integer.parseInt(ops[1]), Integer.parseInt(ops[2]));
			Point p2 = new Point(Integer.parseInt(ops[3]), Integer.parseInt(ops[4]));
			
			Rectangle rectangle = new Rectangle(p1, p2);
			canvas.drawRectangle(rectangle);
			canvas.render();
		}
	}

	public static void handleBucketFillCommand(String command) {
		String[] ops = command.split(" ");

		if (ops.length != CommandConstants.EXPECTED_BUCKET_FILL_COMMAND_LENGTH) {
			throw new IllegalArgumentException(CommandConstants.ERROR_EXPECTED_BUCKET_FILL_COMMAND);
		}

		if (ops[3].length() != 1) {
			throw new IllegalArgumentException(CommandConstants.ERROR_EXPECTED_BUCKET_FILL_COMMAND_WRONG_COLOR);
		}

		if (!checkCanvas()) {
			throw new IllegalStateException(CommandConstants.CANVAS_SHOULD_BE_CREATED);
		} else {
			if (Integer.parseInt(ops[1]) <= 0 || Integer.parseInt(ops[2]) <= 0) {
					throw new IllegalArgumentException(CommandConstants.ERROR_POINT_CANNOT_BE_NEGATIVE);
				}
			canvas.bucketFill(Integer.parseInt(ops[1]), Integer.parseInt(ops[2]), ops[3].charAt(0));
			canvas.render();
		}
	}

	public static boolean checkCanvas() {
		return canvas != null;
	}

}

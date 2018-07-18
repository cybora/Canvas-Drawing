package com.drawing.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.drawing.App;

public class CanvasTest {
	
	private static Canvas canvas;
	
	@BeforeAll
	public static void setUp() throws Exception {
		canvas = new Canvas(20, 4);
	}
	
	// canvas creation
	@Test
	public void canvasTest1() throws Exception {
		assertEquals(canvas.render(),
				"----------------------\n" +
				"|                    |\n" +
				"|                    |\n" +
				"|                    |\n" +
				"|                    |\n" +
				"----------------------\n");
	}
	
	// draw a horizontal line
	@Test
	public void canvasTest2() throws Exception {
		Line line = new Line(1, 2, 6, 2);
		canvas.drawLine(line, Canvas.POINT_CHAR);
		assertEquals(canvas.render(),
				"----------------------\n" +
				"|                    |\n" +
				"|xxxxxx              |\n" +
				"|                    |\n" +
				"|                    |\n" +
				"----------------------\n");
	}
	
	// draw a vertical line
	@Test
	public void canvasTest3() throws Exception {
		Line line = new Line(6, 3, 6, 4);
		canvas.drawLine(line, Canvas.POINT_CHAR);
		assertEquals(canvas.render(),
				"----------------------\n" + 
				"|                    |\n" + 
				"|xxxxxx              |\n" + 
				"|     x              |\n" + 
				"|     x              |\n" + 
				"----------------------\n");
	}
	
	// draw a rectangle
	@Test
	public void canvasTest4() throws Exception {
		Point p1 = new Point(16, 1);
		Point p2 = new Point(20, 3);
		Rectangle rectangle = new Rectangle(p1, p2);
		canvas.drawRectangle(rectangle);
		assertEquals(canvas.render(),
				"----------------------\n" + 
				"|               xxxxx|\n" + 
				"|xxxxxx         x   x|\n" + 
				"|     x         xxxxx|\n" + 
				"|     x              |\n" + 
				"----------------------\n");
	}
	
	// bucket fill space
	@Test
	public void canvasTest5() throws Exception {
		char ch = 'o';
		canvas.bucketFill(10, 3, ch);
		assertEquals(canvas.render(),
				"----------------------\n" + 
				"|oooooooooooooooxxxxx|\n" + 
				"|xxxxxxooooooooox   x|\n" + 
				"|     xoooooooooxxxxx|\n" + 
				"|     xoooooooooooooo|\n" + 
				"----------------------\n");
	}
	
	// bucket fill a rectangle
	@Test
	public void canvasTest6() throws Exception {
		canvas = new Canvas(20, 4);
		canvas.render();
		Point p1 = new Point(10, 1);
		Point p2 = new Point(20, 3);
		Rectangle rectangle = new Rectangle(p1, p2);
		canvas.drawRectangle(rectangle);
		canvas.render();
		char ch = 's';
		canvas.bucketFill(11, 2, ch);
		assertEquals(canvas.render(),
				"----------------------\n" + 
				"|         xxxxxxxxxxx|\n" + 
				"|         xsssssssssx|\n" + 
				"|         xxxxxxxxxxx|\n" + 
				"|                    |\n" + 
				"----------------------\n");
	}
	
	// bucket fill the whole canvas
		@Test
		public void canvasTest7() throws Exception {
			canvas = new Canvas(20, 4);
			char ch = 's';
			canvas.bucketFill(11, 2, ch);
			assertEquals(canvas.render(),
					"----------------------\n" + 
					"|ssssssssssssssssssss|\n" + 
					"|ssssssssssssssssssss|\n" + 
					"|ssssssssssssssssssss|\n" + 
					"|ssssssssssssssssssss|\n" + 
					"----------------------\n");
		}
	
	// get an exception for trying to create a diagonal line
	@Test
	public void canvasTest8() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
	        Line line = new Line(1, 2, 3, 4);
	        canvas.drawLine(line, Canvas.POINT_CHAR);
		});
	}
	
	// get an exception for trying to create a line outside the canvas
	@Test
	public void canvasTest9() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
	        Line line = new Line(1, 2, 1, 30);
	        canvas.drawLine(line, Canvas.POINT_CHAR);
		});
	}
	
	// get an exception for trying to create a rectangle outside the canvas
	@Test
	public void canvasTest10() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
	        Point p1 = new Point(1, 2);
	        Point p2 = new Point(1, 30);
	        Rectangle rectangle = new Rectangle(p1, p2);
			canvas.drawRectangle(rectangle);
		});
	}
	
	// trying to draw a line on null canvas
	public void canvasTest11() throws Exception {
		canvas = null;
		String command = "L 1 2 2 4";
		assertThrows(IllegalStateException.class, () -> {
	        App.handleCommand(command);
		});
	}
	
	// trying to draw a rectangle on null canvas
	public void canvasTest12() throws Exception {
		canvas = null;
		String command = "R 1 2 2 4";
		assertThrows(IllegalStateException.class, () -> {
			App.handleCommand(command);
		});
	}
	
	
	
	
	

}

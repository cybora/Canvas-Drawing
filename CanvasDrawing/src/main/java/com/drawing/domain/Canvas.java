package com.drawing.domain;

public class Canvas {

	private final int width;
	private final int height;
	private final char canvasArray[][];

	private final static char VERTICAL_CANVAS_BORDER = '|';
	private final static char HORIZONTAL_CANVAS_BORDER = '-';
	public final static char POINT_CHAR = 'x';
	private final static String NEW_LINE = "\n";

	public Canvas(int width, int height) {
		
		width += 2;
		height+= 2;
		this.width = width;
		this.height = height;

		canvasArray = new char[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				canvasArray[i][j] = ' ';
			}
		}

		// definition of the four corners of the canvas
		Point canvasTopLeft = new Point(0, 0);
		Point canvasTopRight = new Point(this.width - 1, 0);
		Point canvasBottomLeft = new Point(0, this.height - 1);
		Point canvasBottomRight = new Point(this.width - 1, this.height - 1);

		// definition of the four edges of the canvas
		Line northEdgeCanvas = new Line(canvasTopLeft.getX(), canvasTopLeft.getY(), canvasTopRight.getX(),
				canvasTopRight.getY());
		Line southEdgeCanvas = new Line(canvasBottomLeft.getX(), canvasBottomLeft.getY(), canvasBottomRight.getX(),
				canvasBottomRight.getY());
		Line westEdgeCanvas = new Line(canvasTopLeft.getX(), canvasTopLeft.getY() + 1, canvasBottomLeft.getX(),
				canvasBottomLeft.getY() - 1);
		Line eastEdgeCanvas = new Line(canvasTopRight.getX(), canvasTopRight.getY() + 1, canvasBottomRight.getX(),
				canvasBottomRight.getY() - 1);

		// draw the edges to build the canvas
		drawLine(northEdgeCanvas, HORIZONTAL_CANVAS_BORDER);
		drawLine(southEdgeCanvas, HORIZONTAL_CANVAS_BORDER);
		drawLine(westEdgeCanvas, VERTICAL_CANVAS_BORDER);
		drawLine(eastEdgeCanvas, VERTICAL_CANVAS_BORDER);
	}

	public void drawLine(Line line, char ch) {

		// get the point details for validation and drawing
		int x1 = line.getX1();
		int y1 = line.getY1();
		int x2 = line.getX2();
		int y2 = line.getY2();

		if (ch == POINT_CHAR) { // check the validity of the points if drawLine method is not called for canvas
								// drawing
			if (!(isValidPoint(x1, y1) && isValidPoint(x2, y2))) {
				throw new IllegalArgumentException("The line points are outside");
			}
		}

		if (x1 != x2 && y1 != y2) {
			throw new IllegalArgumentException("Currently only horizontal or vertical lines are supported.");
		}

		for (int i = y1; i <= y2; i++) {
			for (int j = x1; j <= x2; j++) {
				canvasArray[i][j] = ch;
			}
		}
	}

	public void drawRectangle(Rectangle rectangle) {

		Line northEdgeRect = new Line(rectangle.getP1().getX(), rectangle.getP1().getY(), rectangle.getP2().getX(), rectangle.getP1().getY());
		Line westEdgeRect  = new Line(rectangle.getP1().getX(), rectangle.getP1().getY(), rectangle.getP1().getX(), rectangle.getP2().getY());
		Line eastEdgeRect  = new Line(rectangle.getP2().getX(), rectangle.getP1().getY(), rectangle.getP2().getX(), rectangle.getP2().getY());
		Line southEdgeRect = new Line(rectangle.getP1().getX(), rectangle.getP2().getY(), rectangle.getP2().getX(), rectangle.getP2().getY());

		drawLine(northEdgeRect, POINT_CHAR);
		drawLine(westEdgeRect, POINT_CHAR);
		drawLine(eastEdgeRect, POINT_CHAR);
		drawLine(southEdgeRect, POINT_CHAR);
	}

	public String render() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				stringBuilder.append(canvasArray[i][j]);
				System.out.print(canvasArray[i][j]);				
			}
			stringBuilder.append(NEW_LINE);
			System.out.println();
		}
		
		return stringBuilder.toString();
	}

	public void bucketFill(int x, int y, char ch) {
		if (!isValidPointBucket(x, y)) {
			throw new IllegalArgumentException("The bucket fill point is outside");
		}

		// Stack-based recursive implementation (four-way) algo
		if (canvasArray[y][x] != ' ') { // If it reaches a border then return
			return;
		}

		if((x > 0 && x < width - 1) && (y > 0 && y < height - 1))  {
			
			if (canvasArray[y][x] == ' ') {
				canvasArray[y][x] = ch;
			}

			bucketFill(x + 1, y, ch); // one step to the south of node
			bucketFill(x - 1, y, ch); // one step to the north of node
			bucketFill(x, y - 1, ch); // one step to the west of node
			bucketFill(x, y + 1, ch); // one step to the east of node
		}

	}

	private boolean isValidPoint(int x, int y) {
		// check if the given point is in the canvas
		return (x >= 1 && x < width) && (y >= 1 && y < height);
	}
	

	private boolean isValidPointBucket(int x, int y) {
		return (x >= 0 && x < width) && (y >= 0 && y < height);
	}

}

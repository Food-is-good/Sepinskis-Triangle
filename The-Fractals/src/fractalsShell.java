/*
 * Henry Zhang 4th Period
 * Fractals Lab
*/

import java.awt.*;

class fractalsShell {
	public static void main(String[] args) {
		
		//canvas, settings, and drawing
		DrawingPanel panel = new DrawingPanel(300,300);
		panel.setBackground(Color.WHITE);
		Graphics g = panel.getGraphics();
		
		g.setColor(Color.BLACK);
		
		//points to the original triangle
		Point bottomLeft = new Point(50, 250);
		Point topMiddle = new Point(150, 50);
		Point bottomRight = new Point(250, 250);
		
		//two points for the lines
		Point x = new Point(20, 20);
		Point y = new Point(280, 20);

		//Time to use my custom method!
		recur(g, bottomLeft, topMiddle, bottomRight, 6);
		lines(g, x, y, 5);
	}

	public static void drawTriangle(Graphics g, Point p1, Point p2, Point p3)
	{
		//draws three lines connecting each point to draw the triangle
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
		g.drawLine(p2.x, p2.y, p3.x, p3.y);
		g.drawLine(p3.x, p3.y, p1.x, p1.y);
	}

  	
  	public static Point midpoint(Point p1, Point p2)
  	{
  		//finds the average of the x and y of p1 and p2 to return the midpoint of the two points.
  		Point mid = new Point();
  		mid.x = (p1.x + p2.x)/2;
  		mid.y = (p1.y + p2.y)/2;
  		
  		return mid;
  	}

	public static void recur(Graphics g, Point p1, Point p2, Point p3, int depth)
	{
		if(depth==0) {
			//prints the triangle at the end
			drawTriangle(g, p1,p2,p3);
		}
		else {
			//find midpoint between bottom left and top 
			Point x1 = midpoint(p1,p2); 
			//find midpoint between top and bottom right
			Point x2 = midpoint(p2,p3); 
			//find midpoints between bottom left and bottom left
			Point x3 = midpoint(p1,p3); 

			//recur into triangles of left right and top
			recur(g,x1,p1,x3,depth-1); 
			recur(g,p2,x1,x2, depth-1);
			recur(g,x2,x3,p3, depth-1);
		}
	}
	
	//this method making me wanting to go to engineering instead
	public static void lines(Graphics g, Point p1, Point p2, int depth)

	{
		if(depth==0) {
			return;
		}
		else {
			//finding the middle third beginning
			int mid = (p2.x-p1.x)/3;
			
			//finding the midpoints of the points
			Point x1 = new Point(p1.x+mid,p1.y+10);
			Point x2 = new Point(x1.x+mid,p1.y+10);
			
			//draw the halves of the lines
			g.drawLine(p1.x,p1.y, p2.x,p2.y);

			//recurring into the new lines to create new lines
			lines(g,new Point(p1.x,p1.y+10),x1,depth-1); 
			lines(g,x2,new Point(p2.x, p2.y+10), depth-1);
			
		}
	}
}
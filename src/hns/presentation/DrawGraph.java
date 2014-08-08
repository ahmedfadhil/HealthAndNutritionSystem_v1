package hns.presentation;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
public class DrawGraph implements PaintListener {
	  int [] weights;
	  int currentDate;
	  public DrawGraph(int[] weights, int currentDate) {
		  this.weights = weights;
		  this.currentDate= currentDate;

	  }
	public void paintControl(PaintEvent e) {
		// Get the canvas and its dimensions
		Canvas canvas = (Canvas) e.widget;
		int minWeight=1000; 
		int maxWeight=0;
		int []y= new int [weights.length];
		int[]x= new int [weights.length];
		int maxX = canvas.getSize().x;
		int maxY = canvas.getSize().y-10;
		int interval = (int) maxX/10;

		minWeight = findMin(weights);
		maxWeight = findMax(weights);
		e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLUE));
		
		// Draw the points
		for (int i = 0; i < weights.length; i++) {
			y[i]=getY(weights[i], maxY, maxWeight,minWeight);
			x[i]=5+interval*i;
			e.gc.drawOval(x[i],y[i] , 5, 5);		      
		}
		e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
		drawRegression(x,y, maxX,maxY, e);
		
		e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_RED));
		e.gc.drawOval(5+interval*currentDate, getY(weights[currentDate], maxY, maxWeight,minWeight), 5, 5);		      
	}

	int findMin(int [] array){
		int min=array[0];
		for(int i=1; i<array.length; i++){
			if(min>array[i])
				min = array[i];
		}
		return min;
	}
	int findMax(int [] array){
		int max=array[0];
		for(int i=1; i<array.length; i++){
			if(max<array[i])
				max = array[i];
		}
		return max;
	}
	int getY(int curr, int screenDiff, int maxWeight, int minWeight){
	  	int baseDiff = maxWeight - minWeight;
	  	int currDiff = maxWeight - curr;
	  	double quotient = (double)currDiff/baseDiff;
	  	return (int)(quotient*screenDiff);
	}
	void drawRegression( int[] x, int []y, int maxX, int maxY, PaintEvent e){

		int n = y.length;
		int yPoint;
		int sumY = findSum(y);
		int sumX = findSum(x);
		int sumXY = findCrossSum(x, y);
		int sumXX = findCrossSum(x,x);
		double slope = ((double)(n*sumXY)-sumX*sumY)/((n*sumXX)-(sumX*sumX));
		double inter = ((double)sumY-(slope*sumX))/n;

		for (int i =0; i<maxX;i++){
			yPoint=(int) (inter+slope*i);
			e.gc.drawPoint(i,yPoint);
			
		}
		
	}
	
	int findCrossSum(int [] x, int y[]){
		int sum = 0;
		for (int i =0; i<x.length; i++){
			sum+=(x[i]*y[i]);
		}
		return sum;
	}
	
	int findSum(int []x){
		int sum = 0 ;
		for (int i=0; i<x.length; i++){
			sum+=x[i];
		}
		return sum;
	}
}
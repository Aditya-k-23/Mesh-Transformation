package assignment;

public abstract class GraphicalObject{
	
	public abstract void transform(double[][] transformation_matrix);
	
	public void rotateXAxis(double theta) {
		double[][] transformation_matrix = {
				{1.0, 0.0, 0.0},
		        {0.0, Math.cos(theta), -Math.sin(theta)},
		        {0.0,Math.sin(theta), Math.cos(theta)}};
		this.transform(transformation_matrix);
	}
	
	public void rotateYAxis(double theta) {
		double[][] transformation_matrix = {
				{Math.cos(theta), 0.0, Math.sin(theta)},
                {0.0, 1.0, 0.0},
                {-Math.sin(theta), 0.0, Math.cos(theta)}};
		this.transform(transformation_matrix);
	}	
	
	public void rotateZAxis(double theta){
		double[][] transformation_matrix = {
                {Math.cos(theta), -Math.sin(theta), 0.0},
                {Math.sin(theta), Math.cos(theta), 0.0},
                {0.0, 0.0, 1.0}};
		this.transform(transformation_matrix);
	}
	
}
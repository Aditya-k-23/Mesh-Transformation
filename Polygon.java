package assignment;

import java.util.LinkedHashSet;

public class Polygon extends GraphicalObject{
	
	LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();
	
	@SuppressWarnings("unchecked")
	public Polygon(LinkedHashSet<Vertex> input) {
		vertices = (LinkedHashSet<Vertex>)input.clone();
	}
	
	public void transform(double[][] transformation_matrix) {
		for (Vertex x : vertices) {
			x.transform(transformation_matrix);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(getClass() != obj.getClass()) 
			return false;
		Polygon other = (Polygon) obj;
		boolean same = true;
		for(Vertex v1: this.vertices) {
			for(Vertex v2: other.vertices) {
				if(v1.equals(v2)) {
					same = true;
					break;
				}
				same = false;
			}	
		}
		return same;
	}
	
	@Override
	public int hashCode() {
		int sum = 0; 
		int weight = 1;
		for (Vertex x : vertices) {
			sum = sum + weight*x.hashCode();
			weight++;
		}
		return sum;
	}
	
}
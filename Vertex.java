package assignment;

public class Vertex extends GraphicalObject{

	double x,y,z;
	public Vertex(double a, double b, double c) {
		this.x=a;
		this.y=b;
		this.z=c;
	}
	
	public void transform(double[][] t_matrix) {
		Vertex temp = new Vertex(x,y,z);
		temp.x = x*t_matrix[0][0] + y*t_matrix[0][1] + z*t_matrix[0][2];
		temp.y = x*t_matrix[1][0] + y*t_matrix[1][1] + z*t_matrix[1][2];
		temp.z = x*t_matrix[2][0] + y*t_matrix[2][1] + z*t_matrix[2][2];
		x = temp.x;
		y = temp.y;
		z = temp.z;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(getClass() != obj.getClass()) 
			return false;
		Vertex other = (Vertex) obj;
		if(x == other.x && y == other.y && z == other.z)
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return (int)(100000*x + 1000*y + z);
	}
	
	@Override
	public String toString() {
		return x + " " + y + " " + z;
	}
}
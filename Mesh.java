package assignment;

import java.util.HashSet;

public 	class Mesh extends GraphicalObject{
	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	
	public void setReader(MeshReader spl_reader) {
		//Sets reader accordingly
		reader = spl_reader;
	}
	
	public void setWriter(MeshWriter spl_writer) {
		//Sets Writer accordingly
		writer = spl_writer;
	}
	
	public void readFromFile(String fileName) {
		//Uses Reader to read polygons from file
		polygons = reader.read(fileName);
	}
	
	public void writeToFile(String fileName) {
		//Uses Writer to write polygons to file
		writer.write(fileName, polygons);
	}
	
	public void transform(double[][] transformation_matrix) {
		//Transforming a mesh is done by transforming all of its polygons.
		for(Polygon x:polygons) {
			x.transform(transformation_matrix);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(getClass() != obj.getClass()) 
			return false;
		Mesh other = (Mesh) obj;
		boolean same = true;
		for(Polygon p1: this.polygons) {
			for(Polygon p2: other.polygons) {
				if(p1.equals(p2)) {
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
		for (Polygon p : polygons) {
			sum = sum + weight*p.hashCode();
			weight++;
		}
		return sum;
	}
}
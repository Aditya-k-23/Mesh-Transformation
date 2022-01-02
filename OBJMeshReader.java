package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class OBJMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String fileName) {
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();
		File ObjFile = new File(fileName);
		Scanner input = null;
		try {
			input = new Scanner(ObjFile);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(input.hasNextLine()) {
			String line = input.nextLine();
			String[] contents = line.split(" ");
			if(contents[0].equals("v")) {
				Vertex new_v = new Vertex(Double.parseDouble(contents[1]), Double.parseDouble(contents[2]), Double.parseDouble(contents[3]));
				vertices.add(new_v);
			}
			else if(contents[0].equals("f")){
				Vertex[] vertices_arr = new Vertex[vertices.size()];
				vertices_arr = vertices.toArray(vertices_arr);
				LinkedHashSet<Vertex> new_poly_vertices = new LinkedHashSet<Vertex>();
				for(int k = 1; k<contents.length; k++) {
					int index = Integer.parseInt(contents[k]);
					Vertex p_v = new Vertex(vertices_arr[index-1].x, vertices_arr[index-1].y, vertices_arr[index-1].z);
					new_poly_vertices.add(p_v);
				}
				Polygon new_poly = new Polygon(new_poly_vertices);
				polygons.add(new_poly);
				
			}
		}
		
		return polygons;
	}
	
}
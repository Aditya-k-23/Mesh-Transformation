package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OBJMeshWriter implements MeshWriter{

	@Override
	public void write(String fileName, HashSet<Polygon> polygons) {
		LinkedHashSet<Vertex> found_vertices = new LinkedHashSet<Vertex>();
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName, true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(Polygon p: polygons) {
			for(Vertex v: p.vertices) {
				if(!found_vertices.contains(v)) {
					found_vertices.add(v);
					
				}
			}
		}
		
		Vertex[] vertices_arr = new Vertex[found_vertices.size()];
		vertices_arr = found_vertices.toArray(vertices_arr);

		for(Vertex v: vertices_arr) {
			try {
				writer.write("v " + v.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(Polygon p: polygons) {
			int num_faces = p.vertices.size();
			String[] faces = new String[num_faces + 1];
			faces[0] = "f";
			int j = 1;
			for(Vertex v: p.vertices) {
				int index = 0;
				for(int i=0; i < vertices_arr.length; i++) {
					if (vertices_arr[i].equals(v)) {
						index = i;
					}
				}
				faces[j] = String.valueOf(index + 1);
				j++;
			}
			for(int i = 0; i<=num_faces; i++) {
				try {
					writer.write(faces[i] + " ");
					}
				catch (IOException e) {
					 e.printStackTrace();
				}
			}
			try {
				writer.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
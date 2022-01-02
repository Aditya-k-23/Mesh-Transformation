package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class PLYMeshWriter implements MeshWriter{

	@Override
	public void write(String fileName, HashSet<Polygon> polygons) {
		LinkedHashSet<Vertex> found_vertices = new LinkedHashSet<Vertex>();
		int num_faces = 0;
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(fileName, true);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		for(Polygon p: polygons) {	
			for(Vertex v: p.vertices) {
				if(!found_vertices.contains(v)) {
					found_vertices.add(v);
				}
			}
		}
		int num_poly = polygons.size();
		Vertex[] vertices_arr = new Vertex[found_vertices.size()];
		vertices_arr = found_vertices.toArray(vertices_arr);
			
		
		try {
			writer.write("ply\n" + "format ascii 1.0\n" + "element vertex " + vertices_arr.length + "\n");
			writer.write("property float32 x\n" + "property float32 y\n"+ "property float32 z\n");
			writer.write("element face " + num_poly);
			writer.write("\nproperty list uint8 int32 vertex_indices\n" +  "end_header\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(Vertex v: vertices_arr) {
			try {
				writer.write(v.toString()+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		for(Polygon p: polygons) {
			num_faces = p.vertices.size();
			String[] faces = new String[num_faces + 1];
			int j = 1;
			faces[0] = String.valueOf(num_faces);
			for(Vertex v: p.vertices) {
				int index = 0;
				for(int i=0; i < vertices_arr.length; i++) {
					if (vertices_arr[i].equals(v)) {
						index = i;
					}
				}
				faces[j] = String.valueOf(index);
				j++;
			}
			for(int i = 0; i<=num_faces; i++) {
				try {
					writer.write(faces[i] + " ");
					}
				 catch (IOException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					 }
			}
			try {
				writer.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
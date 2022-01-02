package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class PLYMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String fileName) {
		HashSet<Polygon> polygons = new HashSet<Polygon>();
		LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();
		File ObjFile = new File(fileName);
		int num_vertices = 0;
		int num_faces = 0;
		Scanner input = null;
		try {
			input = new Scanner(ObjFile);
		}catch (FileNotFoundException e) {
			System.out.println("Please enter valid file name");
			e.printStackTrace();
		}
		input.nextLine();
		input.nextLine();
		String num_vertex_line = input.nextLine();
		String[] num_vertex_contents = num_vertex_line.split(" ");
		num_vertices = Integer.parseInt(num_vertex_contents[2]);
		input.nextLine();
		input.nextLine();
		input.nextLine();
		String num_faces_line = input.nextLine();
		String[] num_faces_contents = num_faces_line.split(" ");
		num_faces = Integer.parseInt(num_faces_contents[2]);
		input.nextLine();
		input.nextLine();
		for(int i =0; i<num_vertices;i++) {
			String vertex_line = input.nextLine();
			String[] contents = vertex_line.split(" ");
			Vertex v = new Vertex(Double.parseDouble(contents[0]), Double.parseDouble(contents[1]), Double.parseDouble(contents[2]));
			vertices.add(v);
		}
		for(int j =0; j<num_faces; j++) {	
			String faces_line = input.nextLine();
			String[] contents = faces_line.split(" ");
			Vertex[] vertices_arr = new Vertex[vertices.size()];
			vertices_arr = vertices.toArray(vertices_arr);
			LinkedHashSet<Vertex> new_poly_vertices = new LinkedHashSet<Vertex>();
			int vertices_in_face = Integer.parseInt(contents[0]);
			for(int k = 1; k <= vertices_in_face;k++) {
				int index = Integer.parseInt(contents[k]);
				Vertex p_v = new Vertex(vertices_arr[index].x, vertices_arr[index].y, vertices_arr[index].z);
				new_poly_vertices.add(p_v);
			}
			Polygon new_poly = new Polygon(new_poly_vertices);
			polygons.add(new_poly);
		}
		
		//Test Code
		
		//for(Vertex v:vertices) {
		//	System.out.println(v.toString() + "\n");
		//}
		//
		return polygons;
		
	}
	
}
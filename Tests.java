package assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void testVertexHashCode() {
		Vertex p1 = new Vertex(1, 1, 1);
		assertEquals(p1.hashCode(), 101001);
	}
	
	@Test
	void testVerEqualsNull() {
		Vertex p1 = new Vertex(1,1,1);
		assertFalse(p1.equals(null));
	}
	
	@Test
	void testVerEqualsDifferentObject() {
		Vertex p1 = new Vertex(1,1,1);
		assertFalse(p1.equals(14));
	}
	
	@Test
	void testVerEqualsSameX() {
		Vertex p1 = new Vertex(1,1,3);
		Vertex p2 = new Vertex(1,2,3);
		assertFalse(p1.equals(p2));
	}
	
	@Test
	void testVerEqualsSameXY() {
		Vertex p1 = new Vertex(1,1,1);
		Vertex p2 = new Vertex(1,1,3);
		assertFalse(p1.equals(p2));
	}
	
	@Test
	void testVerEqualsSameXYZ() {
		Vertex p1 = new Vertex(1,1,1);
		Vertex p2 = new Vertex(1,1,1);
		assertTrue(p1.equals(p2));
	}
	
	@Test
	void testVerEqualsSameYZ() {
		Vertex p1 = new Vertex(1,1,1);
		Vertex p2 = new Vertex(2,1,1);
		assertFalse(p1.equals(p2));
	}	
	
	@Test
	void testVertexToString() {
		Vertex p1 = new Vertex(1,1,1);
		String s1 = "1.0 1.0 1.0";
		assertEquals(p1.toString(), s1);
	}
	
	@Test
	void testVertexTransform() {
		Vertex p1 = new Vertex(1,2,3);
		Vertex p2 = new Vertex(6,5,3);
		double[][] t_matrix = {{1.0, 1.0, 1.0}, {0.0, 1.0, 1.0}, {0.0, 0.0, 1.0}};
		p1.transform(t_matrix);
		assertEquals(p1, p2);
	}
	
	@Test
	void testRotateXAxisby90() {
		Vertex p1 = new Vertex(1,2,3);
		Vertex x_rot_p1 = new Vertex(1,-3,2);
		p1.rotateXAxis(Math.PI/2);
		assertEquals(p1,x_rot_p1);
	}
	
	@Test
	void testRotateYAxisby90() {
		Vertex p1 = new Vertex(1,2,3);
		Vertex x_rot_p1 = new Vertex(3,2,-0.9999999999999998);
		p1.rotateYAxis(Math.PI/2);
		assertEquals(p1,x_rot_p1);
	}
	
	@Test
	void testRotateZAxisby90() {
		Vertex p1 = new Vertex(1,2,3);
		Vertex x_rot_p1 = new Vertex(-2,1.0000000000000002,3);
		p1.rotateZAxis(Math.PI/2);
		assertEquals(p1,x_rot_p1);
	}
	
	@Test
	void testPolyHashCode() {
		Vertex p1 = new Vertex(2,2,2);
		Vertex p2 = new Vertex(1,1,1);
		Vertex p3 = new Vertex(3,3,3);
		LinkedHashSet<Vertex> vertices= new LinkedHashSet<Vertex>();
		vertices.add(p1);
		vertices.add(p2);
		vertices.add(p3);
		Polygon p = new Polygon(vertices);
		assertEquals(p.hashCode(), 1313013);
	}
	
	@Test
	void testPolyEqualsNull() {
		Vertex p1 = new Vertex(2,2,2);
		Vertex p2 = new Vertex(1,1,1);
		Vertex p3 = new Vertex(3,3,3);
		LinkedHashSet<Vertex> vertices= new LinkedHashSet<Vertex>();
		vertices.add(p1);
		vertices.add(p2);
		vertices.add(p3);
		Polygon p = new Polygon(vertices);
		assertFalse(p.equals(null));
	}
	
	@Test
	void testPolyEqualsDifferentObject() {
		Vertex p1 = new Vertex(2,2,2);
		Vertex p2 = new Vertex(1,1,1);
		Vertex p3 = new Vertex(3,3,3);
		LinkedHashSet<Vertex> vertices= new LinkedHashSet<Vertex>();
		vertices.add(p1);
		vertices.add(p2);
		vertices.add(p3);
		Polygon p = new Polygon(vertices);
		assertFalse(p.equals(14));
	}

	@Test
	void testPolyDifferent() {
		Vertex p1 = new Vertex(2,2,2);
		Vertex p2 = new Vertex(1,1,3);
		Vertex p3 = new Vertex(3,3,4);
		LinkedHashSet<Vertex> vertices= new LinkedHashSet<Vertex>();
		vertices.add(p1);
		vertices.add(p2);
		vertices.add(p3);
		Polygon p = new Polygon(vertices);
		Vertex p4 = new Vertex(12,12,12);
		Vertex p5 = new Vertex(11,11,11);
		Vertex p6 = new Vertex(13,13,13);
		LinkedHashSet<Vertex> vertices2= new LinkedHashSet<Vertex>();
		vertices2.add(p4);
		vertices2.add(p5);
		vertices2.add(p6);
		Polygon new_p = new Polygon(vertices2);
		assertFalse(p.equals(new_p));
	}

	@Test
	void testPolyTransform() {
		Vertex p1 = new Vertex(2,2,2);
		Vertex p4 = new Vertex(2,2,2);
		Vertex p2 = new Vertex(1,1,1);
		Vertex p5 = new Vertex(1,1,1);
		Vertex p3 = new Vertex(3,3,3);
		Vertex p6 = new Vertex(3,3,3);
		LinkedHashSet<Vertex> vertices= new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> transformed_vertices= new LinkedHashSet<Vertex>();
		vertices.add(p1);
		vertices.add(p2);
		vertices.add(p3);
		Polygon p = new Polygon(vertices);
		double[][] t_matrix = {{1.0, 1.0, 1.0}, {0.0, 1.0, 1.0}, {0.0, 0.0, 1.0}};
		p4.transform(t_matrix);
		p5.transform(t_matrix);
		p6.transform(t_matrix);
		transformed_vertices.add(p4);
		transformed_vertices.add(p5);
		transformed_vertices.add(p6);
		Polygon trans_p = new Polygon(transformed_vertices);
		p.transform(t_matrix);
		assertEquals(p, trans_p);
	}
	
	@Test
	void testMeshHashCode() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.readFromFile("C:/Users/Aditya/testobj.obj");
		int actual = m.hashCode();
		int expected = 5418000;
		assertEquals(actual, expected);
		
	}
	
	@Test
	void testMeshOBJReadNoFile() throws FileNotFoundException{
		Mesh m = new Mesh();
		File ObjFile = new File("WrongName");
		Assertions.assertThrows(FileNotFoundException.class, ()->{Scanner input1 = new Scanner(ObjFile);});
	}
	
	@Test
	void testMeshEqualsNull() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.readFromFile("C:/Users/Aditya/testobj.obj");
		assertFalse(m.equals(null));
	}
	
	@Test
	void testMeshEqualsOtherObj() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.readFromFile("C:/Users/Aditya/testobj.obj");
		assertFalse(m.equals(12));
	}
	
	@Test
	void testMeshSelfEquality() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.readFromFile("C:/Users/Aditya/testobj.obj");
		assertEquals(m, m);
	}
	
	@Test
	void testMeshNotEqualsOtherMesh() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.readFromFile("C:/Users/Aditya/testobj.obj");
		Mesh m2 = new Mesh();
		m2.setReader(new PLYMeshReader());
		m2.readFromFile("C:/Users/Aditya/testply.ply");
		assertFalse(m.equals(m2));
	}
	
	@Test
	void testMeshTransform() {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.readFromFile("C:/Users/Aditya/testobj.obj");
		Mesh m2 = new Mesh();
		m2.setReader(new OBJMeshReader());
		m2.readFromFile("C:/Users/Aditya/testobj.obj");
		for(Polygon p:m.polygons) {
			for(Vertex v:p.vertices) {
				v.rotateXAxis(Math.PI/2);
			}
		}
		double[][] t_matrix = {
				{1.0, 0.0, 0.0},
		        {0.0, Math.cos(Math.PI/2), -Math.sin(Math.PI/2)},
		        {0.0,Math.sin(Math.PI/2), Math.cos(Math.PI/2)}};
		m2.transform(t_matrix);
		assertEquals(m,m2);
	}
	
	@Test
	void testMeshWritesToOBJFile() throws IOException {
		Mesh m = new Mesh();
		m.setReader(new OBJMeshReader());
		m.readFromFile("C:/Users/Aditya/testobj.obj");
		File actual = new File("C:/Users/Aditya/actual_testobj.obj");
		m.setWriter(new OBJMeshWriter());
		m.writeToFile(actual.getAbsolutePath());
		Mesh m1 = new Mesh();
		m1.setReader(new OBJMeshReader());
		m1.readFromFile(actual.getAbsolutePath());
		assertTrue(m1.equals(m));
	}
	
	@Test
	void testMeshWritesToPLYFile() throws IOException {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.readFromFile("C:/Users/Aditya/testply.ply");
		File actual = new File("C:/Users/Aditya/actual_testply.ply");
		m.setWriter(new PLYMeshWriter());
		m.writeToFile(actual.getAbsolutePath());
		Mesh m1 = new Mesh();
		m1.setReader(new PLYMeshReader());
		m1.readFromFile(actual.getAbsolutePath());
		assertTrue(m1.equals(m));
	}
	
	@Test
	void testMeshReaderWrongFormat() {
		Mesh m = new Mesh();
		m.setReader(new PLYMeshReader());
		m.readFromFile("C:/Users/Aditya/testply1.ply");
	}
}

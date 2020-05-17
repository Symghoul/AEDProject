package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graph.Edge;
import graph.GraphM;
import graph.Vertex;
import graph.VertexM;

class TestGraphM {

	private GraphM<String> grafMatrix;
	private VertexM<String> vS1;
	private VertexM<String> vS2;
	private VertexM<String> vS3;
	private VertexM<String> vS4;
	private Edge e1;
	private Edge e2;
	private Edge e3;
	private Edge e4;
	
	@BeforeEach
	void setUp1() {		//Grafo no ponderado y no dirigido
		grafMatrix = new GraphM<String>(false, false);
	}
	
	void setUp2() {		//Grafo ponderado y dirigido
		grafMatrix = new GraphM<String>(true, true);
	}
	
	void setUp3() {		//2 Vertices y una arista
		
	vS1 = new VertexM<String>("Hola");
	vS2 = new VertexM<String>("Holi");
	e1 = new Edge(vS1, vS2, 1);
	
	}
	
	void setUp4() {		//Crea un circuito de vertices sin aristas ponderadas
		
		vS1 = new VertexM<String>("Hola");
		vS2 = new VertexM<String>("Holi");
//		vS3 = new VertexM<String>("Hi");
//		vS4 = new VertexM<String>("GUTENMORGUEN¡¡¡");
		e1 = new Edge(vS1, vS2, 1);
//		e2 = new Edge(vS2, vS3, 1);
//		e3 = new Edge(vS3, vS4, 1);
	}

	void setUp5() {
		
		vS1 = new VertexM<String>("Hola");
		vS2 = new VertexM<String>("Holi");
//		vS3 = new VertexM<String>("Hi");
//		vS4 = new VertexM<String>("GUTENMORGUEN¡¡¡");
		e1 = new Edge(vS1, vS2, 50);
//		e2 = new Edge(vS2, vS3, 26);
//		e3 = new Edge(vS3, vS4, 36);
//		e4 = new Edge(vS2, vS4, 36);
	}
	
	@Test
	void testIsEmpty() {
		
		setUp1();
		assertTrue(grafMatrix.isEmpty());
		grafMatrix.insertVertex("Hola");
		assertFalse(grafMatrix.isEmpty());
		
		setUp2();
		assertTrue(grafMatrix.isEmpty());
		grafMatrix.insertVertex("Hola");
		assertFalse(grafMatrix.isEmpty());
	}
	
	@Test
	void testInsertVertex() {
	
		setUp1();
		setUp3();
		grafMatrix.insertVertex("Hola");
		assertEquals(grafMatrix.getElementsReference().get(0).getValue(),(vS1.getValue()));
		
	}
	
	@Test
	void testSearchVertex() {
		
		setUp1();
		setUp3();
		grafMatrix.insertVertex("Hola");
		Vertex<String> v = grafMatrix.searchVertex("Hola");
		System.out.println(grafMatrix.getElementsReference().size());
		System.out.println(v==null);
 		assertNotNull(v);
		
		
	}
	
	@Test
	void testEdgeLabel() {
		

		
	}
	
	@Test
	void testInsertEdge() {
		
		setUp1();
		
	}

}

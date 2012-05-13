package edu.pquinn.raytracer.util;

import edu.pquinn.raytracer.Vertex;

public class RaytracerUtil {
	
	public static Vertex[] toVertexArray(Object[] in) {
		Vertex[] ret = new Vertex[in.length];
		for (int i = 0; i < in.length; i++) {
			ret[i] = (Vertex) in[i];
		}
		return ret;
	}
	
}

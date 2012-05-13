package edu.pquinn.raytracer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.pquinn.raytracer.light.Light;
import edu.pquinn.raytracer.light.PointLight;
import edu.pquinn.raytracer.surface.Plane;
import edu.pquinn.raytracer.surface.Sphere;
import edu.pquinn.raytracer.surface.Surface;
import edu.pquinn.raytracer.util.HitResult;
import edu.pquinn.raytracer.util.RaytracerUtil;
import edu.pquinn.raytracer.util.Vector;

/**
 * World
 * Class representing the World. Contains the frame where the result is rendered.
 * 
 * @author phil <pquinn@ccs.neu.edu>
 *
 */
public class World {
	private static ArrayList<Surface> surfaces;
	private static ArrayList<Vertex> vertices;
	private static ArrayList<Light> lights;
	private static Vertex[] vertexArray;
	private static float[] ambientMaterial;
	private static float[] diffuseMaterial;
	private static float[] specularMaterial;
	private static int specularExponent;
	private static boolean diffuse;
	private static boolean specular;
	private static boolean shadows;
	private static int mirror;
	private static float intensity;
	private static Camera camera;
	private static int nx;
	private static int ny;
	private static BufferedImage image;
	private static JFrame frame;
	
	private static JFileChooser fileChooser;
	private static File inputFile;

	// constructor
	World() {
		World.init();
		panel.setBackground(Color.BLACK);
		panel.setVisible(true);
		panel.setPreferredSize(new Dimension(nx, ny));

		frame = new JFrame("HW6");
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setContentPane(panel);
	}

	private static void init() {
		World.surfaces = new ArrayList<Surface>();
		World.vertices = new ArrayList<Vertex>();
		World.lights = new ArrayList<Light>();
		World.ambientMaterial = new float[] { 0.2f, 0.2f, 0.2f };
		World.diffuseMaterial = new float[] { 1.0f, 1.0f, 1.0f };
		World.specularMaterial = new float[] { 1.0f, 1.0f, 1.0f };
		World.specularExponent = 64;
		World.diffuse = true;
		World.specular = true;
		World.shadows = true;
		World.mirror = 1;
		World.intensity = 1;
		World.nx = 512;
		World.ny = 512;
		World.image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
		World.fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new MyFilter());
	}

	// reads the input
	public void readInput() {
		try {
			int returnVal = fileChooser.showOpenDialog(frame);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				inputFile = fileChooser.getSelectedFile();
			}
		    FileInputStream fstream = new FileInputStream(inputFile);
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = "";
			while ((strLine = br.readLine()) != null) {
				String strLine2 = strLine;
				parseLine(strLine2);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.toString());
			e.printStackTrace();
		}
		
		vertexArray = RaytracerUtil.toVertexArray(vertices.toArray());

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// parses the line
	public void parseLine(String line) {
		StringTokenizer st = new StringTokenizer(line);
		String iden = st.nextToken();
		if (iden.equals("vv")) {
			vertices.add(new Vertex(st));
		} else if (iden.equals("am")) {
			ambientMaterial[0] = Float.parseFloat(st.nextToken());
			ambientMaterial[1] = Float.parseFloat(st.nextToken());
			ambientMaterial[2] = Float.parseFloat(st.nextToken());
		} else if (iden.equals("dm")) {
			diffuseMaterial[0] = Float.parseFloat(st.nextToken());
			diffuseMaterial[1] = Float.parseFloat(st.nextToken());
			diffuseMaterial[2] = Float.parseFloat(st.nextToken());
		} else if (iden.equals("sm")) {
			specularMaterial[0] = Float.parseFloat(st.nextToken());
			specularMaterial[1] = Float.parseFloat(st.nextToken());
			specularMaterial[2] = Float.parseFloat(st.nextToken());
		} /*
		 * else if(iden.equals("ts")){ // int v1 =
		 * Integer.parseInt(st.nextToken()); int v2 =
		 * Integer.parseInt(st.nextToken()); int v3 =
		 * Integer.parseInt(st.nextToken()); Surface t = new Triangle(new
		 * int[]{v1, v2, v3}, this.ambientMaterial, this.diffuseMaterial,
		 * this.specularMaterial, this.specularExponent);
		 * 
		 * surfaces.add(t); }
		 */else if (iden.equals("ss")) {
			int v1 = Integer.parseInt(st.nextToken());
			System.out.println("Parsing Sphere with colors: am: "
					+ Arrays.toString(ambientMaterial));
			System.out.println("Parsing Sphere with colors: dm: "
					+ Arrays.toString(diffuseMaterial));
			System.out.println("Parsing Sphere with colors: sm: "
					+ Arrays.toString(specularMaterial));
			System.out.println("Parsing Sphere with colors: se: "
					+ specularExponent);
			Surface t = new Sphere(v1, ambientMaterial, diffuseMaterial,
					specularMaterial, specularExponent);
			surfaces.add(t);
		} else if (iden.equals("ps")) {
			int v1 = Integer.parseInt(st.nextToken());
			System.out.println("Parsing Plane with colors: am: "
					+ Arrays.toString(ambientMaterial));
			System.out.println("Parsing Plane with colors: dm: "
					+ Arrays.toString(diffuseMaterial));
			System.out.println("Parsing Plane with colors: sm: "
					+ Arrays.toString(specularMaterial));
			System.out.println("Parsing Plane with colors: se: "
					+ specularExponent);
			Surface t = new Plane(v1, ambientMaterial, diffuseMaterial,
					specularMaterial, specularExponent);
			surfaces.add(t);
		} else if (iden.equals("se")) {
			parseSettings(st);
		} else if (iden.equals("pl")) {
			int v1 = Integer.parseInt(st.nextToken());
			float intensity = Float.parseFloat(st.nextToken());
			Light l = new PointLight(v1, intensity);
			lights.add(l);
		} else if (iden.equals("dl")) {
			int v1 = Integer.parseInt(st.nextToken());
			float intensity = Float.parseFloat(st.nextToken());
			Light l = new PointLight(v1, intensity);
			lights.add(l);
		} else if (iden.equals("cc")) {
			int v1 = Integer.parseInt(st.nextToken());
			camera = new Camera(v1);
		} else {
			// ignore it, it's either a comment or bad input
		}
	}

	// parses the settings line
	public void parseSettings(StringTokenizer line) {
		String s = line.nextToken();
		diffuse = s.equals("d");
		
		s = line.nextToken();
		specular = s.equals("s");

		s = line.nextToken();
		shadows = s.equals("a");

		mirror = Integer.parseInt(line.nextToken());

		intensity = Float.parseFloat(line.nextToken());
	}

	// draws the buffered image
	public void drawBufferedImage() {
		HitResult hr = new HitResult(new Vector(0, 0, 0), new Vector(0, 0, 0),
				new double[3], null);
		for (int i = 0; i < ny; i++) {
			for (int j = 0; j < nx; j++) {
				Vector p = pixelToImagePoint(j, i);
				Vector pPrime = cameraToWorldFrame(p);
				Ray r = new Ray(camera.getOrigin(), pPrime.normalize());
				boolean hit = false;
				Surface hitObject = null;
				double t1 = Double.POSITIVE_INFINITY;
				for (Surface s : surfaces) {
					double t = s.intersectRay(r, 0.0, Double.POSITIVE_INFINITY,
							hr);
					if (t < t1) {
						hit = true;
						hitObject = s;
						t1 = t;
						hr.setSurfaceNormal(hr.getSurfaceNormal().normalize());
					}
					if (hit == true) {
						int c = colorToInt(computeFinalColor(hitObject, hr, r,
								mirror));
						image.setRGB(j, i, c);
						hit = false;
					}
				}
			}
		}
	}

	// creates the panel and adds the buffered image to it
	private JPanel panel = new JPanel() {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;
			g2d.drawRenderedImage(image, new AffineTransform());
		};
	};

	// computes the final color of the pixel
	public float[] computeFinalColor(Surface s, HitResult u, Ray r, int depth) {
		Vector v = r.d.negateVector().normalize();
		float[] color = calculateAmbientColor(s);
		for (Light l : lights) {
			if (shadows) {
				boolean shadow = false;
				l.calculatelhat(u);
				Ray sRay = new Ray(u.getIntersectionPoint(), l.getlhat());
				for (Surface s2 : surfaces) {
					if (s2.intersectRay(sRay, 0.0000001, l.getTMax(), u) < Double.POSITIVE_INFINITY) {
						shadow = true;
						break;
					}
				}
				if (shadow) {
					continue;
				}
			}
			if (diffuse) {
				float[] newDiffuse = calculateDiffuseColor(s, u, l);
				color[0] += newDiffuse[0];
				color[1] += newDiffuse[1];
				color[2] += newDiffuse[2];
			}
			if (specular) {
				float[] newSpecular = calculateSpecularColor(s, u, l, r);
				color[0] += newSpecular[0];
				color[1] += newSpecular[1];
				color[2] += newSpecular[2];
			}
		}
		// if(depth > 0){
		// Ray newRay = new Ray(u.intersectionPoint,
		// s.getSurfaceNormal(u.intersectionPoint, u).multiplyByScalar((2 *
		// v.dot(s.getSurfaceNormal(u.intersectionPoint,
		// u)))).subtractVectors(v));
		// return multiplyColors(s.getSpecularMaterial(), computeFinalColor(s,
		// u, newRay, depth - 1));
		// } else {
		return color;
		// }
	}

	// converts the color to a singular int
	public int colorToInt(float[] color) {
		for (int i = 0; i < color.length; i++) {
			if (color[i] > 1) {
				color[i] = 1;
			} else if (color[i] < 0) {
				color[i] = 0;
			}
		}
		return (int) (color[0] * 255) << 16 | (int) (color[1] * 255) << 8
				| (int) (color[2] * 255);
	}

	// multiplies 2 colors
	public float[] multiplyColors(float[] c1, float[] c2) {
		float[] result = new float[3];
		for (int i = 0; i < 3; i++) {
			result[i] = c1[i] * c2[i];
		}
		return result;
	}

	// calculates the ambient color
	public float[] calculateAmbientColor(Surface s) {
		float[] result = {s.getAmbientMaterial()[0] * intensity,
						  s.getAmbientMaterial()[1] * intensity,
						  s.getAmbientMaterial()[2] * intensity };
		return result;
	}

	// calculates the diffuse color
	public float[] calculateDiffuseColor(Surface s, HitResult u, Light light) {
		light.calculatelhat(u);
		Vector l = light.getlhat().normalize();
		double ndotl = s.getSurfaceNormal(u.getIntersectionPoint(), u).dotProduct(l);
		if (ndotl > 0) {
			Vector l2 = new Vector(0, 0, 0);
			l2.setX(ndotl * light.getIntensity());
			l2.setY(ndotl * light.getIntensity());
			l2.setZ(ndotl * light.getIntensity());
			float r = (float) (s.getDiffuseMaterial()[0] * l2.getX());
			float g = (float) (s.getDiffuseMaterial()[1] * l2.getY());
			float b = (float) (s.getDiffuseMaterial()[2] * l2.getZ());
			float[] result = { r, g, b };
			return result;
		} else {
			return new float[] { 0.0f, 0.0f, 0.0f };
		}
	}

	// calculates the specular color
	public float[] calculateSpecularColor(Surface s, HitResult u, Light light,
			Ray ray) {
		Vector l = light.getlhat().normalize();
		/*
		 * Vector v = new Vector(0,0,0); v.setX(this.camera.getOrigin().getX() -
		 * u.intersectionPoint.getX()); v.setY(this.camera.getOrigin().getY() -
		 * u.intersectionPoint.getY()); v.setZ(this.camera.getOrigin().getZ() -
		 * u.intersectionPoint.getZ()); v = v.normalize();
		 */
		Vector v = ray.d.negateVector().normalize();
		Vector h = v.addVector(l).normalize();
		if (h.magnitude() > .0001) {
			double ndh = s.getSurfaceNormal(u.getIntersectionPoint(), u).dotProduct(h);
			if (ndh > 0) {
				double ndothtothep = Math.pow(ndh, s.getSpecularExponent());
				double amt = ndothtothep * light.getIntensity();
				float r = (float) (s.getSpecularMaterial()[0] * amt);
				float g = (float) (s.getSpecularMaterial()[1] * amt);
				float b = (float) (s.getSpecularMaterial()[2] * amt);
				return new float[] { r, g, b };
			}
		}
		return new float[] { 0, 0, 0 };
	}


	// computes the camera frame
	public void computeCameraFrame() {
		Vector d = vertexArray[camera.getVertex()].getD();
		Vector newZ = new Vector(0, 0, 0);
		newZ = d.negateVector();
		newZ = newZ.normalize();
		camera.setZAxis(newZ);
		Vector newX = d.cross(new Vector(0, 1, 0)).normalize();
		camera.setXAxis(newX);
		Vector newY = newZ.cross(newX);
		newY = newY.normalize();
		camera.setYAxis(newY);
	}

	// converts the pixel to a point in the image plane
	public Vector pixelToImagePoint(int x, int y) {
		Vector result = new Vector(0, 0, 0);
		result.setX((((double) x) / nx) - .5);
		result.setY((((double) -ny) / nx) * ((((double) y) / ny) - .5));
		result.setZ((-Math.sqrt(3) / 2));
		return result;
	}

	// converts a point in camera frame to the world frame
	public Vector cameraToWorldFrame(Vector v) {
		Vector result = new Vector(0, 0, 0);
		result.setX((camera.getXAxis().getX() * v.getX())
				+ (camera.getYAxis().getX() * v.getY())
				+ (camera.getZAxis().getX() * v.getZ()));
		result.setY((camera.getXAxis().getY() * v.getX())
				+ (camera.getYAxis().getY() * v.getY())
				+ (camera.getZAxis().getY() * v.getZ()));
		result.setZ((camera.getXAxis().getZ() * v.getX())
				+ (camera.getYAxis().getZ() * v.getY())
				+ (camera.getZAxis().getZ() * v.getZ()));
		return result;

	}

	// references all the items
	public void referenceItems() {
		for (Light l : lights) {
			Vertex v = vertexArray[l.getVertex()];
			l.setCenter(v.getPt());
			l.setDirection(v.getD());
		}
		for (Surface s : surfaces) {
			Vertex v = vertexArray[s.getVertexIndex()];
			s.setCenter(v.getPt());
			s.setDirection(v.getD());
		}
		camera.setOrigin(vertexArray[camera.getVertex()].getPt());
		computeCameraFrame();
	}

	public static JFrame getFrame() {
		return frame;
	}
	
	// instantiates the world
	public static void createAndShow() {
		World world = new World();
		world.readInput();
		world.referenceItems();
		world.drawBufferedImage();
		World.getFrame().setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				World.createAndShow();
			}
		});
	}
}

class MyFilter extends javax.swing.filechooser.FileFilter {
	  public boolean accept(File file) {
	    String filename = file.getName();
	    return filename.endsWith(".rts");
	  }

	  public String getDescription() {
	    return "*.rts";
	  }
}

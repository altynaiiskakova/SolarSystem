import gdp.stdlib.StdIn;
import gdp.stdlib.StdDraw;


public class SolarSystem {

    public static void main(String[] args) {
        int n = gdp.stdlib.StdIn.readInt();
        double rad = gdp.stdlib.StdIn.readDouble();

        gdp.stdlib.StdDraw.setXscale(-rad, rad);
        gdp.stdlib.StdDraw.setYscale(-rad, rad);

        final double G = 6.67e-11;

        double planet[][] = new double[n][6];
        String[] name = new String[10000];

        for (int i = 0; i < n; i++) {
            planet[i][0] = gdp.stdlib.StdIn.readDouble(); //x-coordinate
            planet[i][1] = gdp.stdlib.StdIn.readDouble(); // y-coordinate
            planet[i][2] = gdp.stdlib.StdIn.readDouble(); //fx
            planet[i][3] = gdp.stdlib.StdIn.readDouble(); //fy
            planet[i][4] = gdp.stdlib.StdIn.readDouble(); // m
            name[i] = gdp.stdlib.StdIn.readString(); //name.jpg
        }

        double[][] r = new double[n][n];
        double[][] f = new double[n][n];
        double[][] fx = new double[n][n];
        double[][] fy = new double[n][n];
        double[][] a = new double[n][2];

        while (true) {
            gdp.stdlib.StdDraw.clear();

            for (int i = 0; i < planet.length; i++) {
                for (int j = 0; j < planet.length; j++) {
                    if (i==j){
                        continue;
                    }
                    r[i][j] = Math.sqrt(Math.pow(planet[i][0] - planet[j][0], 2) + Math.pow(planet[i][1] - planet[j][1], 2)); // r = √delta(x)² + delta(y)²
                }
            }

            for (int i = 0; i < planet.length; i++) {
                for (int j = 0; j < planet.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    f[i][j] = (G * planet[i][4] * planet[j][4]) / Math.pow(r[i][j], 2);
                }
            }

            for (int i = 0; i < planet.length; i++) {
                for (int j = 0; j < planet.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    fx[i][j] = f[i][j] * (planet[j][0] - planet[i][0]) / r[i][j];
                    fy[i][j] = f[i][j] * (planet[j][1] - planet[i][1]) / r[i][j];

                }
            }
            for (int i=0; i<planet.length; i++){
                double x = 0;
                double y = 0;

                for (int j=0; j<planet.length; j++){
                    x+=fx[i][j];
                    y+=fy[i][j];
                }
                a[i][0] = x / planet[i][4];
                a[i][1] = y / planet[i][4];
            }

            for (int i=0; i<planet.length; i++) {
                planet[i][2] += 25000 * a[i][0]; // fx
                planet[i][3] += 25000 * a[i][1]; // fy
            }

            for (int i=0; i<planet.length; i++) {
                planet[i][0] += 25000 * planet[i][2];
                planet[i][1] += 25000 * planet[i][3];
            }

            gdp.stdlib.StdDraw.picture(0, 0, ".../IdeaProjects/SolarSystem/out/production/SolarSystem/starfield_milkway.jpg");

            for (int i=0; i<planet.length; i++) {
                gdp.stdlib.StdDraw.picture(planet[i][0], planet[i][1], name[i]);
            }

            gdp.stdlib.StdDraw.show(10);

        }

    }
}

public class NBody
{
    public static double readRadius(String filename)
    {
        In in = new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String filename)
    {
        In in = new In(filename);
        int planetsNum = in.readInt();
        double secondItemInFile = in.readDouble();
        Planet[] retVal = new Planet[planetsNum];
        for(int i = 0; i < planetsNum; i++)
        {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet p = new Planet(xP, yP, xVel, yVel, mass, img);
            retVal[i] = p;
        }
        return retVal;
    }

    public static void main(String[] args)
    {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double univerRadius = readRadius(fileName);
        Planet[] planets = readPlanets(fileName);

        StdDraw.setXscale(-univerRadius, univerRadius);
        StdDraw.setYscale(-univerRadius, univerRadius);

        StdDraw.enableDoubleBuffering();

        double t = 0;

        while(t <= T)
        {
            double xForces[] = new double[planets.length];
            double yForces[] = new double[planets.length];
            
            for(int j = 0; j < planets.length; j++)
            {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }

            for(int j = 0; j < planets.length; j++)
            {
                planets[j].update(dt, xForces[j], yForces[j]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(int j = 0; j < planets.length; j++)
            {
                Planet p = planets[j];
                p.draw();
            }

            StdDraw.show();

            StdDraw.pause(10);

            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", univerRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }  
        
    }
}


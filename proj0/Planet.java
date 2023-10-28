public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p)
	{
		return Math.sqrt((this.xxPos - p.xxPos)*(this.xxPos - p.xxPos) + (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p)
	{
		double r = this.calcDistance(p);
		double ret = (G*this.mass*p.mass)/(r*r);
		return ret;
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - xxPos;
		double r = calcDistance(p);
		return calcForceExertedBy(p) * dx / r;
	}

	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - yyPos;
		double r = calcDistance(p);
		return calcForceExertedBy(p) * dy / r;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets)
	{
		double totalForceX = 0;
		for(int i = 0; i < allPlanets.length; i++)
		{
			if(allPlanets[i] == this)
			{
				continue;
			}
			else
			{
				totalForceX += this.calcForceExertedByX(allPlanets[i]);
			}
		}
		return totalForceX;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets)
	{
		double totalForceY = 0;
		for(int i = 0; i < allPlanets.length; i++)
		{
			if(allPlanets[i] == this)
			{
				continue;
			}
			else
			{
				totalForceY += this.calcForceExertedByY(allPlanets[i]);
			}
		}
		return totalForceY;
	}

	public void update(double dt, double fX, double fY)
	{
		double aX = fX/mass;
		double aY = fY/mass;
		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}

	public void draw()
	{
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
}
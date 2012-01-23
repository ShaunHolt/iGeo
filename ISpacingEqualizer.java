/*---

    iGeo - http://igeo.jp

    Copyright (c) 2002-2012 Satoru Sugihara

    This file is part of iGeo.

    iGeo is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, version 3.

    iGeo is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with iGeo.  If not, see <http://www.gnu.org/licenses/>.

---*/

package igeo;

import java.util.ArrayList;

/**
   Tension force to equalize spacing between pt1- pt2 and pt2 - pt3.
   
   @author Satoru Sugihara
   @version 0.7.0.0;
*/
public class ISpacingEqualizer extends IDynamicsBase implements ITensionI{
    //public static double defaultTension=1.0;
    
    public IParticleI pt1, pt2, pt3;
    /** tension is a coefficient to convert distance of two points to amount of force. */
    public double tension = defaultTension;
    public boolean constantTension=false;
    
    public ISpacingEqualizer(IParticleI p1, IParticleI p2, IParticleI p3, IObject parent){
	super(parent);
	pt1=p1; pt2=p2; pt3=p3;
    }
    
    public ISpacingEqualizer(IParticle p1, IParticle p2, IParticle p3, IObject parent){
	super(parent);
	pt1=p1; pt2=p2; pt3=p3;
    }
    
    public ISpacingEqualizer(IVec p1, IVec p2, IVec p3, IObject parent){
	super(parent);
	pt1 = new IParticle(p1); pt2=new IParticle(p2); pt3 = new IParticle(p3);
    }
    
    public ISpacingEqualizer(IVecI p1, IVecI p2, IVecI p3, IObject parent){
	super(parent);
	pt1 = new IParticle(p1.get()); pt2=new IParticle(p2.get()); pt3=new IParticle(p3.get());
    }
    
    public ISpacingEqualizer(IParticleI p1, IParticleI p2, IParticleI p3){
	super();
	pt1=p1; pt2=p2; pt3=p3;

    }
    
    public ISpacingEqualizer(IParticle p1, IParticle p2, IParticle p3){
	super();
	pt1=p1; pt2=p2; pt3=p3;
	//new ICurve(pt1.pos(), pt3.pos()).clr(0,1.0,0); //
    }
    
    public ISpacingEqualizer(IVec p1, IVec p2, IVec p3){
	super();
	pt1 = new IParticle(p1); pt2=new IParticle(p2); pt3=new IParticle(p3);
    }
    
    public ISpacingEqualizer(IVecI p1, IVecI p2, IVecI p3){
	super();
	pt1 = new IParticle(p1.get()); pt2=new IParticle(p2.get()); pt3=new IParticle(p3.get());
    }
    
    public double tension(){ return tension; }
    public ISpacingEqualizer tension(double tension){ this.tension = tension; return this; }
    
    public boolean constant(){ return constantTension; }
    public ISpacingEqualizer constant(boolean cnst){ constantTension = cnst; return this; }

    public IParticleI pt(int i){
	if(i==0) return pt1; if(i==1) return pt2; return pt3;
    }
    
    public ISpacingEqualizer parent(IObject par){ super.parent(par); return this; }
    public ISpacingEqualizer target(IObject targetObj){ super.target(targetObj); return this; }
    public ISpacingEqualizer removeTarget(int i){ super.removeTarget(i); return this; }
    public ISpacingEqualizer removeTarget(IObject obj){ super.removeTarget(obj); return this; }
    
    synchronized public void interact(ArrayList<IDynamics> dynamics){
	IVec p1 = pt1.pos(), p2 = pt2.pos(), p3 = pt3.pos();
	IVec dif = p3.diff(p1);

	
	dif.div(2).add(p1).sub(p2);
	if(!constantTension){ dif.mul(tension); }
	else if(dif.len2()>0){ dif.len(tension); }
	// adding force to the middle point
	pt2.addForce(dif);
	// adding force to the end points, half in the opposite direction.
	dif.mul(-0.5);
	pt1.addForce(dif);
	pt3.addForce(dif);
	
	/*
	double len2 = dif.len2();
	if(len2>0){
	    dif.mul(p2.diff(p1).dot(dif)/len2).add(p1).sub(p2);
	    if(!constantTension){ dif.mul(tension); }
	    	    
	    // adding force to the middle point
	    pt2.addForce(dif);
	    // adding force to the end points, half in the opposite direction.
	    dif.mul(-0.5);
	    pt1.addForce(dif);
	    pt3.addForce(dif);
	}
	*/
    }
    
    synchronized public void update(){ updateTarget(); }
    
}
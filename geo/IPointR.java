/*---

    iGeo - http://igeo.jp

    Copyright (c) 2002-2011 Satoru Sugihara

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

package igeo.geo;

import java.awt.Color;

import igeo.core.*;
import igeo.gui.*;

/**
   Reference class of a point object to contain any instance of IVecI.
   
   @author Satoru Sugihara
   @version 0.7.0.0;
*/
public class IPointR extends IObject implements IVecI{
    public IVecI pos;
    
    public IPointR(){ pos = new IVec(); initPoint(null); }
    public IPointR(IVecI v){ pos = v; initPoint(null); }
    public IPointR(double x, double y, double z){ pos = new IVec(x,y,z); initPoint(null); }
    
    public IPointR(IServerI s){ super(s); pos = new IVec(0,0,0); initPoint(s); }
    public IPointR(IServerI s, IVecI v){ super(s); pos = v; initPoint(s); }
    public IPointR(IServerI s, double x, double y, double z){
	super(s); pos = new IVec(x,y,z); initPoint(s); 
    }
    
    public IPointR(IPointR p){
	super(p);
	pos = p.pos.dup();
	initPoint(p.server);
	setColor(p.getColor());
    }
    
    public IPointR(IServerI s, IPointR p){
	super(s,p);
	pos = p.pos.dup();
	initPoint(s);
	setColor(p.getColor());
    }
    
    protected void initPoint(IServerI s){
	// costly to use instanceof?
	if(pos instanceof IVec) parameter = (IVec)pos;
	else if(pos instanceof IVecR) parameter = (IVecR)pos;
	else if(pos instanceof IVec4) parameter = (IVec4)pos;
	else if(pos instanceof IVec4R) parameter = (IVec4R)pos;
	
	//addGraphic(new IPointGraphic(this));
	if(graphics==null) initGraphic(s); // not init when using copy constructor
    }
    
    public IGraphicObject createGraphic(IGraphicMode m){
	return new IPointGraphic(this);
    }
    
    public double x(){ return pos.x(); }
    public double y(){ return pos.y(); }
    public double z(){ return pos.z(); }
    public IVec get(){ return pos.get(); }
    
    public IPointR dup(){ return new IPointR(this); }
    
    public IVec2I to2d(){ return pos.to2d(); }
    public IVec4I to4d(){ return pos.to4d(); }
    public IVec4I to4d(double w){ return pos.to4d(w); }
    public IVec4I to4d(IDoubleI w){ return pos.to4d(w); }
    
    public IDoubleI getX(){ return pos.getX(); }
    public IDoubleI getY(){ return pos.getY(); }
    public IDoubleI getZ(){ return pos.getZ(); }
    
    public IPointR set(IVecI v){ pos.set(v); return this; }
    public IPointR set(double x, double y, double z){ pos.set(x,y,z); return this;}
    public IPointR set(IDoubleI x, IDoubleI y, IDoubleI z){ pos.set(x,y,z); return this; }
    
    public IPointR add(double x, double y, double z){ pos.add(x,y,z); return this; }
    public IPointR add(IDoubleI x, IDoubleI y, IDoubleI z){ pos.add(x,y,z); return this; }    
    public IPointR add(IVecI v){ pos.add(v); return this; }
    public IPointR sub(double x, double y, double z){ pos.sub(x,y,z); return this; }
    public IPointR sub(IDoubleI x, IDoubleI y, IDoubleI z){ pos.sub(x,y,z); return this; }
    public IPointR sub(IVecI v){ pos.sub(v); return this; }
    public IPointR mul(IDoubleI v){ pos.mul(v); return this; }
    public IPointR mul(double v){ pos.mul(v); return this; }
    public IPointR div(IDoubleI v){ pos.div(v); return this; }
    public IPointR div(double v){ pos.div(v); return this; }
    public IPointR neg(){ pos.neg(); return this; }
    public IPointR rev(){ return neg(); }
    
    public IPointR add(IVecI v, double f){ pos.add(v,f); return this; }
    public IPointR add(IVecI v, IDoubleI f){ pos.add(v,f); return this; }
    
    
    public double dot(IVecI v){ return pos.dot(v); }
    public double dot(ISwitchE e, IVecI v){ return dot(v); }
    public IDoubleI dot(ISwitchR r, IVecI v){ return pos.dot(r,v); }
    
    public IVecI cross(IVecI v){ return pos.cross(v); }
    
    public double len(){ return pos.len(); }
    public double len(ISwitchE e){ return len(); }
    public IDoubleI len(ISwitchR r){ return pos.len(r); }
    
    public double len2(){ return pos.len2(); }
    public double len2(ISwitchE e){ return len2(); }
    public IDoubleI len2(ISwitchR r){ return pos.len2(r); }
    
    public IPointR len(IDoubleI l){ pos.len(l); return this; }
    public IPointR len(double l){ pos.len(l); return this; }
    
    public IPointR unit(){ pos.unit(); return this; }
    
    public double dist(IVecI v){ return pos.dist(v); }
    public double dist(ISwitchE e, IVecI v){ return dist(v); }
    public IDoubleI dist(ISwitchR r, IVecI v){ return pos.dist(r,v); }
    
    public double dist2(IVecI v){ return pos.dist2(v); }
    public double dist2(ISwitchE e, IVecI v){ return dist2(v); }
    public IDoubleI dist2(ISwitchR r, IVecI v){ return pos.dist2(r,v); }
    
    public boolean eq(IVecI v){ return pos.eq(v); }
    public boolean eq(ISwitchE e, IVecI v){ return eq(v); }
    public IBoolI eq(ISwitchR r, IVecI v){ return pos.eq(r,v); }
    
    public boolean eq(IVecI v, double resolution){ return pos.eq(v,resolution); }
    public boolean eq(ISwitchE e, IVecI v, double resolution){ return eq(v,resolution); }
    public IBoolI eq(ISwitchR r, IVecI v, IDoubleI resolution){ return pos.eq(r,v,resolution); }
    
    public boolean eqX(IVecI v){ return pos.eqX(v); }
    public boolean eqY(IVecI v){ return pos.eqY(v); }
    public boolean eqZ(IVecI v){ return pos.eqZ(v); }
    public boolean eqX(ISwitchE e, IVecI v){ return eqX(v); }
    public boolean eqY(ISwitchE e, IVecI v){ return eqY(v); }
    public boolean eqZ(ISwitchE e, IVecI v){ return eqZ(v); }
    public IBoolI eqX(ISwitchR r, IVecI v){ return pos.eqX(r,v); }
    public IBoolI eqY(ISwitchR r, IVecI v){ return pos.eqY(r,v); }
    public IBoolI eqZ(ISwitchR r, IVecI v){ return pos.eqZ(r,v); }
    
    public boolean eqX(IVecI v, double resolution){ return pos.eqX(v,resolution); }
    public boolean eqY(IVecI v, double resolution){ return pos.eqY(v,resolution); }
    public boolean eqZ(IVecI v, double resolution){ return pos.eqZ(v,resolution); }
    public boolean eqX(ISwitchE e, IVecI v, double resolution){ return eqX(v,resolution); }
    public boolean eqY(ISwitchE e, IVecI v, double resolution){ return eqY(v,resolution); }
    public boolean eqZ(ISwitchE e, IVecI v, double resolution){ return eqZ(v,resolution); }
    public IBoolI eqX(ISwitchR r, IVecI v, IDoubleI resolution){ return pos.eqX(r,v,resolution); }
    public IBoolI eqY(ISwitchR r, IVecI v, IDoubleI resolution){ return pos.eqY(r,v,resolution); }
    public IBoolI eqZ(ISwitchR r, IVecI v, IDoubleI resolution){ return pos.eqZ(r,v,resolution); }
    
    
    public double angle(IVecI v){ return pos.angle(v); }
    public double angle(ISwitchE e, IVecI v){ return angle(v); }
    public IDoubleI angle(ISwitchR r, IVecI v){ return pos.angle(r,v); }
    
    public double angle(IVecI v, IVecI axis){ return pos.angle(v,axis); }
    public double angle(ISwitchE e, IVecI v, IVecI axis){ return angle(v,axis); }
    public IDoubleI angle(ISwitchR r, IVecI v, IVecI axis){ return pos.angle(r,v,axis); }
    
    public IPointR rot(IVecI axis, IDoubleI angle){ pos.rot(axis,angle); return this; }
    public IPointR rot(IVecI axis, double angle){ pos.rot(axis,angle); return this; }
    
    public IPointR rot(IVecI center, IVecI axis, double angle){
	pos.rot(center, axis,angle); return this;
    }
    public IPointR rot(IVecI center, IVecI axis, IDoubleI angle){
	pos.rot(center, axis,angle); return this;
    }
        
    /**
       rotate to destination direction vector
    */
    public IPointR rot(IVecI axis, IVecI destDir){ pos.rot(axis,destDir); return this; }
    /**
       rotate to destination point location
    */
    public IPointR rot(IVecI center, IVecI axis, IVecI destPt){
	pos.rot(center,axis,destPt); return this;
    }
    
    /**
       same with mul
    */
    public IPointR scale(IDoubleI f){ pos.scale(f); return this; }
    public IPointR scale(double f){ pos.scale(f); return this; }
    
    public IPointR scale(IVecI center, IDoubleI f){ pos.scale(center,f); return this; }
    public IPointR scale(IVecI center, double f){ pos.scale(center,f); return this; }
    
    /**
       mirror/reflect/flip 3 dimensionally to the other side of the plane
    */
    public IPointR mirror(IVecI planeDir){ pos.mirror(planeDir); return this; }
    public IPointR mirror(IVecI center, IVecI planeDir){
	pos.mirror(center,planeDir); return this;
    }
    
    public IPointR transform(IMatrix3I mat){ pos.transform(mat); return this; }
    public IPointR transform(IMatrix4I mat){ pos.transform(mat); return this; }
    public IPointR transform(IVecI xvec, IVecI yvec, IVecI zvec){
	pos.transform(xvec,yvec,zvec); return this;
    }
    public IPointR transform(IVecI xvec, IVecI yvec, IVecI zvec, IVecI translate){
	pos.transform(xvec,yvec,zvec,translate); return this;
    }
    
    // methods creating new instance // returns IPoint?, not IVec?
    public IPointR diff(IVecI v){ return dup().sub(v); }
    public IPointR mid(IVecI v){ return dup().add(v).div(2); }
    public IPointR sum(IVecI v){ return dup().add(v); }
    public IPointR sum(IVecI... v){
	IPointR ret = this.dup();
        for(IVecI vi: v) ret.add(vi);
        return ret;
    }
    
    public IPointR bisect(IVecI v){
	return dup().unit().add(v.dup().unit());
    }
    
    /**
       weighted sum
    */
    public IPointR sum(IVecI v2, double w1, double w2){
	return dup().mul(w1).add(v2,w2);
    }
    public IPointR sum(IVecI v2, double w2){
	return dup().mul(1.0-w2).add(v2,w2);
    }
    
    public IPointR sum(IVecI v2, IDoubleI w1, IDoubleI w2){
	return dup().mul(w1).add(v2,w2);
    }
    public IPointR sum(IVecI v2, IDoubleI w2){
	return dup().mul(new IDouble(1.0).sub(w2)).add(v2,w2);
    }
    
    
    /**
       set size of dot in graphic 
    */
    public IPointR setSize(double sz){ return size(sz); }
    public IPointR size(double sz){
	for(int i=0; graphics!=null && i<graphics.size(); i++)
	    if(graphics.get(i) instanceof IPointGraphic)
		((IPointGraphic)graphics.get(i)).size(sz);
	return this;
    }
    
    public double getSize(){ return size(); }
    public double size(){
	if(graphics==null){
	    IOut.err("no graphics is set"); //
	    return -1;
	}
	for(int i=0; graphics!=null && i<graphics.size(); i++)
	    if(graphics.get(i) instanceof IPointGraphic)
		return ((IPointGraphic)graphics.get(i)).size();
	return -1;
    }
    
    public IPointR name(String nm){ super.name(nm); return this; }
    public IPointR layer(ILayer l){ super.layer(l); return this; }
    
    public IPointR hide(){ super.hide(); return this; }
    public IPointR show(){ super.show(); return this; }
    
    public IPointR clr(Color c){ super.clr(c); return this; }
    public IPointR clr(int gray){ super.clr(gray); return this; }
    public IPointR clr(float fgray){ super.clr(fgray); return this; }
    public IPointR clr(double dgray){ super.clr(dgray); return this; }
    public IPointR clr(int gray, int alpha){ super.clr(gray,alpha); return this; }
    public IPointR clr(float fgray, float falpha){ super.clr(fgray,falpha); return this; }
    public IPointR clr(double dgray, double dalpha){ super.clr(dgray,dalpha); return this; }
    public IPointR clr(int r, int g, int b){ super.clr(r,g,b); return this; }
    public IPointR clr(float fr, float fg, float fb){ super.clr(fr,fg,fb); return this; }
    public IPointR clr(double dr, double dg, double db){ super.clr(dr,dg,db); return this; }
    public IPointR clr(int r, int g, int b, int a){ super.clr(r,g,b,a); return this; }
    public IPointR clr(float fr, float fg, float fb, float fa){ super.clr(fr,fg,fb,fa); return this; }
    public IPointR clr(double dr, double dg, double db, double da){ super.clr(dr,dg,db,da); return this; }
    public IPointR hsb(float h, float s, float b, float a){ super.hsb(h,s,b,a); return this; }
    public IPointR hsb(double h, double s, double b, double a){ super.hsb(h,s,b,a); return this; }
    public IPointR hsb(float h, float s, float b){ super.hsb(h,s,b); return this; }
    public IPointR hsb(double h, double s, double b){ super.hsb(h,s,b); return this; }
    
    public IPointR setColor(Color c){ super.setColor(c); return this; }
    public IPointR setColor(int gray){ super.setColor(gray); return this; }
    public IPointR setColor(float fgray){ super.setColor(fgray); return this; }
    public IPointR setColor(double dgray){ super.setColor(dgray); return this; }
    public IPointR setColor(int gray, int alpha){ super.setColor(gray,alpha); return this; }
    public IPointR setColor(float fgray, float falpha){ super.setColor(fgray,falpha); return this; }
    public IPointR setColor(double dgray, double dalpha){ super.setColor(dgray,dalpha); return this; }
    public IPointR setColor(int r, int g, int b){ super.setColor(r,g,b); return this; }
    public IPointR setColor(float fr, float fg, float fb){ super.setColor(fr,fg,fb); return this; }
    public IPointR setColor(double dr, double dg, double db){ super.setColor(dr,dg,db); return this; }
    public IPointR setColor(int r, int g, int b, int a){ super.setColor(r,g,b,a); return this; }
    public IPointR setColor(float fr, float fg, float fb, float fa){ super.setColor(fr,fg,fb,fa); return this; }
    public IPointR setColor(double dr, double dg, double db, double da){ super.setColor(dr,dg,db,da); return this; }
    public IPointR setHSBColor(float h, float s, float b, float a){ super.setHSBColor(h,s,b,a); return this; }
    public IPointR setHSBColor(double h, double s, double b, double a){ super.setHSBColor(h,s,b,a); return this; }
    public IPointR setHSBColor(float h, float s, float b){ super.setHSBColor(h,s,b); return this; }
    public IPointR setHSBColor(double h, double s, double b){ super.setHSBColor(h,s,b); return this; }
    
}

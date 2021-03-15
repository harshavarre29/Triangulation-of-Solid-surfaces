
public class Shape implements ShapeInterface {
	 DynamicArray<pi> pointarray=new DynamicArray<>();
	 DynamicArray<EI> edgearr=new DynamicArray<>();
	 DynamicArray<PointInterface> centeroidarr=new DynamicArray<>();
	 DynamicArray<TI> triarr=new DynamicArray<>();
	 public boolean ADD_TRIANGLE(float [] triangle_coord){
		 float a=((triangle_coord[0]-triangle_coord[3])*(triangle_coord[1]-triangle_coord[7]))-((triangle_coord[0]-triangle_coord[6])*(triangle_coord[1]-triangle_coord[4]));
		 float c=((triangle_coord[1]-triangle_coord[4])*(triangle_coord[2]-triangle_coord[8]))-((triangle_coord[1]-triangle_coord[7])*(triangle_coord[2]-triangle_coord[5]));
		 float b=((triangle_coord[0]-triangle_coord[3])*(triangle_coord[2]-triangle_coord[8]))-((triangle_coord[0]-triangle_coord[6])*(triangle_coord[2]-triangle_coord[5]));
		 boolean bool=false;
		 if(a!=0 || b!=0 || c!=0) {
			 bool=true;
			 pi p1=new pi(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
			 pi p2=new pi(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
			 pi p3=new pi(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
			 EI e1=new EI(p1,p2);
			 EI e2=new EI(p2,p3);
			 EI e3=new EI(p3,p1);
			 TI triangle=new TI(p1,p2,p3);
			 boolean bo=false;
			 for(int i=0;i<triarr.currentSize;i++) {
				 if(triarr.get(i).isadj(triangle)==3) {
					 bo=true;
					 break;
				 }
			 }
			 if(!bo) {
				 for(int i=0;i<triarr.currentSize;i++) {
					 if(triarr.get(i).isadj(triangle)==2) {
						triarr.get(i).adjtri.insert(triangle);
						triangle.adjtri.insert(triarr.get(i));
					 }
				 }
				 triarr.insert(triangle);
				 int aa=0;
				 for(int i=0;i<pointarray.currentSize;i++) {
					if(pointarray.get(i).equals(p1)) {
						aa=1;
						pointarray.get(i).triarray.insert(triangle);
						pointarray.get(i).adjpoint.insert(p2);
						pointarray.get(i).adjpoint.insert(p3);
						break;
					}	
				}
				if(aa==0) {
					p1.triarray.insert(triangle);
					p1.adjpoint.insert(p2);
					p1.adjpoint.insert(p3);
					pointarray.insert(p1);
				}
				aa=0;
				for(int i=0;i<pointarray.currentSize;i++) {
					if(pointarray.get(i).equals(p2)) {
						aa=1;
						pointarray.get(i).triarray.insert(triangle);
						pointarray.get(i).adjpoint.insert(p1);
						pointarray.get(i).adjpoint.insert(p3);
						break;
					}	
				}
				if(aa==0) {
					p2.triarray.insert(triangle);
					p2.adjpoint.insert(p1);
					p2.adjpoint.insert(p3);
					pointarray.insert(p2);
				}
				aa=0;
				for(int i=0;i<pointarray.currentSize;i++) {
					if(pointarray.get(i).equals(p3)) {
						aa=1;
						pointarray.get(i).triarray.insert(triangle);
						pointarray.get(i).adjpoint.insert(p2);
						pointarray.get(i).adjpoint.insert(p1);
						break;
					}	
				}
				if(aa==0) {
					p3.triarray.insert(triangle);
					p3.adjpoint.insert(p2);
					p3.adjpoint.insert(p1);
					pointarray.insert(p3);
				}
				pi centeroid=new pi((p3.getX()+p2.getX()+p1.getX())/3,(p3.getY()+p2.getY()+p1.getY())/3,(p3.getZ()+p2.getZ()+p1.getZ())/3);
				centeroidarr.add(centeroid);
				aa=0;
				for(int i=0;i<edgearr.currentSize;i++) {
					if(edgearr.get(i).compareTo(e3)==0) {
						aa=1;
						edgearr.get(i).adjp.insert(p2);
						break;
					}
				}
				if(aa==0) {
					e3.adjp.insert(p2);
					edgearr.insert(e3);
				}
				aa=0;
				for(int i=0;i<edgearr.currentSize;i++) {
					if(edgearr.get(i).compareTo(e2)==0) {
						aa=1;
						edgearr.get(i).adjp.insert(p1);
						break;
					}
				}
				if(aa==0) {
					e2.adjp.insert(p1);
					edgearr.insert(e2);
				}
				aa=0;
				for(int i=0;i<edgearr.currentSize;i++) {
					if(edgearr.get(i).compareTo(e1)==0) {
						aa=1;
						edgearr.get(i).adjp.insert(p3);
						break;
					}
				}
				if(aa==0) {
					e1.adjp.insert(p3);
					edgearr.insert(e1);
				}
			 }
		 }
		 else {
			 bool=false;
		 }
		 return bool;
	 }
	 
	 public int TYPE_MESH(){
		 int impmesh=0;
		 for(int i=0;i<edgearr.currentSize;i++) {
			 if(edgearr.get(i).adjp.currentSize==1)
				 impmesh++;
			 else if(edgearr.get(i).adjp.currentSize>2)
				 return 3;
		 }
		 if(impmesh==0)
			 return 1;
		 return 2;
	 }
	 
	 public EdgeInterface [] BOUNDARY_EDGES() {
		 DynamicArray<EI> bedges=new DynamicArray<>();
		 for(int i=0;i<edgearr.currentSize;i++) {
			 if(edgearr.get(i).adjp.currentSize==1) {
				 bedges.add(edgearr.get(i));
			 }
		 }
		 //sort and add to be and return.
		 for(int i=0;i<bedges.currentSize;i++) {
			 for (int j = 0; j < bedges.currentSize-i-1; j++) {
	             if(bedges.get(j).length-bedges.get(j+1).length>0.001) {
	            	 EI temp=bedges.get(j);
	            	 bedges.append(j, bedges.get(j+1));
	            	 bedges.append(j+1, temp);
				 }
			 }
		 }
		 int n=bedges.currentSize;
		 EdgeInterface[] e=new EdgeInterface[n];
		 for(int i=n-1;i>=0;i--) {
			 e[n-1-i]=bedges.get(n-i-1);
		 }
		 if(n==0) {
			 return null;
		 }
		 return e;
	 }
	 
	 public int COUNT_CONNECTED_COMPONENTS() {
		 int a=0;
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(!triarr.get(i).isvisited) {
				 a++;
				 explore(triarr.get(i));
				 triarr.get(i).isvisited=true;
			 }
		 }
		 for(int i=0;i<triarr.currentSize;i++) {
			 triarr.get(i).isvisited=false;
		 }
		 return a;
	 }
	 
	 public void explore(TI t) {
		 if(!t.isvisited)
		 t.isvisited=true;
		 for(int j=0;j<t.adjtri.currentSize;j++) {
			 if(!t.adjtri.get(j).isvisited) {
			 this.explore(t.adjtri.get(j));
			 }
		 }
	 }

	 public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord) {
		 int a=0;
		 DynamicArray<TriangleInterface> tarr=new DynamicArray<>();
		 pi p1=new pi(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		 pi p2=new pi(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		 pi p3=new pi(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		 TI triangle=new TI(p1,p2,p3);
		 boolean b=false;
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).compareTo(triangle)==0) {
				 b=true;
			 }
		 }
		 if(!b)
			 return null;
		 for(int i=0;i<triarr.currentSize;i++) {
			a=0;
			if(p1.compareTo(triarr.get(i).give(0))==0 || p1.compareTo(triarr.get(i).give(1))==0 || p1.compareTo(triarr.get(i).give(2))==0) {
				a++;
			}
			if(p2.compareTo(triarr.get(i).give(0))==0 || p2.compareTo(triarr.get(i).give(1))==0 || p2.compareTo(triarr.get(i).give(2))==0) {
				a++;
			}
			if(p3.compareTo(triarr.get(i).give(0))==0 || p3.compareTo(triarr.get(i).give(1))==0 || p3.compareTo(triarr.get(i).give(2))==0) {
				a++;
			}
			if(a==2) {
				tarr.add((TriangleInterface)triarr.get(i));
			}
		 }
		 TriangleInterface [] ta=new TriangleInterface[tarr.currentSize];
		 for(int i=0;i<tarr.currentSize;i++) {
			 ta[i]=tarr.get(i);
		 }
		 return ta;
	 }
	 @Override
	 public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
		 pi p1=new pi(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		 pi p2=new pi(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		 pi p3=new pi(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		 TI triangle=new TI(p1,p2,p3);
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).compareTo(triangle)==0) {
				 EdgeInterface[] e=new EdgeInterface[3];
				 e[0]=new EI(p1,p2);
				 e[1]=new EI(p2,p3);
				 e[2]=new EI(p3,p1);
				 return e;
			 }
		 }
		 return null;
	 }
	 
	 public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
		 pi p1=new pi(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		 pi p2=new pi(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		 pi p3=new pi(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		 TI triangle=new TI(p1,p2,p3);
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).compareTo(triangle)==0) {
				 PointInterface[] p=new PointInterface[3];
				 p[0]=triarr.get(i).give(0);
				 p[1]=triarr.get(i).give(1);
				 p[2]=triarr.get(i).give(2);
				 return p;
			 }
		 }
		 return null;
	 }
	 
	 public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
		 pi p1=new pi(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		 pi p2=new pi(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		 pi p3=new pi(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		 TI t1=new TI(p1,p2,p3);
		 int size=0;
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).compareTo(t1)==0) {
				 explore(triarr.get(i));
				 triarr.get(i).isvisited=true;
				 break;
			 }
		 }
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).isvisited) {
				 size++; 
			 }
		 }
		 TriangleInterface[] tarr=new TriangleInterface[size];
		 size=0;
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).isvisited) {
				 triarr.get(i).isvisited=false;
				 tarr[size]=triarr.get(i);
				 size++;
			 }
		 }
		 return tarr;
	 }
	 
	 public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates) {
		 boolean b=false;
		 pi p1=new pi(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		 for(int i=0;i<pointarray.currentSize;i++) {
			 if(pointarray.get(i).compareTo(p1)==0)
				 b=true;
		 }
		 if(!b)
		 return null;
		 DynamicArray<TI> tarr=new DynamicArray<>();
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(p1.compareTo(triarr.get(i).give(0))==0 || p1.compareTo(triarr.get(i).give(1))==0 || p1.compareTo(triarr.get(i).give(2))==0) {
				 tarr.add(triarr.get(i));
			 }
		 }
		 TriangleInterface [] ta=new TriangleInterface[tarr.currentSize];
		 for(int i=0;i<ta.length;i++) {
			 ta[i]=tarr.get(i);
		 }
		 return ta;
	 }
	 
	 public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates) {
		 pi p1=new pi(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		 for(int i=0;i<pointarray.currentSize;i++) {
			 if(pointarray.get(i).compareTo(p1)==0) {
				 PointInterface [] pa=new PointInterface[pointarray.get(i).adjpoint.currentSize];
				 for(int j=0;j<pa.length;j++) {
					 pa[j]=pointarray.get(i).adjpoint.get(j);
				 }
				 return pa;
			 }
		 }
		 return null;
	 }
	 
	 public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates) {
		 pi p1=new pi(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		 for(int i=0;i<pointarray.currentSize;i++) {
			 if(pointarray.get(i).compareTo(p1)==0) {
				 EdgeInterface [] pa=new EdgeInterface[pointarray.get(i).adjpoint.currentSize];
				 for(int j=0;j<pa.length;j++) {
					 EI e=new EI(p1,pointarray.get(i).adjpoint.get(j));
					 pa[j]=e;
				 }
				 return pa;
			 }
		 }
		 return null;
	 }
	 
	 public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates) {
		 DynamicArray<TI> tarr=new DynamicArray<>();
		 pi p1=new pi(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		 for(int i=0;i<pointarray.currentSize;i++) {
			 if(pointarray.get(i).compareTo(p1)==0) {
				 for(int j=0;j<triarr.currentSize;j++) {
					 if(triarr.get(j).give(0).compareTo(p1)==0 || triarr.get(j).give(1).compareTo(p1)==0 || triarr.get(j).give(2).compareTo(p1)==0) {
						 tarr.add(triarr.get(j));
					 }
				 }
				 TriangleInterface [] ta=new TriangleInterface[tarr.currentSize];
				 for(int l=0;l<ta.length;l++) {
					 ta[l]=tarr.get(l);
				 }
				 return ta;
			 }
		 }
		 return null;
	 }
	 
	 public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2) {
		 pi p1=new pi(triangle_coord_1[0],triangle_coord_1[1],triangle_coord_1[2]);
		 pi p2=new pi(triangle_coord_1[3],triangle_coord_1[4],triangle_coord_1[5]);
		 pi p3=new pi(triangle_coord_1[6],triangle_coord_1[7],triangle_coord_1[8]);
		 pi p4=new pi(triangle_coord_2[0],triangle_coord_2[1],triangle_coord_2[2]);
		 pi p5=new pi(triangle_coord_2[3],triangle_coord_2[4],triangle_coord_2[5]);
		 pi p6=new pi(triangle_coord_2[6],triangle_coord_2[7],triangle_coord_2[8]);
		 TI t1=new TI(p1,p2,p3);
		 TI t2=new TI(p4,p5,p6);
		 boolean b=false;
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).compareTo(t1)==0) {
				 explore(triarr.get(i));
				 break;
			 }
		 }
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).compareTo(t2)==0 && triarr.get(i).isvisited) {
				 b= true;
				 break;
			 }
		 }
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(triarr.get(i).isvisited) {
				 triarr.get(i).isvisited=false;
			 }
		 }
		 return b;
	 }
	 
	 public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates) {
		 pi p1=new pi(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2]);
		 pi p2=new pi(edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
		 EI e1=new EI(p1,p2);
		 for(int i=0;i<edgearr.currentSize;i++) {
			 if(e1.compareTo(edgearr.get(i))==0) {
				 TriangleInterface [] ta=new TriangleInterface[edgearr.get(i).adjp.currentSize];
				 for(int j=0;j<ta.length;j++) {
					 TI t=new TI(p1,p2,edgearr.get(i).adjp.get(j));
					 ta[j]=t;
				 }
				 return ta;
			 }
		 }
		 return null;
	 }
	 
	 public PointInterface [] CENTROID () {
		 DynamicArray<pi> point=new DynamicArray<>();
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(!triarr.get(i).isvisited) {
				 DynamicArray<pi>PointGetter=new DynamicArray<>();
				 PointGetter.insert(triarr.get(i).give(0));
				 PointGetter.insert(triarr.get(i).give(1));
				 PointGetter.insert(triarr.get(i).give(2));
				 triarr.get(i).isvisited=true;
				 DFS(triarr.get(i),PointGetter);
				 int n=PointGetter.currentSize;
		
				 float[]X=new float[3];
				 for(int j=0;j<n;j++)
				 {
					 X[0]+=PointGetter.get(j).getX();
					 X[1]+=PointGetter.get(j).getY();
					 X[2]+=PointGetter.get(j).getZ();
					 PointGetter.get(j).used=false;
				 }
				 pi pp=(new pi(X[0]/n,X[1]/n,X[2]/n)) ;
				 pp.no=n;
				 point.add(pp);
			 }
		 }
		 int n=point.currentSize;
		 for(int i=0;i<n;i++) {
			 for(int j=0;j<n-i-1;j++) {
				 if(point.get(j).no>point.get(j+1).no) {
					 pi temp=point.get(j);
					 point.append(j,point.get(j+1));
					 point.append(j+1, temp);
				 }
			 }
		 }
		 PointInterface[] p=new PointInterface[n];
		 for(int i=0;i<n;i++) {
			 p[i]=point.get(i);
		 }
		 for(int i=0;i<pointarray.currentSize;i++) {
			 pointarray.get(i).used=false;
		 }
		 for(int i=0;i<triarr.currentSize;i++) {
			 triarr.get(i).isvisited=false;
		 }
		 return p;
	 }
	 
	 public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){		 
		 pi p1=new pi(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		 for(int i=0;i<pointarray.currentSize;i++) {
			 if(pointarray.get(i).compareTo(p1)==0) {
				 DynamicArray<pi>PointGetter=new DynamicArray<>();
				 p1.used=true;
				 PointGetter.insert(pointarray.get(i).triarray.get(0).give(0));
				 PointGetter.insert(pointarray.get(i).triarray.get(0).give(1));
				 PointGetter.insert(pointarray.get(i).triarray.get(0).give(2));
				 DFS(pointarray.get(i).triarray.get(0),PointGetter);
				 pointarray.get(i).triarray.get(0).isvisited=true;
				 int n=PointGetter.currentSize;
				 float[]X=new float[3];
				 for(int j=0;j<n;j++)
				 {
					 X[0]+=PointGetter.get(j).getX();
					 X[1]+=PointGetter.get(j).getY();
					 X[2]+=PointGetter.get(j).getZ();
					 PointGetter.get(j).used=false;
				 }
				 for(int j=0;j<triarr.currentSize;j++) {
					 triarr.get(j).isvisited=false;
				 }
				 return new pi(X[0]/n,X[1]/n,X[2]/n);
			 }
		 }

		 return null;
	 }
	 
	 public void DFS(TI t,DynamicArray<pi>PointGetter) {
		 t.isvisited=true;
			 for(int j=0;j<t.adjtri.currentSize;j++) {
				 if(!t.adjtri.get(j).isvisited) {
					if(!t.adjtri.get(j).give(0).used)
					{
						t.adjtri.get(j).give(0).used=true;
						PointGetter.insert(t.adjtri.get(j).give(0));
					}
					if(!t.adjtri.get(j).give(1).used)
					{
						t.adjtri.get(j).give(1).used=true;
						PointGetter.insert(t.adjtri.get(j).give(1));
					}
					if(!t.adjtri.get(j).give(2).used)
					{
						t.adjtri.get(j).give(2).used=true;
						PointGetter.insert(t.adjtri.get(j).give(2));
					}
				 DFS(t.adjtri.get(j),PointGetter);
				 }
			 }
	 }
	 
	 public PointInterface [] CLOSEST_COMPONENTS() {
		 DynamicArray<DynamicArray<pi>> points=new DynamicArray<>();
		 for(int i=0;i<triarr.currentSize;i++) {
			 if(!triarr.get(i).isvisited) {
				 DynamicArray<pi>PointGetter=new DynamicArray<>();
				 PointGetter.insert(triarr.get(i).give(0));
				 PointGetter.insert(triarr.get(i).give(1));
				 PointGetter.insert(triarr.get(i).give(2));
				 DFS(triarr.get(i),PointGetter);
				 points.insert(PointGetter);
			 }
		 }
		 pi p1=null;
		 pi p2=null;
		 float f=1000;
		 int n=points.currentSize;
		 for(int i=0;i<n;i++) {
			 for(int j=0;j<n;j++) {
				 for(int l=0;l<points.get(i).currentSize;l++) {
					 for(int m=0;m<points.get(j).currentSize;m++) {
						 float flo=Length(points.get(i).get(l),points.get(j).get(m));
						 if(f>flo && l!=m) {
							 EI e=new EI(points.get(i).get(l),points.get(j).get(m));
							 boolean b=false;
							 for(int g=0;g<edgearr.currentSize;g++) {
								 if(e.compareTo(edgearr.get(g))==0) {
									 b=true;
									 break;
								 }
							 }
							 if(!b) {
								 f=flo;
								 p1=points.get(i).get(l);
								 p2=points.get(j).get(m);
							 }
						 }
					 }
				 }
			 }
		 }
		 for(int i=0;i<pointarray.currentSize;i++) {
			 pointarray.get(i).used=false;
		 }
		 for(int i=0;i<triarr.currentSize;i++) {
			 triarr.get(i).isvisited=false;
		 }
		 if(p1==null || p2==null) {
			 return null;
		 }
		 PointInterface[] po=new PointInterface[2];
		 po[0]=(PointInterface)p1;
		 po[1]=p2;
		 return po;
	 }
	 
	 public float Length(pi p1,pi p2) {
			return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y)+(p1.z-p2.z)*(p1.z-p2.z);
	 }

	 public int MAXIMUM_DIAMETER() {
		 int max=0;
		 for(int i=0;i<triarr.currentSize;i++) {
			 BFS(triarr.get(i));
			 for(int j=0;j<triarr.currentSize;j++) {
				 if(max<triarr.get(j).dist) {
					 max=triarr.get(j).dist;
				 }
				 triarr.get(j).dist=0;
				 triarr.get(j).isvisited=false;
			 }
		 }
		 return max;
	 }
	 
	 public void BFS(TI t) {
		 t.isvisited=true;
		 queue<TI> que=new queue<>();
		 que.insert(t);
		 while(!que.isEmpty()) {
			 TI temp = que.get();
			 for(int i = 0; i < temp.adjtri.currentSize; i++) {
				 if(!temp.adjtri.get(i).isvisited) {
					 temp.adjtri.get(i).dist=temp.dist+1;
					 //System.out.println(temp.dist+1);
					 temp.adjtri.get(i).isvisited = true;
					 que.insert(temp.adjtri.get(i));
				 }
			 }
		 }
	 }
	 
}

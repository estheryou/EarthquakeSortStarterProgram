
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData,int from){
     int max=from;
       for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getMagnitude() > quakeData.get(max).getMagnitude()) {
                max = i;
            }
        }
        return max;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
      for(int i=0;i<49;i++){
        int index=getLargestDepth(in,i);
        QuakeEntry original=in.get(i);
        QuakeEntry maxfornow=in.get(index);
        in.set(i,maxfornow);
        in.set(index,original);
        }
    }
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        //2sortByMagnitudeWithBubbleSort(list);
        //3sortByMagnitudeWithBubbleSortWithCheck(list);
       //4 sortByMagnitudeWithCheck(list);
       int quakeNumber=77;
        System.out.println("Print quake entry in position"+quakeNumber);
        System.out.println(list.get(quakeNumber));
        
    }
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
       for(int i=0;i<quakeData.size()-numSorted-1;i++){
         if(quakeData.get(i).getMagnitude()>quakeData.get(i+1).getMagnitude()){
            QuakeEntry one=quakeData.get(i);
            QuakeEntry two=quakeData.get(i+1);
            quakeData.set(i,two);
            quakeData.set(i+1,one);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
       for(int i=0;i<in.size()-1;i++){
          onePassBubbleSort(in,i);
        } 
    
    }
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
	
	public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
	   for(int i=0;i<quakes.size()-1;i++){
	     if(quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude()){
	       
	       return false;
	       }
	   
	   }
	   return true;
	   
	   }
	public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
	   for(int i=0;i<in.size()-1;i++){
          onePassBubbleSort(in,i);
          if(checkInSortedOrder(in)){
            System.out.println("the needed sort is:"+i+"+1");
            break;
           }
        } 
	   }
	public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
	   for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if(checkInSortedOrder(in)){
            System.out.println("the needed sort is:"+i+"+1");
            break;
           }
        }
	   
	   }
}

package com.phone.cn.utils.near;  
public class EarthDistance{   
    private static final  double EARTH_RADIUS = 6371000;//赤道半径(单位m)  
      
    /** 
     * 转化为弧度(rad) 
     * */  
    private static double rad(double d)  
    {  
       return d * Math.PI / 180.0;  
    }  
    /** 
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下 
     * @param lon1 第一点的精度 
     * @param lat1 第一点的纬度 
     * @param lon2 第二点的精度 
     * @param lat3 第二点的纬度 
     * @return 返回的距离，单位m 
     * */  
    public static double GetDistance(double lon1,double lat1,double lon2, double lat2)  
    {  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;  
       double b = rad(lon1) - rad(lon2);  
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
       s = s * EARTH_RADIUS;  
       s = Math.round(s * 10000) / 10000;  
       return s;  
    }  
       
   
    
    /* 
     * 二维数组排序，比较array[][2]的值，返回二维数组 
     * */  
    public static String[][] getOrder(String[][] array){  
        for (int j = 0; j < array.length ; j++) {  
            for (int bb = 0; bb < array.length - 1; bb++) {  
                String[] ss;  
                int a1=Integer.valueOf(array[bb][2]);  //转化成int型比较大小  
                int a2=Integer.valueOf(array[bb+1][2]);  
                if (a1>a2) {  
                    ss = array[bb];  
                    array[bb] = array[bb + 1];  
                    array[bb + 1] = ss;  
                      
                }  
            }  
        }   
        return array;  
    }  
      
    /*打印数组*/  
    public static void showArray(String[][] array){  
          for(int a=0;a<array.length;a++){  
            for(int j=0;j<array[0].length;j++)  
                System.out.print(array[a][j]+" ");  
            System.out.println();  
        }  
    }
    
    @SuppressWarnings("static-access")
	public static void main(String []args){  
    	//30.268234
          double lon1= 120.111066;    
          double lat1=30.268234;  
          double lon2= 120.111334;   
          double lat2=30.268271;  
          double dist;  
          String geocode;  
            
          dist=EarthDistance.GetDistance(lon1, lat1, lon2, lat2);   
          System.out.println("两点相距：" + dist + " 米");  
            
            
          GeoHashKit geohash = new GeoHashKit();  
          geocode=geohash.encode(lat1, lon1);  
          System.out.println("当前位置编码：" + geocode);  
           
          geocode=geohash.encode(lat2, lon2);  
          System.out.println("远方位置编码：" + geocode);  
  
       }
} 
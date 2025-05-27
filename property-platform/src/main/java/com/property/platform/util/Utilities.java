package com.property.platform.util;

public class Utilities {

    public class IdGenerator {
        private static int counter=1;
        public static synchronized int next(){return counter++;}
    }


    public class PriceParser {
        public static long parse(String s){
            s=s.toUpperCase();
            var num=Long.parseLong(s.replaceAll("[^0-9]",""));
            if(s.endsWith("K")) return num*1_000;
            if(s.endsWith("L")) return num*100_000;
            if(s.endsWith("CR"))return num*10_000_00;
            return num;
        }
    }

    public class SizeParser {
        public static double toSqFt(String s){
            s=s.toLowerCase();var val=Double.parseDouble(s.replaceAll("[^0-9.]",""));
            if(s.endsWith("sqm")) return val*10.7639;
            if(s.endsWith("sqyd"))return val*9;
            return val; // sqft
        }
    }


    public static class Range<T extends Comparable<T>>{
        private final T min, max;
        private Range(T min,T max){this.min=min;this.max=max;}
        public static Range<Long> parseLong(String s){
            var parts=s.split("-");
            if(parts.length==2) return new Range<>(Long.parseLong(parts[0]),Long.parseLong(parts[1]));
            var v=Long.parseLong(parts[0]); return new Range<>(v,null);
        }
        public static Range<Double> parseDouble(String s){
            var parts=s.split("-");
            if(parts.length==2) return new Range<>(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
            var v=Double.parseDouble(parts[0]); return new Range<>(v,null);
        }
        public boolean includes(T v){
            if(min!=null&&v.compareTo(min)<0) return false;
            if(max!=null&&v.compareTo(max)>0) return false;
            return true;
        }
    }

}

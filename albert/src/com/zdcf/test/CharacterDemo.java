package com.zdcf.test;

public class CharacterDemo {

    public static void main(String[] args) {

        // create 2 character primitives ch1, ch2
        char ch1, ch2;

        // assign values to ch1, ch2
        ch1 = 'j';
        ch2 = '4';

        // create 2 int primitives i1, i2
        int i1, i2;

        // assign numeric values of ch1, ch2 to i1, i2
        i1 = Character.getNumericValue(ch1);
        i2 = Character.getNumericValue(ch2);

        String str1 = "Numeric value of " + ch1 + " is " + i1;
        String str2 = "Numeric value of " + ch2 + " is " + i2;

        // print i1, i2 values
        System.out.println( str1 );
        System.out.println( str2 );
    }
}

//    Numeric value of j is 19
////        Numeric value of 4 is 4
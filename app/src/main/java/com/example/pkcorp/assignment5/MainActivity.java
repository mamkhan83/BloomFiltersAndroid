package com.example.pkcorp.assignment5;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
   private TextView tvSet, tvTestSet, tvOutput;
    private int set[] = new int[15];
    private int testSet[] = new int[15];

    double falsePositiveProbability = 0.1;
    int expectedSize = 100;

    int Low = 0;
    int High = 50;

    BloomFilter<String> bloomFilter = new BloomFilter<String>(
            falsePositiveProbability, expectedSize);
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSet = (TextView) findViewById(R.id.tv_set);
        tvTestSet = (TextView) findViewById(R.id.tv_test_set);
        tvOutput = (TextView) findViewById(R.id.tv_output);

        tvSet.append("[");

        //creating set for adding in adding in array
        for (int i = 0; i < set.length; i++) {
            // if(set.length == i)
            //  break;

            int no = random.nextInt(High-Low) + Low;
            set[i] = no;
            bloomFilter.add(no+"");
            tvSet.append(no + "");
            if(set.length-1 != i)
                tvSet.append(",");
        }
        tvSet.append("]");

        tvTestSet.append("[");
        //creating set for testing
        for (int i = 0; i < testSet.length; i++) {
            //if(testSet.length == i)
            //  break;
            int no = random.nextInt(High-Low) + Low;
            testSet[i] = no;
            tvTestSet.append(no+"");
            if(testSet.length-1 != i)
                tvTestSet.append(",");
        }
        tvTestSet.append("]");


        for (int i = 0; i < testSet.length; i++) {
            if(bloomFilter.contains(testSet[i]+""))
            {
               tvOutput.append("\n"+testSet[i]+", "+bloomFilter.expectedFalsePositiveProbability());
                Log.d("test", "\n"+testSet[i]+", "+bloomFilter.expectedFalsePositiveProbability());
            }
            else{
                Log.d("test", "MainActivity:onCreate: bloom filter does not contain "+testSet[i]);
            }

        }
    }
}

package com.jeikei.facelibrary;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class TimerInterpolator implements Runnable{
	
	private final static int NUM_OF_VALUE = 3;

	private long startTime;
	private long prev_timeGap;
	private long current_timeGap;
	
	private double[][] realValue = new double[2][NUM_OF_VALUE];
	private double[] estimatedValue = new double[NUM_OF_VALUE];
	private double[] estimatedDirection = new double[NUM_OF_VALUE];
	
	private int e_currentPointer;

	private final static int E_STATE_FRONT = 0;
	private final static int E_STATE_BACK  = 1;
	
	private boolean isThreadRun;
	
	private int stateOfCompute;
	public final static int STATE_STOP = -1;
	public final static int STATE_PREPARE = 0;
	public final static int STATE_READY = 1;
	
	public final static int IDX_FACE_LOCATION_X = 0;
	public final static int IDX_FACE_LOCATION_Y = 1;
	public final static int IDX_FACE_DISTANCE = 2;

	String TAG = "faceSDK:TimerInterpolator";
	
	public TimerInterpolator()
	{
		stateOfCompute = STATE_STOP;
		init();
	}
	
	public void close()
	{
		isThreadRun = false;
		stateOfCompute = STATE_STOP;
	}
	
	public void init()
	{
		if(stateOfCompute == STATE_STOP)
		{
			startTime = -1;
			stateOfCompute = STATE_PREPARE;
			
			e_currentPointer = E_STATE_FRONT;
			
			for(int i=0; i<NUM_OF_VALUE; i++)
			{
				realValue[0][i] = -1;
				realValue[1][i] = -1;
				estimatedValue[i] = -1;
				estimatedDirection[i] = -1;
			}
			
			
			(new Thread(this, "a")).start();
		}
		
	}
	public int getState()
	{
		return stateOfCompute;
	}
	
	public void putValues(double faceLocationX, double faceLocationY, double faceDistance)
	{
		realValue[e_currentPointer][0] = faceLocationX;
		realValue[e_currentPointer][1] = faceLocationY;
		realValue[e_currentPointer][2] = faceDistance;
		
		estimatedValue[0] = realValue[e_currentPointer][0];
		estimatedValue[1] = realValue[e_currentPointer][1];
		estimatedValue[2] = realValue[e_currentPointer][2];
		
		estimate_direction();
		
		checkTimeGap();
	}
	
	public double getTimeGap()
	{
		return prev_timeGap;
	}
	
	public double getEstimatedValue(int idx)
	{
		return estimatedValue[idx];
	}
	
	private void estimate_direction()
	{
		//Log.i(TAG, "realValue0 : " + realValue[0][0] + ", realValue1 : " + realValue[1][0]);
		
		if(realValue[E_STATE_FRONT][2] == -1 || realValue[E_STATE_BACK][2] == -1)
			return;
		
		for(int i=0; i<NUM_OF_VALUE; i++)
		{
			estimatedDirection[i] = (realValue[e_currentPointer][i] - realValue[1-e_currentPointer][i]);
			//Log.i(TAG, "estimation"+i+" : " + estimatedDirection[i] );
		}
	}
	
	private void checkTimeGap()
	{
		if(startTime == -1)
		{
			startTime = System.currentTimeMillis();
			
			stateOfCompute = STATE_PREPARE;
			//Log.i(TAG, "startTime = " + startTime);
		}
		else
		{
			prev_timeGap = (System.currentTimeMillis() - startTime);
			//Log.i(TAG, "end-start = " + (long)(System.currentTimeMillis() - startTime) );
			startTime = System.currentTimeMillis();
			
			stateOfCompute = STATE_READY; 
			
			e_currentPointer = 1-e_currentPointer;
		}
		
		if(current_timeGap > 3000)
		{
			startTime = System.currentTimeMillis();
		}
	}
	
	private double computeTimeRatio()
	{
		if(startTime == -1) return -1;
		
		current_timeGap = System.currentTimeMillis() - startTime;
			
		double tmpTime = Math.log(prev_timeGap - current_timeGap) / Math.log(prev_timeGap);
		if(tmpTime < 0 || Double.isNaN(tmpTime)) tmpTime = 0;
		
		//Log.i(TAG, "prevTimeGap : " + prev_timeGap + ", currentTimeGap : "+ current_timeGap + ", tmpTime : " + tmpTime);
		
		return tmpTime;
	}

	private void computeInterpolatedValues()
	{
		double tr = computeTimeRatio();
		double slicedRatio = (((double)current_timeGap / (double)prev_timeGap) * tr);
		
		for(int i=0; i<NUM_OF_VALUE; i++)
		{
			estimatedValue[i] += ((slicedRatio) * realValue[e_currentPointer][i]);
			
			if(Math.abs(estimatedValue[i]) > Math.abs(realValue[e_currentPointer][i])*2)
				estimatedValue[i] = realValue[e_currentPointer][i] * 2;
			
			Log.i(TAG, "estimatedValue["+i+"] : " + estimatedValue[i]);
		}
		//Log.i(TAG, "slicedRatio(NO variable) : " + ((double)current_timeGap / (double)prev_timeGap));
		//Log.i(TAG, "current_timeGap : " + current_timeGap + ", prev_timeGap : " + prev_timeGap + ", tr : " + tr);
		//Log.i(TAG, "slicedRatio : " + slicedRatio);
	}
	
	public void run() {
		isThreadRun = true;
		while(isThreadRun)
		{
			//Log.i( TAG, "state : " + stateOfCompute );
			
			if(stateOfCompute == STATE_READY)	
			{
				computeTimeRatio();
				computeInterpolatedValues();
			}
				
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}

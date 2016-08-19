package com.dcl.myclock;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends Activity {
	 /* @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	       setContentView(new MyClock(this)); 
	  }*/
	  private MyClock drawClock;  
	    protected static final int MESSAGE = 123;  
	    public Handler handler;  
	    private Thread mthread;  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        drawClock = new MyClock(this);  
	        handler = new Handler() {  
	            public void handleMessage(Message mess) {  
	                if (mess.what == MESSAGE) {  
	                    setContentView(drawClock);
	                }  
	                super.handleMessage(mess);  
	            }  
	        };  
	        mthread = new myThread();  
	        mthread.start();  
	    }  
	  
	    class myThread extends Thread {  
	        public void run() {  
	            super.run();  
	            while (Thread.interrupted() == false) {  
	                try {  
	                    Thread.sleep(1000);  
	                } catch (InterruptedException e) {  
	                    e.printStackTrace();  
	                }  
	                Message m = new Message();  
	                m.what = MESSAGE;  
	                handler.sendMessage(m);  
	            }  
	        }  
	    }  
}

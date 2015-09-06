package com.mymemory.main.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import com.mymemory.interfaces.ResourceBundle;

public class MMResourceBundle implements ResourceBundle{
	
	private Properties lookup;
	private volatile boolean destroyYourself = false;
	private volatile Integer changecount = 0;
	private Object monitor = new Object();
	private String path;
	
	private Thread worker = new Thread(new Runnable() {
		@Override
		public void run() {
			while(true){
				synchronized(monitor){
					try {
						monitor.wait();
					} catch (InterruptedException e) {
						System.out.println("Supervisior is Inturrupted");
					}
					if(changecount>5){
						try {
							lookup.store(new FileOutputStream(path), "Updating a file");
							changecount=0;
							System.out.println("Writting successfull");
						} catch (FileNotFoundException e) {
							System.out.println("Updater thread Failed To update "+e);
						} catch (IOException e) {
							System.out.println("Updater thread Failed To update "+e);
						}
					}
					if(destroyYourself){
						System.out.println("Good Bye! Everyone i am going to die "+Thread.currentThread().getName());
						break;
					}
				}
			}
		}
	},"worker");
	
	private Thread background = new Thread(new Runnable() {
		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					System.out.println("Manager is Awaken from the Sleep");
				}
				Thread[] tt = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
				Thread.currentThread().getThreadGroup().enumerate(tt);
				boolean ff = true;
				for(Thread t:tt){
					if(t!=null && t.getName().equals("MMResourceBundle") && t.isAlive()){
						ff = false;
					}
				}
				if(ff){
					synchronized(monitor){
						if(changecount>0){
							changecount=10;
						}
						destroyYourself = true;
						monitor.notify();
					}
					break;
				}
			}
		}
	},"background");
	
	@SuppressWarnings({"unchecked", "rawtypes"})
    public MMResourceBundle(String path, boolean stream) throws IOException {
       this.path = path;
       if(stream)
    	   loadStream(new FileInputStream(path));
       else
    	   loadReader(new FileReader(path));
       System.out.println(Thread.currentThread().getName());
       Thread.currentThread().setName("MMResourceBundle");
       worker.start();
       background.start();
    }
	
	protected void finalize() throws Throwable {
		System.out.println("Finalizing everything");
	}
	
	@Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void loadStream(InputStream stream) throws IOException {
        lookup = new Properties();
        lookup.load(stream);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void loadReader(Reader reader) throws IOException {
    	lookup = new Properties();
    	lookup.load(reader);
    }

    private Object handleGetObject(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return lookup.get(key);
    }
    
    @Override
    public String getString(String key){
    	return (String) handleGetObject(key);
    }
    
    @Override
    public void setKeyValue(String key, String value) throws FileNotFoundException, IOException{
    	lookup.put(key, value);
    	synchronized (monitor) {
    		changecount++;
    		monitor.notify();
		}
    }
}

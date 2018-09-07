package jImage;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Class to act as a pipe between Image.concurrentSave() and Saver.
 * Protected because all functionality is abstracted down to concurrentSave().
 * 
 * @author cy
 *
 */
class SavePipe {
  private Queue<Image> images = new ArrayBlockingQueue<Image>(100);
  private Queue<String> names = new ArrayBlockingQueue<String>(100);
  private Saver s = new Saver(images, names, Thread.currentThread());
  private Thread th = null;
  private static SavePipe pipe = null;
  
  /**
   * Starts the Thread.
   */
  protected SavePipe() {
    th = new Thread(s);
    th.start();
  }
  
  /**
   * Returns the singleton instance of SavePipe
   * @return Instance of SavePipe
   */
  public synchronized static SavePipe getPipe() {
    synchronized(pipe) {
      if (pipe == null) {
        pipe = new SavePipe();
      }
      return pipe;
    }
  }
  
  /**
   * Adds the Image and file name to the queues to be processed by the Saver thread.
   * @param im Image to be added
   * @param fn File name for the image
   */
  public synchronized void save(Image im, String fn) {
    synchronized(pipe) {
      images.add(im);
      names.add(fn);
    }
  }
  
  /**
   * Runnable class that actually does the saving.
   * 
   * @author cy
   *
   */
  private class Saver implements Runnable{
    private Queue<Image> images;
    private Queue<String> names;
    private Thread mainThread;
    public Saver(Queue<Image> ims, Queue<String> fns, Thread main) {
      images = ims;
      names = fns;
      mainThread = main;
    }
    
    public void run() {
      while(true) {
        if(Thread.interrupted()) {
          break;
        }
        if(!mainThread.isAlive() && images.size() == 0 && names.size() == 0) {
          break;
        }
        if(images.size() == names.size() && images.size()>0 && names.size()>0) {
          Image i = images.remove();
          String fn = names.remove();
          i.save(fn);
        }
      }
    }
  }
}
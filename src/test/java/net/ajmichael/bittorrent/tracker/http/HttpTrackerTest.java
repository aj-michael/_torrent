package net.ajmichael.bittorrent.tracker.http;

import java.util.concurrent.ExecutionException;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.ajmichael.bittorrent.tracker.http.HttpTracker;

@RunWith(JUnit4.class)
public class HttpTrackerTest {
  
  public void testDeleteThis() throws InterruptedException, ExecutionException {
    HttpTracker tracker = new HttpTracker();
    tracker.announce().get();
  }
}

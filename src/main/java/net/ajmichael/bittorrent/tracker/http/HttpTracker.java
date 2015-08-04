package net.ajmichael.bittorrent.tracker.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.ajmichael.bittorrent.tracker.Tracker;
import net.ajmichael.bittorrent.tracker.TrackerAnnounceResponse;
import net.ajmichael.bittorrent.tracker.TrackerConnectResponse;
import net.ajmichael.bittorrent.tracker.TrackerScrapeResponse;
import net.ajmichael.common.lang.NotImplementedException;

public class HttpTracker implements Tracker {
  private final ExecutorService executor;
  
  public HttpTracker() {
    this(Executors.newFixedThreadPool(1));
  }
  
  public HttpTracker(ExecutorService executor) {
    this.executor = executor;
  }

  public Future<TrackerConnectResponse> connect() {
    throw new NotImplementedException();
  }

  @Override
  public Future<TrackerAnnounceResponse> announce() {
    HttpTrackerRequest req = null;
    return this.executor.submit(req);
  }

  @Override
  public Future<TrackerScrapeResponse> scrape() {
    throw new NotImplementedException();
  }
}

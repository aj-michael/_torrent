package net.ajmichael._torrent.bittorrent.tracker.http;

import java.util.concurrent.Future;

import net.ajmichael._torrent.bittorrent.tracker.Tracker;
import net.ajmichael._torrent.bittorrent.tracker.TrackerAnnounceResponse;
import net.ajmichael._torrent.bittorrent.tracker.TrackerConnectResponse;
import net.ajmichael._torrent.bittorrent.tracker.TrackerScrapeResponse;
import net.ajmichael.common.lang.NotImplementedException;

public class HttpTracker implements Tracker {

  @Override
  public Future<TrackerConnectResponse> connect() {
    throw new NotImplementedException();
  }

  @Override
  public Future<TrackerAnnounceResponse> announce() {
    throw new NotImplementedException();
  }

  @Override
  public Future<TrackerScrapeResponse> scrape() {
    throw new NotImplementedException();
  }
}

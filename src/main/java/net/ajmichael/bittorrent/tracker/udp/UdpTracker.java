package net.ajmichael.bittorrent.tracker.udp;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ajmichael.bittorrent.metadata.Metadata;
import net.ajmichael.bittorrent.tracker.Tracker;
import net.ajmichael.bittorrent.tracker.TrackerAnnounceResponse;
import net.ajmichael.bittorrent.tracker.TrackerConnectResponse;
import net.ajmichael.bittorrent.tracker.TrackerScrapeResponse;

/** TODO(aj-michael) Document this. */
public class UdpTracker implements Tracker {
  private static final Logger logger = LoggerFactory.getLogger(UdpTracker.class);

  private final Metadata metadata;

  public UdpTracker(Metadata metadata) {
    logger.info("Constructed UdpTracker with metadata {}.", metadata);
    this.metadata = metadata;
  }

  public Future<TrackerConnectResponse> connect() {
    return null;
  }

  @Override
  public Future<TrackerAnnounceResponse> announce() {
    return null;
  }

  @Override
  public Future<TrackerScrapeResponse> scrape() {
    return null;
  }
}

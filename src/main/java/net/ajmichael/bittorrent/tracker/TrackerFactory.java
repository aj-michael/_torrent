package net.ajmichael.bittorrent.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ajmichael.bittorrent.metainfo.Metainfo;
import net.ajmichael.bittorrent.tracker.udp.UdpTracker;

public class TrackerFactory {
  private static final Logger logger = LoggerFactory.getLogger(TrackerFactory.class);

  private final Metainfo metadata;

  public TrackerFactory(Metainfo metadata) {
    logger.info("Constructed TrackerFactory with metadata {}.", metadata);
    this.metadata = metadata;
  }

  public Tracker create() {
    return new UdpTracker(this.metadata);
  }
}

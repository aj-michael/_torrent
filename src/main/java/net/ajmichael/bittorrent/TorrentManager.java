package net.ajmichael.bittorrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ajmichael.bittorrent.metainfo.Metainfo;
import net.ajmichael.bittorrent.tracker.Tracker;
import net.ajmichael.bittorrent.tracker.TrackerFactory;

/**
 * Primary driver for a specific torrent. Drives all phases of tracker communication, downloading
 * and uploading.
 */
public final class TorrentManager implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(TorrentManager.class);
  
  private final Metainfo metadata;
  private final Tracker tracker;

  public TorrentManager(Metainfo metadata) {
    logger.info("Constructed TorrentManager with metadata {}.", metadata);
    this.metadata = metadata;
    this.tracker = new TrackerFactory(metadata).create();
  }

  @Override
  public void run() {
    
  }

}

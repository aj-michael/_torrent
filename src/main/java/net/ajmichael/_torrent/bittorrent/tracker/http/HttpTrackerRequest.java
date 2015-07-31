package net.ajmichael._torrent.bittorrent.tracker.http;

import java.net.InetAddress;
import java.util.Optional;

/**
 * A container for HTTP tracker request parameters.
 * 
 * @see https://wiki.theory.org/BitTorrentSpecification#Tracker_Request_Parameters
 */
public final class HttpTrackerRequest {
  public static enum Event {
    STARTED("started"), STOPPED("stopped"), COMPLETED("completed");

    private final String value;

    private Event(final String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  final byte[] infoHash;
  final byte[] peerId;
  final int port;
  final int uploadedBytes;
  final int downloadedBytes;
  final int leftBytes;
  final boolean compact;
  final boolean noPeerId;
  final Event event;
  final Optional<InetAddress> ip;
  final Optional<Integer> numWant;
  final Optional<String> key;
  final Optional<String> trackerId;

  public HttpTrackerRequest(byte[] infoHash,
                            byte[] peerId,
                            int port,
                            int uploadedBytes,
                            int downloadedBytes,
                            int leftBytes,
                            boolean compact,
                            boolean noPeerId,
                            Event event,
                            Optional<InetAddress> ip,
                            Optional<Integer> numWant,
                            Optional<String> key,
                            Optional<String> trackerId) {
    this.infoHash = infoHash;
    this.peerId = peerId;
    this.port = port;
    this.uploadedBytes = uploadedBytes;
    this.downloadedBytes = downloadedBytes;
    this.leftBytes = leftBytes;
    this.compact = compact;
    this.noPeerId = noPeerId;
    this.event = event;
    this.ip = ip;
    this.numWant = numWant;
    this.key = key;
    this.trackerId = trackerId;
  }
}

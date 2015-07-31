package net.ajmichael._torrent.bittorrent.tracker;

import java.net.InetSocketAddress;
import java.util.List;

public class TrackerAnnounceResponse {
  static final int ACTION = 1;

  final int transaction_id;
  final int interval;
  final int leechers;
  final int seeders;
  final List<InetSocketAddress> peers;

  TrackerAnnounceResponse(
      int transaction_id, int interval, int leechers, int seeders, List<InetSocketAddress> peers) {
    this.transaction_id = transaction_id;
    this.interval = interval;
    this.leechers = leechers;
    this.seeders = seeders;
    this.peers = peers;
  }
}

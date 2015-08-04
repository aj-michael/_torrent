package net.ajmichael.bittorrent;

import java.net.InetAddress;

/** Information on a peer that can be used to communicate with it. */
final class PeerInfo {
  final InetAddress ipAddress;
  final int port;

  PeerInfo(InetAddress ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
  }
}

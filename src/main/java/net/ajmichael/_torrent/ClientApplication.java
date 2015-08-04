package net.ajmichael._torrent.client;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.ajmichael._torrent.bittorrent.TorrentManager;
import net.ajmichael._torrent.bittorrent.metadata.Metadata;

/**
 * A simple bittorrent application.
 */
public class ClientApplication implements Runnable {
  List<TorrentManager> torrentManagers;

  public ClientApplication() {
    torrentManagers = new ArrayList<>();
  }

  @Override
  public void run() {
    Metadata metadata;
    //metadata = new MagnetUri("abc").parse();
    metadata = null;
    torrentManagers.add(new TorrentManager(metadata));
  }

  public static void main(String[] args) {
    System.setProperty("org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY", "trace");
    Thread clientExecutionThread = new Thread(new ClientApplication());
    clientExecutionThread.start();
  }
}

package net.ajmichael.bittorrent.tracker;

import java.util.List;

/** TODO(aj-michael) Document this. */
public class TrackerScrapeResponse {
  public static final int ACTION = 2;

  public final int transactionId;
  public final List<TrackerStats> stats;

  TrackerScrapeResponse(int transactionId, List<TrackerStats> stats) {
    this.transactionId = transactionId;
    this.stats = stats;
  }

  /** TODO(aj-michael) Document this. */
  public static class TrackerStats {
    public final int seeders;
    public final int completed;
    public final int leechers;

    TrackerStats(int seeders, int completed, int leechers) {
      this.seeders = seeders;
      this.completed = completed;
      this.leechers = leechers;
    }
  }
}

package net.ajmichael.bittorrent.metainfo;

import java.util.List;
import java.util.Optional;

public final class FileDictionary {
  public final int length;
  public final Optional<String> md5sum;
  public final List<String> path;

  FileDictionary(int length, Optional<String> md5sum, List<String> path) {
    this.length = length;
    this.md5sum = md5sum;
    this.path = path;
  }
}

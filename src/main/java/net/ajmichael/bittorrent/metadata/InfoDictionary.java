package net.ajmichael.bittorrent.metadata;

import java.util.List;
import java.util.Optional;

public class InfoDictionary {
  public final String name;
  public final int pieceLength;
  public final String pieces;
  public final Optional<Integer> privateBit;
  public final List<FileDictionary> files;

  InfoDictionary(
      int pieceLength,
      String pieces,
      Optional<Integer> privateBit,
      String name,
      List<FileDictionary> files) {
    this.files = files;
    this.name = name;
    this.pieceLength = pieceLength;
    this.pieces = pieces;
    this.privateBit = privateBit;
  }
}
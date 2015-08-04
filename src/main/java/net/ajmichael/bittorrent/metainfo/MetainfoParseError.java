package net.ajmichael.bittorrent.metainfo;

public class MetainfoParseError extends Exception {
  public MetainfoParseError(String message) {
    super(message);
  }

  public static MetainfoParseError missingField(String field) {
    String message = "Missing required field: " + field + ".";
    return new MetainfoParseError(message);
  }
}

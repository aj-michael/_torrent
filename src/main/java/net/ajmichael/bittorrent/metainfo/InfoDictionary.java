package net.ajmichael.bittorrent.metainfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InfoDictionary {
  private static final String NAME_FIELD = "name";
  private static final String PIECE_LENGTH_FIELD = "piece length";
  private static final String PIECES_FIELD = "pieces";
  private static final String PRIVATE_FIELD = "private";
  private static final String FILES_FIELD = "files";
  private static final String LENGTH_FIELD = "length";
  private static final String MD5_SUM_FIELD = "md5sum";
  private static final String PATH_FIELD = "path";

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

  // TODO(aj-michael) I'm not parsing most of the fields yet. This is wrong. Fix it.
  public static InfoDictionary fromRaw(Map<String, Object> rawDictionary)
      throws MetainfoParseError {
    Optional<String> name = parseStringField(NAME_FIELD, rawDictionary);
    Optional<Integer> pieceLength = parseIntegerField(PIECE_LENGTH_FIELD, rawDictionary);
    Optional<String> pieces = parseStringField(PIECES_FIELD, rawDictionary);
    Optional<Integer> privateBit = parseIntegerField(PRIVATE_FIELD, rawDictionary);
    
    if (!pieceLength.isPresent()) {
      throw MetainfoParseError.missingField(PIECE_LENGTH_FIELD);
    } else if (!pieces.isPresent()) {
      throw MetainfoParseError.missingField(PIECES_FIELD);
    } else if (!name.isPresent()) {
      throw MetainfoParseError.missingField(NAME_FIELD);
    }
    
    return new InfoDictionary(pieceLength.get(),
                              pieces.get(),
                              privateBit,
                              name.get(),
                              new ArrayList<>());
  }
  
  private static Optional<String> parseStringField(String field, Map<String, Object> dictionary) {
    Object rawValue = dictionary.get(field);
    if (rawValue != null && rawValue instanceof String) {
      return Optional.of((String) rawValue);
    }
    return Optional.empty();
  }
  
  private static Optional<Integer> parseIntegerField(String field, Map<String, Object> dictionary) {
    Object rawValue = dictionary.get(field);
    if (rawValue != null && rawValue instanceof Integer) {
      return Optional.of((Integer) rawValue);
    }
    return Optional.empty();
  }
}
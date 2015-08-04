package net.ajmichael.bittorrent.metainfo;

import static net.ajmichael.bittorrent.bencode.Decode.decodeDictionary;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/** A container and parser class for the common .torrent protocol files. */
public class TorrentMetainfoFile {
  private static final Charset DEFAULT_CHARSET = Charsets.ISO_8859_1;
  
  // Required fields.
  private static final String INFO_FIELD = "info";
  private static final String ANNOUNCE_FIELD = "announce";
  
  // Optional fields.
  private static final String ANNOUNCE_LIST_FIELD = "announce-list";
  private static final String CREATION_DATE_FIELD = "creation date";
  private static final String COMMENT_FIELD = "comment";
  private static final String CREATED_BY_FIELD = "created by";
  private static final String ENCODING_FIELD = "encoding";

  private final File metadataFile;
  private final Charset charset;

  public TorrentMetainfoFile(File metadataFile) {
    this(metadataFile, DEFAULT_CHARSET);
  }

  public TorrentMetainfoFile(File metadataFile, Charset charset) {
    this.metadataFile = metadataFile;
    this.charset = charset;
  }

  /**
   * TODO(aj-michael) Document this.
   * 
   * @see https://wiki.theory.org/BitTorrentSpecification#Metainfo_File_Structure
   *
   * @throws IOException
   * @throws MetainfoParseError 
   */
  Metainfo parse() throws IOException, MetainfoParseError {
    String fullEncodedMetadata = Files.toString(this.metadataFile, this.charset);

    // The entire file should be one dictionary. I may want to verify that there is nothing left
    // over here. Oh well. Maybe future me will come up with a clean idea for that.
    Map<String, Object> fullFileDictionary = decodeDictionary(fullEncodedMetadata).decodedValue;

    System.out.println(fullFileDictionary.keySet());
    
    Optional<InfoDictionary> infoDictionary = parseInfoDictionary(fullFileDictionary);
    Optional<String> announce = parseStringField(ANNOUNCE_FIELD, fullFileDictionary);
    
    if (!infoDictionary.isPresent()) {
      throw MetainfoParseError.missingField(INFO_FIELD);
    } else if (!announce.isPresent()) {
      throw MetainfoParseError.missingField(ANNOUNCE_FIELD);
    }
    
    Optional<List<String>> announceList = parseAnnounceList(fullFileDictionary);
    Optional<Integer> creationDate = parseCreationDate(fullFileDictionary);
    Optional<String> comment = parseStringField(COMMENT_FIELD, fullFileDictionary);
    Optional<String> createdBy = parseStringField(CREATED_BY_FIELD, fullFileDictionary);
    Optional<String> encoding = parseStringField(ENCODING_FIELD, fullFileDictionary);

    return new Metainfo(announce.get(),
                        announceList.isPresent() ? announceList.get() : new ArrayList<String>(),
                        infoDictionary.get(),
                        creationDate,
                        comment,
                        createdBy,
                        encoding);
  }
  
  /** @see http://bittorrent.org/beps/bep_0012.html */
  private static Optional<List<String>> parseAnnounceList(Map<String, Object> fullFileDictionary) {
    Object rawAnnounceList = fullFileDictionary.get(ANNOUNCE_LIST_FIELD);
    if (rawAnnounceList != null && rawAnnounceList instanceof List<?>) {
      List<String> typedList = new ArrayList<>();
      for (Object tier : (List<?>) rawAnnounceList) {
        if (tier != null && tier instanceof List<?>) {
          Collections.shuffle((List<?>) tier);
          for (Object entry : (List<?>) tier) {
            if (entry != null && entry instanceof String) {
              typedList.add((String) entry);
            }
          }
        }
      }
      return Optional.of(typedList);
    }
    return Optional.empty();
  }

  private static Optional<String> parseStringField(
      String field, Map<String, Object> fullFileDictionary) {
    Object rawValue = fullFileDictionary.get(field);
    if (rawValue != null && rawValue instanceof String) {
      return Optional.of((String) rawValue);
    }
    return Optional.empty();
  }

  private static Optional<InfoDictionary> parseInfoDictionary(
      Map<String, Object> fullFileDictionary) throws MetainfoParseError {
    Object rawInfoDictionary = fullFileDictionary.get(INFO_FIELD);
    if (rawInfoDictionary != null && rawInfoDictionary instanceof Map) {
      // This is potentially unsafe. Unfortunately, Java sucks and erases generics at compile time,
      // so it's the best I can do in O(1) time. So it goes.
      @SuppressWarnings("unchecked")
      Map<String, Object> typedInfoDictionary = (Map<String, Object>) rawInfoDictionary;
      return Optional.of(InfoDictionary.fromRaw(typedInfoDictionary));
    }
    return Optional.empty();
  }
  
  private static Optional<Integer> parseCreationDate(Map<String, Object> fullFileDictionary) {
    Object rawCreationDate = fullFileDictionary.get(CREATION_DATE_FIELD);
    if (rawCreationDate != null && rawCreationDate instanceof Integer) {
      return Optional.of((Integer) rawCreationDate);
    }
    return Optional.empty();
  }
}

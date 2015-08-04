package net.ajmichael.bittorrent.bencode;

import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Implementation of Bencode decode operations.
 * 
 * @see <a href="https://wiki.theory.org/BitTorrentSpecification#Bencoding">Bencoding</a>.
 */
public final class Decode {
  private static final char DICT_CHAR = 'd';
  private static final char END_CHAR = 'e';
  private static final char INT_CHAR = 'i';
  private static final char LIST_CHAR = 'l';

  public static Intermediary<? extends Object> decodeObject(String encoded) {
    if (Character.isDigit(encoded.charAt(0))) {
      return decodeString(encoded);
    } else if (encoded.charAt(0) == INT_CHAR) {
      return decodeInteger(encoded);
    } else if (encoded.charAt(0) == LIST_CHAR) {
      return decodeList(encoded);
    } else if (encoded.charAt(0) == DICT_CHAR) {
      return decodeDictionary(encoded);
    } else {
      throw new InvalidEncodingException(encoded);
    }
  }

  static Intermediary<Map<String, Object>> decodeDictionary(String encoded) {
    Preconditions.checkArgument(encoded.charAt(0) == DICT_CHAR);
    encoded = encoded.substring(1);
    Map<String, Object> dictionary = Maps.newHashMap();
    while (encoded.charAt(0) != END_CHAR) {
      Intermediary<String> intermediaryKey = decodeString(encoded);
      Intermediary<? extends Object> intermediaryValue =
          decodeObject(intermediaryKey.encodedRemainder);
      dictionary.put(intermediaryKey.decodedValue, intermediaryValue.decodedValue);
      encoded = intermediaryValue.encodedRemainder;
    }
    return Intermediary.of(dictionary, encoded.substring(1));
  }
  
  static Intermediary<String> decodeString(String encoded) {
    Preconditions.checkArgument(Character.isDigit(encoded.charAt(0)));
    int splitIndex = encoded.indexOf(':');
    int length = Integer.parseInt(encoded.substring(0, splitIndex));
    String valueAndRemainder = encoded.substring(splitIndex + 1);
    return Intermediary.of(
        valueAndRemainder.substring(0, length),
        valueAndRemainder.substring(length));
  }

  static Intermediary<Integer> decodeInteger(String encoded) {
    Preconditions.checkArgument(encoded.charAt(0) == INT_CHAR);
    int splitIndex = encoded.indexOf(END_CHAR);
    return Intermediary.of(
        Integer.parseInt(encoded.substring(1, splitIndex)),
        encoded.substring(splitIndex + 1));
  }

  static Intermediary<List<Object>> decodeList(String encoded) {
    Preconditions.checkArgument(encoded.charAt(0) == LIST_CHAR);
    encoded = encoded.substring(1);
    List<Object> list = Lists.newLinkedList();
    while (encoded.charAt(0) != END_CHAR) {
      Intermediary<? extends Object> intermediary = decodeObject(encoded);
      list.add(intermediary.decodedValue);
      encoded = intermediary.encodedRemainder;
    }
    return Intermediary.of(list, encoded.substring(1));
  }

  public static class InvalidEncodingException extends RuntimeException {
    private static final long serialVersionUID = -2724160896591512622L;

    public InvalidEncodingException(String invalidString) {
      super(invalidString);
    }
  };

  /**
   * The result of reading one object from a bencoded string of arbitrary length.
   * 
   * By definition, bencoded strings must be parsed linearly. One cannot determine which or how
   * many bencoded objects are contained in a string until all of them are parsed. Thus, this is the
   * intermediary result of decoding exactly one object. If the encodedRemainder is the empty
   * string, then no more objects can be read.
   */
  public static final class Intermediary<T> {
    public final T decodedValue;
    public final String encodedRemainder;
  
    private Intermediary(T decodedValue, String encodedRemainder) {
      this.decodedValue = decodedValue;
      this.encodedRemainder = encodedRemainder;
    }
  
    public static <T> Intermediary<T> of(T decodedValue, String encodedRemainder) {
      Preconditions.checkNotNull(decodedValue);
      Preconditions.checkNotNull(encodedRemainder);
      return new Intermediary<T>(decodedValue, encodedRemainder);
    }
  }
}


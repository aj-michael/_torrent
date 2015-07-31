package net.ajmichael._torrent.bittorrent.bencode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import net.ajmichael._torrent.bittorrent.bencode.Decode;
import net.ajmichael._torrent.bittorrent.bencode.Decode.Intermediary;
import net.ajmichael._torrent.bittorrent.bencode.Decode.InvalidEncodingException;

public class DecodeTest {

  @Test
  public void testSuccessfulReadString() {
    String encodedData = "4:spamd3:cow3:mooe";
    Intermediary<String> intermediary = Decode.decodeString(encodedData);
    assertEquals("spam", intermediary.decodedValue);
    assertEquals("d3:cow3:mooe", intermediary.encodedRemainder);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReadString() {
    String encodedData = "i3549e4:spamd3:cow3:mooe";
    Decode.decodeString(encodedData);
  }

  @Test
  public void testSuccessfulReadInteger() {
    String encodedData = "i3549e4:spamd3:cow3:mooe";
    Intermediary<Integer> intermediary = Decode.decodeInteger(encodedData);
    assertEquals(new Integer(3549), intermediary.decodedValue);
    assertEquals("4:spamd3:cow3:mooe", intermediary.encodedRemainder);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReadInteger() {
    String encodedData = "4:spamd3:cow3:mooe";
    Decode.decodeInteger(encodedData);
  }

  @Test
  public void testSuccessfulReadList() {
    String encodedData = "l4:spam4:eggsei3546e";
    Intermediary<List<Object>> intermediary = Decode.decodeList(encodedData);
    assertEquals("i3546e", intermediary.encodedRemainder);
    List<Object> list = intermediary.decodedValue;
    assertEquals(2, list.size());
    assertTrue(list.get(0) instanceof String);
    assertEquals("spam", list.get(0));
    assertTrue(list.get(1) instanceof String);
    assertEquals("eggs", list.get(1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReadList() {
    String encodedData = "4:spamd3:cow3:mooe";
    Decode.decodeList(encodedData);
  }

  @Test
  public void testSuccessfulReadDictionary() {
    String encodedData = "d3:cow3:moo4:spami45eei123e";
    Intermediary<Map<String, Object>> intermediary = Decode.decodeDictionary(encodedData);
    assertEquals("i123e", intermediary.encodedRemainder);
    Map<String, Object> dictionary = intermediary.decodedValue;
    assertEquals(2, dictionary.size());
    Set<String> expectedKeys = ImmutableSet.of("cow", "spam");
    assertEquals(expectedKeys, dictionary.keySet());
    assertEquals("moo", dictionary.get("cow"));
    assertEquals(45, dictionary.get("spam"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReadDictionary() {
    String encodedData = "l4:spam4:eggsei3546e";
    Decode.decodeDictionary(encodedData);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testSuccessfulReadObject() {
    String encodedData = "l4:spam4:eggsei3546e";
    Intermediary<? extends Object> intermediary = Decode.decodeObject(encodedData);
    assertEquals("i3546e", intermediary.encodedRemainder);
    List<Object> list = (List<Object>) intermediary.decodedValue;
    assertEquals(2, list.size());
    assertTrue(list.get(0) instanceof String);
    assertEquals("spam", list.get(0));
    assertTrue(list.get(1) instanceof String);
    assertEquals("eggs", list.get(1));
  }

  @Test(expected = InvalidEncodingException.class)
  public void testInvalidReadObject() {
    String encodedData = "adam";
    Decode.decodeObject(encodedData);
  }
}

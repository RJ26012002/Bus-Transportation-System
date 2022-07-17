package com.example.bst.documents;
import com.example.bst.MapLocation;

public class Bus {
  private String id;
  private String from;
  private MapLocation mapLocation;

  public Bus(String id, String from, MapLocation mapLocation) {
    this.id = id;
    this.from = from;
    this.mapLocation = mapLocation;
  }

  public Bus() {
  }

  public MapLocation getMapLocation() {
    return mapLocation;
  }

  public void setMapLocation(MapLocation mapLocation) {
    this.mapLocation = mapLocation;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  @Override
  public String toString() {
    return "Bus{" + "from='" + from + "'" + '}';
  }
}


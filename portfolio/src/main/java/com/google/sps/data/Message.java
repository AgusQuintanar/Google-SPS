package com.google.sps.data;

/** An item on a message list. */
public final class Message {

  private final long id;
  private final String  name, 
                        email,
                        message;


  public Message(long id, String name, String email, String message) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.message = message;
  }
}